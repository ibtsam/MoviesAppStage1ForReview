package islo.hawk.moviesappstage1.utilities;

import android.os.Parcel;
import android.os.Parcelable;

public class Dataset implements Parcelable {
    // movieTitle,releaseDate,voteAverage,overview;
    private String movieTitle;
    private String releaseDate;
    private String voteAverage ;
    private String overview ;
    private String backdropImage;

    public Dataset(String movieTitle,String releaseDate,String voteAverage,String overview,String backdropImage) {
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.backdropImage = backdropImage;

    }


    protected Dataset(Parcel in) {
        movieTitle = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readString();
        overview = in.readString();
        backdropImage = in.readString();
    }

    public static final Creator<Dataset> CREATOR = new Creator<Dataset>() {
        @Override
        public Dataset createFromParcel(Parcel in) {
            return new Dataset(in);
        }

        @Override
        public Dataset[] newArray(int size) {
            //   return new Dataset[size];
            return new Dataset[0];
        }
    };

    public String getMovieTitle() {
        return movieTitle;
    }

    // public void setMovieTitle(String movieTitle) {
    //     this.movieTitle = movieTitle;
    //   }

    public String getReleaseDate() {
        return releaseDate;
    }

    //  public void setReleaseDate(String releaseDate) {
    //       this.releaseDate = releaseDate;
    //  }

    public String getVoteAverage() {
        return voteAverage;
    }

//    public void setVoteAverage(String voteAverage) {
    //      this.voteAverage = voteAverage;
    //   }

    public String getOverview() {
        return overview;
    }

    //   public void setOverview(String overview) {
    //       this.overview = overview;
    //   }

    public String getBackdropImage() {
        return backdropImage;
    }

//    public void setBackdropImage(String backdropImage) {
    //       this.backdropImage = backdropImage;
    //  }

    @Override
    public int describeContents() {
        return hashCode();
        //bydefault it was 0
        //  return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieTitle);
        dest.writeString(releaseDate);
        dest.writeString(voteAverage);
        dest.writeString(overview);
        dest.writeString(backdropImage);
    }
}
