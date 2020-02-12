package islo.hawk.moviesappstage1.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {
     public NetworkUtils(){
throw new AssertionError();
    }

    public  URL buildUrl(String kindOfMovies){
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


    public String getResponseFromHttpUrl(URL url) throws IOException {
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
