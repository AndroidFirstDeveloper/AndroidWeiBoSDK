/*    */ package com.sina.weibo.sdk.api.share;
/*    */ 
/*    */ import android.content.Context;
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
/*    */ public class ProvideMessageForWeiboRequest
/*    */   extends BaseRequest
/*    */ {
/*    */   public ProvideMessageForWeiboRequest() {}
/*    */   
/* 37 */   public ProvideMessageForWeiboRequest(Bundle bundle) { fromBundle(bundle); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public int getType() { return 2; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   final boolean check(Context context, VersionCheckHandler handler) { return true; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\ProvideMessageForWeiboRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */