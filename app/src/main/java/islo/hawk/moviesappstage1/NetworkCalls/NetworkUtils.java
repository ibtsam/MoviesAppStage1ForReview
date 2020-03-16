package islo.hawk.moviesappstage1.NetworkCalls;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import islo.hawk.moviesappstage1.utilities.HardcodedData;

public  final class NetworkUtils {
     public NetworkUtils(){
throw new AssertionError();
    }

    public  static URL buildUrl(String kindOfMovies){
        Uri builtUri = Uri.parse(HardcodedData.getBaseUrl()).buildUpon()
                .appendPath(kindOfMovies)
                .appendQueryParameter(HardcodedData.getKeywordApikey(),HardcodedData.getApiKey()).build();

        URL url =null;
        try {
            url= new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
