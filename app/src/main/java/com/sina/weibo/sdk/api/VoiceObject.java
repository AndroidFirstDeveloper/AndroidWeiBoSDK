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
/*     */ public class VoiceObject
/*     */   extends BaseMediaObject
/*     */ {
/*     */   public static final String EXTRA_KEY_DEFAULTTEXT = "extra_key_defaulttext";
/*     */   public String defaultText;
/*     */   public String h5Url;
/*     */   public String dataUrl;
/*     */   public String dataHdUrl;
/*     */   public int duration;
/*     */   
/*  53 */   public static final Parcelable.Creator<VoiceObject> CREATOR = new Parcelable.Creator<VoiceObject>()
/*     */     {
/*  55 */       public VoiceObject createFromParcel(Parcel in) { return new VoiceObject(in); }
/*     */ 
/*     */ 
/*     */       
/*  59 */       public VoiceObject[] newArray(int size) { return new VoiceObject[size]; }
/*     */     };
/*     */ 
/*     */   
/*     */   public VoiceObject() {}
/*     */ 
/*     */   
/*     */   public VoiceObject(Parcel in) {
/*  67 */     super(in);
/*  68 */     this.h5Url = in.readString();
/*  69 */     this.dataUrl = in.readString();
/*  70 */     this.dataHdUrl = in.readString();
/*  71 */     this.duration = in.readInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToParcel(Parcel dest, int flags) {
/*  76 */     super.writeToParcel(dest, flags);
/*  77 */     dest.writeString(this.h5Url);
/*  78 */     dest.writeString(this.dataUrl);
/*  79 */     dest.writeString(this.dataHdUrl);
/*  80 */     dest.writeInt(this.duration);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkArgs() {
/*  85 */     if (!super.checkArgs()) {
/*  86 */       return false;
/*     */     }
/*  88 */     if (this.dataUrl != null && this.dataUrl.length() > 512) {
/*  89 */       LogUtil.e("Weibo.VoiceObject", "checkArgs fail, dataUrl is invalid");
/*  90 */       return false;
/*     */     } 
/*  92 */     if (this.dataHdUrl != null && this.dataHdUrl.length() > 512) {
/*  93 */       LogUtil.e("Weibo.VoiceObject", "checkArgs fail, dataHdUrl is invalid");
/*  94 */       return false;
/*     */     } 
/*  96 */     if (this.duration <= 0) {
/*  97 */       LogUtil.e("Weibo.VoiceObject", "checkArgs fail, duration is invalid");
/*  98 */       return false;
/*     */     } 
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public int getObjType() { return 6; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseMediaObject toExtraMediaObject(String str) {
/* 110 */     if (!TextUtils.isEmpty(str)) {
/*     */       try {
/* 112 */         JSONObject json = new JSONObject(str);
/* 113 */         this.defaultText = json.optString("extra_key_defaulttext");
/* 114 */       } catch (JSONException jSONException) {}
/*     */     }
/*     */     
/* 117 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String toExtraMediaString() {
/*     */     try {
/* 123 */       JSONObject json = new JSONObject();
/* 124 */       if (!TextUtils.isEmpty(this.defaultText)) {
/* 125 */         json.put("extra_key_defaulttext", this.defaultText);
/*     */       }
/* 127 */       return json.toString();
/* 128 */     } catch (JSONException jSONException) {
/*     */       
/* 130 */       return "";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\VoiceObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */