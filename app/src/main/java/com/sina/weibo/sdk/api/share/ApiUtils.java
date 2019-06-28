/*     */
package com.sina.weibo.sdk.api.share;
/*     */
/*     */

import android.content.ContentResolver;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.content.pm.PackageManager;
/*     */ import android.content.pm.ResolveInfo;
/*     */ import android.content.pm.Signature;
/*     */ import android.database.Cursor;
/*     */ import android.net.Uri;
/*     */ import android.text.TextUtils;
/*     */ import com.sina.weibo.sdk.utils.LogUtil;
/*     */ import com.sina.weibo.sdk.utils.MD5;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;

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
/*     */ public class ApiUtils
        /*     */ {
    /*     */   private static final String TAG = "ApiUtils";
    /*     */   public static final int BUILD_INT = 10350;
    /*     */   public static final int BUILD_INT_VER_2_2 = 10351;
    /*     */   public static final int BUILD_INT_VER_2_3 = 10352;
    /*     */   public static final int BUILD_INT_VER_2_5 = 10353;
    /*     */   private static final String WEIBO_IDENTITY_ACTION = "com.sina.weibo.action.sdkidentity";
    /*  63 */   private static final Uri WEIBO_NAME_URI = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    /*     */
    /*     */
    /*     */   private static final String WEIBO_SIGN = "18da2bf10352443a00a5e046d9fca6bd";

    /*     */
    /*     */
    /*     */
    /*     */   public static class WeiboInfo
            /*     */ {
        /*     */     public String packageName;
        /*     */
        /*     */
        /*     */     public int supportApi;

        /*     */
        /*     */
        /*     */
        /*  79 */
        public String toString() {
            return "WeiboInfo: PackageName = " + this.packageName + ", supportApi = " + this.supportApi;
        }
        /*     */
    }

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
    /*  91 */
    public static boolean isWeiboAppSupportAPI(int supportApi) {
        return (supportApi >= 10350);
    }

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
    public static WeiboInfo queryWeiboInfoByPackage(Context context, String packageName) {
        /* 103 */
        if (context == null || packageName == null) {
            /* 104 */
            return null;
            /*     */
        }
        /*     */
        /* 107 */
        WeiboInfo info = queryWeiboInfoByAsset(context, packageName);
        /* 108 */
        if (info != null) {
            /* 109 */
            return info;
            /*     */
        }
        /*     */
        /* 112 */
        info = queryWeiboInfoByProvider(context);
        /* 113 */
        if (info != null && packageName.equals(info.packageName)) {
            /* 114 */
            return info;
            /*     */
        }
        /*     */
        /* 117 */
        return null;
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public static WeiboInfo queryWeiboInfo(Context context) {
        /* 128 */
        WeiboInfo winfo = queryWeiboInfoByProvider(context);
        /* 129 */
        if (winfo == null) {
            /* 130 */
            return queryWeiboInfoByFile(context);
            /*     */
        }
        /*     */
        /* 133 */
        return winfo;
        /*     */
    }

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
    public static boolean validateWeiboSign(Context context, String pkgName) {
        /*     */
        PackageInfo packageInfo;
        /*     */
        try {
            /* 147 */
            packageInfo = context.getPackageManager().getPackageInfo(
                    /* 148 */           pkgName, PackageManager.GET_SIGNATURES);
            /* 149 */
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            /* 150 */
            return false;
            /*     */
        }
        /*     */
        /* 153 */
        return containSign(packageInfo.signatures, "18da2bf10352443a00a5e046d9fca6bd");
        /*     */
    }

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
    public static boolean containSign(Signature[] signatures, String destSign) {
        /* 165 */
        if (signatures != null && destSign != null) {
            Signature[] var5 = signatures;
            int var4 = signatures.length;

            for (int var3 = 0; var3 < var4; ++var3) {
                Signature signature = var5[var3];
                String s = MD5.hexdigest(signature.toByteArray());
                if (destSign.equals(s)) {
                    LogUtil.d("ApiUtils", "check pass");
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    private static WeiboInfo queryWeiboInfoByProvider(Context context) {
        /* 188 */
        ContentResolver cr = context.getContentResolver();
        /* 189 */
        Cursor cursor = null;
        /*     */
        /* 191 */
        try {
            cursor = cr.query(WEIBO_NAME_URI, null, null, null, null);
            /* 192 */
            if (cursor == null) {
                /* 193 */
                return null;
                /*     */
            }
            /*     */
            /* 196 */
            int supportApiIndex = cursor.getColumnIndex("support_api");
            /* 197 */
            int packageIndex = cursor.getColumnIndex("package");
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
        }
        /*     */
        /* 215 */ catch (Exception e)
            /* 216 */ {
            e.printStackTrace();
            /* 217 */
            LogUtil.e("ApiUtils", e.getMessage());
        }
        /*     */ finally
            /* 219 */ {
            if (cursor != null)
                /* 220 */ {
                cursor.close();
                /* 221 */
                cursor = null;
            }
        }
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }
        /*     */
        /*     */
        /* 224 */
        return null;
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    private static WeiboInfo queryWeiboInfoByFile(Context context) {
        /* 235 */
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        /* 236 */
        intent.addCategory("android.intent.category.DEFAULT");
        /* 237 */
        List<ResolveInfo> list = context.getPackageManager().queryIntentServices(intent, 0);
        /* 238 */
        if (list == null || list.isEmpty()) {
            /* 239 */
            return null;
            /*     */
        }
        /*     */
        /* 242 */
        for (ResolveInfo ri : list) {
            /* 243 */
            if (ri.serviceInfo == null ||
                    /* 244 */         ri.serviceInfo.applicationInfo == null ||
                    /* 245 */         ri.serviceInfo.applicationInfo.packageName == null ||
                    /* 246 */         ri.serviceInfo.applicationInfo.packageName.length() == 0) {
                /*     */
                continue;
                /*     */
            }
            /*     */
            /* 250 */
            String packageName = ri.serviceInfo.applicationInfo.packageName;
            /* 251 */
            WeiboInfo winfo = queryWeiboInfoByAsset(context, packageName);
            /* 252 */
            if (winfo != null) {
                /* 253 */
                return winfo;
                /*     */
            }
            /*     */
        }
        /*     */
        /* 257 */
        return null;
        /*     */
    }

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
    private static WeiboInfo queryWeiboInfoByAsset(Context context, String packageName) {
        /* 269 */
        if (context == null || packageName == null) {
            /* 270 */
            return null;
            /*     */
        }
        /*     */
        /* 273 */
        InputStream is = null;
        /*     */
        try {
            /* 275 */
            Context weiboContext =
                    /* 276 */         context.createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY);
            /*     */
            /* 278 */
            int bufferSize = 1024;
            /* 279 */
            byte[] buf = new byte[1024];
            /*     */
            /* 281 */
            is = weiboContext.getAssets().open("weibo_for_sdk.json");
            /* 282 */
            StringBuilder sbContent = new StringBuilder();
            /*     */
            int readNum;
            /* 284 */
            while ((readNum = is.read(buf, 0, 1024)) != -1) {
                /* 285 */
                sbContent.append(new String(buf, 0, readNum));
                /*     */
            }
            /*     */
            /* 288 */
            if (TextUtils.isEmpty(sbContent.toString()) || !validateWeiboSign(context, packageName)) {
                /* 289 */
                return null;
                /*     */
            }
            /*     */
            /* 292 */
            JSONObject json = new JSONObject(sbContent.toString());
            /* 293 */
            int supportApi = json.optInt("support_api", -1);
            /*     */
            /* 295 */
            WeiboInfo winfo = new WeiboInfo();
            /* 296 */
            winfo.packageName = packageName;
            /* 297 */
            winfo.supportApi = supportApi;
            /* 298 */
            return winfo;
            /*     */
        }
        /* 300 */ catch (PackageManager.NameNotFoundException e) {
            /* 301 */
            e.printStackTrace();
            /* 302 */
        } catch (IOException e) {
            /* 303 */
            e.printStackTrace();
            /* 304 */
        } catch (JSONException e) {
            /* 305 */
            e.printStackTrace();
            /* 306 */
        } catch (Exception e) {
            /* 307 */
            LogUtil.e("ApiUtils", e.getMessage());
            /*     */
        } finally {
            /* 309 */
            if (is != null) {
                /*     */
                try {
                    /* 311 */
                    is.close();
                    /* 312 */
                } catch (IOException iOException) {
                }
                /*     */
            }
            /*     */
        }
        /*     */
        /*     */
        /* 317 */
        return null;
        /*     */
    }
    /*     */
}


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\weibo\sdk\api\share\ApiUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */