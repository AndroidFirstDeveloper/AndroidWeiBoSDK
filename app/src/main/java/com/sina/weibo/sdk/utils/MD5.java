/*    */ package com.sina.weibo.sdk.utils;
/*    */ 
/*    */ import java.security.MessageDigest;
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
/*    */ public class MD5
/*    */ {
/*    */   private static final char[] hexDigits = { 
/* 30 */       '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*    */   
/*    */   public static String hexdigest(String string) {
/* 33 */     String s = null;
/*    */     
/*    */     try {
/* 36 */       s = hexdigest(string.getBytes());
/* 37 */     } catch (Exception e) {
/* 38 */       e.printStackTrace();
/*    */     } 
/* 40 */     return s;
/*    */   }
/*    */   
/*    */   public static String hexdigest(byte[] bytes) {
/* 44 */     String s = null;
/*    */     try {
/* 46 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 47 */       md.update(bytes);
/* 48 */       byte[] tmp = md.digest();
/* 49 */       char[] str = new char[32];
/* 50 */       int k = 0;
/* 51 */       for (int i = 0; i < 16; i++) {
/* 52 */         byte byte0 = tmp[i];
/* 53 */         str[k++] = hexDigits[byte0 >>> 4 & 0xF];
/* 54 */         str[k++] = hexDigits[byte0 & 0xF];
/*    */       } 
/* 56 */       s = new String(str);
/* 57 */     } catch (Exception e) {
/* 58 */       e.printStackTrace();
/*    */     } 
/* 60 */     return s;
/*    */   }
/*    */ 
/*    */   
/* 64 */   public static void main(String[] args) { System.out.println(hexdigest("c")); }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sd\\utils\MD5.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */