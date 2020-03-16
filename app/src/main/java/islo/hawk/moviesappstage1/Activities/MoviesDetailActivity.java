package islo.hawk.moviesappstage1.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import islo.hawk.moviesappstage1.MVVM.SearchMovieViewModel;
import islo.hawk.moviesappstage1.MVVM.SearchMovieViewModelFactory;
import islo.hawk.moviesappstage1.MVVM.MainViewModel;
import islo.hawk.moviesappstage1.R;
import islo.hawk.moviesappstage1.database.MovieDatabase;
import islo.hawk.moviesappstage1.utilities.HardcodedData;
import islo.hawk.moviesappstage1.database.Movie;
import islo.hawk.moviesappstage1.NetworkCalls.NetworkUtilsForDetails;


public class MoviesDetailActivity extends AppCompatActivity {

    public TextView movieTitle, reviews, trailers, releaseDate, voteAverage, overview;
    Button markFav;
    ImageView backdropImage;
    String review;
    String youtubeKey;
    String movieId;
    private MovieDatabase mDb;
    Boolean favButtonStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_movies_details);
        mDb = MovieDatabase.getInstance(getApplicationContext());


        movieTitle = (TextView) findViewById(R.id.movie_title);
        releaseDate = (TextView) findViewById(R.id.release_date);
        voteAverage = (TextView) findViewById(R.id.vote_average);
        overview = (TextView) findViewById(R.id.overview);
        backdropImage = (ImageView) findViewById(R.id.backdrop_img);
        reviews = (TextView) findViewById(R.id.reviews);
        trailers = (TextView) findViewById(R.id.trailers);
        trailers.setEnabled(false);
        markFav = (Button) findViewById(R.id.mark_button);
        final Movie movie = getIntent().getParcelableExtra("movieObject");

        movieTitle.setText(movie.getMovieTitle());
        releaseDate.setText(movie.getReleaseDate());
        voteAverage.setText(movie.getVoteAverage());
        overview.setText(movie.getOverview());

        movieId = String.valueOf(movie.getId());

        Picasso.get()
                .load(HardcodedData.getBackdropImageBaseurl() + HardcodedData.getBackdropImageSize() + movie.getBackdropImage())
                .into(backdropImage);

        new AsycTaskDetails().execute(movieId, "reviews");

        new AsycTaskForTrailer().execute(movieId, "videos");


        SearchMovieViewModelFactory factory = new SearchMovieViewModelFactory( this,movie.getId());
        final SearchMovieViewModel viewModel
                = ViewModelProviders.of(this, factory).get(SearchMovieViewModel.class);

        viewModel.getTask().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie1) {
                viewModel.getTask().removeObserver(this);
                if(movie1!=null){

                    markFav.setText(getResources().getString(R.string.already_favourite));
                    favButtonStatus =true;
                }
                else{
                    favButtonStatus =false;
                }

            }



        });

        markFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movieEntity = new Movie(movie.getId(), movie.getMovieTitle(), movie.getReleaseDate(), movie.getVoteAverage(), movie.getOverview(),
                        movie.getBackdropImage(), movie.getPosterImage());
                MainViewModel mainViewModel = new MainViewModel(getApplication());
            if(favButtonStatus){
                mainViewModel.delete(movieEntity);
                markFav.setText(getResources().getString(R.string.mark_favourite));
                favButtonStatus=false;
            }
            else{
                mainViewModel.insert(movieEntity);
                markFav.setText(getResources().getString(R.string.already_favourite));
                favButtonStatus=true;
            }


            }
        });

        trailers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(HardcodedData.getYoutubeBaseurl() + youtubeKey));

                MoviesDetailActivity.this.startActivity(webIntent);
            }
        });

    }


    public class AsycTaskDetails extends AsyncTask<String, Void, String[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(String... strings) {
            String id = strings[0];
            String rev = strings[1];
            URL movieUrl = NetworkUtilsForDetails.buildUrl(id, rev);
            try {
                String response = NetworkUtilsForDetails.getResponseFromHttpUrl(movieUrl);
                JSONObject mainJsonObject = new JSONObject(response);
                JSONArray mainJsonArray = mainJsonObject.optJSONArray("results");
                JSONObject jsonObject = mainJsonArray.getJSONObject(0);
                review = String.valueOf(jsonObject.get(HardcodedData.getCONTENT()));
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] s) {

            reviews.setText(review);
            super.onPostExecute(s);
        }
    }

    public class AsycTaskForTrailer extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(String... strings) {
            String id = strings[0];
            String rev = strings[1];
            URL movieUrl = NetworkUtilsForDetails.buildUrl(id, rev);
            try {
                String response = NetworkUtilsForDetails.getResponseFromHttpUrl(movieUrl);
                JSONObject mainJsonObject = new JSONObject(response);
                JSONArray mainJsonArray = mainJsonObject.optJSONArray("results");
                JSONObject jsonObject = mainJsonArray.optJSONObject(0);
                youtubeKey = jsonObject.optString("key");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] s) {

            trailers.setEnabled(true);
            super.onPostExecute(s);
        }
    }


}
