//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;

public class ResourceManager {
    private static final String TAG = ResourceManager.class.getName();
    public static final int dimen_dialog_left_margin = 1;
    public static final int dimen_dialog_top_margin = 2;
    public static final int dimen_dialog_right_margin = 3;
    public static final int dimen_dialog_bottom_margin = 4;
    public static final int DIALOG_LEFT_MARGIN = 10;
    public static final int DIALOG_TOP_MARGIN = 30;
    public static final int DIALOG_RIGHT_MARGIN = 10;
    public static final int DIALOG_BOTTOM_MARGIN = 10;
    private static final SparseIntArray sLayoutMap = new SparseIntArray();
    public static final int drawable_dialog_background = 1;
    public static final int drawable_dialog_close_button = 2;
    private static final String DIALOG_BACKGROUND_IMAGE_NAME = "weibosdk_dialog_bg.9.png";
    private static final String DIALOG_CLOSE_BUTTON_IMAGE_NAME = "ic_com_sina_weibo_sdk_close.png";
    private static final String DRAWABLE = "drawable";
    private static final String DRAWABLE_LDPI = "drawable-ldpi";
    private static final String DRAWABLE_MDPI = "drawable-mdpi";
    private static final String DRAWABLE_HDPI = "drawable-hdpi";
    private static final String DRAWABLE_XHDPI = "drawable-xhdpi";
    private static final String DRAWABLE_XXHDPI = "drawable-xxhdpi";
    private static final String[] PRE_INSTALL_DRAWBLE_PATHS;
    private static final SparseArray<String> sDrawableMap;
    public static final int string_loading = 1;
    public static final int string_network_not_available = 2;
    private static final String LOADING_EN = "Loading...";
    private static final String LOADING_ZH_CN = "加载中...";
    private static final String LOADING_ZH_TW = "載入中...";
    private static final String NETWORK_NOT_AVAILABLE_EN = "Network is not available";
    private static final String NETWORK_NOT_AVAILABLE_ZH_CN = "无法连接到网络，请检查网络配置";
    private static final String NETWORK_NOT_AVAILABLE_ZH_TW = "無法連接到網络，請檢查網络配置";
    private static final HashMap<Locale, SparseArray<String>> sLanguageMap;

    static {
        sLayoutMap.put(1, 10);
        sLayoutMap.put(2, 30);
        sLayoutMap.put(3, 10);
        sLayoutMap.put(4, 10);
        PRE_INSTALL_DRAWBLE_PATHS = new String[]{"drawable-xxhdpi", "drawable-xhdpi", "drawable-hdpi", "drawable-mdpi", "drawable-ldpi", "drawable"};
        sDrawableMap = new SparseArray();
        sDrawableMap.put(1, "weibosdk_dialog_bg.9.png");
        sDrawableMap.put(2, "ic_com_sina_weibo_sdk_close.png");
        sLanguageMap = new HashMap();
        SparseArray<String> stringMap = new SparseArray();
        stringMap.put(1, "加载中...");
        stringMap.put(2, "无法连接到网络，请检查网络配置");
        sLanguageMap.put(Locale.SIMPLIFIED_CHINESE, stringMap);
        stringMap = new SparseArray();
        stringMap.put(1, "載入中...");
        stringMap.put(2, "無法連接到網络，請檢查網络配置");
        sLanguageMap.put(Locale.TRADITIONAL_CHINESE, stringMap);
        stringMap = new SparseArray();
        stringMap.put(1, "Loading...");
        stringMap.put(2, "Network is not available");
        sLanguageMap.put(Locale.ENGLISH, stringMap);
    }

    public ResourceManager() {
    }

    public static String getString(Context context, int id) {
        Locale locale = getLanguage();
        SparseArray<String> stringMap = (SparseArray)sLanguageMap.get(locale);
        return (String)stringMap.get(id, "");
    }

    public static Drawable getDrawable(Context context, int id) {
        String path = getAppropriatePathOfDrawable(context, (String)sDrawableMap.get(id, ""));
        return getDrawableFromAssert(context, path, false);
    }

    public static Drawable getNinePatchDrawable(Context context, int id) {
        String path = getAppropriatePathOfDrawable(context, (String)sDrawableMap.get(id, ""));
        return getDrawableFromAssert(context, path, true);
    }

    public static int getDimensionPixelSize(int id) {
        return sLayoutMap.get(id, 0);
    }

    public static Locale getLanguage() {
        Locale locale = Locale.getDefault();
        return !Locale.SIMPLIFIED_CHINESE.equals(locale) && !Locale.TRADITIONAL_CHINESE.equals(locale) ? Locale.ENGLISH : locale;
    }

    public static String getAppropriatePathOfDrawable(Context context, String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            LogUtil.e(TAG, "id is NOT correct!");
            return null;
        } else {
            String pathPrefix = getCurrentDpiFolder(context);
            String path = pathPrefix + "/" + fileName;
            LogUtil.i(TAG, "Maybe the appropriate path: " + path);
            if (isFileExisted(context, path)) {
                return path;
            } else {
                LogUtil.d(TAG, "Not the correct path, we need to find one...");
//                int ix = false;
                boolean bFound = false;

                for(int ix = 0; ix < PRE_INSTALL_DRAWBLE_PATHS.length; ++ix) {
                    if (!bFound) {
                        if (pathPrefix.equals(PRE_INSTALL_DRAWBLE_PATHS[ix])) {
                            bFound = true;
                            LogUtil.i(TAG, "Have Find index: " + ix + ", " + PRE_INSTALL_DRAWBLE_PATHS[ix]);
                        }
                    } else {
                        path = PRE_INSTALL_DRAWBLE_PATHS[ix] + "/" + fileName;
                        if (isFileExisted(context, path)) {
                            return path;
                        }
                    }
                }

                LogUtil.e(TAG, "Not find the appropriate path for drawable");
                return null;
            }
        }
    }

    public static Drawable getDrawableFromAssert(Context context, String relativePath, boolean isNinePatch) {
        Drawable rtDrawable = null;
        AssetManager asseets = context.getAssets();
        InputStream is = null;

        try {
            is = asseets.open(relativePath);
            if (is != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                if (isNinePatch) {
                    Configuration config = context.getResources().getConfiguration();
                    Resources res = new Resources(context.getAssets(), metrics, config);
                    rtDrawable = new NinePatchDrawable(res, bitmap, bitmap.getNinePatchChunk(), new Rect(0, 0, 0, 0), (String)null);
                } else {
                    bitmap.setDensity(metrics.densityDpi);
                    rtDrawable = new BitmapDrawable(context.getResources(), bitmap);
                }
            }
        } catch (IOException var18) {
            var18.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }

                is = null;
            }

        }

        return (Drawable)rtDrawable;
    }

    private static boolean isFileExisted(Context context, String filePath) {
        if (context != null && !TextUtils.isEmpty(filePath)) {
            AssetManager asseets = context.getAssets();
            InputStream is = null;

            try {
                is = asseets.open(filePath);
                LogUtil.d(TAG, "file [" + filePath + "] existed");
                return true;
            } catch (IOException var13) {
                LogUtil.d(TAG, "file [" + filePath + "] NOT existed");
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException var12) {
                    var12.printStackTrace();
                    is = null;
                }

            }

            return false;
        } else {
            return false;
        }
    }

    private static String getCurrentDpiFolder(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int density = dm.densityDpi;
        if (density <= 120) {
            return "drawable-ldpi";
        } else if (density > 120 && density <= 160) {
            return "drawable-mdpi";
        } else if (density > 160 && density <= 240) {
            return "drawable-hdpi";
        } else {
            return density > 240 && density <= 320 ? "drawable-xhdpi" : "drawable-xxhdpi";
        }
    }

    private static View extractView(Context context, String fileName, ViewGroup root) throws Exception {
        XmlResourceParser parser = context.getAssets().openXmlResourceParser(fileName);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(parser, root);
    }

    private static Drawable extractDrawable(Context context, String fileName) throws Exception {
        InputStream inputStream = context.getAssets().open(fileName);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        TypedValue value = new TypedValue();
        value.density = dm.densityDpi;
        Drawable drawable = Drawable.createFromResourceStream(context.getResources(), value, inputStream, fileName);
        inputStream.close();
        return drawable;
    }
}
