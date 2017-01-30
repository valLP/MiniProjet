package valentinbourgeois.miniprojet.model;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class AsyncImageDownloader extends AsyncTask<List<String>, Integer, Void> {

    private IAsyncImageDownloaderCaller _caller;
    private String _baseUrl;
    private String _dir;
    private boolean _err;

    public AsyncImageDownloader(IAsyncImageDownloaderCaller caller, String baseUrl, String directory)
    {
        _caller = caller;
        _baseUrl = baseUrl;
        _dir = directory;
        _err = false;
    }

    @Override
    protected Void doInBackground(List<String>... params)
    {
        int i=0;
        for(String urlImage : params[0])
        {
            download(urlImage);
            publishProgress(i++);
            //Log.d("DOWNLOAD,">>>>DONE<<<<");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        _caller.callbackImageDownloader(_err);
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        _caller.progressImageDownloader(values[0]);
    }

    private void save(String name,InputStream in) throws IOException
    {
        File f = new File(Environment.getExternalStorageDirectory(), _dir+name);
        f.createNewFile();
        FileOutputStream fos = new FileOutputStream(f);

        byte[] buffer = new byte[1024];
        int read;
        do{
            read = in.read(buffer);
            if(read>0)
                fos.write(buffer,0,read);
        }while(read>0);
        in.close();
        fos.close();
    }

    private void download(String urlImage)
    {
        URL url = null;
        HttpURLConnection urlConnection;
        InputStream in;

        try {
            url = new URL(_baseUrl+urlImage);
        } catch (MalformedURLException e) {
            Log.d("URL requete", e.toString());
            _err = true;
            return;
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            save(urlImage,in);
        } catch (IOException e) {
            Log.d("Connection HTTP",e.toString());
            _err = true;
        }
    }

}
