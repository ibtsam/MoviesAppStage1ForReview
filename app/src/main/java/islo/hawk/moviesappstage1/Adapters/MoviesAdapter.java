package islo.hawk.moviesappstage1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import islo.hawk.moviesappstage1.R;
import islo.hawk.moviesappstage1.utilities.HardcodedData;
import islo.hawk.moviesappstage1.database.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private ImageView posterImages;
    private ArrayList<Movie> moviesArraylist;
    Context context;
    private final MoviesAdapterOnClickHandler moviesAdapterOnClickHandler;

    public interface MoviesAdapterOnClickHandler {
        void onClick(int moviesHandler);
    }

    public MoviesAdapter(ArrayList<Movie> moviesArraylist, Context context_, MoviesAdapterOnClickHandler moviesAdapterOnClickHandler) {
        this.moviesArraylist = moviesArraylist;
        this.context = context_;
        this.moviesAdapterOnClickHandler = moviesAdapterOnClickHandler;
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int layoutId = R.layout.activity_movies_recyclerview_items;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        Boolean isAttachToParent = false;
        View view = layoutInflater.inflate(layoutId, parent, isAttachToParent);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {


        Movie movie = moviesArraylist.get(position);
        Picasso.get()
                .load(HardcodedData.getBackdropImageBaseurl() + HardcodedData.getBackdropImageSize() + movie.getPosterImage())
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return moviesArraylist.size();
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView posterImageView;

        public MoviesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.imagePoster_recyclerview);
            itemView.setOnClickListener(this);

        }

        public ImageView getPosterImageView() {
            return this.posterImageView;
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            moviesAdapterOnClickHandler.onClick(adapterPosition);


        }
    }

}
