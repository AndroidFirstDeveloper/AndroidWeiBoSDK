/*     */ package com.sina.weibo.sdk.utils;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
/*     */ import android.net.NetworkInfo;
/*     */ import android.net.wifi.ScanResult;
/*     */ import android.net.wifi.WifiConfiguration;
/*     */ import android.net.wifi.WifiInfo;
/*     */ import android.net.wifi.WifiManager;
/*     */ import android.webkit.CookieManager;
/*     */ import android.webkit.CookieSyncManager;
/*     */ import java.util.List;
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
/*     */ public class NetworkHelper
/*     */ {
/*     */   public static boolean hasInternetPermission(Context context) {
/*  51 */     if (context != null) {
/*  52 */       return (context.checkCallingOrSelfPermission("android.permission.INTERNET") == PackageManager.PERMISSION_GRANTED);
/*     */     }
/*     */ 
/*     */     
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNetworkAvailable(Context context) {
/*  67 */     if (context != null) {
/*  68 */       NetworkInfo info = getActiveNetworkInfo(context);
/*  69 */       return (info != null && info.isConnected());
/*     */     } 
/*     */     
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWifiValid(Context context) {
/*  83 */     if (context != null) {
/*  84 */       NetworkInfo info = getActiveNetworkInfo(context);
/*     */       
/*  86 */       return (info != null && 
/*  87 */         1 == info.getType() && 
/*  88 */         info.isConnected());
/*     */     } 
/*     */     
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isMobileNetwork(Context context) {
/* 102 */     if (context != null) {
/* 103 */       NetworkInfo info = getActiveNetworkInfo(context);
/*     */       
/* 105 */       if (info == null) {
/* 106 */         return false;
/*     */       }
/*     */       
/* 109 */       return (info != null && 
/* 110 */         info.getType() == 0 && 
/* 111 */         info.isConnected());
/*     */     } 
/*     */     
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NetworkInfo getActiveNetworkInfo(Context context) {
/* 125 */     ConnectivityManager connectivity = 
/* 126 */       (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
/* 127 */     return connectivity.getActiveNetworkInfo();
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
/*     */   public static NetworkInfo getNetworkInfo(Context context, int networkType) {
/* 150 */     ConnectivityManager connectivityManager = 
/* 151 */       (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
/* 152 */     return connectivityManager.getNetworkInfo(networkType);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getNetworkType(Context context) {
/* 174 */     if (context != null) {
/* 175 */       NetworkInfo info = getActiveNetworkInfo(context);
/*     */       
/* 177 */       return (info == null) ? -1 : info.getType();
/*     */     } 
/*     */     
/* 180 */     return -1;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getWifiState(Context context) {
/* 196 */     WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
/*     */     
/* 198 */     if (wifi == null) {
/* 199 */       return 4;
/*     */     }
/*     */     
/* 202 */     return wifi.getWifiState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NetworkInfo.DetailedState getWifiConnectivityState(Context context) {
/* 213 */     NetworkInfo networkInfo = getNetworkInfo(context, 1);
/* 214 */     return (networkInfo == null) ? NetworkInfo.DetailedState.FAILED : networkInfo.getDetailedState();
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
/*     */   
/*     */   public static boolean wifiConnection(Context context, String wifiSSID, String password) {
/* 228 */     boolean isConnection = false;
/* 229 */     WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
/* 230 */     String strQuotationSSID = "\"" + wifiSSID + "\"";
/*     */     
/* 232 */     WifiInfo wifiInfo = wifi.getConnectionInfo();
/* 233 */     if (wifiInfo != null && (
/* 234 */       wifiSSID.equals(wifiInfo.getSSID()) || strQuotationSSID.equals(wifiInfo.getSSID()))) {
/* 235 */       isConnection = true;
/*     */     } else {
/* 237 */       List<ScanResult> scanResults = wifi.getScanResults();
/* 238 */       if (scanResults != null && scanResults.size() != 0) {
/* 239 */         for (int nAllIndex = scanResults.size() - 1; nAllIndex >= 0; nAllIndex--) {
/* 240 */           String strScanSSID = ((ScanResult)scanResults.get(nAllIndex)).SSID;
/* 241 */           if (wifiSSID.equals(strScanSSID) || strQuotationSSID.equals(strScanSSID)) {
/* 242 */             WifiConfiguration config = new WifiConfiguration();
/* 243 */             config.SSID = strQuotationSSID;
/* 244 */             config.preSharedKey = "\"" + password + "\"";
/* 245 */             config.status = 2;
/*     */             
/* 247 */             int nAddWifiId = wifi.addNetwork(config);
/* 248 */             isConnection = wifi.enableNetwork(nAddWifiId, false);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 255 */     return isConnection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearCookies(Context context, String url) {
/* 264 */     CookieSyncManager.createInstance(context);
/* 265 */     CookieManager cookieManager = CookieManager.getInstance();
/* 266 */     cookieManager.setAcceptCookie(true);
/* 267 */     cookieManager.removeSessionCookie();
/* 268 */     cookieManager.removeAllCookie();
/* 269 */     CookieSyncManager.getInstance().sync();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sd\\utils\NetworkHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */