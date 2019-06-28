package com.sina.weibo.sdk.net;

import com.sina.weibo.sdk.exception.WeiboException;

public interface RequestListener {
  void onComplete(String paramString);
  
  void onWeiboException(WeiboException paramWeiboException);
}


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\net\RequestListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */