package islo.hawk.moviesappstage1.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import islo.hawk.moviesappstage1.MVVM.MainViewModel;
import islo.hawk.moviesappstage1.Adapters.MoviesAdapter;
import islo.hawk.moviesappstage1.R;
import islo.hawk.moviesappstage1.Services.AsyncTaskForMainActivity;
import islo.hawk.moviesappstage1.Services.InterfaceForCallBacks;
import islo.hawk.moviesappstage1.utilities.HardcodedData;
import islo.hawk.moviesappstage1.database.Movie;
import islo.hawk.moviesappstage1.NetworkCalls.NetworkUtils;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {
    private ArrayList<Movie> moviesArraylist;
    private MoviesAdapter moviesAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.recyclerView = findViewById(R.id.movies_poster_recyclerview);

        gridLayoutManager = new GridLayoutManager(this, 3);
        moviesArraylist = new ArrayList();

        if (savedInstanceState != null) {
            moviesArraylist = savedInstanceState.getParcelableArrayList("movieArray");
        }

        recyclerView.setLayoutManager(gridLayoutManager);
        moviesAdapter = new MoviesAdapter(moviesArraylist, this, this);

        recyclerView.setAdapter(moviesAdapter);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("movieArray", moviesArraylist);
        super.onSaveInstanceState(outState);
    }

    private void setupViewModel() {
        if (moviesArraylist != null) {
            moviesArraylist.clear();
            moviesAdapter.notifyDataSetChanged();
        }
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> taskEntries) {

                moviesArraylist.addAll(taskEntries);
                moviesAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onClick(int moviesHandler) {
        Context context = this;

        Intent intent = new Intent(MainActivity.this, MoviesDetailActivity.class);
        intent.putExtra("movieObject", moviesArraylist.get(moviesHandler));
        startActivity(intent);

    }

//    private class BgTasks extends AsyncTask<String, Void, String[]> {
//
//        @Override
//        protected void onPreExecute() {
//
//            if (moviesArraylist != null) {
//                moviesArraylist.clear();
//                moviesAdapter.notifyDataSetChanged();
//            }
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String[] doInBackground(String... strings) {
//
//            String movieType = strings[0];
//            URL movieUrl = NetworkUtils.buildUrl(movieType);
//            try {
//                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(movieUrl);
//                JSONObject mainJsonObject = new JSONObject(jsonResponse);
//                JSONArray mainJsonArray = mainJsonObject.optJSONArray(HardcodedData.getMainJsonobject());
//
//                for (int i = 0; i < mainJsonArray.length(); i++) {
//                    JSONObject jsonObject = mainJsonArray.getJSONObject(i);
//                    int id = jsonObject.optInt(HardcodedData.getId());
//                    String orignalTitle = jsonObject.optString(HardcodedData.getOriginalTitle());
//                    String backdropPath = jsonObject.optString(HardcodedData.getBackdropPath());
//                    String overview = jsonObject.optString(HardcodedData.getOVERVIEW());
//                    String voteAverage = jsonObject.optString(HardcodedData.getVoteAverage());
//                    String releaseDate = jsonObject.optString(HardcodedData.getReleaseDate());
//                    String posterPath = jsonObject.optString(HardcodedData.getPosterPath());
//                    Movie movie = new Movie(id, orignalTitle, releaseDate, voteAverage, overview, backdropPath, posterPath);
//                    moviesArraylist.add(movie);
//                }
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return new String[0];
//        }
//
//        @Override
//        protected void onPostExecute(String[] strings) {
//
//            moviesAdapter.notifyDataSetChanged();
//
//            super.onPostExecute(strings);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_options, menu);
        return true;
    }


    public void HandlingCallBackMethods(String value){

        AsyncTaskForMainActivity asyncTaskForMainActivity = new AsyncTaskForMainActivity(this, new InterfaceForCallBacks() {
            @Override
            public void Success(Object object) {
                moviesArraylist.addAll((Collection<? extends Movie>) object);
                moviesAdapter.notifyDataSetChanged();
            }
        });
        asyncTaskForMainActivity.execute(value);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.top_rated_movies) {

            if (moviesArraylist != null) {
                moviesArraylist.clear();
                moviesAdapter.notifyDataSetChanged();
            }

            HandlingCallBackMethods(String.valueOf(HardcodedData.getTopRated()));

            return true;
        }
        else if (item.getItemId() == R.id.popular_movies) {
            HandlingCallBackMethods(String.valueOf(HardcodedData.getTopRated()));
            return true;
        } else if (item.getItemId() == R.id.fav_movies) {
            setupViewModel();
            return true;
        }

        return true;

    }

}
