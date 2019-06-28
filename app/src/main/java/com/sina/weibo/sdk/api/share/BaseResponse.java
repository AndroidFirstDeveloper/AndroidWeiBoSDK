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
/*    */ 
/*    */ public abstract class BaseResponse
/*    */   extends Base
/*    */ {
/*    */   public int errCode;
/*    */   public String errMsg;
/*    */   public String reqPackageName;
/*    */   
/*    */   public void toBundle(Bundle bundle) {
/* 44 */     bundle.putInt("_weibo_command_type", getType());
/* 45 */     bundle.putInt("_weibo_resp_errcode", this.errCode);
/* 46 */     bundle.putString("_weibo_resp_errstr", this.errMsg);
/* 47 */     bundle.putString("_weibo_transaction", this.transaction);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fromBundle(Bundle bundle) {
/* 56 */     this.errCode = bundle.getInt("_weibo_resp_errcode");
/* 57 */     this.errMsg = bundle.getString("_weibo_resp_errstr");
/* 58 */     this.transaction = bundle.getString("_weibo_transaction");
/* 59 */     this.reqPackageName = bundle.getString("_weibo_appPackage");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\BaseResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */