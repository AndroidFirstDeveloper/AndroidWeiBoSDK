/*    */ package com.sina.weibo.sdk.exception;
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
/*    */ public class WeiboHttpException
/*    */   extends WeiboException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final int mStatusCode;
/*    */   
/*    */   public WeiboHttpException(String message, int statusCode) {
/* 39 */     super(message);
/* 40 */     this.mStatusCode = statusCode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public int getStatusCode() { return this.mStatusCode; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\exception\WeiboHttpException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */