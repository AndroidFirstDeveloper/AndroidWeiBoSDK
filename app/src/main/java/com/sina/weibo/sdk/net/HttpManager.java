/*     */ package com.sina.weibo.sdk.net;
/*     */ 
/*     */ import android.graphics.Bitmap;
/*     */ import com.sina.weibo.sdk.exception.WeiboException;
/*     */ import com.sina.weibo.sdk.exception.WeiboHttpException;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ import java.security.KeyManagementException;
/*     */ import java.security.KeyStore;
/*     */ import java.security.KeyStoreException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.UnrecoverableKeyException;
/*     */ import java.util.Set;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.TrustManagerFactory;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.HttpVersion;
/*     */ import org.apache.http.StatusLine;
/*     */ import org.apache.http.client.HttpClient;
/*     */ import org.apache.http.client.methods.HttpDelete;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpPost;
/*     */ import org.apache.http.conn.scheme.PlainSocketFactory;
/*     */ import org.apache.http.conn.scheme.Scheme;
/*     */ import org.apache.http.conn.scheme.SchemeRegistry;
/*     */ import org.apache.http.conn.ssl.SSLSocketFactory;
/*     */ import org.apache.http.entity.ByteArrayEntity;
/*     */ import org.apache.http.impl.client.DefaultHttpClient;
/*     */ import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
/*     */ import org.apache.http.params.BasicHttpParams;
/*     */ import org.apache.http.params.HttpConnectionParams;
/*     */ import org.apache.http.params.HttpProtocolParams;
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
/*     */ class HttpManager
/*     */ {
/*  74 */   private static final String BOUNDARY = getBoundry();
/*  75 */   private static final String MP_BOUNDARY = "--" + BOUNDARY;
/*  76 */   private static final String END_MP_BOUNDARY = "--" + BOUNDARY + "--";
/*     */ 
/*     */   
/*     */   private static final String MULTIPART_FORM_DATA = "multipart/form-data";
/*     */ 
/*     */   
/*     */   private static final String HTTP_METHOD_POST = "POST";
/*     */ 
/*     */   
/*     */   private static final String HTTP_METHOD_GET = "GET";
/*     */ 
/*     */   
/*     */   private static final int CONNECTION_TIMEOUT = 5000;
/*     */   
/*     */   private static final int SOCKET_TIMEOUT = 20000;
/*     */   
/*     */   private static final int BUFFER_SIZE = 8192;
/*     */   
/*     */   private static SSLSocketFactory sSSLSocketFactory;
/*     */ 
/*     */   
/*     */   public static String openUrl(String url, String method, WeiboParameters params) throws WeiboException {
/*  98 */     HttpResponse response = requestHttpExecute(url, method, params);
/*  99 */     return readRsponse(response);
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
/*     */   private static HttpResponse requestHttpExecute(String url, String method, WeiboParameters params) {
/* 113 */     HttpResponse response = null;
/*     */     
/*     */     try {
/* 116 */       HttpClient client = getNewHttpClient();
/* 117 */       client.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
/*     */       
/* 119 */       HttpDelete httpDelete = null;
/* 120 */       ByteArrayOutputStream baos = null;
/*     */ 
/*     */       
/* 123 */       if (method.equals("GET")) {
/* 124 */         url = String.valueOf(url) + "?" + params.encodeUrl();
/* 125 */         HttpGet httpGet = new HttpGet(url);
/*     */       }
/* 127 */       else if (method.equals("POST")) {
/* 128 */         HttpPost post = new HttpPost(url);
/* 129 */         HttpPost httpPost = post;
/*     */         
/* 131 */         baos = new ByteArrayOutputStream();
/* 132 */         if (params.hasBinaryData()) {
/* 133 */           post.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
/* 134 */           buildParams(baos, params);
/*     */         } else {
/* 136 */           Object value = params.get("content-type");
/* 137 */           if (value != null && value instanceof String) {
/* 138 */             params.remove("content-type");
/* 139 */             post.setHeader("Content-Type", (String)value);
/*     */           } else {
/* 141 */             post.setHeader("Content-Type", "application/x-www-form-urlencoded");
/*     */           } 
/*     */ 
/*     */           
/* 145 */           String postParam = params.encodeUrl();
/* 146 */           baos.write(postParam.getBytes("UTF-8"));
/*     */         } 
/* 148 */         post.setEntity(new ByteArrayEntity(baos.toByteArray()));
/* 149 */         baos.close();
/*     */       }
/* 151 */       else if (method.equals("DELETE")) {
/* 152 */         httpDelete = new HttpDelete(url);
/*     */       } 
/*     */ 
/*     */       
/* 156 */       response = client.execute(httpDelete);
/* 157 */       StatusLine status = response.getStatusLine();
/* 158 */       int statusCode = status.getStatusCode();
/*     */ 
/*     */       
/* 161 */       if (statusCode != 200) {
/* 162 */         String result = readRsponse(response);
/* 163 */         throw new WeiboHttpException(result, statusCode);
/*     */       } 
/* 165 */     } catch (IOException e) {
/* 166 */       throw new WeiboException(e);
/*     */     } 
/*     */     
/* 169 */     return response;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static HttpClient getNewHttpClient() {
/*     */     try {
/* 177 */       BasicHttpParams basicHttpParams = new BasicHttpParams();
/* 178 */       HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
/* 179 */       HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
/*     */       
/* 181 */       SchemeRegistry registry = new SchemeRegistry();
/* 182 */       registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
/* 183 */       registry.register(new Scheme("https", getSSLSocketFactory(), 'ƻ'));
/*     */       
/* 185 */       ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, registry);
/* 186 */       HttpConnectionParams.setConnectionTimeout(basicHttpParams, 5000);
/* 187 */       HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
/* 188 */       return new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
/*     */     }
/* 190 */     catch (Exception e) {
/* 191 */       return new DefaultHttpClient();
/*     */     } 
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
/*     */   private static void buildParams(OutputStream baos, WeiboParameters params) throws WeiboException {
/*     */     try {
/* 205 */       Set<String> keys = params.keySet();
/*     */ 
/*     */       
/* 208 */       for (String key : keys) {
/* 209 */         Object value = params.get(key);
/* 210 */         if (value instanceof String) {
/* 211 */           StringBuilder sb = new StringBuilder(100);
/* 212 */           sb.setLength(0);
/* 213 */           sb.append(MP_BOUNDARY).append("\r\n");
/* 214 */           sb.append("content-disposition: form-data; name=\"").append(key).append("\"\r\n\r\n");
/* 215 */           sb.append(params.get(key)).append("\r\n");
/*     */           
/* 217 */           baos.write(sb.toString().getBytes());
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 222 */       for (String key : keys) {
/* 223 */         Object value = params.get(key);
/* 224 */         if (value instanceof Bitmap) {
/* 225 */           StringBuilder sb = new StringBuilder();
/* 226 */           sb.append(MP_BOUNDARY).append("\r\n");
/* 227 */           sb.append("content-disposition: form-data; name=\"").append(key).append("\"; filename=\"file\"\r\n");
/* 228 */           sb.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
/* 229 */           baos.write(sb.toString().getBytes());
/*     */           
/* 231 */           Bitmap bmp = (Bitmap)value;
/* 232 */           ByteArrayOutputStream stream = new ByteArrayOutputStream();
/* 233 */           bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
/* 234 */           byte[] bytes = stream.toByteArray();
/*     */           
/* 236 */           baos.write(bytes);
/* 237 */           baos.write("\r\n".getBytes()); continue;
/* 238 */         }  if (value instanceof ByteArrayOutputStream) {
/* 239 */           StringBuilder sb = new StringBuilder();
/* 240 */           sb.append(MP_BOUNDARY).append("\r\n");
/* 241 */           sb.append("content-disposition: form-data; name=\"").append(key).append("\"; filename=\"file\"\r\n");
/* 242 */           sb.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
/* 243 */           baos.write(sb.toString().getBytes());
/*     */           
/* 245 */           ByteArrayOutputStream stream = (ByteArrayOutputStream)value;
/* 246 */           baos.write(stream.toByteArray());
/* 247 */           baos.write("\r\n".getBytes());
/* 248 */           stream.close();
/*     */         } 
/*     */       } 
/* 251 */       baos.write(("\r\n" + END_MP_BOUNDARY).getBytes());
/* 252 */     } catch (IOException e) {
/* 253 */       throw new WeiboException(e);
/*     */     } 
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
/*     */   private static String readRsponse(HttpResponse response) throws WeiboException {
/* 266 */     if (response == null) {
/* 267 */       return null;
/*     */     }
/*     */     
/* 270 */     HttpEntity entity = response.getEntity();
/* 271 */    InputStream inputStream = null;
/* 272 */     ByteArrayOutputStream content = new ByteArrayOutputStream();
/*     */     try {
/* 274 */       inputStream = entity.getContent();
/* 275 */       Header header = response.getFirstHeader("Content-Encoding");
/* 276 */       if (header != null && header.getValue().toLowerCase().indexOf("gzip") > -1) {
/* 277 */         inputStream = new GZIPInputStream(inputStream);
/*     */       }
/*     */       
/* 280 */       int readBytes = 0;
/* 281 */       byte[] buffer = new byte[8192];
/* 282 */       while ((readBytes = inputStream.read(buffer)) != -1) {
/* 283 */         content.write(buffer, 0, readBytes);
/*     */       }
/*     */       
/* 286 */       return new String(content.toByteArray(), "UTF-8");
/* 287 */     } catch (IOException e) {
/* 288 */       throw new WeiboException(e);
/*     */     } finally {
/* 290 */       if (inputStream != null) {
/*     */         try {
/* 292 */           inputStream.close();
/* 293 */         } catch (IOException e) {
/* 294 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getBoundry() {
/* 304 */   StringBuffer  sb = new StringBuffer();
/* 305 */     for (int t = 1; t < 12; t++) {
/* 306 */       long time = System.currentTimeMillis() + t;
/* 307 */       if (time % 3L == 0L) {
/* 308 */         sb.append((char)(int)time % '\t');
/* 309 */       } else if (time % 3L == 1L) {
/* 310 */         sb.append((char)(int)(65L + time % 26L));
/*     */       } else {
/* 312 */         sb.append((char)(int)(97L + time % 26L));
/*     */       } 
/*     */     } 
/* 315 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private static SSLSocketFactory getSSLSocketFactory() {
/* 320 */     if (sSSLSocketFactory == null) {
/* 321 */     InputStream  caInput = null;
/*     */ 
/*     */       
/*     */       try {
/* 325 */         caInput = HttpManager.class.getResourceAsStream("cacert.cer");
/*     */ 
/*     */         
/* 328 */         String keyStoreType = KeyStore.getDefaultType();
/* 329 */         KeyStore keyStore = KeyStore.getInstance(keyStoreType);
/* 330 */         keyStore.load(caInput, null);
/* 331 */         sSSLSocketFactory = new MySSLSocketFactory(keyStore);
/* 332 */       } catch (Exception e) {
/* 333 */         e.printStackTrace();
/*     */         
/* 335 */         sSSLSocketFactory = MySSLSocketFactory.getSocketFactory();
/*     */       } finally {
/* 337 */         if (caInput != null) {
/*     */           try {
/* 339 */             caInput.close();
/* 340 */           } catch (IOException iOException) {}
/*     */         }
/*     */       } 
/*     */     } 
/* 344 */     return sSSLSocketFactory;
/*     */   }
/*     */   
/*     */   private static class MySSLSocketFactory extends SSLSocketFactory {
/* 348 */     SSLContext sslContext = SSLContext.getInstance("TLS");
/*     */ 
/*     */     
/*     */     public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
/* 352 */       super(truststore);
/*     */       
/* 354 */       TrustManager[] tms = createTrustManagers(truststore);
/*     */       
/* 356 */       this.sslContext.init(null, tms, null);
/*     */     }
/*     */ 
/*     */     
/*     */     private TrustManager[] createTrustManagers(KeyStore keystore) throws KeyStoreException, NoSuchAlgorithmException {
/* 361 */       if (keystore == null) {
/* 362 */         throw new IllegalArgumentException("Keystore may not be null");
/*     */       }
/* 364 */       TrustManagerFactory tmfactory = TrustManagerFactory.getInstance(
/* 365 */           TrustManagerFactory.getDefaultAlgorithm());
/* 366 */       tmfactory.init(keystore);
/* 367 */       return tmfactory.getTrustManagers();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 373 */     public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException { return this.sslContext.getSocketFactory().createSocket(socket, host, port, autoClose); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 378 */     public Socket createSocket() throws IOException { return this.sslContext.getSocketFactory().createSocket(); }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\net\HttpManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */