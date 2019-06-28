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
/*    */ public class WeiboAuthException
/*    */   extends WeiboException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static final String DEFAULT_AUTH_ERROR_CODE = "-1";
/*    */   public static final String DEFAULT_AUTH_ERROR_DESC = "Unknown Error Description";
/*    */   public static final String DEFAULT_AUTH_ERROR_TYPE = "Unknown Error Type";
/*    */   private final String mErrorType;
/*    */   private final String mErrorCode;
/*    */   
/*    */   public WeiboAuthException(String errorCode, String errorType, String errorDescription) {
/* 63 */     super(errorDescription);
/* 64 */     this.mErrorType = errorType;
/* 65 */     this.mErrorCode = errorCode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   public String getErrorType() { return this.mErrorType; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 83 */   public String getErrorCode() { return this.mErrorCode; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\exception\WeiboAuthException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */