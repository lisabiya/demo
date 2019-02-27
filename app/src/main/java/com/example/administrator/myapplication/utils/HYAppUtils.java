package com.example.administrator.myapplication.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.WifiManager;
import androidx.core.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class HYAppUtils {

    /**
     * 获取versionname
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 获取versioncode
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int versioncode = -1;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }


    /**
     * 获取手机IMEI唯一设备码
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        return tm.getDeviceId();
    }

    /**
     * 获取设备硬件地址
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String macAddress = wm.getConnectionInfo().getMacAddress();
        return macAddress;
    }

    /**
     * 申请授权 备注
     *
     * @param context
     * @param name
     * @return
     */
    public static String getRemark(Context context, String name, String loginName) {
        /*TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String tmDevice = "" + tm.getDeviceId();*/
        String deviceId = HYAppUtils.getMacAddress(context);
        String remarkStr = "设备名称:Android 设备型号:" + android.os.Build.MODEL
                + " 操作系统:" + android.os.Build.VERSION.RELEASE + " 序列号/唯一码:"
                + deviceId + "申请授权的用户名:" + loginName + " 姓名：" + name;
        return remarkStr;
    }


    /**
     * 打开乱键盘
     *
     * @param editText
     */
    public static void openSoftInput(EditText editText) {
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) editText
                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 关闭软键盘
     */
    public static void hideSoftInput(EditText editText, Context context) {
        ((InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 获取软键盘状态
     *
     * @param v
     * @param context
     * @return
     */
    public static Boolean getKeyBoardState(View v, Context context) {
        v.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return inputMethodManager.showSoftInput(v,
                InputMethodManager.SHOW_FORCED);

    }

    /**
     * 播放本地视频
     *
     * @param context
     * @param uri
     */
    public static void playVedio(Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "video/*");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    /**
     * 复制剪切板
     *
     * @param text
     * @param context
     */
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static void copyString(String text, Context context) {
        ClipboardManager cmd = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmd.setText(text);
    }

    /**
     * 拨打手机号
     *
     * @param context
     * @param tel
     */
    public static void Call(Context context, String tel) {
        if (!TextUtils.isEmpty(tel)) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + tel));
            // 方法内部会自动为Inter 添加类别:android.intent。category.DEFAULT
            context.startActivity(intent);
        }
    }

    /**
     * 适配科学计数
     *
     * @param content
     * @return
     */
    public static String parseScientificCount(String content) {
        try {
            Double d = new Double(content);
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setGroupingUsed(false);
            String count = numberFormat.format(d) + "";
            if (count.endsWith(".0")) {
                count = count.replace(".0", "");
            }
            return count;
        } catch (Exception e) {
            return content;
        }

    }

    /**
     * view->bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap getViewBitmap(View view) {
        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        view.clearFocus();
        view.setPressed(false);
        boolean willNotCache = view.willNotCacheDrawing();
        view.setWillNotCacheDrawing(false);
        int color = view.getDrawingCacheBackgroundColor();
        view.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            view.destroyDrawingCache();
        }
        view.buildDrawingCache();
        Bitmap cacheBitmap = view.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        view.destroyDrawingCache();
        view.setWillNotCacheDrawing(willNotCache);
        view.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    public static String parseNearCateName(String name) {
        if (TextUtils.isEmpty(name) || !name.contains("*")) {
            return name;
        }
        return name.substring(0, name.indexOf("*"));

    }


    /**
     * 获取系统语言
     *
     * @param context
     * @return
     */
    public static String getAppLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        return language;
    }

    /**
     * 精确到小数点后六 位
     *
     * @param point
     * @return
     */
    public static double double2six(double point) {
        DecimalFormat fs = new DecimalFormat("0.000000");
        String g = fs.format(point);
        return Double.parseDouble(g);
    }

    /**
     * http 自定义userAgent
     *
     * @param context
     * @return
     */
    public static String getHttpUserAgent(Context context) {
        String userAgent = "Android	"
                + getAppVersionName(context)
                + "("
                + android.os.Build.MODEL
                + " ;"
                + android.os.Build.VERSION.RELEASE
                + " ;"
                + context.getResources().getConfiguration().locale
                .getLanguage() + " )";
        if (android.os.Build.VERSION.SDK_INT < 21) {
            // 5.0以下版本需要转码
            try {
                userAgent = new String(userAgent.getBytes("UTF-8"),
                        "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return userAgent;
    }


    /**
     * 提交数据
     *
     * @param context
     * @return
     */
    public static String getUpdateMessage(Context context) {
        String userAgent = " 应用名称：" + context.getResources().getString(R.string.app_name)
                + " 应用版本：" + getAppVersionName(context)
                + " 设备名称：Android"
                + " 设备型号：" + android.os.Build.MODEL
                + " 操作系统：" + android.os.Build.VERSION.RELEASE
                + " 序列号/唯一码：" + getIMEI(context);

        return userAgent;
    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    private static String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        Log.e("end", file.getName() + "---" + file.getPath());
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") return type;
        Log.e("end", end);
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    /**
     * 打开文件
     *
     * @param file
     */
    public static void openFile(Context context, File file) {
        try {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //设置intent的Action属性
            intent.setAction(Intent.ACTION_VIEW);
            //获取文件file的MIME类型
            String type = getMIMEType(file);
            Log.e("type", type);
            //设置intent的data和Type属性。
            intent.setDataAndType(Uri.fromFile(file), type);
            //跳转
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "无法打开此文件,请安装第三方应用", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    //建立一个MIME类型与文件后缀名的匹配表
    private static final String[][] MIME_MapTable = {
            //{后缀名，    MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".prop", "text/plain"},
            {".rar", "application/x-rar-compressed"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            //{".xml",    "text/xml"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/zip"},
            {"", "*/*"}
    };

    /**
     * 发邮件
     *
     * @param uri
     */
//    public static void sendMail(Context context, Uri uri) {
//        if (uri == null) {
//            return;
//        }
//        String[] tos = {" "};
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("message/rfc822"); // 设置邮件格式
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_EMAIL, tos);
//        intent.putExtra(Intent.EXTRA_SUBJECT, "截图_" + HYDateUtils.getDate2String(HYDateUtils.getCurrectTime())); // 主题
//        intent.putExtra(Intent.EXTRA_STREAM, uri);
//        context.startActivity(Intent.createChooser(intent, "请选择邮件类应用"));
//    }
}
