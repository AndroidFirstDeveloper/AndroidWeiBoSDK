/*    */ package com.sina.weibo.sdk.net;
/*    */ 
/*    */ import android.content.BroadcastReceiver;
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import android.database.Cursor;
/*    */ import android.net.Uri;
/*    */ import android.net.wifi.WifiInfo;
/*    */ import android.net.wifi.WifiManager;
/*    */ import org.apache.http.HttpHost;

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
/*    */ public class NetStateManager
/*    */ {
/*    */   private static Context mContext;
/* 40 */   public static NetState CUR_NETSTATE = NetState.Mobile;
/*    */   
/*    */   public enum NetState {
/* 43 */     Mobile, WIFI, NOWAY;
/*    */   }
/*    */   
/*    */   public class NetStateReceive
/*    */     extends BroadcastReceiver {
/*    */     public void onReceive(Context context, Intent intent) {
/* 49 */       mContext = context;
/* 50 */       if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
/* 51 */         WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
/* 52 */         WifiInfo info = wifiManager.getConnectionInfo();
/* 53 */         if (!wifiManager.isWifiEnabled() || -1 == info.getNetworkId()) {
/* 54 */           NetStateManager.CUR_NETSTATE = NetState.Mobile;
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static HttpHost getAPN() {
/* 66 */   HttpHost proxy = null;
/* 67 */     Uri uri = Uri.parse("content://telephony/carriers/preferapn");
/* 68 */     Cursor mCursor = null;
/* 69 */     if (mContext != null) {
/* 70 */       mCursor = mContext.getContentResolver().query(uri, null, null, null, null);
/*    */     }
/* 72 */     if (mCursor != null && mCursor.moveToFirst()) {
/*    */       
/* 74 */       String proxyStr = mCursor.getString(mCursor.getColumnIndex("proxy"));
/* 75 */       if (proxyStr != null && proxyStr.trim().length() > 0) {
/* 76 */         proxy = new HttpHost(proxyStr, 80);
/*    */       }
/* 78 */       mCursor.close();
/*    */     } 
/* 80 */     return proxy;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\net\NetStateManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */