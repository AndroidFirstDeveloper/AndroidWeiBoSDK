/*     */ package com.sina.weibo.sdk.utils;
/*     */ 
/*     */ import android.util.Log;
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
/*     */ public class LogUtil
/*     */ {
/*     */   private static boolean sIsLogEnable = true;
/*     */   
/*  36 */   public static void enableLog() { sIsLogEnable = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static void disableLog() { sIsLogEnable = false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void d(String tag, String msg) {
/*  53 */     if (sIsLogEnable) {
/*     */       
/*  55 */       StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[3];
/*  56 */       String fileInfo = String.valueOf(stackTrace.getFileName()) + "(" + 
/*  57 */         stackTrace.getLineNumber() + ") " + 
/*  58 */         stackTrace.getMethodName();
/*  59 */       Log.d(tag, String.valueOf(fileInfo) + ": " + msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void i(String tag, String msg) {
/*  70 */     if (sIsLogEnable) {
/*  71 */       StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[3];
/*  72 */       String fileInfo = String.valueOf(stackTrace.getFileName()) + "(" + 
/*  73 */         stackTrace.getLineNumber() + ") " + 
/*  74 */         stackTrace.getMethodName();
/*  75 */       Log.i(tag, String.valueOf(fileInfo) + ": " + msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void e(String tag, String msg) {
/*  86 */     if (sIsLogEnable) {
/*  87 */       StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[3];
/*  88 */       String fileInfo = String.valueOf(stackTrace.getFileName()) + "(" + 
/*  89 */         stackTrace.getLineNumber() + ") " + 
/*  90 */         stackTrace.getMethodName();
/*  91 */       Log.e(tag, String.valueOf(fileInfo) + ": " + msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void w(String tag, String msg) {
/* 102 */     if (sIsLogEnable) {
/* 103 */       StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[3];
/* 104 */       String fileInfo = String.valueOf(stackTrace.getFileName()) + "(" + 
/* 105 */         stackTrace.getLineNumber() + ") " + 
/* 106 */         stackTrace.getMethodName();
/* 107 */       Log.w(tag, String.valueOf(fileInfo) + ": " + msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void v(String tag, String msg) {
/* 118 */     if (sIsLogEnable) {
/* 119 */       StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[3];
/* 120 */       String fileInfo = String.valueOf(stackTrace.getFileName()) + "(" + 
/* 121 */         stackTrace.getLineNumber() + ") " + 
/* 122 */         stackTrace.getMethodName();
/* 123 */       Log.v(tag, String.valueOf(fileInfo) + ": " + msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getStackTraceMsg() {
/* 133 */  StackTraceElement   stackTrace = Thread.currentThread().getStackTrace()[3];
/* 134 */     return String.valueOf(stackTrace.getFileName()) + "(" + 
/* 135 */       stackTrace.getLineNumber() + ") " + 
/* 136 */       stackTrace.getMethodName();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sd\\utils\LogUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */