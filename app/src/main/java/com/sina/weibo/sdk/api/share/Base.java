package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;

public abstract class Base {
  public String transaction;
  
  public abstract void toBundle(Bundle paramBundle);
  
  public abstract void fromBundle(Bundle paramBundle);
  
  public abstract int getType();
  
  abstract boolean check(Context paramContext, VersionCheckHandler paramVersionCheckHandler);
}


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\Base.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */