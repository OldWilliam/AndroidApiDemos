package me.wx.demo.apidemos.View.Demo002;

import android.os.Environment;

/**
 * Created by ein on 2017/2/15.
 */

public class Constants {
    public static String getFullApkName(){
        return Environment.getExternalStorageDirectory() + "/inke.apk";
    }
}
