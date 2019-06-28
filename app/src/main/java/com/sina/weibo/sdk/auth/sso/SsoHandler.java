/*     */ package com.sina.weibo.sdk.auth.sso;
/*     */
/*     */ import android.app.Activity;
/*     */ import android.app.ActivityManager;
/*     */ import android.content.ActivityNotFoundException;
/*     */ import android.content.ComponentName;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.ServiceConnection;
/*     */ import android.os.Bundle;
/*     */ import android.os.IBinder;
/*     */ import android.os.RemoteException;
/*     */ import android.text.TextUtils;
/*     */ import com.sina.sso.RemoteSSO;
/*     */ import com.sina.weibo.sdk.auth.Oauth2AccessToken;
/*     */ import com.sina.weibo.sdk.auth.WeiboAuth;
/*     */ import com.sina.weibo.sdk.auth.WeiboAuthListener;
/*     */ import com.sina.weibo.sdk.exception.WeiboDialogException;
/*     */ import com.sina.weibo.sdk.utils.LogUtil;
/*     */ import com.sina.weibo.sdk.utils.SecurityHelper;
/*     */ import java.util.List;
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
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class SsoHandler
/*     */ {
/*     */   private static final String TAG = "Weibo_SSO_login";
/*     */   private static final String DEFAULT_SINA_WEIBO_PACKAGE_NAME = "com.sina.weibo";
/*     */   private static final String DEFAULT_WEIBO_REMOTE_SSO_SERVICE_NAME = "com.sina.weibo.remotessoservice";
/*     */   private static final int REQUEST_CODE_SSO_AUTH = 32973;
/*     */   private WeiboAuth mWeiboAuth;
/*     */   private WeiboAuthListener mAuthListener;
/*     */   private Activity mAuthActivity;
/*     */   private int mSSOAuthRequestCode;
/*     */   private ServiceConnection mConnection;
/*     */
/*     */   public SsoHandler(Activity activity, WeiboAuth weiboAuth) {
/*  72 */     this.mConnection = new ServiceConnection()
/*     */       {
/*     */         public void onServiceDisconnected(ComponentName name) {
/*  75 */           SsoHandler.this.mWeiboAuth.anthorize(SsoHandler.this.mAuthListener);
/*     */         }
/*     */
/*     */
/*     */         public void onServiceConnected(ComponentName name, IBinder service) {
/*  80 */           RemoteSSO remoteSSOservice = RemoteSSO.Stub.asInterface(service);
/*     */           try {
/*  82 */             String ssoPackageName = remoteSSOservice.getPackageName();
/*  83 */             String ssoActivityName = remoteSSOservice.getActivityName();
/*     */
/*     */
/*  86 */             SsoHandler.this.mAuthActivity.getApplicationContext().unbindService(SsoHandler.this.mConnection);
/*     */
/*  88 */             if (!SsoHandler.this.startSingleSignOn(ssoPackageName, ssoActivityName)) {
/*  89 */               SsoHandler.this.mWeiboAuth.anthorize(SsoHandler.this.mAuthListener);
/*     */             }
/*  91 */           } catch (RemoteException e) {
/*  92 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */       };
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/* 104 */     this.mAuthActivity = activity;
/* 105 */     this.mWeiboAuth = weiboAuth;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/* 115 */   public void authorize(WeiboAuthListener listener) { authorize(32973, listener, null); }
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
/* 128 */   public void authorize(WeiboAuthListener listener, String packageName) { authorize(32973, listener, packageName); }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void authorize(int requestCode, WeiboAuthListener listener, String packageName) {
/* 139 */     this.mSSOAuthRequestCode = requestCode;
/* 140 */     this.mAuthListener = listener;
/*     */
/*     */
/* 143 */     boolean bindSucced = bindRemoteSSOService(this.mAuthActivity.getApplicationContext(), packageName);
/*     */
/*     */
/* 146 */     if (!bindSucced &&
/* 147 */       this.mWeiboAuth != null) {
/* 148 */       this.mWeiboAuth.anthorize(this.mAuthListener);
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
/*     */   public void authorizeCallBack(int requestCode, int resultCode, Intent data) {
/* 173 */     LogUtil.d("Weibo_SSO_login", "requestCode: " + requestCode + ", resultCode: " + resultCode + ", data: " + data);
/* 174 */     if (requestCode == this.mSSOAuthRequestCode)
/*     */     {
/*     */
/* 177 */       if (resultCode == -1) {
/* 178 */         if (!SecurityHelper.checkResponseAppLegal(this.mAuthActivity, data)) {
/*     */           return;
/*     */         }
/*     */
/*     */
/* 183 */         String error = data.getStringExtra("error");
/* 184 */         if (error == null) {
/* 185 */           error = data.getStringExtra("error_type");
/*     */         }
/*     */
/*     */
/* 189 */         if (error != null) {
/* 190 */           if (error.equals("access_denied") || error.equals("OAuthAccessDeniedException")) {
/* 191 */             LogUtil.d("Weibo_SSO_login", "Login canceled by user.");
/*     */
/* 193 */             this.mAuthListener.onCancel();
/*     */           } else {
/* 195 */             String description = data.getStringExtra("error_description");
/* 196 */             if (description != null) {
/* 197 */               error = String.valueOf(error) + ":" + description;
/*     */             }
/*     */
/* 200 */             LogUtil.d("Weibo_SSO_login", "Login failed: " + error);
/* 201 */             this.mAuthListener.onWeiboException(
/* 202 */                 new WeiboDialogException(error, resultCode, description));
/*     */           }
/*     */         } else {
/*     */
/* 206 */           Bundle bundle = data.getExtras();
/* 207 */           Oauth2AccessToken accessToken = Oauth2AccessToken.parseAccessToken(bundle);
/*     */
/* 209 */           if (accessToken != null && accessToken.isSessionValid()) {
/* 210 */             LogUtil.d("Weibo_SSO_login", "Login Success! " + accessToken.toString());
/* 211 */             this.mAuthListener.onComplete(bundle);
/*     */           } else {
/* 213 */             LogUtil.d("Weibo_SSO_login", "Failed to receive access token by SSO");
/*     */
/* 215 */             this.mWeiboAuth.anthorize(this.mAuthListener);
/*     */           }
/*     */
/*     */         }
/*     */
/* 220 */       } else if (resultCode == 0) {
/*     */
/*     */
/* 223 */         if (data != null) {
/* 224 */           LogUtil.d("Weibo_SSO_login", "Login failed: " + data.getStringExtra("error"));
/* 225 */           this.mAuthListener.onWeiboException(
/* 226 */               new WeiboDialogException(
/* 227 */                 data.getStringExtra("error"),
/* 228 */                 data.getIntExtra("error_code", -1),
/* 229 */                 data.getStringExtra("failing_url")));
/*     */         } else {
/*     */
/* 232 */           LogUtil.d("Weibo_SSO_login", "Login canceled by user.");
/* 233 */           this.mAuthListener.onCancel();
/*     */         }
/*     */       }
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
/*     */
/*     */   public static ComponentName isServiceExisted(Context context, String packageName) {
/* 248 */     ActivityManager activityManager =
/* 249 */       (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
/* 250 */     List<ActivityManager.RunningServiceInfo> serviceList =
/* 251 */       activityManager.getRunningServices(2147483647);
/*     */
/* 253 */     for (ActivityManager.RunningServiceInfo runningServiceInfo : serviceList) {
/* 254 */       ComponentName serviceName = runningServiceInfo.service;
/*     */
/* 256 */       if (serviceName.getPackageName().equals(packageName) &&
/* 257 */         serviceName.getClassName().equals(String.valueOf(packageName) + ".business.RemoteSSOService")) {
/* 258 */         return serviceName;
/*     */       }
/*     */     }
/*     */
/*     */
/* 263 */     return null;
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
/*     */   private boolean bindRemoteSSOService(Context context, String packageName) {
/* 276 */     String tempPkgName = (TextUtils.isEmpty(packageName) || packageName.trim().equals("")) ?
/* 277 */       "com.sina.weibo" : packageName;
/* 278 */     Intent intent = new Intent("com.sina.weibo.remotessoservice");
/* 279 */     intent.setPackage(tempPkgName);
/*     */
/* 281 */     if (!context.bindService(intent, this.mConnection, Context.BIND_AUTO_CREATE)) {
/* 282 */       intent = new Intent("com.sina.weibo.remotessoservice");
                intent.setPackage(tempPkgName);
/* 283 */       return context.bindService(intent, this.mConnection,  Context.BIND_AUTO_CREATE);
/*     */     }
/*     */
/* 286 */     return true;
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
/*     */   private boolean startSingleSignOn(String ssoPackageName, String ssoActivityName) {
/* 299 */     boolean bSucceed = true;
/* 300 */     Intent intent = new Intent();
/*     */
/* 302 */     intent.setClassName(ssoPackageName, ssoActivityName);
/*     */
/*     */
/* 305 */     intent.putExtras(this.mWeiboAuth.getAuthInfo().getAuthBundle());
/*     */
/*     */
/* 308 */     intent.putExtra("_weibo_command_type", 3);
/* 309 */     intent.putExtra("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
/*     */
/*     */
/* 312 */     if (!SecurityHelper.validateAppSignatureForIntent(this.mAuthActivity, intent)) {
/* 313 */       return false;
/*     */     }
/*     */
/*     */
/*     */     try {
/* 318 */       this.mAuthActivity.startActivityForResult(intent, this.mSSOAuthRequestCode);
/* 319 */     } catch (ActivityNotFoundException e) {
/* 320 */       bSucceed = false;
/*     */     }
/*     */
/*     */
/*     */
/* 325 */     return bSucceed;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\auth\sso\SsoHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */