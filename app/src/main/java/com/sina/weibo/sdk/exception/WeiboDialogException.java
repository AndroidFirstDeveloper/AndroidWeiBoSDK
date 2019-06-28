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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WeiboDialogException
/*    */   extends WeiboException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int mErrorCode;
/*    */   private String mFailingUrl;
/*    */   
/*    */   public WeiboDialogException(String message, int errorCode, String failingUrl) {
/* 45 */     super(message);
/* 46 */     this.mErrorCode = errorCode;
/* 47 */     this.mFailingUrl = failingUrl;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public int getErrorCode() { return this.mErrorCode; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public String getFailingUrl() { return this.mFailingUrl; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\exception\WeiboDialogException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */