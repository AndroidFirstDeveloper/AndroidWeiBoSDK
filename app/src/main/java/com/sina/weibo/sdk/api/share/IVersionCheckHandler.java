package com.sina.weibo.sdk.api.share;

import android.content.Context;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;

interface IVersionCheckHandler {
  boolean check(Context paramContext, WeiboMessage paramWeiboMessage);
  
  boolean check(Context paramContext, WeiboMultiMessage paramWeiboMultiMessage);
}


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\IVersionCheckHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */