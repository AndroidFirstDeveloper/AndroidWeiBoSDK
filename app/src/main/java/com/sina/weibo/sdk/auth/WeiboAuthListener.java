package com.sina.weibo.sdk.auth;

import android.os.Bundle;
import com.sina.weibo.sdk.exception.WeiboException;

public interface WeiboAuthListener {
  void onComplete(Bundle paramBundle);
  
  void onWeiboException(WeiboException paramWeiboException);
  
  void onCancel();
}


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\auth\WeiboAuthListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */