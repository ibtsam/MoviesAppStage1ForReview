package islo.hawk.moviesappstage1.MVVM;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import islo.hawk.moviesappstage1.database.MovieDatabase;

public class SearchMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    private final MovieDatabase mDb;
    private final int mTaskId;
    public SearchMovieViewModelFactory(Context context, int taskId) {
       // mDb = appDatabase;
        mTaskId = taskId;
        mDb = MovieDatabase.getInstance(context);

    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new SearchMovieViewModel(mDb, mTaskId);
    }

}
