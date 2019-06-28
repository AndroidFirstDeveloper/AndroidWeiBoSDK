/*     */ package com.sina.weibo.sdk.api.share;
/*     */ 
/*     */ import android.app.AlertDialog;
/*     */ import android.app.Dialog;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ import android.content.Intent;
/*     */ import android.net.Uri;
/*     */ import com.sina.weibo.sdk.utils.Utility;
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
/*     */ public class WeiboDownloader
/*     */ {
/*     */   private static final String TITLE_CHINESS = "提示";
/*     */   private static final String PROMPT_CHINESS = "未安装微博客户端，是否现在去下载？";
/*     */   private static final String OK_CHINESS = "现在下载";
/*     */   private static final String CANCEL_CHINESS = "以后再说";
/*     */   private static final String TITLE_ENGLISH = "Notice";
/*     */   private static final String PROMPT_ENGLISH = "Sina Weibo client is not installed, download now?";
/*     */   private static final String OK_ENGLISH = "Download Now";
/*     */   private static final String CANCEL_ENGLISH = "Download Later";
/*     */   
/*     */   public static Dialog createDownloadConfirmDialog(final Context context, final IWeiboDownloadListener listener) {
/*  56 */     String title = "提示";
/*  57 */     String prompt = "未安装微博客户端，是否现在去下载？";
/*  58 */     String ok = "现在下载";
/*  59 */     String cancel = "以后再说";
/*     */     
/*  61 */     if (!Utility.isChineseLocale(context.getApplicationContext())) {
/*  62 */       title = "Notice";
/*  63 */       prompt = "Sina Weibo client is not installed, download now?";
/*  64 */       ok = "Download Now";
/*  65 */       cancel = "Download Later";
/*     */     } 
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
/*  83 */     return (new AlertDialog.Builder(context)).setMessage(prompt).setTitle(title).setPositiveButton(ok, new DialogInterface.OnClickListener()
/*     */         {
/*     */           public void onClick(DialogInterface dialog, int which)
/*     */           {
/*     */             if (listener != null) {
/*     */               listener.onCancel();
/*     */             }
/*     */           }
/*     */         }).setNegativeButton(cancel, new DialogInterface.OnClickListener()
/*     */         {
/*  93 */           public void onClick(DialogInterface dialog, int which) { WeiboDownloader.downloadWeibo(context); }
/*  94 */         }).create(); } private static void downloadWeibo(Context context) { Intent intent = new Intent();
/*  95 */     intent.setAction("android.intent.action.VIEW");
/*  96 */     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
/*  97 */     Uri url = Uri.parse("http://app.sina.cn/appdetail.php?appID=84560");
/*  98 */     intent.setData(url);
/*     */     try {
/* 100 */       context.startActivity(intent);
/* 101 */     } catch (Exception e) {
/* 102 */       e.printStackTrace();
/*     */     }  }
/*     */ 
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\WeiboDownloader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */