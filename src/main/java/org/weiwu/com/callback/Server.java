package org.weiwu.com.callback;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Timer;
import java.util.TimerTask;

public class Server {
    private IDownloadStatusCallback iCallback;
    private String mDownloadUrl = null;

    private Timer mTimer = null;

    private int mProgress = 0;

    public Server(IDownloadStatusCallback iCallback, String mDownloadUrl){
        this.iCallback = iCallback;
        this.mDownloadUrl = mDownloadUrl;
        mProgress = 0;
    }

    public void Run(){
        iCallback.showDownloadURL(mDownloadUrl);
        iCallback.startDownload();

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                iCallback.showDownloadProgress(mProgress);
                if(mProgress == 100){
                    iCallback.stopDownload();
                    mTimer.cancel();
                }
                mProgress += 10;
            }
        }, 0, 1000);
    }
}
