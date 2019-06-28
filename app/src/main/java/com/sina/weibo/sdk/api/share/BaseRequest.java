/*    */ package com.sina.weibo.sdk.api.share;
/*    */ 
/*    */ import android.os.Bundle;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BaseRequest
/*    */   extends Base
/*    */ {
/*    */   public String packageName;
/*    */   
/*    */   public void toBundle(Bundle bundle) {
/* 41 */     bundle.putInt("_weibo_command_type", getType());
/* 42 */     bundle.putString("_weibo_transaction", this.transaction);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fromBundle(Bundle bundle) {
/* 52 */     this.transaction = bundle.getString("_weibo_transaction");
/* 53 */     this.packageName = bundle.getString("_weibo_appPackage");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\BaseRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */