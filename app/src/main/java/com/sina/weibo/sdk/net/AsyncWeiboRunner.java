/*     */ package com.sina.weibo.sdk.net;
/*     */ 
/*     */ import android.os.AsyncTask;
/*     */ import com.sina.weibo.sdk.exception.WeiboException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AsyncWeiboRunner
/*     */ {
/*     */   @Deprecated
/*  46 */   public static void requestByThread(final String url, final WeiboParameters params, final String httpMethod, final RequestListener listener) { (new Thread()
/*     */       {
/*     */         public void run() {
/*     */           try {
/*  50 */             String resp = HttpManager.openUrl(url, httpMethod, params);
/*  51 */             if (listener != null) {
/*  52 */               listener.onComplete(resp);
/*     */             }
/*  54 */           } catch (WeiboException e) {
/*  55 */             if (listener != null) {
/*  56 */               listener.onWeiboException(e);
/*     */             }
/*     */           } 
/*     */         }
/*  60 */       }).start(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public static String request(String url, WeiboParameters params, String httpMethod) throws WeiboException { return HttpManager.openUrl(url, httpMethod, params); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public static void requestAsync(String url, WeiboParameters params, String httpMethod, RequestListener listener) { (new RequestRunner(url, params, httpMethod, listener)).execute(new Void[1]); }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class RequestRunner
/*     */     extends AsyncTask<Void, Void, AsyncTaskResult<String>>
/*     */   {
/*     */     private final String mUrl;
/*     */ 
/*     */     
/*     */     private final WeiboParameters mParams;
/*     */ 
/*     */     
/*     */     private final String mHttpMethod;
/*     */     
/*     */     private final RequestListener mListener;
/*     */ 
/*     */     
/*     */     public RequestRunner(String url, WeiboParameters params, String httpMethod, RequestListener listener) {
/* 112 */       this.mUrl = url;
/* 113 */       this.mParams = params;
/* 114 */       this.mHttpMethod = httpMethod;
/* 115 */       this.mListener = listener;
/*     */     }
/*     */ 
/*     */     
/*     */     protected AsyncTaskResult<String> doInBackground(Void... params) {
/*     */       try {
/* 121 */         String result = HttpManager.openUrl(this.mUrl, this.mHttpMethod, this.mParams);
/* 122 */         return new AsyncTaskResult(result);
/* 123 */       } catch (WeiboException e) {
/*     */         
/* 125 */         return new AsyncTaskResult(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void onPreExecute() {}
/*     */ 
/*     */     
/*     */     protected void onPostExecute(AsyncTaskResult<String> result) {
/* 135 */       WeiboException exception = result.getError();
/* 136 */       if (exception != null) {
/* 137 */         this.mListener.onWeiboException(exception);
/*     */       } else {
/* 139 */         this.mListener.onComplete((String)result.getResult());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static class AsyncTaskResult<T>
/*     */     extends Object {
/*     */     private T result;
/*     */     private WeiboException error;
/*     */     
/* 149 */     public T getResult() { return (T)this.result; }
/*     */ 
/*     */ 
/*     */     
/* 153 */     public WeiboException getError() { return this.error; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     public AsyncTaskResult(T result) { this.result = result; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     public AsyncTaskResult(WeiboException error) { this.error = error; }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\net\AsyncWeiboRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */