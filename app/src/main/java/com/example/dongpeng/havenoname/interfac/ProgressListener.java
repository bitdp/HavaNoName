package com.example.dongpeng.havenoname.interfac;

/**
 * Created by dongpeng on 2017/1/6.
 */

public interface ProgressListener
{
    void update(long bytesRead, long contentLength, boolean done);
}
