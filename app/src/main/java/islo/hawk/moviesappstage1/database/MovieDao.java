package islo.hawk.moviesappstage1.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie_table")
    LiveData<List<Movie>> loadAllMovies();

    @Insert void insertMovie(Movie movie);

    @Delete
    void deleteTask(Movie movie);

    @Query("SELECT * FROM movie_table WHERE id = :id")
    LiveData<Movie> loadTaskById(int id);

}
