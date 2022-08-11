package org.weiwu.com.callback;

public interface IDownloadStatusCallback {
    public void startDownload();
    public void stopDownload();
    public void showDownloadURL(String url);
    public void showDownloadProgress(int progress);
}
