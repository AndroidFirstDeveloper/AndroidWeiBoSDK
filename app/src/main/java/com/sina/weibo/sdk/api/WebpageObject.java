/*    */ package com.sina.weibo.sdk.api;
/*    */ 
/*    */ import android.os.Parcel;
/*    */ import android.os.Parcelable;
/*    */ import android.text.TextUtils;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
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
/*    */ public class WebpageObject
/*    */   extends BaseMediaObject
/*    */ {
/*    */   public static final String EXTRA_KEY_DEFAULTTEXT = "extra_key_defaulttext";
/*    */   public String defaultText;
/*    */   
/* 39 */   public static final Parcelable.Creator<WebpageObject> CREATOR = new Parcelable.Creator<WebpageObject>()
/*    */     {
/* 41 */       public WebpageObject createFromParcel(Parcel in) { return new WebpageObject(in); }
/*    */ 
/*    */ 
/*    */       
/* 45 */       public WebpageObject[] newArray(int size) { return new WebpageObject[size]; }
/*    */     };
/*    */ 
/*    */ 
/*    */   
/*    */   public WebpageObject() {}
/*    */ 
/*    */   
/* 53 */   public WebpageObject(Parcel in) { super(in); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public void writeToParcel(Parcel dest, int flags) { super.writeToParcel(dest, flags); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkArgs() {
/* 63 */     if (!super.checkArgs()) {
/* 64 */       return false;
/*    */     }
/* 66 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 71 */   public int getObjType() { return 5; }
/*    */ 
/*    */ 
/*    */   
/*    */   protected BaseMediaObject toExtraMediaObject(String str) {
/* 76 */     if (!TextUtils.isEmpty(str)) {
/*    */       try {
/* 78 */         JSONObject json = new JSONObject(str);
/* 79 */         this.defaultText = json.optString("extra_key_defaulttext");
/* 80 */       } catch (JSONException jSONException) {}
/*    */     }
/*    */     
/* 83 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String toExtraMediaString() {
/*    */     try {
/* 89 */       JSONObject json = new JSONObject();
/* 90 */       if (!TextUtils.isEmpty(this.defaultText)) {
/* 91 */         json.put("extra_key_defaulttext", this.defaultText);
/*    */       }
/* 93 */       return json.toString();
/* 94 */     } catch (JSONException jSONException) {
/*    */       
/* 96 */       return "";
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\WebpageObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */