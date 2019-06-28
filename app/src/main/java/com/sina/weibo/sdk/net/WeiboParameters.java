/*     */ package com.sina.weibo.sdk.net;
/*     */ 
/*     */ import android.graphics.Bitmap;
/*     */ import android.text.TextUtils;
/*     */ import com.sina.weibo.sdk.utils.LogUtil;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Set;
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
/*     */ public class WeiboParameters
/*     */ {
/*     */   private static final String DEFAULT_CHARSET = "UTF-8";
/*  39 */   private LinkedHashMap<String, Object> mParams = new LinkedHashMap();
/*     */ 
/*     */   
/*  42 */   public LinkedHashMap<String, Object> getParams() { return this.mParams; }
/*     */ 
/*     */ 
/*     */   
/*  46 */   public void setParams(LinkedHashMap<String, Object> params) { this.mParams = params; }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  51 */   public void add(String key, String val) { this.mParams.put(key, val); }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  56 */   public void add(String key, int value) { this.mParams.put(key, String.valueOf(value)); }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  61 */   public void add(String key, long value) { this.mParams.put(key, String.valueOf(value)); }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  66 */   public void add(String key, Object val) { this.mParams.put(key, val.toString()); }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public void put(String key, String val) { this.mParams.put(key, val); }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public void put(String key, int value) { this.mParams.put(key, String.valueOf(value)); }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void put(String key, long value) { this.mParams.put(key, String.valueOf(value)); }
/*     */ 
/*     */ 
/*     */   
/*  82 */   public void put(String key, Bitmap bitmap) { this.mParams.put(key, bitmap); }
/*     */ 
/*     */ 
/*     */   
/*  86 */   public void put(String key, Object val) { this.mParams.put(key, val.toString()); }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public Object get(String key) { return this.mParams.get(key); }
/*     */ 
/*     */   
/*     */   public void remove(String key) {
/*  94 */     if (this.mParams.containsKey(key)) {
/*  95 */       this.mParams.remove(key);
/*  96 */       this.mParams.remove(this.mParams.get(key));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 101 */   public Set<String> keySet() { return this.mParams.keySet(); }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public boolean containsKey(String key) { return this.mParams.containsKey(key); }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public boolean containsValue(String value) { return this.mParams.containsValue(value); }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public int size() { return this.mParams.size(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public String encodeUrl() {
/* 118 */     StringBuilder sb = new StringBuilder();
/* 119 */     boolean first = true;
/* 120 */     for (String key : this.mParams.keySet()) {
/* 121 */       if (first) {
/* 122 */         first = false;
/*     */       } else {
/* 124 */         sb.append("&");
/*     */       } 
/*     */       
/* 127 */       Object value = this.mParams.get(key);
/* 128 */       if (value instanceof String) {
/* 129 */         String param = (String)value;
/* 130 */         if (!TextUtils.isEmpty(param)) {
/*     */           try {
/* 132 */             sb.append(String.valueOf(URLEncoder.encode(key, "UTF-8")) + "=" + 
/* 133 */                 URLEncoder.encode(param, "UTF-8"));
/* 134 */           } catch (UnsupportedEncodingException e) {
/* 135 */             e.printStackTrace();
/*     */           } 
/*     */         }
/* 138 */         LogUtil.i("encodeUrl", sb.toString());
/*     */       } 
/*     */     } 
/*     */     
/* 142 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public boolean hasBinaryData() {
/* 146 */     Set<String> keys = this.mParams.keySet();
/* 147 */     for (String key : keys) {
/* 148 */       Object value = this.mParams.get(key);
/* 149 */       if (value instanceof java.io.ByteArrayOutputStream || 
/* 150 */         value instanceof Bitmap) {
/* 151 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 155 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\net\WeiboParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */