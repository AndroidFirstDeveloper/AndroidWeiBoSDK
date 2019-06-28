/*    */ package com.sina.weibo.sdk.api;
/*    */ 
/*    */ import android.os.Parcel;
/*    */ import android.os.Parcelable;
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
/*    */ public class CmdObject
/*    */   extends BaseMediaObject
/*    */ {
/*    */   public String cmd;
/*    */   public static final String CMD_HOME = "home";
/*    */   
/* 34 */   public static final Parcelable.Creator<CmdObject> CREATOR = new Parcelable.Creator<CmdObject>()
/*    */     {
/* 36 */       public CmdObject createFromParcel(Parcel in) { return new CmdObject(in); }
/*    */ 
/*    */ 
/*    */       
/* 40 */       public CmdObject[] newArray(int size) { return new CmdObject[size]; }
/*    */     };
/*    */ 
/*    */ 
/*    */   
/*    */   public CmdObject() {}
/*    */ 
/*    */   
/* 48 */   public CmdObject(Parcel in) { this.cmd = in.readString(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 53 */   public int describeContents() { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public void writeToParcel(Parcel dest, int flags) { dest.writeString(this.cmd); }
/*    */ 
/*    */   
/*    */   public boolean checkArgs() {
/* 62 */     if (this.cmd == null || this.cmd.length() == 0 || this.cmd.length() > 1024) {
/* 63 */       return false;
/*    */     }
/* 65 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 70 */   public int getObjType() { return 7; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   protected BaseMediaObject toExtraMediaObject(String str) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 80 */   protected String toExtraMediaString() { return ""; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\CmdObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */