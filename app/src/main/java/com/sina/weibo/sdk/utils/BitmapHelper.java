/*     */ package com.sina.weibo.sdk.utils;
/*     */ 
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.graphics.Rect;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public final class BitmapHelper
/*     */ {
/*     */   public static boolean makesureSizeNotTooLarge(Rect rect) {
/*  42 */     int FIVE_M = 5242880;
/*  43 */     if (rect.width() * rect.height() * 2 > 5242880)
/*     */     {
/*  45 */       return false;
/*     */     }
/*  47 */     return true;
/*     */   }
/*     */   
/*     */   public static int getSampleSizeOfNotTooLarge(Rect rect) {
/*  51 */     int FIVE_M = 5242880;
/*  52 */     double ratio = rect.width() * rect.height() * 2.0D / 5242880.0D;
/*  53 */     return (ratio >= 1.0D) ? (int)ratio : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSampleSizeAutoFitToScreen(int vWidth, int vHeight, int bWidth, int bHeight) {
/*  61 */     if (vHeight == 0 || vWidth == 0) {
/*  62 */       return 1;
/*     */     }
/*     */     
/*  65 */     int ratio = Math.max(bWidth / vWidth, bHeight / vHeight);
/*     */     
/*  67 */     int ratioAfterRotate = Math.max(bHeight / vWidth, bWidth / vHeight);
/*     */     
/*  69 */     return Math.min(ratio, ratioAfterRotate);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public static boolean verifyBitmap(byte[] datas) { return verifyBitmap(new ByteArrayInputStream(datas)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean verifyBitmap(InputStream input) {
/*  83 */     if (input == null) {
/*  84 */       return false;
/*     */     }
/*  86 */     BitmapFactory.Options options = new BitmapFactory.Options();
/*  87 */     options.inJustDecodeBounds = true;
/*  88 */     input = (input instanceof BufferedInputStream) ? input : new BufferedInputStream(input);
/*  89 */     BitmapFactory.decodeStream(input, null, options);
/*     */     try {
/*  91 */       input.close();
/*  92 */     } catch (IOException e) {
/*  93 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  96 */     return (options.outHeight > 0 && options.outWidth > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean verifyBitmap(String path) {
/*     */     try {
/* 104 */       return verifyBitmap(new FileInputStream(path));
/* 105 */     } catch (FileNotFoundException e) {
/* 106 */       e.printStackTrace();
/*     */       
/* 108 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sd\\utils\BitmapHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */