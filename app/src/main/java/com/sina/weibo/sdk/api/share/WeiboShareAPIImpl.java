/*     */ package com.sina.weibo.sdk.api.share;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.Dialog;
/*     */ import android.content.ActivityNotFoundException;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Bundle;
/*     */ import android.text.TextUtils;
/*     */ import com.sina.weibo.sdk.exception.WeiboShareException;
/*     */ import com.sina.weibo.sdk.utils.LogUtil;
/*     */ import com.sina.weibo.sdk.utils.MD5;
/*     */ import com.sina.weibo.sdk.utils.Utility;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class WeiboShareAPIImpl
/*     */   implements IWeiboShareAPI
/*     */ {
/*     */   private static final String TAG = "WeiboApiImpl";
/*     */   private Context mContext;
/*     */   private String mAppKey;
/*     */   private ApiUtils.WeiboInfo mWeiboInfo;
/*     */   private boolean mNeedDownloadWeibo;
/*     */   private IWeiboDownloadListener mDownloadListener;
/*     */   private Dialog mDownloadConfirmDialog;
/*     */   
/*     */   public WeiboShareAPIImpl(Context context, String appKey, boolean needDownloadWeibo) {
/*  55 */     this.mWeiboInfo = null;
/*     */ 
/*     */     
/*  58 */     this.mNeedDownloadWeibo = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.mDownloadConfirmDialog = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     this.mContext = context;
/*  74 */     this.mAppKey = appKey;
/*  75 */     this.mNeedDownloadWeibo = needDownloadWeibo;
/*     */ 
/*     */     
/*  78 */     this.mWeiboInfo = ApiUtils.queryWeiboInfo(this.mContext);
/*  79 */     if (this.mWeiboInfo != null) {
/*  80 */       LogUtil.d("WeiboApiImpl", this.mWeiboInfo.toString());
/*     */     } else {
/*  82 */       LogUtil.d("WeiboApiImpl", "WeiboInfo: is null");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public boolean isWeiboAppInstalled() { return (this.mWeiboInfo != null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public boolean isWeiboAppSupportAPI() { return ApiUtils.isWeiboAppSupportAPI(getWeiboAppSupportAPI()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public int getWeiboAppSupportAPI() { return (this.mWeiboInfo == null) ? -1 : this.mWeiboInfo.supportApi; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean registerApp() {
/*     */     try {
/* 127 */       if (!checkEnvironment(this.mNeedDownloadWeibo)) {
/* 128 */         return false;
/*     */       }
/* 130 */     } catch (Exception e) {
/* 131 */       return false;
/*     */     } 
/*     */     
/* 134 */     sendBroadcast(this.mContext, "com.sina.weibo.sdk.Intent.ACTION_WEIBO_REGISTER", this.mAppKey, null, null);
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleWeiboResponse(Intent intent, IWeiboHandler.Response handler) {
/* 154 */     String appPackage = intent.getStringExtra("_weibo_appPackage");
/*     */     
/* 156 */     if (appPackage == null) {
/* 157 */       LogUtil.e("WeiboApiImpl", "responseListener() faild appPackage is null");
/* 158 */       return false;
/*     */     } 
/*     */     
/* 161 */     if (!(handler instanceof Activity)) {
/* 162 */       LogUtil.e("WeiboApiImpl", "responseListener() faild handler is not Activity");
/* 163 */       return false;
/*     */     } 
/*     */     
/* 166 */     Activity act = (Activity)handler;
/* 167 */     String callPkg = act.getCallingPackage();
/* 168 */     LogUtil.d("WeiboApiImpl", "responseListener() callPkg : " + callPkg);
/*     */     
/* 170 */     if (intent.getStringExtra("_weibo_transaction") == null) {
/* 171 */       LogUtil.e("WeiboApiImpl", "responseListener() faild intent TRAN is null");
/* 172 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     if (!ApiUtils.validateWeiboSign(this.mContext, appPackage)) {
/* 183 */       LogUtil.e("WeiboApiImpl", "responseListener() faild appPackage validateSign faild");
/* 184 */       return false;
/*     */     } 
/*     */     
/* 187 */     SendMessageToWeiboResponse data = new SendMessageToWeiboResponse(intent.getExtras());
/* 188 */     handler.onResponse(data);
/* 189 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleWeiboRequest(Intent intent, IWeiboHandler.Request handler) {
/* 205 */     if (intent == null || handler == null) {
/* 206 */       return false;
/*     */     }
/*     */     
/* 209 */     String appPackage = intent.getStringExtra("_weibo_appPackage");
/* 210 */     String transaction = intent.getStringExtra("_weibo_transaction");
/*     */     
/* 212 */     if (appPackage == null) {
/* 213 */       LogUtil.e("WeiboApiImpl", "requestListener() faild appPackage validateSign faild");
/* 214 */       handler.onRequest(null);
/* 215 */       return false;
/* 216 */     }  if (transaction == null) {
/* 217 */       LogUtil.e("WeiboApiImpl", "requestListener() faild intent TRAN is null");
/* 218 */       handler.onRequest(null);
/* 219 */       return false;
/* 220 */     }  if (!ApiUtils.validateWeiboSign(this.mContext, appPackage)) {
/* 221 */       LogUtil.e("WeiboApiImpl", "requestListener() faild appPackage validateSign faild");
/* 222 */       handler.onRequest(null);
/* 223 */       return false;
/*     */     } 
/* 225 */     ProvideMessageForWeiboRequest data = new ProvideMessageForWeiboRequest(intent.getExtras());
/* 226 */     handler.onRequest(data);
/* 227 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean launchWeibo() {
/* 238 */     if (this.mWeiboInfo == null) {
/* 239 */       LogUtil.e("WeiboApiImpl", "startWeibo() faild winfo is null");
/* 240 */       return false;
/*     */     } 
/*     */     
/*     */     try {
/* 244 */       if (TextUtils.isEmpty(this.mWeiboInfo.packageName)) {
/* 245 */         LogUtil.e("WeiboApiImpl", "startWeibo() faild packageName is null");
/* 246 */         return false;
/*     */       } 
/*     */       
/* 249 */       this.mContext.startActivity(
/* 250 */           this.mContext.getPackageManager().getLaunchIntentForPackage(this.mWeiboInfo.packageName));
/* 251 */     } catch (Exception e) {
/* 252 */       LogUtil.e("WeiboApiImpl", e.getMessage());
/* 253 */       return false;
/*     */     } 
/*     */     
/* 256 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean sendRequest(BaseRequest request) {
/* 269 */     if (request == null) {
/* 270 */       LogUtil.e("WeiboApiImpl", "sendRequest faild act == null or request == null");
/* 271 */       return false;
/*     */     } 
/*     */     
/*     */     try {
/* 275 */       if (!checkEnvironment(this.mNeedDownloadWeibo)) {
/* 276 */         return false;
/*     */       }
/* 278 */     } catch (Exception e) {
/* 279 */       return false;
/*     */     } 
/*     */     
/* 282 */     VersionCheckHandler checkHandler = new VersionCheckHandler(this.mWeiboInfo.packageName);
/* 283 */     if (!request.check(this.mContext, checkHandler)) {
/* 284 */       LogUtil.e("WeiboApiImpl", "sendRequest faild request check faild");
/* 285 */       return false;
/*     */     } 
/*     */     
/* 288 */     Bundle data = new Bundle();
/* 289 */     request.toBundle(data);
/*     */     
/* 291 */     return launchWeiboActivity((Activity)this.mContext, "com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY", this.mWeiboInfo.packageName, this.mAppKey, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean sendResponse(BaseResponse response) {
/* 303 */     if (response == null) {
/* 304 */       LogUtil.e("WeiboApiImpl", "sendResponse failed response null");
/* 305 */       return false;
/*     */     } 
/*     */     
/* 308 */     if (!response.check(this.mContext, new VersionCheckHandler())) {
/* 309 */       LogUtil.e("WeiboApiImpl", "sendResponse checkArgs fail");
/* 310 */       return false;
/*     */     } 
/* 312 */     Bundle data = new Bundle();
/* 313 */     response.toBundle(data);
/* 314 */     sendBroadcast(this.mContext, "com.sina.weibo.sdk.Intent.ACTION_WEIBO_RESPONSE", this.mAppKey, response.reqPackageName, data);
/*     */     
/* 316 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 327 */   public void registerWeiboDownloadListener(IWeiboDownloadListener listener) { this.mDownloadListener = listener; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkEnvironment(boolean bShowDownloadDialog) throws WeiboShareException {
/* 340 */     if (this.mWeiboInfo == null) {
/* 341 */       if (bShowDownloadDialog) {
/* 342 */         if (this.mDownloadConfirmDialog == null) {
/* 343 */           this.mDownloadConfirmDialog = WeiboDownloader.createDownloadConfirmDialog(this.mContext, this.mDownloadListener);
/* 344 */           this.mDownloadConfirmDialog.show();
/*     */         }
/* 346 */         else if (!this.mDownloadConfirmDialog.isShowing()) {
/* 347 */           this.mDownloadConfirmDialog.show();
/*     */         } 
/*     */         
/* 350 */         return false;
/*     */       } 
/* 352 */       throw new WeiboShareException("Weibo is NOT installed!");
/*     */     } 
/*     */ 
/*     */     
/* 356 */     if (!ApiUtils.isWeiboAppSupportAPI(this.mWeiboInfo.supportApi)) {
/* 357 */       throw new WeiboShareException("Weibo do NOT support Share API!");
/*     */     }
/*     */     
/* 360 */     if (!ApiUtils.validateWeiboSign(this.mContext, this.mWeiboInfo.packageName)) {
/* 361 */       throw new WeiboShareException("Weibo signature is incorrect!");
/*     */     }
/*     */     
/* 364 */     return true;
/*     */   }
/*     */   
/*     */   private boolean launchWeiboActivity(Activity activity, String action, String pkgName, String appkey, Bundle data) {
/* 368 */     if (activity == null || 
/* 369 */       TextUtils.isEmpty(action) || 
/* 370 */       TextUtils.isEmpty(pkgName) || 
/* 371 */       TextUtils.isEmpty(appkey)) {
/* 372 */       LogUtil.e("ActivityHandler", "send fail, invalid arguments");
/* 373 */       return false;
/*     */     } 
/*     */     
/* 376 */     Intent intent = new Intent();
/* 377 */     intent.setPackage(pkgName);
/* 378 */     intent.setAction(action);
/* 379 */     String appPackage = activity.getPackageName();
/*     */     
/* 381 */     intent.putExtra("_weibo_sdkVersion", 22);
/* 382 */     intent.putExtra("_weibo_appPackage", appPackage);
/* 383 */     intent.putExtra("_weibo_appKey", appkey);
/* 384 */     intent.putExtra("_weibo_flag", 538116905);
/* 385 */     intent.putExtra("_weibo_sign", MD5.hexdigest(Utility.getSign(activity, appPackage)));
/*     */     
/* 387 */     if (data != null) {
/* 388 */       intent.putExtras(data);
/*     */     }
/*     */     
/*     */     try {
/* 392 */       LogUtil.d("WeiboApiImpl", "intent=" + intent + ", extra=" + intent.getExtras());
/* 393 */       activity.startActivityForResult(intent, 765);
/* 394 */     } catch (ActivityNotFoundException e) {
/* 395 */       LogUtil.e("WeiboApiImpl", "Failed, target ActivityNotFound");
/* 396 */       return false;
/*     */     } 
/*     */     
/* 399 */     return true;
/*     */   }
/*     */   
/*     */   private void sendBroadcast(Context context, String action, String key, String packageName, Bundle data) {
/* 403 */     Intent intent = new Intent(action);
/* 404 */     String appPackage = context.getPackageName();
/* 405 */     intent.putExtra("_weibo_sdkVersion", 22);
/* 406 */     intent.putExtra("_weibo_appPackage", appPackage);
/* 407 */     intent.putExtra("_weibo_appKey", key);
/* 408 */     intent.putExtra("_weibo_flag", 538116905);
/* 409 */     intent.putExtra("_weibo_sign", MD5.hexdigest(Utility.getSign(context, appPackage)));
/*     */     
/* 411 */     if (!TextUtils.isEmpty(packageName)) {
/* 412 */       intent.setPackage(packageName);
/*     */     }
/*     */     
/* 415 */     if (data != null) {
/* 416 */       intent.putExtras(data);
/*     */     }
/*     */     
/* 419 */     LogUtil.d("WeiboApiImpl", "intent=" + intent + ", extra=" + intent.getExtras());
/* 420 */     context.sendBroadcast(intent, "com.sina.weibo.permission.WEIBO_SDK_PERMISSION");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\WeiboShareAPIImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */