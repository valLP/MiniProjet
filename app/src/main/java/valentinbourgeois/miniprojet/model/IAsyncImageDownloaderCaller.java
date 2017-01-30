package valentinbourgeois.miniprojet.model;


public interface IAsyncImageDownloaderCaller {
    public void callbackImageDownloader(boolean error);
    public void progressImageDownloader(int progress);
}
