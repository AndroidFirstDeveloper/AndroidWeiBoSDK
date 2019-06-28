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
/*    */ public class WeiboException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 475022994858770424L;
/*    */   
/*    */   public WeiboException() {}
/*    */   
/* 45 */   public WeiboException(String message) { super(message); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public WeiboException(String detailMessage, Throwable throwable) { super(detailMessage, throwable); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public WeiboException(Throwable throwable) { super(throwable); }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\exception\WeiboException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */