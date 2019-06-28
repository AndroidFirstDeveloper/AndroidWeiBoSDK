/*     */ package com.sina.weibo.sdk.api;
/*     */ 
/*     */ import android.os.Parcel;
/*     */ import android.os.Parcelable;
/*     */ import android.text.TextUtils;
/*     */ import com.sina.weibo.sdk.utils.LogUtil;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
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
/*     */ public class VideoObject
/*     */   extends BaseMediaObject
/*     */ {
/*     */   public static final String EXTRA_KEY_DEFAULTTEXT = "extra_key_defaulttext";
/*     */   public String defaultText;
/*     */   public String h5Url;
/*     */   public String dataUrl;
/*     */   public String dataHdUrl;
/*     */   public int duration;
/*     */   
/*  54 */   public static final Parcelable.Creator<VideoObject> CREATOR = new Parcelable.Creator<VideoObject>()
/*     */     {
/*  56 */       public VideoObject createFromParcel(Parcel in) { return new VideoObject(in); }
/*     */ 
/*     */ 
/*     */       
/*  60 */       public VideoObject[] newArray(int size) { return new VideoObject[size]; }
/*     */     };
/*     */ 
/*     */   
/*     */   public VideoObject() {}
/*     */ 
/*     */   
/*     */   public VideoObject(Parcel in) {
/*  68 */     super(in);
/*  69 */     this.h5Url = in.readString();
/*  70 */     this.dataUrl = in.readString();
/*  71 */     this.dataHdUrl = in.readString();
/*  72 */     this.duration = in.readInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToParcel(Parcel dest, int flags) {
/*  77 */     super.writeToParcel(dest, flags);
/*  78 */     dest.writeString(this.h5Url);
/*  79 */     dest.writeString(this.dataUrl);
/*  80 */     dest.writeString(this.dataHdUrl);
/*  81 */     dest.writeInt(this.duration);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkArgs() {
/*  86 */     if (!super.checkArgs()) {
/*  87 */       return false;
/*     */     }
/*  89 */     if (this.dataUrl != null && this.dataUrl.length() > 512) {
/*  90 */       LogUtil.e("Weibo.VideoObject", "checkArgs fail, dataUrl is invalid");
/*  91 */       return false;
/*     */     } 
/*  93 */     if (this.dataHdUrl != null && this.dataHdUrl.length() > 512) {
/*  94 */       LogUtil.e("Weibo.VideoObject", "checkArgs fail, dataHdUrl is invalid");
/*  95 */       return false;
/*     */     } 
/*  97 */     if (this.duration <= 0) {
/*  98 */       LogUtil.e("Weibo.VideoObject", "checkArgs fail, duration is invalid");
/*  99 */       return false;
/*     */     } 
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public int getObjType() { return 4; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseMediaObject toExtraMediaObject(String str) {
/* 111 */     if (!TextUtils.isEmpty(str)) {
/*     */       try {
/* 113 */         JSONObject json = new JSONObject(str);
/* 114 */         this.defaultText = json.optString("extra_key_defaulttext");
/* 115 */       } catch (JSONException jSONException) {}
/*     */     }
/*     */     
/* 118 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String toExtraMediaString() {
/*     */     try {
/* 124 */       JSONObject json = new JSONObject();
/* 125 */       if (!TextUtils.isEmpty(this.defaultText)) {
/* 126 */         json.put("extra_key_defaulttext", this.defaultText);
/*     */       }
/* 128 */       return json.toString();
/* 129 */     } catch (JSONException jSONException) {
/*     */       
/* 131 */       return "";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\VideoObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */