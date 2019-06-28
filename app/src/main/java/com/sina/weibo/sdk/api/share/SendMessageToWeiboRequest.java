/*    */ package com.sina.weibo.sdk.api.share;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.os.Bundle;
/*    */ import com.sina.weibo.sdk.api.WeiboMessage;
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
/*    */ public class SendMessageToWeiboRequest
/*    */   extends BaseRequest
/*    */ {
/*    */   public WeiboMessage message;
/*    */   
/*    */   public SendMessageToWeiboRequest() {}
/*    */   
/* 39 */   public SendMessageToWeiboRequest(Bundle data) { fromBundle(data); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public int getType() { return 1; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void fromBundle(Bundle data) {
/* 49 */     super.fromBundle(data);
/* 50 */     this.message = new WeiboMessage(data);
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBundle(Bundle data) {
/* 55 */     super.toBundle(data);
/* 56 */     data.putAll(this.message.toBundle(data));
/*    */   }
/*    */ 
/*    */   
/*    */   final boolean check(Context context, VersionCheckHandler handler) {
/* 61 */     if (this.message == null) {
/* 62 */       return false;
/*    */     }
/*    */     
/* 65 */     if (handler != null && 
/* 66 */       !handler.check(context, this.message)) {
/* 67 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 71 */     return this.message.checkArgs();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\SendMessageToWeiboRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */