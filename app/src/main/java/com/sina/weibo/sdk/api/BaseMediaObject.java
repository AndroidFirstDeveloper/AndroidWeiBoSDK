/*     */ package com.sina.weibo.sdk.api;
/*     */ 
/*     */ import android.graphics.Bitmap;
/*     */ import android.os.Parcel;
/*     */ import android.os.Parcelable;
/*     */ import com.sina.weibo.sdk.utils.LogUtil;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
import java.io.OutputStream;

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
/*     */ public abstract class BaseMediaObject
/*     */   implements Parcelable
/*     */ {
/*     */   public static final int MEDIA_TYPE_TEXT = 1;
/*     */   public static final int MEDIA_TYPE_IMAGE = 2;
/*     */   public static final int MEDIA_TYPE_MUSIC = 3;
/*     */   public static final int MEDIA_TYPE_VIDEO = 4;
/*     */   public static final int MEDIA_TYPE_WEBPAGE = 5;
/*     */   public static final int MEDIA_TYPE_VOICE = 6;
/*     */   public static final int MEDIA_TYPE_CMD = 7;
/*     */   public String actionUrl;
/*     */   public String schema;
/*     */   public String identify;
/*     */   public String title;
/*     */   public String description;
/*     */   public byte[] thumbData;
/*     */   
/*     */   public BaseMediaObject() {}
/*     */   
/*     */   public BaseMediaObject(Parcel in) {
/*  77 */     this.actionUrl = in.readString();
/*  78 */     this.schema = in.readString();
/*  79 */     this.identify = in.readString();
/*  80 */     this.title = in.readString();
/*  81 */     this.description = in.readString();
/*  82 */     this.thumbData = in.createByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setThumbImage(Bitmap bitmap) {
/*  92 */     ByteArrayOutputStream os = null;
/*     */     try {
/*  94 */       os = new ByteArrayOutputStream();
/*  95 */       bitmap.compress(Bitmap.CompressFormat.JPEG, 85, os);
/*  96 */       this.thumbData = os.toByteArray();
/*  97 */     } catch (Exception e) {
/*  98 */       e.printStackTrace();
/*  99 */       LogUtil.e("Weibo.BaseMediaObject", "put thumb failed");
/*     */     } finally {
/*     */       try {
/* 102 */         if (os != null) {
/* 103 */           os.close();
/*     */         }
/* 105 */       } catch (IOException e) {
/* 106 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public int describeContents() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToParcel(Parcel dest, int flags) {
/* 124 */     dest.writeString(this.actionUrl);
/* 125 */     dest.writeString(this.schema);
/* 126 */     dest.writeString(this.identify);
/* 127 */     dest.writeString(this.title);
/* 128 */     dest.writeString(this.description);
/* 129 */     dest.writeByteArray(this.thumbData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getObjType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean checkArgs() {
/* 149 */     if (this.actionUrl == null || this.actionUrl.length() > 512) {
/* 150 */       LogUtil.e("Weibo.BaseMediaObject", "checkArgs fail, actionUrl is invalid");
/* 151 */       return false;
/*     */     } 
/* 153 */     if (this.identify == null || this.identify.length() > 512) {
/* 154 */       LogUtil.e("Weibo.BaseMediaObject", "checkArgs fail, identify is invalid");
/* 155 */       return false;
/*     */     } 
/* 157 */     if (this.thumbData == null || this.thumbData.length > 32768) {
/* 158 */       LogUtil.e("Weibo.BaseMediaObject", "checkArgs fail, thumbData is invalid,size is " + (
/* 159 */           (this.thumbData != null) ? this.thumbData.length : -1) + "! more then 32768.");
/* 160 */       return false;
/*     */     } 
/* 162 */     if (this.title == null || this.title.length() > 512) {
/* 163 */       LogUtil.e("Weibo.BaseMediaObject", "checkArgs fail, title is invalid");
/* 164 */       return false;
/*     */     } 
/* 166 */     if (this.description == null || this.description.length() > 1024) {
/* 167 */       LogUtil.e("Weibo.BaseMediaObject", "checkArgs fail, description is invalid");
/* 168 */       return false;
/*     */     } 
/* 170 */     return true;
/*     */   }
/*     */   
/*     */   protected abstract BaseMediaObject toExtraMediaObject(String paramString);
/*     */   
/*     */   protected abstract String toExtraMediaString();
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\BaseMediaObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */