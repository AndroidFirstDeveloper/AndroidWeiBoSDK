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
/*    */ public class ProvideMultiMessageForWeiboResponse
/*    */   extends BaseResponse
/*    */ {
/*    */   public WeiboMultiMessage multiMessage;
/*    */   
/*    */   public ProvideMultiMessageForWeiboResponse() {}
/*    */   
/* 39 */   public ProvideMultiMessageForWeiboResponse(Bundle bundle) { fromBundle(bundle); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public int getType() { return 2; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void fromBundle(Bundle bundle) {
/* 49 */     super.fromBundle(bundle);
/* 50 */     this.multiMessage = new WeiboMultiMessage(bundle);
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBundle(Bundle bundle) {
/* 55 */     super.toBundle(bundle);
/* 56 */     bundle.putAll(this.multiMessage.toBundle(bundle));
/*    */   }
/*    */ 
/*    */   
/*    */   final boolean check(Context context, VersionCheckHandler handler) {
/* 61 */     if (this.multiMessage == null) {
/* 62 */       return false;
/*    */     }
/*    */     
/* 65 */     if (handler != null) {
/* 66 */       handler.setPackageName(this.reqPackageName);
/* 67 */       if (!handler.check(context, this.multiMessage)) {
/* 68 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 72 */     return this.multiMessage.checkArgs();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\ProvideMultiMessageForWeiboResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */