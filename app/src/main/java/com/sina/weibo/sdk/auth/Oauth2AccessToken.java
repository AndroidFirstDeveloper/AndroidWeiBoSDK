/*     */ package com.sina.weibo.sdk.auth;
/*     */ 
/*     */ import android.os.Bundle;
/*     */ import android.text.TextUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
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
/*     */ public class Oauth2AccessToken
/*     */ {
/*     */   private static final String KEY_UID = "uid";
/*     */   private static final String KEY_ACCESS_TOKEN = "access_token";
/*     */   private static final String KEY_EXPIRES_IN = "expires_in";
/*     */   private static final String KEY_REFRESH_TOKEN = "refresh_token";
/*  40 */   private String mUid = "";
/*     */   
/*  42 */   private String mAccessToken = "";
/*     */   
/*  44 */   private String mRefreshToken = "";
/*     */   
/*  46 */   private long mExpiresTime = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Oauth2AccessToken() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Oauth2AccessToken(String responseText) {
/*  62 */     if (responseText != null && 
/*  63 */       responseText.indexOf("{") >= 0) {
/*     */       try {
/*  65 */         JSONObject json = new JSONObject(responseText);
/*  66 */         setUid(json.optString("uid"));
/*  67 */         setToken(json.optString("access_token"));
/*  68 */         setExpiresIn(json.optString("expires_in"));
/*  69 */         setRefreshToken(json.optString("refresh_token"));
/*  70 */       } catch (JSONException e) {
/*  71 */         e.printStackTrace();
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
/*     */   public Oauth2AccessToken(String accessToken, String expiresIn) {
/*  84 */     this.mAccessToken = accessToken;
/*  85 */     this.mExpiresTime = System.currentTimeMillis();
/*  86 */     if (expiresIn != null) {
/*  87 */       this.mExpiresTime += Long.parseLong(expiresIn) * 1000L;
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
/*     */   public static Oauth2AccessToken parseAccessToken(String responseJsonText) {
/*  99 */     if (!TextUtils.isEmpty(responseJsonText) && 
/* 100 */       responseJsonText.indexOf("{") >= 0) {
/*     */       try {
/* 102 */         JSONObject json = new JSONObject(responseJsonText);
/* 103 */         Oauth2AccessToken token = new Oauth2AccessToken();
/* 104 */         token.setUid(json.optString("uid"));
/* 105 */         token.setToken(json.optString("access_token"));
/* 106 */         token.setExpiresIn(json.optString("expires_in"));
/* 107 */         token.setRefreshToken(json.optString("refresh_token"));
/*     */         
/* 109 */         return token;
/* 110 */       } catch (JSONException e) {
/* 111 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Oauth2AccessToken parseAccessToken(Bundle bundle) {
/* 127 */     if (bundle != null) {
/* 128 */       Oauth2AccessToken accessToken = new Oauth2AccessToken();
/* 129 */       accessToken.setUid(getString(bundle, "uid", ""));
/* 130 */       accessToken.setToken(getString(bundle, "access_token", ""));
/* 131 */       accessToken.setExpiresIn(getString(bundle, "expires_in", ""));
/* 132 */       accessToken.setRefreshToken(getString(bundle, "refresh_token", ""));
/*     */       
/* 134 */       return accessToken;
/*     */     } 
/*     */     
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public boolean isSessionValid() { return (!TextUtils.isEmpty(this.mAccessToken) && 
/* 147 */       this.mExpiresTime != 0L && 
/* 148 */       System.currentTimeMillis() < this.mExpiresTime); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bundle toBundle() {
/* 156 */     Bundle bundle = new Bundle();
/* 157 */     bundle.putString("uid", this.mUid);
/* 158 */     bundle.putString("access_token", this.mAccessToken);
/* 159 */     bundle.putString("refresh_token", this.mRefreshToken);
/* 160 */     bundle.putString("expires_in", Long.toString(this.mExpiresTime));
/* 161 */     return bundle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 169 */     return "uid: " + this.mUid + ", " + 
/* 170 */       "access_token" + ": " + this.mAccessToken + ", " + 
/* 171 */       "refresh_token" + ": " + this.mRefreshToken + ", " + 
/* 172 */       "expires_in" + ": " + Long.toString(this.mExpiresTime);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public String getUid() { return this.mUid; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public void setUid(String uid) { this.mUid = uid; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   public String getToken() { return this.mAccessToken; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public void setToken(String mToken) { this.mAccessToken = mToken; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public String getRefreshToken() { return this.mRefreshToken; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   public void setRefreshToken(String refreshToken) { this.mRefreshToken = refreshToken; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 230 */   public long getExpiresTime() { return this.mExpiresTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public void setExpiresTime(long mExpiresTime) { this.mExpiresTime = mExpiresTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpiresIn(String expiresIn) {
/* 249 */     if (!TextUtils.isEmpty(expiresIn) && !expiresIn.equals("0")) {
/* 250 */       setExpiresTime(System.currentTimeMillis() + Long.parseLong(expiresIn) * 1000L);
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
/*     */   private static String getString(Bundle bundle, String key, String defaultValue) {
/* 268 */     if (bundle != null) {
/* 269 */       String value = bundle.getString(key);
/* 270 */       return (value != null) ? value : defaultValue;
/*     */     } 
/*     */     
/* 273 */     return defaultValue;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\auth\Oauth2AccessToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */