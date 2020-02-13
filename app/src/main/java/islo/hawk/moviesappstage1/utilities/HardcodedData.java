package islo.hawk.moviesappstage1.utilities;

public  class HardcodedData {
    private static String MAIN_JSONOBJECT = "results";
    private static String BACKDROP_IMAGE_BASEURL= "http://image.tmdb.org/t/p/";
    private static String BACKDROP_IMAGE_SIZE = "w185";
    private static String TOP_RATED = "top_rated";
    private static String POPULAR = "popular";

    //json object strings
    private static String ORIGINAL_TITLE= "original_title" ;

    public static String getPosterPath() {
        return POSTER_PATH;
    }

    private static String POSTER_PATH = "poster_path";
    private static String BACKDROP_PATH = "backdrop_path";
    private static String OVERVIEW = "overview";
    private static String VOTE_AVERAGE = "vote_average";
    private static String RELEASE_DATE = "release_date";

    public static String getKeywordApikey() {
        return KEYWORD_APIKEY;
    }

    private static String KEYWORD_APIKEY = "api_key";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    private static String BASE_URL ="https://api.themoviedb.org/3/movie/";
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
