package islo.hawk.moviesappstage1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import islo.hawk.moviesappstage1.utilities.HardcodedData;
import islo.hawk.moviesappstage1.utilities.Movie;


public class MoviesDetails extends AppCompatActivity {

    public TextView movieTitle;
    TextView releaseDate, voteAverage, overview;
    ImageView backdropImage;
//    @BindView(R.id.movie_title) TextView movieTitle;
//    @BindView(R.id.release_date) TextView releaseDate;
//    @BindView(R.id.vote_average) TextView voteAverage;
//    @BindView(R.id.overview) TextView overview;
//    @BindView(R.id.backdrop_img) ImageView backdropImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movies_details);
        // ButterKnife.bind(this);


        movieTitle = (TextView) findViewById(R.id.movie_title);
        releaseDate = (TextView) findViewById(R.id.release_date);
        voteAverage = (TextView) findViewById(R.id.vote_average);
        overview = (TextView) findViewById(R.id.overview);
        backdropImage = (ImageView) findViewById(R.id.backdrop_img);

        Movie movie = getIntent().getParcelableExtra("movieObject");

        movieTitle.setText(movie.getMovieTitle());
        releaseDate.setText(movie.getReleaseDate());
        voteAverage.setText(movie.getVoteAverage());
        overview.setText(movie.getOverview());


        Picasso.get()
                .load(HardcodedData.getBackdropImageBaseurl() + HardcodedData.getBackdropImageSize() + movie.getBackdropImage())
                .into(backdropImage);
    }
}
