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
/*    */ public class ProvideMessageForWeiboResponse
/*    */   extends BaseResponse
/*    */ {
/*    */   public WeiboMessage message;
/*    */   
/*    */   public ProvideMessageForWeiboResponse() {}
/*    */   
/* 39 */   public ProvideMessageForWeiboResponse(Bundle bundle) { fromBundle(bundle); }
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
/* 50 */     this.message = new WeiboMessage(bundle);
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBundle(Bundle bundle) {
/* 55 */     super.toBundle(bundle);
/* 56 */     bundle.putAll(this.message.toBundle(bundle));
/*    */   }
/*    */ 
/*    */   
/*    */   final boolean check(Context context, VersionCheckHandler handler) {
/* 61 */     if (this.message == null) {
/* 62 */       return false;
/*    */     }
/*    */     
/* 65 */     if (handler != null) {
/* 66 */       handler.setPackageName(this.reqPackageName);
/* 67 */       if (!handler.check(context, this.message)) {
/* 68 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 72 */     return this.message.checkArgs();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\ProvideMessageForWeiboResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */