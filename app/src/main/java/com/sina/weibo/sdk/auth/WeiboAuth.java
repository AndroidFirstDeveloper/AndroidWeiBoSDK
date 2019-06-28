/*     */ package com.sina.weibo.sdk.auth;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.os.Bundle;
/*     */ import com.sina.weibo.sdk.net.WeiboParameters;
/*     */ import com.sina.weibo.sdk.utils.LogUtil;
/*     */ import com.sina.weibo.sdk.utils.NetworkHelper;
/*     */ import com.sina.weibo.sdk.utils.ResourceManager;
/*     */ import com.sina.weibo.sdk.utils.UIUtils;
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
/*     */ public class WeiboAuth
/*     */ {
/*     */   public static final String TAG = "Weibo_web_login";
/*     */   private static final String OAUTH2_BASE_URL = "https://open.weibo.cn/oauth2/authorize?";
/*     */   public static final int OBTAIN_AUTH_CODE = 0;
/*     */   public static final int OBTAIN_AUTH_TOKEN = 1;
/*     */   private Context mContext;
/*     */   private AuthInfo mAuthInfo;
/*     */   
/*     */   public static class AuthInfo
/*     */   {
/*     */     private String mAppKey;
/*     */     private String mRedirectUrl;
/*     */     private String mScope;
/*     */     private String mPackageName;
/*     */     private String mKeyHash;
/*     */     private Bundle mBundle;
/*     */     
/*     */     public AuthInfo(Context context, String appKey, String redirectUrl, String scope) {
/*  58 */       this.mAppKey = "";
/*     */       
/*  60 */       this.mRedirectUrl = "";
/*     */       
/*  62 */       this.mScope = "";
/*     */       
/*  64 */       this.mPackageName = "";
/*     */       
/*  66 */       this.mKeyHash = "";
/*     */       
/*  68 */       this.mBundle = null;
/*     */ 
/*     */       
/*  71 */       this.mAppKey = appKey;
/*  72 */       this.mRedirectUrl = redirectUrl;
/*  73 */       this.mScope = scope;
/*     */       
/*  75 */       this.mPackageName = context.getPackageName();
/*  76 */       this.mKeyHash = Utility.getSign(context, this.mPackageName);
/*     */       
/*  78 */       initAuthBundle();
/*     */     }
/*     */ 
/*     */     
/*  82 */     public String getAppKey() { return this.mAppKey; }
/*     */ 
/*     */ 
/*     */     
/*  86 */     public String getRedirectUrl() { return this.mRedirectUrl; }
/*     */ 
/*     */ 
/*     */     
/*  90 */     public String getScope() { return this.mScope; }
/*     */ 
/*     */ 
/*     */     
/*  94 */     public String getPackageName() { return this.mPackageName; }
/*     */ 
/*     */ 
/*     */     
/*  98 */     public String getKeyHash() { return this.mKeyHash; }
/*     */ 
/*     */ 
/*     */     
/* 102 */     public Bundle getAuthBundle() { return this.mBundle; }
/*     */ 
/*     */     
/*     */     private void initAuthBundle() {
/* 106 */       this.mBundle = new Bundle();
/* 107 */       this.mBundle.putString("appKey", this.mAppKey);
/* 108 */       this.mBundle.putString("redirectUri", this.mRedirectUrl);
/* 109 */       this.mBundle.putString("scope", this.mScope);
/* 110 */       this.mBundle.putString("packagename", this.mPackageName);
/* 111 */       this.mBundle.putString("key_hash", this.mKeyHash);
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
/*     */   public WeiboAuth(Context context, String appKey, String redirectUrl, String scope) {
/* 126 */     this.mContext = context;
/* 127 */     this.mAuthInfo = new AuthInfo(context, appKey, redirectUrl, scope);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WeiboAuth(Context context, AuthInfo authInfo) {
/* 137 */     this.mContext = context;
/* 138 */     this.mAuthInfo = authInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public AuthInfo getAuthInfo() { return this.mAuthInfo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public void setAuthInfo(AuthInfo authInfo) { this.mAuthInfo = authInfo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public void anthorize(WeiboAuthListener listener) { authorize(listener, 1); }
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
/* 177 */   public void authorize(WeiboAuthListener listener, int type) { startDialog(listener, type); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void startDialog(WeiboAuthListener listener, int type) {
/* 188 */     if (listener == null) {
/*     */       return;
/*     */     }
/*     */     
/* 192 */     WeiboParameters requestParams = new WeiboParameters();
/* 193 */     requestParams.put("client_id", this.mAuthInfo.mAppKey);
/* 194 */     requestParams.put("redirect_uri", this.mAuthInfo.mRedirectUrl);
/* 195 */     requestParams.put("scope", this.mAuthInfo.mScope);
/* 196 */     requestParams.put("response_type", "code");
/* 197 */     requestParams.put("display", "mobile");
/*     */ 
/*     */     
/* 200 */     if (1 == type) {
/* 201 */       requestParams.put("packagename", this.mAuthInfo.mPackageName);
/* 202 */       requestParams.put("key_hash", this.mAuthInfo.mKeyHash);
/*     */     } 
/*     */     
/* 205 */     String url = "https://open.weibo.cn/oauth2/authorize?" + requestParams.encodeUrl();
/* 206 */     if (!NetworkHelper.hasInternetPermission(this.mContext)) {
/* 207 */       UIUtils.showAlert(this.mContext, "Error", "Application requires permission to access the Internet");
/*     */     }
/* 209 */     else if (NetworkHelper.isNetworkAvailable(this.mContext)) {
/* 210 */       (new WeiboDialog(this.mContext, url, listener, this)).show();
/*     */     } else {
/* 212 */       String networkNotAvailable = ResourceManager.getString(this.mContext, 2);
/* 213 */       LogUtil.i("Weibo_web_login", "String: " + networkNotAvailable);
/* 214 */       UIUtils.showToast(this.mContext, networkNotAvailable, 0);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\auth\WeiboAuth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */