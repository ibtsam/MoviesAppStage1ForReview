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

// perpose: For Movie trailers and movie reviews

public class NetworkUtilsForDetails {

    public NetworkUtilsForDetails(){
        throw new AssertionError();
    }

    public  static URL buildUrl(String id,String dataToGet){
        Uri builtUri = Uri.parse(HardcodedData.getBaseurlFortrailerReview()).buildUpon()
                .appendPath(HardcodedData.getMovie())
                .appendPath(id)
                .appendPath(dataToGet)
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
