//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import com.sina.weibo.sdk.api.share.ApiUtils;
import com.sina.weibo.sdk.api.share.ApiUtils.WeiboInfo;

public class SecurityHelper {
    private static final String WEIBO_MD5_SIGNATURE = "18da2bf10352443a00a5e046d9fca6bd";

    public SecurityHelper() {
    }

    public static boolean validateAppSignatureForIntent(Context context, Intent intent) {
        PackageManager pkgMgr = context.getPackageManager();
        if (pkgMgr == null) {
            return false;
        } else {
            ResolveInfo resolveInfo = pkgMgr.resolveActivity(intent, 0);
            if (resolveInfo == null) {
                return false;
            } else {
                String packageName = resolveInfo.activityInfo.packageName;

                try {
                    PackageInfo packageInfo = pkgMgr.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
                    return containSign(packageInfo.signatures, "18da2bf10352443a00a5e046d9fca6bd");
                } catch (NameNotFoundException var6) {
                    var6.printStackTrace();
                } catch (Exception var7) {
                    var7.printStackTrace();
                }

                return false;
            }
        }
    }

    public static boolean checkResponseAppLegal(Context context, Intent intent) {
        WeiboInfo winfo = ApiUtils.queryWeiboInfo(context);
        if (winfo != null && winfo.supportApi <= 10352) {
            return true;
        } else if (winfo == null) {
            return true;
        } else {
            String appPackage = intent != null ? intent.getStringExtra("_weibo_appPackage") : null;
            return appPackage != null && intent.getStringExtra("_weibo_transaction") != null && ApiUtils.validateWeiboSign(context, appPackage);
        }
    }

    public static boolean containSign(Signature[] signatures, String destSign) {
        if (signatures != null && destSign != null) {
            Signature[] var5 = signatures;
            int var4 = signatures.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                Signature signature = var5[var3];
                String s = MD5.hexdigest(signature.toByteArray());
                if (destSign.equals(s)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }
}
