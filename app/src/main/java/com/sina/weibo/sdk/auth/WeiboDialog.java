/*     */ package com.sina.weibo.sdk.auth;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.app.Dialog;
/*     */ import android.app.ProgressDialog;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Bundle;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.webkit.WebView;
/*     */ import android.webkit.WebViewClient;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.RelativeLayout;
/*     */ import com.sina.weibo.sdk.exception.WeiboAuthException;
/*     */ import com.sina.weibo.sdk.exception.WeiboDialogException;
/*     */ import com.sina.weibo.sdk.utils.LogUtil;
/*     */ import com.sina.weibo.sdk.utils.NetworkHelper;
/*     */ import com.sina.weibo.sdk.utils.ResourceManager;
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
/*     */ public class WeiboDialog
/*     */   extends Dialog
/*     */ {
/*     */   private static final String TAG = "WeiboDialog";
/*     */   private static final int WEBVIEW_CONTAINER_MARGIN_TOP = 25;
/*     */   private static final int WEBVIEW_MARGIN = 10;
/*     */   private Context mContext;
/*     */   private RelativeLayout mRootContainer;
/*     */   private RelativeLayout mWebViewContainer;
/*     */   private ProgressDialog mLoadingDlg;
/*     */   private WebView mWebView;
/*     */   private boolean mIsDetached = false;
/*     */   private String mAuthUrl;
/*     */   private WeiboAuthListener mListener;
/*     */   private WeiboAuth mWeibo;
/*  83 */   private static int theme = 16973840;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WeiboDialog(Context context, String authUrl, WeiboAuthListener listener, WeiboAuth weibo) {
/*  93 */     super(context, theme);
/*  94 */     this.mAuthUrl = authUrl;
/*  95 */     this.mListener = listener;
/*  96 */     this.mContext = context;
/*  97 */     this.mWeibo = weibo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBackPressed() {
/* 106 */     super.onBackPressed();
/*     */     
/* 108 */     if (this.mListener != null) {
/* 109 */       this.mListener.onCancel();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dismiss() {
/* 119 */     if (!this.mIsDetached) {
/* 120 */       if (this.mLoadingDlg != null && this.mLoadingDlg.isShowing()) {
/* 121 */         this.mLoadingDlg.dismiss();
/*     */       }
/*     */       
/* 124 */       super.dismiss();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onAttachedToWindow() {
/* 130 */     this.mIsDetached = false;
/* 131 */     super.onAttachedToWindow();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDetachedFromWindow() {
/* 139 */     if (this.mWebView != null) {
/* 140 */       this.mWebViewContainer.removeView(this.mWebView);
/* 141 */       this.mWebView.stopLoading();
/* 142 */       this.mWebView.removeAllViews();
/* 143 */       this.mWebView.destroy();
/* 144 */       this.mWebView = null;
/*     */     } 
/*     */     
/* 147 */     this.mIsDetached = true;
/* 148 */     super.onDetachedFromWindow();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCreate(Bundle savedInstanceState) {
/* 157 */     super.onCreate(savedInstanceState);
/*     */ 
/*     */     
/* 160 */     initWindow();
/*     */     
/* 162 */     initLoadingDlg();
/*     */     
/* 164 */     initWebView();
/*     */     
/* 166 */     initCloseButton();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initWindow() {
/* 173 */     requestWindowFeature(1);
/* 174 */     getWindow().setFeatureDrawableAlpha(0, 0);
/* 175 */     getWindow().setSoftInputMode(16);
/*     */     
/* 177 */     this.mRootContainer = new RelativeLayout(getContext());
/* 178 */     this.mRootContainer.setBackgroundColor(0);
/* 179 */     addContentView(this.mRootContainer, new ViewGroup.LayoutParams(-1, -1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initLoadingDlg() {
/* 187 */     this.mLoadingDlg = new ProgressDialog(getContext());
/*     */     
/* 189 */     this.mLoadingDlg.requestWindowFeature(1);
/*     */     
/* 191 */     this.mLoadingDlg.setMessage(ResourceManager.getString(this.mContext, 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"SetJavaScriptEnabled"})
/*     */   private void initWebView() {
/* 199 */     this.mWebViewContainer = new RelativeLayout(getContext());
/* 200 */     this.mWebView = new WebView(getContext());
/* 201 */     this.mWebView.getSettings().setJavaScriptEnabled(true);
/*     */     
/* 203 */     this.mWebView.getSettings().setSavePassword(false);
/* 204 */     this.mWebView.setWebViewClient(new WeiboWebViewClient());
/* 205 */     this.mWebView.requestFocus();
/* 206 */     this.mWebView.setScrollBarStyle(View.OVER_SCROLL_ALWAYS);
/* 207 */     this.mWebView.setVisibility(View.INVISIBLE);
/*     */ 
/*     */     
/* 210 */     NetworkHelper.clearCookies(this.mContext, this.mAuthUrl);
/* 211 */     this.mWebView.loadUrl(this.mAuthUrl);
/*     */     
/* 213 */     RelativeLayout.LayoutParams webViewContainerLayout = new RelativeLayout.LayoutParams(
/* 214 */         -1, -1);
/*     */     
/* 216 */     RelativeLayout.LayoutParams webviewLayout = new RelativeLayout.LayoutParams(
/* 217 */         -1, -1);
/*     */ 
/*     */     
/* 220 */     DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
/* 221 */     float density = dm.density;
/* 222 */     int margin = (int)(10.0F * density);
/* 223 */     webviewLayout.setMargins(margin, margin, margin, margin);
/* 224 */     Drawable background = ResourceManager.getNinePatchDrawable(this.mContext, 1);
/*     */     
/* 226 */     this.mWebViewContainer.setBackgroundDrawable(background);
/*     */ 
/*     */     
/* 229 */     this.mWebViewContainer.addView(this.mWebView, webviewLayout);
/* 230 */     this.mWebViewContainer.setGravity(17);
/*     */     
/* 232 */     Drawable drawable = ResourceManager.getDrawable(this.mContext, 2);
/* 233 */     int width = drawable.getIntrinsicWidth() / 2 + 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 242 */     webViewContainerLayout.setMargins(width, (int)(25.0F * dm.density), width, width);
/* 243 */     this.mRootContainer.addView(this.mWebViewContainer, webViewContainerLayout);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initCloseButton() {
/* 250 */     ImageView closeImage = new ImageView(this.mContext);
/* 251 */     Drawable drawable = ResourceManager.getDrawable(this.mContext, 2);
/* 252 */     closeImage.setImageDrawable(drawable);
/* 253 */     closeImage.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View v) {
/* 256 */             WeiboDialog.this.dismiss();
/*     */             
/* 258 */             if (WeiboDialog.this.mListener != null) {
/* 259 */               WeiboDialog.this.mListener.onCancel();
/*     */             }
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 266 */     RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
/* 267 */         -2, -2);
/* 268 */     RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)this.mWebViewContainer.getLayoutParams();
/* 269 */     layoutParams.leftMargin = params.leftMargin - drawable.getIntrinsicWidth() / 2 + 5;
/* 270 */     layoutParams.topMargin = params.topMargin - drawable.getIntrinsicHeight() / 2 + 5;
/* 271 */     this.mRootContainer.addView(closeImage, layoutParams);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class WeiboWebViewClient
/*     */     extends WebViewClient
/*     */   {
/*     */     private boolean isCallBacked = false;
/*     */ 
/*     */     
/*     */     public boolean shouldOverrideUrlLoading(WebView view, String url) {
/* 283 */       LogUtil.i("WeiboDialog", "load URL: " + url);
/*     */ 
/*     */       
/* 286 */       if (url.startsWith("sms:")) {
/* 287 */         Intent sendIntent = new Intent("android.intent.action.VIEW");
/* 288 */         sendIntent.putExtra("address", url.replace("sms:", ""));
/* 289 */         sendIntent.setType("vnd.android-dir/mms-sms");
/* 290 */         WeiboDialog.this.getContext().startActivity(sendIntent);
/* 291 */         return true;
/*     */       } 
/* 293 */       return super.shouldOverrideUrlLoading(view, url);
/*     */     }
/*     */ 
/*     */     
/*     */     public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
/* 298 */       LogUtil.d("WeiboDialog", "onReceivedError: errorCode = " + errorCode + 
/* 299 */           ", description = " + description + 
/* 300 */           ", failingUrl = " + failingUrl);
/* 301 */       super.onReceivedError(view, errorCode, description, failingUrl);
/*     */       
/* 303 */       if (WeiboDialog.this.mListener != null) {
/* 304 */         WeiboDialog.this.mListener.onWeiboException(new WeiboDialogException(description, errorCode, failingUrl));
/*     */       }
/* 306 */       WeiboDialog.this.dismiss();
/*     */     }
/*     */ 
/*     */     
/*     */     public void onPageStarted(WebView view, String url, Bitmap favicon) {
/* 311 */       LogUtil.d("WeiboDialog", "onPageStarted URL: " + url);
/* 312 */       if (url.startsWith(WeiboDialog.this.mWeibo.getAuthInfo().getRedirectUrl()) && 
/* 313 */         !this.isCallBacked) {
/* 314 */         this.isCallBacked = true;
/* 315 */         WeiboDialog.this.handleRedirectUrl(url);
/* 316 */         view.stopLoading();
/* 317 */         WeiboDialog.this.dismiss();
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 322 */       super.onPageStarted(view, url, favicon);
/*     */       
/* 324 */       if (!WeiboDialog.this.mIsDetached && WeiboDialog.this.mLoadingDlg != null && !WeiboDialog.this.mLoadingDlg.isShowing()) {
/* 325 */         WeiboDialog.this.mLoadingDlg.show();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void onPageFinished(WebView view, String url) {
/* 331 */       LogUtil.d("WeiboDialog", "onPageFinished URL: " + url);
/* 332 */       super.onPageFinished(view, url);
/* 333 */       if (!WeiboDialog.this.mIsDetached && WeiboDialog.this.mLoadingDlg != null) {
/* 334 */         WeiboDialog.this.mLoadingDlg.dismiss();
/*     */       }
/*     */       
/* 337 */       if (WeiboDialog.this.mWebView != null) {
/* 338 */         WeiboDialog.this.mWebView.setVisibility(View.VISIBLE);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private WeiboWebViewClient() {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleRedirectUrl(String url) {
/* 355 */     Bundle values = Utility.parseUrl(url);
/*     */     
/* 357 */     String errorType = values.getString("error");
/* 358 */     String errorCode = values.getString("error_code");
/* 359 */     String errorDescription = values.getString("error_description");
/*     */     
/* 361 */     if (errorType == null && errorCode == null) {
/* 362 */       this.mListener.onComplete(values);
/*     */     } else {
/* 364 */       this.mListener.onWeiboException(
/* 365 */           new WeiboAuthException(errorCode, errorType, errorDescription));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\auth\WeiboDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */