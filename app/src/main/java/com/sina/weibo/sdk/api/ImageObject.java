/*     */ package com.sina.weibo.sdk.api;
/*     */ 
/*     */ import android.graphics.Bitmap;
/*     */ import android.os.Parcel;
/*     */ import android.os.Parcelable;
/*     */ import com.sina.weibo.sdk.utils.LogUtil;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
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
/*     */ public class ImageObject
/*     */   extends BaseMediaObject
/*     */ {
/*     */   private static final int DATA_SIZE = 2097152;
/*     */   public byte[] imageData;
/*     */   public String imagePath;
/*     */   
/*  45 */   public static final Parcelable.Creator<ImageObject> CREATOR = new Parcelable.Creator<ImageObject>()
/*     */     {
/*  47 */       public ImageObject createFromParcel(Parcel in) { return new ImageObject(in); }
/*     */ 
/*     */ 
/*     */       
/*  51 */       public ImageObject[] newArray(int size) { return new ImageObject[size]; }
/*     */     };
/*     */ 
/*     */   
/*     */   public ImageObject() {}
/*     */ 
/*     */   
/*     */   public ImageObject(Parcel in) {
/*  59 */     this.imageData = in.createByteArray();
/*  60 */     this.imagePath = in.readString();
/*     */   }
/*     */   
/*     */   public final void setImageObject(Bitmap bitmap) {
/*  64 */   ByteArrayOutputStream  os = null;
/*     */     try {
/*  66 */       os = new ByteArrayOutputStream();
/*  67 */       bitmap.compress(Bitmap.CompressFormat.JPEG, 85, os);
/*  68 */       this.imageData = os.toByteArray();
/*  69 */     } catch (Exception e) {
/*  70 */       e.printStackTrace();
/*  71 */       LogUtil.e("Weibo.ImageObject", "put thumb failed");
/*     */     } finally {
/*     */       try {
/*  74 */         if (os != null) {
/*  75 */           os.close();
/*     */         }
/*  77 */       } catch (IOException e) {
/*  78 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public int describeContents() { return 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToParcel(Parcel dest, int flags) {
/*  90 */     dest.writeByteArray(this.imageData);
/*  91 */     dest.writeString(this.imagePath);
/*     */   }
/*     */   
/*     */   public boolean checkArgs() {
/*  95 */     if (this.imageData == null && this.imagePath == null) {
/*  96 */       LogUtil.e("Weibo.ImageObject", "imageData and imagePath are null");
/*  97 */       return false;
/*     */     } 
/*  99 */     if (this.imageData != null && this.imageData.length > 2097152) {
/* 100 */       LogUtil.e("Weibo.ImageObject", "imageData is too large");
/* 101 */       return false;
/*     */     } 
/* 103 */     if (this.imagePath != null && this.imagePath.length() > 512) {
/* 104 */       LogUtil.e("Weibo.ImageObject", "imagePath is too length");
/* 105 */       return false;
/*     */     } 
/* 107 */     if (this.imagePath != null) {
/* 108 */       File file = new File(this.imagePath);
/*     */       try {
/* 110 */         if (!file.exists() || file.length() == 0L || file.length() > 10485760L) {
/* 111 */           LogUtil.e("Weibo.ImageObject", 
/* 112 */               "checkArgs fail, image content is too large or not exists");
/* 113 */           return false;
/*     */         } 
/* 115 */       } catch (SecurityException e) {
/* 116 */         LogUtil.e("Weibo.ImageObject", 
/* 117 */             "checkArgs fail, image content is too large or not exists");
/* 118 */         return false;
/*     */       } 
/*     */     } 
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 126 */   public int getObjType() { return 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   protected BaseMediaObject toExtraMediaObject(String str) { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   protected String toExtraMediaString() { return ""; }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\ImageObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */