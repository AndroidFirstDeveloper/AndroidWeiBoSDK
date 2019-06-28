/*    */ package com.sina.weibo.sdk.api.share;
/*    */ 
/*    */ import android.content.Context;
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
/*    */ public class WeiboShareSDK
/*    */ {
/* 44 */   public static IWeiboShareAPI createWeiboAPI(Context context, String appKey, boolean isDownloadWeibo) { return new WeiboShareAPIImpl(context, appKey, isDownloadWeibo); }
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
/* 57 */   public static IWeiboShareAPI createWeiboAPI(Context context, String appKey) { return createWeiboAPI(context, appKey, true); }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\WeiboShareSDK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */