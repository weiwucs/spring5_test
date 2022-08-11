package org.weiwu.com.callback;


public class Client implements IDownloadStatusCallback{
    private Server mServer = null;

    public Client(){

    }

    public void download(String url){
        mServer = new Server(Client.this, url);
        mServer.Run();
    }

    @Override
    public void startDownload() {
        System.out.println("start download");
    }

    @Override
    public void stopDownload() {
        System.out.println("stop download");
    }

    @Override
    public void showDownloadURL(String url) {
        System.out.println("download url: " + url);
    }

    @Override
    public void showDownloadProgress(int progress) {
        System.out.println("download progress: " + progress);
    }
}
