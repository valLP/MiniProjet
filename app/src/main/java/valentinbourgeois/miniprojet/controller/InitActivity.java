package valentinbourgeois.miniprojet.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.File;
import java.util.List;

import valentinbourgeois.miniprojet.R;
import valentinbourgeois.miniprojet.model.AsyncImageDownloader;
import valentinbourgeois.miniprojet.model.Categories;
import valentinbourgeois.miniprojet.model.IAsyncDataLoaderCaller;
import valentinbourgeois.miniprojet.model.IAsyncImageDownloaderCaller;
import valentinbourgeois.miniprojet.model.XmlAsyncDataLoader;

public class InitActivity extends AppCompatActivity implements IAsyncDataLoaderCaller, IAsyncImageDownloaderCaller{
    private Categories _cateogries;
    public static final String SOURCE = "http://public.ave-comics.com/gabriel/iut/images.xml";
    public static final String BASE_URL_IMAGE = "http://public.ave-comics.com/gabriel/iut/images/";
    public static final String TMPDIR = "img/";
    private ProgressBar _pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        _cateogries = Categories.getInstance();

        File dir = new File(Environment.getExternalStorageDirectory(), TMPDIR);
        dir.mkdirs();

        XmlAsyncDataLoader dataLoader = new XmlAsyncDataLoader(this,SOURCE,_cateogries);
        dataLoader.execute();
    }

    @Override
    public void callbackDataLoader(boolean error) {
        if(error)
            finish();
        else
        {
            List<String> urls = _cateogries.getImagesUrl();
            _pBar = (ProgressBar) findViewById(R.id.progressBar);
            _pBar.setMax(urls.size());
            _pBar.invalidate();
            AsyncImageDownloader imageDownloader = new AsyncImageDownloader(this,BASE_URL_IMAGE,TMPDIR);
            imageDownloader.execute(urls);
        }
    }

    @Override
    public void progressImageDownloader(int progress) {
        _pBar.setProgress(progress);
    }

    @Override
    public void callbackImageDownloader(boolean error) {
        if(error)
            finish();
        else
            startCategories();
    }

    private void startCategories() {
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Log.d("Cache","CLEAR");
        File dir = new File(Environment.getExternalStorageDirectory(), TMPDIR);
        dir.delete();
    }
}
