/*     */ package com.sina.weibo.sdk.api;
/*     */ 
/*     */ import android.os.Bundle;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WeiboMultiMessage
/*     */ {
/*     */   private static final String TAG = "WeiboMultiMessage";
/*     */   public TextObject textObject;
/*     */   public ImageObject imageObject;
/*     */   public BaseMediaObject mediaObject;
/*     */   
/*     */   public WeiboMultiMessage() {}
/*     */   
/*  48 */   public WeiboMultiMessage(Bundle data) { toBundle(data); }
/*     */ 
/*     */   
/*     */   public Bundle toBundle(Bundle data) {
/*  52 */     if (this.textObject != null) {
/*  53 */       data.putParcelable("_weibo_message_text", this.textObject);
/*  54 */       data.putString("_weibo_message_text_extra", this.textObject.toExtraMediaString());
/*     */     } 
/*  56 */     if (this.imageObject != null) {
/*  57 */       data.putParcelable("_weibo_message_image", this.imageObject);
/*  58 */       data.putString("_weibo_message_image_extra", this.imageObject.toExtraMediaString());
/*     */     } 
/*  60 */     if (this.mediaObject != null) {
/*  61 */       data.putParcelable("_weibo_message_media", this.mediaObject);
/*  62 */       data.putString("_weibo_message_media_extra", this.mediaObject.toExtraMediaString());
/*     */     } 
/*  64 */     return data;
/*     */   }
/*     */   
/*     */   public WeiboMultiMessage toObject(Bundle data) {
/*  68 */     this.textObject = (TextObject)data.getParcelable("_weibo_message_text");
/*  69 */     if (this.textObject != null) {
/*  70 */       this.textObject.toExtraMediaObject(data.getString("_weibo_message_text_extra"));
/*     */     }
/*  72 */     this.imageObject = (ImageObject)data.getParcelable("_weibo_message_image");
/*  73 */     if (this.imageObject != null) {
/*  74 */       this.imageObject.toExtraMediaObject(data.getString("_weibo_message_image_extra"));
/*     */     }
/*  76 */     this.mediaObject = (BaseMediaObject)data.getParcelable("_weibo_message_media");
/*  77 */     if (this.mediaObject != null) {
/*  78 */       this.mediaObject.toExtraMediaObject(data.getString("_weibo_message_media_extra"));
/*     */     }
/*  80 */     return this;
/*     */   }
/*     */   
/*     */   public boolean checkArgs() {
/*  84 */     if (this.textObject != null && !this.textObject.checkArgs()) {
/*  85 */       LogUtil.e("WeiboMultiMessage", "checkArgs fail, textObject is invalid");
/*  86 */       return false;
/*     */     } 
/*  88 */     if (this.imageObject != null && !this.imageObject.checkArgs()) {
/*  89 */       LogUtil.e("WeiboMultiMessage", "checkArgs fail, imageObject is invalid");
/*  90 */       return false;
/*     */     } 
/*  92 */     if (this.mediaObject != null && !this.mediaObject.checkArgs()) {
/*  93 */       LogUtil.e("WeiboMultiMessage", "checkArgs fail, mediaObject is invalid");
/*  94 */       return false;
/*     */     } 
/*  96 */     if (this.textObject == null && this.imageObject == null && this.mediaObject == null) {
/*  97 */       LogUtil.e("WeiboMultiMessage", "checkArgs fail, textObject and imageObject and mediaObject is null");
/*  98 */       return false;
/*     */     } 
/* 100 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\WeiboMultiMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */