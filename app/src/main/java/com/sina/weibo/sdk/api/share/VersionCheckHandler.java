/*     */ package com.sina.weibo.sdk.api.share;
/*     */ 
/*     */ import android.content.Context;
/*     */ import com.sina.weibo.sdk.api.WeiboMessage;
/*     */ import com.sina.weibo.sdk.api.WeiboMultiMessage;
/*     */ import com.sina.weibo.sdk.utils.LogUtil;
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
/*     */ public class VersionCheckHandler
/*     */   implements IVersionCheckHandler
/*     */ {
/*     */   private static final String TAG = "VersionCheckHandler";
/*     */   private String mPackageName;
/*     */   
/*  41 */   public VersionCheckHandler(String packageName) { this.mPackageName = packageName; }
/*     */ 
/*     */ 
/*     */   
/*     */   public VersionCheckHandler() {}
/*     */ 
/*     */   
/*  48 */   public void setPackageName(String packageName) { this.mPackageName = packageName; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean check(Context context, WeiboMessage message) {
/*  53 */     LogUtil.d("VersionCheckHandler", "check WeiboMessage package : " + this.mPackageName);
/*     */     
/*  55 */     if (this.mPackageName == null || this.mPackageName.length() == 0) {
/*  56 */       return false;
/*     */     }
/*     */     
/*  59 */     ApiUtils.WeiboInfo winfo = ApiUtils.queryWeiboInfoByPackage(context, this.mPackageName);
/*  60 */     if (winfo == null) {
/*  61 */       return false;
/*     */     }
/*     */     
/*  64 */     LogUtil.d("VersionCheckHandler", "check WeiboMessage WeiboInfo supportApi : " + winfo.supportApi);
/*     */     
/*  66 */     if (winfo.supportApi < 10351)
/*     */     {
/*  68 */       if (message.mediaObject != null && message.mediaObject instanceof com.sina.weibo.sdk.api.VoiceObject) {
/*  69 */         message.mediaObject = null;
/*     */       }
/*     */     }
/*     */     
/*  73 */     if (winfo.supportApi < 10352)
/*     */     {
/*  75 */       if (message.mediaObject != null && message.mediaObject instanceof com.sina.weibo.sdk.api.CmdObject) {
/*  76 */         message.mediaObject = null;
/*     */       }
/*     */     }
/*     */     
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean check(Context context, WeiboMultiMessage message) {
/*  85 */     LogUtil.d("VersionCheckHandler", "check WeiboMultiMessage package : " + this.mPackageName);
/*     */     
/*  87 */     if (this.mPackageName == null || this.mPackageName.length() == 0) {
/*  88 */       return false;
/*     */     }
/*     */     
/*  91 */     ApiUtils.WeiboInfo winfo = ApiUtils.queryWeiboInfoByPackage(context, this.mPackageName);
/*  92 */     if (winfo == null) {
/*  93 */       return false;
/*     */     }
/*     */     
/*  96 */     LogUtil.d("VersionCheckHandler", "check WeiboMultiMessage WeiboInfo supportApi : " + winfo.supportApi);
/*     */     
/*  98 */     if (winfo.supportApi < 10351)
/*     */     {
/* 100 */       return false;
/*     */     }
/*     */     
/* 103 */     if (winfo.supportApi < 10352)
/*     */     {
/* 105 */       if (message.mediaObject != null && message.mediaObject instanceof com.sina.weibo.sdk.api.CmdObject) {
/* 106 */         message.mediaObject = null;
/*     */       }
/*     */     }
/*     */     
/* 110 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\VersionCheckHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */