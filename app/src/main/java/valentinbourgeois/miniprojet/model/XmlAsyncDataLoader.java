package valentinbourgeois.miniprojet.model;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class XmlAsyncDataLoader extends AsyncTask<Void, Void, Void> {
    private static String _urlSrc;
    private IAsyncDataLoaderCaller _caller;
    private Categories _categories;
    private URL _url;
    private HttpURLConnection _urlConnection;
    private boolean _err;

    public XmlAsyncDataLoader(IAsyncDataLoaderCaller caller, String src, Categories categories)
    {
        _urlSrc = src;
        _caller = caller;
        _categories = categories;
        _err = false;
    }

    @Override
    protected Void doInBackground(Void... v) {
        InputStream xmlFile = getContentFromUrl(_urlSrc);
        if(!_err)
            parseXml(xmlFile);
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        _caller.callbackDataLoader(_err);
    }

    private InputStream getContentFromUrl(String url_xml)
    {
        InputStream in = null;

        try {
            _url = new URL(url_xml);
        } catch (MalformedURLException e) {
            Log.d("URL requete",e.toString());
            _err = true;
        }

        if(_err)
            return null;

        try {
            _urlConnection = (HttpURLConnection) _url.openConnection();
            in = new BufferedInputStream(_urlConnection.getInputStream());
        } catch (IOException e) {
            Log.d("Connection HTTP",e.toString());
            _err = true;
        }
        return in;
    }

    private void parseXml(InputStream in)
    {
        SAXParser parser;
        try
        {
            parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(in, new CategoriesXmlHandler(_categories));
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
            _err = true;
        }
        catch (SAXException e)
        {
            e.printStackTrace();
            _err = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            _err = true;
        }
    }
}
