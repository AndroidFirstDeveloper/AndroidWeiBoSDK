package com.sina.weibo.sdk.api.share;

import android.content.Intent;
import com.sina.weibo.sdk.exception.WeiboShareException;

public interface IWeiboShareAPI {
  boolean isWeiboAppInstalled();
  
  boolean isWeiboAppSupportAPI();
  
  int getWeiboAppSupportAPI();
  
  boolean registerApp();
  
  boolean handleWeiboResponse(Intent paramIntent, IWeiboHandler.Response paramResponse);
  
  boolean handleWeiboRequest(Intent paramIntent, IWeiboHandler.Request paramRequest);
  
  boolean launchWeibo();
  
  boolean sendRequest(BaseRequest paramBaseRequest);
  
  boolean sendResponse(BaseResponse paramBaseResponse);
  
  void registerWeiboDownloadListener(IWeiboDownloadListener paramIWeiboDownloadListener);
  
  boolean checkEnvironment(boolean paramBoolean) throws WeiboShareException;
}


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\IWeiboShareAPI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */