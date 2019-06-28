/*    */ package com.sina.weibo.sdk.api;
/*    */ 
/*    */ import android.os.Bundle;
/*    */ import com.sina.weibo.sdk.utils.LogUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class WeiboMessage
/*    */ {
/*    */   public BaseMediaObject mediaObject;
/*    */   
/*    */   public WeiboMessage() {}
/*    */   
/* 39 */   public WeiboMessage(Bundle data) { toBundle(data); }
/*    */ 
/*    */   
/*    */   public Bundle toBundle(Bundle data) {
/* 43 */     if (this.mediaObject != null) {
/* 44 */       data.putParcelable("_weibo_message_media", this.mediaObject);
/* 45 */       data.putString("_weibo_message_media_extra", this.mediaObject.toExtraMediaString());
/*    */     } 
/* 47 */     return data;
/*    */   }
/*    */   
/*    */   public WeiboMessage toObject(Bundle data) {
/* 51 */     this.mediaObject = (BaseMediaObject)data.getParcelable("_weibo_message_media");
/* 52 */     if (this.mediaObject != null) {
/* 53 */       this.mediaObject.toExtraMediaObject(data.getString("_weibo_message_media_extra"));
/*    */     }
/* 55 */     return this;
/*    */   }
/*    */   
/*    */   public boolean checkArgs() {
/* 59 */     if (this.mediaObject == null) {
/* 60 */       LogUtil.e("Weibo.WeiboMessage", "checkArgs fail, mediaObject is null");
/* 61 */       return false;
/*    */     } 
/* 63 */     if (this.mediaObject != null && !this.mediaObject.checkArgs()) {
/* 64 */       LogUtil.e("Weibo.WeiboMessage", "checkArgs fail, mediaObject is invalid");
/* 65 */       return false;
/*    */     } 
/* 67 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\WeiboMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */