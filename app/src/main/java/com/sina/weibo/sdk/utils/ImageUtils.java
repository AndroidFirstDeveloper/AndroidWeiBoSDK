/*     */ package com.sina.weibo.sdk.utils;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Matrix;
/*     */ import android.graphics.Paint;
/*     */ import android.net.ConnectivityManager;
/*     */ import android.net.NetworkInfo;
/*     */ import android.text.TextUtils;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
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
/*     */ public class ImageUtils
/*     */ {
/*     */   private static void revitionImageSizeHD(String picfile, int size, int quality) throws IOException {
/*  51 */     if (size <= 0) {
/*  52 */       throw new IllegalArgumentException("size must be greater than 0!");
/*     */     }
/*  54 */     if (!isFileExisted(picfile)) {
/*  55 */       throw new FileNotFoundException((picfile == null) ? "null" : picfile);
/*     */     }
/*     */     
/*  58 */     if (!BitmapHelper.verifyBitmap(picfile)) {
/*  59 */       throw new IOException("");
/*     */     }
/*     */     
/*  62 */     int photoSizesOrg = 2 * size;
/*  63 */     FileInputStream input = new FileInputStream(picfile);
/*  64 */     BitmapFactory.Options opts = new BitmapFactory.Options();
/*  65 */     opts.inJustDecodeBounds = true;
/*  66 */     BitmapFactory.decodeStream(input, null, opts);
/*     */     try {
/*  68 */       input.close();
/*  69 */     } catch (Exception e1) {
/*  70 */       e1.printStackTrace();
/*     */     } 
/*     */     
/*  73 */     int rate = 0;
/*  74 */     for (int i = 0;; i++) {
/*  75 */       if (opts.outWidth >> i <= photoSizesOrg && opts.outHeight >> i <= photoSizesOrg) {
/*  76 */         rate = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  81 */     opts.inSampleSize = (int)Math.pow(2.0D, rate);
/*  82 */     opts.inJustDecodeBounds = false;
/*     */     
/*  84 */     Bitmap temp = safeDecodeBimtapFile(picfile, opts);
/*     */     
/*  86 */     if (temp == null) {
/*  87 */       throw new IOException("Bitmap decode error!");
/*     */     }
/*     */     
/*  90 */     deleteDependon(picfile);
/*  91 */     makesureFileExist(picfile);
/*     */     
/*  93 */     int org = (temp.getWidth() > temp.getHeight()) ? temp.getWidth() : temp.getHeight();
/*  94 */     float rateOutPut = size / org;
/*     */     
/*  96 */     if (rateOutPut < 1.0F) {
/*     */       Bitmap outputBitmap;
/*     */       while (true) {
/*     */         try {
/* 100 */           outputBitmap = Bitmap.createBitmap((int)(temp.getWidth() * rateOutPut), 
/* 101 */               (int)(temp.getHeight() * rateOutPut), Bitmap.Config.ARGB_8888);
/*     */           break;
/* 103 */         } catch (OutOfMemoryError e) {
/* 104 */           System.gc();
/* 105 */           rateOutPut = (float)(rateOutPut * 0.8D);
/*     */         } 
/*     */       } 
/* 108 */       if (outputBitmap == null) {
/* 109 */         temp.recycle();
/*     */       }
/* 111 */       Canvas canvas = new Canvas(outputBitmap);
/* 112 */       Matrix matrix = new Matrix();
/* 113 */       matrix.setScale(rateOutPut, rateOutPut);
/* 114 */       canvas.drawBitmap(temp, matrix, new Paint());
/* 115 */       temp.recycle();
/* 116 */       temp = outputBitmap;
/*     */     } 
/* 118 */     FileOutputStream output = new FileOutputStream(picfile);
/* 119 */     if (opts != null && opts.outMimeType != null && opts.outMimeType.contains("png")) {
/* 120 */       temp.compress(Bitmap.CompressFormat.PNG, quality, output);
/*     */     } else {
/* 122 */       temp.compress(Bitmap.CompressFormat.JPEG, quality, output);
/*     */     } 
/*     */     try {
/* 125 */       output.close();
/* 126 */     } catch (Exception e) {
/* 127 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 130 */     temp.recycle();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void revitionImageSize(String picfile, int size, int quality) throws IOException {
/* 141 */     if (size <= 0) {
/* 142 */       throw new IllegalArgumentException("size must be greater than 0!");
/*     */     }
/*     */     
/* 145 */     if (!isFileExisted(picfile)) {
/* 146 */       throw new FileNotFoundException((picfile == null) ? "null" : picfile);
/*     */     }
/*     */     
/* 149 */     if (!BitmapHelper.verifyBitmap(picfile)) {
/* 150 */       throw new IOException("");
/*     */     }
/*     */     
/* 153 */     FileInputStream input = new FileInputStream(picfile);
/* 154 */     BitmapFactory.Options opts = new BitmapFactory.Options();
/* 155 */     opts.inJustDecodeBounds = true;
/* 156 */     BitmapFactory.decodeStream(input, null, opts);
/*     */     try {
/* 158 */       input.close();
/* 159 */     } catch (Exception e) {
/* 160 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 163 */     int rate = 0;
/* 164 */     for (int i = 0;; i++) {
/* 165 */       if (opts.outWidth >> i <= size && opts.outHeight >> i <= size) {
/* 166 */         rate = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 171 */     opts.inSampleSize = (int)Math.pow(2.0D, rate);
/* 172 */     opts.inJustDecodeBounds = false;
/*     */     
/* 174 */     Bitmap temp = safeDecodeBimtapFile(picfile, opts);
/*     */     
/* 176 */     if (temp == null) {
/* 177 */       throw new IOException("Bitmap decode error!");
/*     */     }
/*     */     
/* 180 */     deleteDependon(picfile);
/* 181 */     makesureFileExist(picfile);
/* 182 */     FileOutputStream output = new FileOutputStream(picfile);
/* 183 */     if (opts != null && opts.outMimeType != null && opts.outMimeType.contains("png")) {
/* 184 */       temp.compress(Bitmap.CompressFormat.PNG, quality, output);
/*     */     } else {
/* 186 */       temp.compress(Bitmap.CompressFormat.JPEG, quality, output);
/*     */     } 
/*     */     try {
/* 189 */       output.close();
/* 190 */     } catch (Exception e) {
/* 191 */       e.printStackTrace();
/*     */     } 
/* 193 */     temp.recycle();
/*     */   }
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
/*     */   public static boolean revitionPostImageSize(Context context, String picfile) {
/*     */     try {
/* 207 */       if (NetworkHelper.isWifiValid(context)) {
/* 208 */         revitionImageSizeHD(picfile, 1600, 75);
/*     */       } else {
/* 210 */         revitionImageSize(picfile, 1024, 75);
/*     */       } 
/*     */       
/* 213 */       return true;
/* 214 */     } catch (IOException e) {
/* 215 */       e.printStackTrace();
/*     */       
/* 217 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Bitmap safeDecodeBimtapFile(String bmpFile, BitmapFactory.Options opts) {
/* 229 */     BitmapFactory.Options optsTmp = opts;
/* 230 */     if (optsTmp == null) {
/* 231 */       optsTmp = new BitmapFactory.Options();
/* 232 */       optsTmp.inSampleSize = 1;
/*     */     } 
/*     */     
/* 235 */     Bitmap bmp = null;
/* 236 */     FileInputStream input = null;
/*     */     
/* 238 */     int MAX_TRIAL = 5;
/* 239 */     for (int i = 0; i < 5; i++) {
/*     */       try {
/* 241 */         input = new FileInputStream(bmpFile);
/* 242 */         bmp = BitmapFactory.decodeStream(input, null, opts);
/*     */         try {
/* 244 */           input.close(); break;
/* 245 */         } catch (IOException e) {
/* 246 */           e.printStackTrace();
/*     */           break;
/*     */         } 
/* 249 */       } catch (OutOfMemoryError e) {
/* 250 */         e.printStackTrace();
/* 251 */         optsTmp.inSampleSize *= 2;
/*     */         try {
/* 253 */           input.close();
/* 254 */         } catch (IOException e1) {
/* 255 */           e1.printStackTrace();
/*     */         } 
/* 257 */       } catch (FileNotFoundException e) {
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 262 */     return bmp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void delete(File file) {
/* 271 */     if (file != null && file.exists() && !file.delete()) {
/* 272 */       throw new RuntimeException(String.valueOf(file.getAbsolutePath()) + " doesn't be deleted!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean deleteDependon(String filepath) {
/* 283 */     if (TextUtils.isEmpty(filepath))
/* 284 */       return false; 
/* 285 */     File file = new File(filepath);
/* 286 */     int retryCount = 1, maxRetryCount = 0;
/* 287 */     maxRetryCount = (maxRetryCount < 1) ? 5 : maxRetryCount;
/* 288 */     boolean isDeleted = false;
/* 289 */     if (file != null)
/* 290 */       while (!isDeleted && retryCount <= maxRetryCount && file.isFile() && file.exists()) {
/* 291 */         if (!(isDeleted = file.delete())) {
/* 292 */           retryCount++;
/*     */         }
/*     */       }  
/* 295 */     return isDeleted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isFileExisted(String filepath) {
/* 304 */     if (TextUtils.isEmpty(filepath))
/* 305 */       return false; 
/* 306 */     File file = new File(filepath);
/* 307 */     if (file != null && file.exists()) {
/* 308 */       return true;
/*     */     }
/* 310 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isParentExist(File file) {
/* 319 */     if (file == null) {
/* 320 */       return false;
/*     */     }
/* 322 */     File parent = file.getParentFile();
/* 323 */     if (parent != null && !parent.exists()) {
/* 324 */       if (!file.exists() && !file.mkdirs()) {
/* 325 */         return false;
/*     */       }
/* 327 */       return true;
/*     */     } 
/*     */     
/* 330 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void makesureFileExist(String filePath) {
/* 339 */     if (filePath == null)
/*     */       return; 
/* 341 */     File file = new File(filePath);
/* 342 */     if (file != null && !file.exists() && 
/* 343 */       isParentExist(file)) {
/* 344 */       if (file.exists())
/* 345 */         delete(file); 
/*     */       try {
/* 347 */         file.createNewFile();
/* 348 */       } catch (IOException e) {
/* 349 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWifi(Context mContext) {
/* 361 */     ConnectivityManager connectivityManager = (ConnectivityManager)mContext
/* 362 */       .getSystemService(Context.CONNECTIVITY_SERVICE);
/* 363 */     NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
/* 364 */     if (activeNetInfo != null && activeNetInfo.getType() == 1) {
/* 365 */       return true;
/*     */     }
/* 367 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sd\\utils\ImageUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */