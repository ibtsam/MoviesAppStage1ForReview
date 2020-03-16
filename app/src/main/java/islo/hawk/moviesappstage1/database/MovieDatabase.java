package islo.hawk.moviesappstage1.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Movie.class}, version = 1, exportSchema = true)
public abstract class MovieDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "MOVIES";
    private static MovieDatabase sInstance;

    public abstract MovieDao taskDao();


    public static MovieDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class, MovieDatabase.DATABASE_NAME)
                        .build();
                Log.d("database instance","is created");

            }
            Log.d("database","iscalled");
        }
        return sInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(sInstance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao taskDao;

        private PopulateDbAsyncTask(MovieDatabase db) {
            taskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            taskDao.insertMovie(new Movie(1, "movie", "date", "vote", "overview", "backdropImage", "posterImage"));
            taskDao.insertMovie(new Movie(2, "movie", "date", "vote", "overview", "backdropImage", "posterImage"));
            taskDao.insertMovie(new Movie(3, "movie", "date", "vote", "overview", "backdropImage", "posterImage"));
            return null;
        }
    }

}
