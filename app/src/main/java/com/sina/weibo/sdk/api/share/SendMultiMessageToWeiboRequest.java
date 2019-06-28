/*    */ package com.sina.weibo.sdk.api.share;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.os.Bundle;
/*    */ import com.sina.weibo.sdk.api.WeiboMultiMessage;
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
/*    */ public class SendMultiMessageToWeiboRequest
/*    */   extends BaseRequest
/*    */ {
/*    */   public WeiboMultiMessage multiMessage;
/*    */   
/*    */   public SendMultiMessageToWeiboRequest() {}
/*    */   
/* 39 */   public SendMultiMessageToWeiboRequest(Bundle data) { fromBundle(data); }
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
/* 50 */     this.multiMessage = new WeiboMultiMessage(data);
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBundle(Bundle data) {
/* 55 */     super.toBundle(data);
/* 56 */     data.putAll(this.multiMessage.toBundle(data));
/*    */   }
/*    */ 
/*    */   
/*    */   final boolean check(Context context, VersionCheckHandler handler) {
/* 61 */     if (this.multiMessage == null) {
/* 62 */       return false;
/*    */     }
/*    */     
/* 65 */     if (handler != null && 
/* 66 */       !handler.check(context, this.multiMessage)) {
/* 67 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 71 */     return this.multiMessage.checkArgs();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\SendMultiMessageToWeiboRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */