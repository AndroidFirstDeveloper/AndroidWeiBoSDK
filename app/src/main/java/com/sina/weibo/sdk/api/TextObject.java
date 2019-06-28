/*    */ package com.sina.weibo.sdk.api;
/*    */ 
/*    */ import android.os.Parcel;
/*    */ import android.os.Parcelable;
/*    */ import com.sina.weibo.sdk.utils.LogUtil;
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
/*    */ public class TextObject
/*    */   extends BaseMediaObject
/*    */ {
/*    */   public String text;
/*    */   
/*    */   public TextObject() {}
/*    */   
/* 39 */   public TextObject(Parcel in) { this.text = in.readString(); }
/*    */ 
/*    */   
/* 42 */   public static final Parcelable.Creator<TextObject> CREATOR = new Parcelable.Creator<TextObject>()
/*    */     {
/* 44 */       public TextObject createFromParcel(Parcel in) { return new TextObject(in); }
/*    */ 
/*    */ 
/*    */       
/* 48 */       public TextObject[] newArray(int size) { return new TextObject[size]; }
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public int describeContents() { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public void writeToParcel(Parcel dest, int flags) { dest.writeString(this.text); }
/*    */ 
/*    */   
/*    */   public boolean checkArgs() {
/* 63 */     if (this.text == null || this.text.length() == 0 || this.text.length() > 1024) {
/* 64 */       LogUtil.e("Weibo.TextObject", "checkArgs fail, text is invalid");
/* 65 */       return false;
/*    */     } 
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 72 */   public int getObjType() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 77 */   protected BaseMediaObject toExtraMediaObject(String str) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 82 */   protected String toExtraMediaString() { return ""; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\TextObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */