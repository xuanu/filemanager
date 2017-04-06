package apk.zeffect.cn.filemanager.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

public class OpenFiles {
    public static Intent openFile(Context pContext, String filePath) {

        File file = new File(filePath);
        if (!file.exists()) return null;
        /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            return getAudioFileIntent(pContext, filePath);
        } else if (end.equals("3gp") || end.equals("mp4")) {
            return getAudioFileIntent(pContext, filePath);
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp")) {
            return getImageFileIntent(pContext, filePath);
        } else if (end.equals("apk")) {
            return getApkFileIntent(pContext, filePath);
        } else if (end.equals("ppt")) {
            return getPptFileIntent(pContext, filePath);
        } else if (end.equals("xls")) {
            return getExcelFileIntent(pContext, filePath);
        } else if (end.equals("doc")) {
            return getWordFileIntent(pContext, filePath);
        } else if (end.equals("pdf")) {
            return getPdfFileIntent(pContext, filePath);
        } else if (end.equals("chm")) {
            return getChmFileIntent(pContext, filePath);
        } else if (end.equals("txt")) {
            return getTextFileIntent(pContext, filePath, false);
        } else {
            return getAllIntent(pContext, filePath);
        }
    }

    //Android获取一个用于打开APK文件的intent
    public static Intent getAllIntent(Context pContext, String param) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "*/*");
        } else {
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "*/*");
        }
        return intent;
    }

    //Android获取一个用于打开APK文件的intent
    public static Intent getApkFileIntent(Context pContext, String param) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "application/vnd.android.package-archive");
        } else {
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        return intent;
    }

    //Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent(Context pContext, String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "video/*");
        } else {
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "video/*");
        }
        return intent;
    }

    //Android获取一个用于打开AUDIO文件的intent
    public static Intent getAudioFileIntent(Context pContext, String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "audio/*");
        } else {
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "audio/*");
        }
        return intent;
    }

    //Android获取一个用于打开Html文件的intent
    public static Intent getHtmlFileIntent(Context pContext, String param) {
        Uri uri = Uri.parse(param).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "text/html");
        } else {
            intent.setDataAndType(uri, "text/html");
        }
        return intent;
    }

    //Android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(Context pContext, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "image/*");
        } else {
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "image/*");
        }
        return intent;
    }

    //Android获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent(Context pContext, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "application/vnd.ms-powerpoint");
        } else {
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        }
        return intent;
    }

    //Android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(Context pContext, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "application/vnd.ms-excel");
        } else {
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        }
        return intent;
    }

    //Android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(Context pContext, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "application/msword");
        } else {
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "application/msword");
        }
        return intent;
    }

    //Android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(Context pContext, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "application/x-chm");
        } else {
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "application/x-chm");
        }
        return intent;
    }

    //Android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(Context pContext, String param, boolean paramBoolean) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
                Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(tempUri, "text/plain");
            } else {
                Uri uri1 = Uri.parse(param);
                intent.setDataAndType(uri1, "text/plain");
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
                Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(tempUri, "text/plain");
            } else {
                Uri uri2 = Uri.fromFile(new File(param));
                intent.setDataAndType(uri2, "text/plain");
            }
        }
        return intent;
    }

    //Android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(Context pContext, String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            Uri tempUri = FileProvider.getUriForFile(pContext, "zeffect.cn.apks.filemanager", new File(param));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(tempUri, "application/pdf");
        } else {
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "application/pdf");
        }
        return intent;
    }
}