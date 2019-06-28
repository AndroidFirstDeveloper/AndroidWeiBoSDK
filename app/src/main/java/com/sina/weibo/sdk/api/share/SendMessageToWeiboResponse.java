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
/*    */ public class SendMessageToWeiboResponse
/*    */   extends BaseResponse
/*    */ {
/*    */   public SendMessageToWeiboResponse() {}
/*    */   
/* 37 */   public SendMessageToWeiboResponse(Bundle data) { fromBundle(data); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public int getType() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   final boolean check(Context context, VersionCheckHandler handler) { return true; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\SendMessageToWeiboResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */