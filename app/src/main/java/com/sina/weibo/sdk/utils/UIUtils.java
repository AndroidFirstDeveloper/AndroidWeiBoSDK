/*    */ package com.sina.weibo.sdk.utils;
/*    */ 
/*    */ import android.app.AlertDialog;
/*    */ import android.content.Context;
/*    */ import android.widget.Toast;
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
/*    */ public class UIUtils
/*    */ {
/*    */   public static void showAlert(Context context, String title, String text) {
/* 40 */     if (context != null) {
/* 41 */       (new AlertDialog.Builder(context))
/* 42 */         .setTitle(title)
/* 43 */         .setMessage(text)
/* 44 */         .create()
/* 45 */         .show();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void showAlert(Context context, int titleId, int textId) {
/* 57 */     if (context != null) {
/* 58 */       showAlert(context, context.getString(titleId), context.getString(textId));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void showToast(Context context, int resId, int duration) {
/* 70 */     if (context != null) {
/* 71 */       Toast.makeText(context, resId, duration).show();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void showToast(Context context, CharSequence text, int duration) {
/* 83 */     if (context != null) {
/* 84 */       Toast.makeText(context, text, duration).show();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void showToastInCenter(Context context, int resId, int duration) {
/* 96 */     if (context != null) {
/* 97 */       Toast toast = Toast.makeText(context, resId, duration);
/* 98 */       toast.setGravity(17, 0, 0);
/* 99 */       toast.show();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sd\\utils\UIUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */