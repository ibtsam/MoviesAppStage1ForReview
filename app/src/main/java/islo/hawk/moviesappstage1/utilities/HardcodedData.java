package islo.hawk.moviesappstage1.utilities;

public class HardcodedData {


    private static String id = "id";
    private static String MAIN_JSONOBJECT = "results";
    private static String BACKDROP_IMAGE_BASEURL = "https://image.tmdb.org/t/p/";
    private static String BACKDROP_IMAGE_SIZE = "w185";
    private static String TOP_RATED = "top_rated";
    private static String POPULAR = "popular";

    public static String getBaseurlFortrailerReview() {
        return BASEURL_FORTRAILER_REVIEW;
    }

    private static String BASEURL_FORTRAILER_REVIEW = "https://api.themoviedb.org/3/";

    public static String getMovie() {
        return movie;
    }

    private static String movie = "movie";

    //json object strings
    private static String ORIGINAL_TITLE = "original_title";

    public static String getPosterPath() {
        return POSTER_PATH;
    }

    public static String getCONTENT() {
        return CONTENT;
    }

    public static String getYoutubeBaseurl() {
        return YOUTUBE_BASEURL;
    }

    public static String YOUTUBE_BASEURL = "https://www.youtube.com/watch?v=";
    private static String CONTENT = "content";
    private static String POSTER_PATH = "poster_path";
    private static String BACKDROP_PATH = "backdrop_path";
    private static String OVERVIEW = "overview";
    private static String VOTE_AVERAGE = "vote_average";
    private static String RELEASE_DATE = "release_date";

    public static String getKeywordApikey() {
        return KEYWORD_APIKEY;
    }

    public static String getId() {
        return id;
    }

    private static String KEYWORD_APIKEY = "api_key";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    private static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static String API_KEY = "57bab8e2dada3ace8377a2be57c3ab03";

    public static String getMainJsonobject() {
        return MAIN_JSONOBJECT;
    }

    public static String getBackdropImageBaseurl() {
        return BACKDROP_IMAGE_BASEURL;
    }

    public static String getBackdropImageSize() {
        return BACKDROP_IMAGE_SIZE;
    }

    public static String getTopRated() {
        return TOP_RATED;
    }

    public static String getPOPULAR() {
        return POPULAR;
    }

    public static String getOriginalTitle() {
        return ORIGINAL_TITLE;
    }

    public static String getBackdropPath() {
        return BACKDROP_PATH;
    }

    public static String getOVERVIEW() {
        return OVERVIEW;
    }

    public static String getVoteAverage() {
        return VOTE_AVERAGE;
    }

    public static String getReleaseDate() {
        return RELEASE_DATE;
    }
}
