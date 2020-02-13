package islo.hawk.moviesappstage1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import islo.hawk.moviesappstage1.utilities.HardcodedData;
import islo.hawk.moviesappstage1.utilities.Movie;
import islo.hawk.moviesappstage1.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {
   private ArrayList<String> posterArrylist=new ArrayList<>();
    private ArrayList<Movie> moviesArraylist;
    private MoviesAdapter moviesAdapter;
    private RecyclerView recyclerView;
    private  GridLayoutManager gridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.recyclerView= findViewById(R.id.movies_poster_recyclerview);

       gridLayoutManager = new GridLayoutManager(this,3);
       moviesArraylist = new ArrayList();

     //   NetworkUtils networkUtils =new NetworkUtils();


        recyclerView.setLayoutManager(gridLayoutManager);
        moviesAdapter = new MoviesAdapter(moviesArraylist,this,this);


      recyclerView.setAdapter(moviesAdapter);


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onClick(int moviesHandler) {
        Context context = this;

     //   JSONObject dataset = (JSONObject) moviesArraylist.get(Integer.parseInt(moviesHandler));
        Intent intent = new Intent(MainActivity.this,MoviesDetails.class);
        intent.putExtra("movieObject",moviesArraylist.get(moviesHandler));
     //   intent.putExtra("object", moviesArraylist.get(Integer.parseInt(moviesHandler)));
            startActivity(intent);


    }

    private  class BgTasks extends AsyncTask<String,Void,String[]>{

      //  NetworkUtils networkUtils =new NetworkUtils();
        @Override
        protected void onPreExecute() {

            if(moviesArraylist!=null){
                posterArrylist.clear();
                moviesArraylist.clear();
                moviesAdapter.notifyDataSetChanged();
            }
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(String... strings) {

            String movieType=strings[0];
            URL movieUrl= NetworkUtils.buildUrl(movieType);
            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(movieUrl);
                JSONObject mainJsonObject = new JSONObject(jsonResponse);
                JSONArray mainJsonArray = mainJsonObject.optJSONArray(HardcodedData.getMainJsonobject());

                for(int i =0;i<mainJsonArray.length();i++){
                  //  moviesArraylist.add(mainJsonArray.getJSONObject(i));
                  JSONObject jsonObject=  mainJsonArray.getJSONObject(i);
                 String orignalTitle=   jsonObject.optString(HardcodedData.getOriginalTitle());
                   String jBackdroppath = jsonObject.optString(HardcodedData.getBackdropPath());
                    String j_overview = jsonObject.optString(HardcodedData.getOVERVIEW());
                    String j_voteaverage = jsonObject.optString(HardcodedData.getVoteAverage());
                    String j_releasedate = jsonObject.optString(HardcodedData.getReleaseDate());
                    String j_posterpath = jsonObject.optString(HardcodedData.getPosterPath());
                    Movie movie = new Movie(orignalTitle,j_releasedate,j_voteaverage,j_overview,jBackdroppath,j_posterpath);
                    moviesArraylist.add(movie);
                }
//                for(int x=0;x<moviesArraylist.size();x++){
//                    JSONObject innerJsonObject= (JSONObject) moviesArraylist.get(x);
//                   posterArrylist.add(HardcodedData.getBackdropImageBaseurl()+HardcodedData.getBackdropImageSize()+innerJsonObject.optString("poster_path"));
//
//                }
                Log.d("jsonArrayLength",String.valueOf(mainJsonArray.length()));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
           return new String[0];
        }

        @Override
        protected void onPostExecute(String[] strings) {

            moviesAdapter.notifyDataSetChanged();

            super.onPostExecute(strings);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_options,menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        BgTasks bgTasks = new BgTasks();

        if(item.getItemId()==R.id.top_rated_movies){


            bgTasks.execute(String.valueOf(HardcodedData.getTopRated()));
            return true;
        }
        else{
            bgTasks.execute(String.valueOf(HardcodedData.getPOPULAR()));

            return true;
        }

    }




}
