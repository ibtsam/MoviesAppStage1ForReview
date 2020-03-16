package islo.hawk.moviesappstage1.MVVM;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import islo.hawk.moviesappstage1.database.Movie;
import islo.hawk.moviesappstage1.database.MovieDatabase;

public class SearchMovieViewModel extends ViewModel {

    private LiveData<Movie> movieById;

    public SearchMovieViewModel(MovieDatabase appDatabase, int taskId) {
        super();
        movieById = appDatabase.taskDao().loadTaskById(taskId);

    }

    public LiveData<Movie> getTask() {
        return movieById;
    }
}

