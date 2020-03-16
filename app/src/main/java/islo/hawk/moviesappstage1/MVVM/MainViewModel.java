package islo.hawk.moviesappstage1.MVVM;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import islo.hawk.moviesappstage1.database.Movie;
import islo.hawk.moviesappstage1.MVVM.Repository;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Movie>> movieEntity;

    public MainViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        movieEntity = repository.getAllMovies();
    }

    public LiveData<List<Movie>> getMovies() {

        return movieEntity;
    }

    public void delete(Movie movie){
        repository.deleteMovie(movie);
    }



    public void insert(Movie movieEntity) {
        repository.insert(movieEntity);
    }
}
