package com.example.dongpeng.havenoname.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

/**
 * Created by dongpeng on 2017/1/9.
 */

public class SaveFileUtil {
    public void saveFile(Context context,Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            File file=context.getExternalCacheDir();
            File downFile=new File(file,"测试.pdf");
            if (downFile.exists()){
                downFile.delete();
            }
            BufferedSink sink = null;
            //下载文件到本地
            try {
                sink = Okio.buffer(Okio.sink(downFile));
                sink.writeAll(response.body().source());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (sink != null) sink.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
//            Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
            Log.d("下载成功", "isSuccessful");
        } else {
//            Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
        }
    }

}
