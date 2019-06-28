package com.sina.weibo.sdk.api.share;

public interface IWeiboHandler {
  public static interface Request {
    void onRequest(BaseRequest param1BaseRequest);
  }
  
  public static interface Response {
    void onResponse(BaseResponse param1BaseResponse);
  }
}


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\IWeiboHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */