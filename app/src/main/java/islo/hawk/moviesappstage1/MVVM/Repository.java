package islo.hawk.moviesappstage1.MVVM;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import islo.hawk.moviesappstage1.database.Movie;
import islo.hawk.moviesappstage1.database.MovieDao;
import islo.hawk.moviesappstage1.database.MovieDatabase;

public class Repository {

    private MovieDao movieDao;
    private LiveData<List<Movie>> movieList;

    public Repository(Application application) {
        MovieDatabase appDatabase = MovieDatabase.getInstance(application);
        movieDao = appDatabase.taskDao();
        movieList = movieDao.loadAllMovies();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieList;
    }

    public void insert(Movie movieEntity) {

        new InsertAsyncTask(movieDao).execute(movieEntity);
    }

    public void deleteMovie(final Movie movie){

        new DeleteMovieAsyncTask(movieDao).execute(movie);

    }

    private static class DeleteMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        private DeleteMovieAsyncTask(MovieDao noteDao) {
            this.movieDao = noteDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.deleteTask(movies[0]);
            return null;
        }
    }


    private static class InsertAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao noteDao;

        private InsertAsyncTask(MovieDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Movie... notes) {
            noteDao.insertMovie(notes[0]);
            return null;
        }
    }


}
