package islo.hawk.moviesappstage1.Services;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import islo.hawk.moviesappstage1.NetworkCalls.NetworkUtils;
import islo.hawk.moviesappstage1.database.Movie;
import islo.hawk.moviesappstage1.utilities.HardcodedData;

public class AsyncTaskForMainActivity extends AsyncTask<String, Void, ArrayList<Movie>> {

    Context context;
    InterfaceForCallBacks mCallBacks;
    ArrayList<Movie> moviesArraylist;

    public AsyncTaskForMainActivity(Context context, InterfaceForCallBacks callBacksInterface) {

        this.context = context;
        mCallBacks = callBacksInterface;

    }

    @Override
    protected void onPreExecute() {
        moviesArraylist = new ArrayList();
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Movie> doInBackground(String... strings) {

        String movieType = strings[0];
        URL movieUrl = NetworkUtils.buildUrl(movieType);
        try {
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(movieUrl);
            JSONObject mainJsonObject = new JSONObject(jsonResponse);
            JSONArray mainJsonArray = mainJsonObject.optJSONArray(HardcodedData.getMainJsonobject());

            for (int i = 0; i < mainJsonArray.length(); i++) {
                JSONObject jsonObject = mainJsonArray.getJSONObject(i);
                int id = jsonObject.optInt(HardcodedData.getId());
                String orignalTitle = jsonObject.optString(HardcodedData.getOriginalTitle());
                String backdropPath = jsonObject.optString(HardcodedData.getBackdropPath());
                String overview = jsonObject.optString(HardcodedData.getOVERVIEW());
                String voteAverage = jsonObject.optString(HardcodedData.getVoteAverage());
                String releaseDate = jsonObject.optString(HardcodedData.getReleaseDate());
                String posterPath = jsonObject.optString(HardcodedData.getPosterPath());
                Movie movie = new Movie(id, orignalTitle, releaseDate, voteAverage, overview, backdropPath, posterPath);
                moviesArraylist.add(movie);
            }
            return moviesArraylist;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movieArrylist) {

        mCallBacks.Success(movieArrylist);
    }


}
