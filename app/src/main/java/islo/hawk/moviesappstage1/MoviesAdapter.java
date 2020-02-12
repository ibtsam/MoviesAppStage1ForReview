package islo.hawk.moviesappstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

   private ImageView posterImages;
    private ArrayList<String> moviesArraylist;
    Context context;
    private final MoviesAdapterOnClickHandler moviesAdapterOnClickHandler;

    public interface MoviesAdapterOnClickHandler{
        void onClick(String moviesHandler);
    }

    public MoviesAdapter(ArrayList<String> moviesArraylist,Context context_,MoviesAdapterOnClickHandler moviesAdapterOnClickHandler) {
        this.moviesArraylist= moviesArraylist;
        this.context= context_;
        this.moviesAdapterOnClickHandler = moviesAdapterOnClickHandler;
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();

        int layoutId= R.layout.activity_movies_recyclerview_items;
        LayoutInflater layoutInflater =LayoutInflater.from(context);

        Boolean isAttachToParent=false;
        View view = layoutInflater.inflate(layoutId,parent,isAttachToParent);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {


        Picasso.get()
                .load(moviesArraylist.get(position))
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

        public ImageView getPosterImageView(){
            return  this.posterImageView;
        }

        @Override
        public void onClick(View v) {
            int adapterPosition= getAdapterPosition();
            String images = moviesArraylist.get(adapterPosition);
            moviesAdapterOnClickHandler.onClick(String.valueOf(adapterPosition));


        }
    }

}
