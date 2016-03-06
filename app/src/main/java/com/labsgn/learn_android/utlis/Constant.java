package com.labsgn.learn_android.utlis;

/**
 * Created by rhony on 03/03/16.
 */
public final class Constant {

    private static String currentUrl;

    private static final String API_KEY = "54wzfswsa4qmjg8hjwa64d4c";

    private static final String URL_BOX_OFFICE = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";

    private static final String CHAR_Q = "?";
    private static final String CHAR_AND = "&";

    private static final String PARAM_API_KEY = "apikey=";
    private static final String PARAM_LIMIT = "limit=";

    //Todo 15. Menyiapkan interface untuk constant baru
    public interface NA{
        String NA = "NA";
    }

    public enum URL_TYPE {
        BOX_OFFICE, SEARCH, UPCOMING
    }

    private Constant(URL_TYPE url_type){
        currentUrl = null;
        switch (url_type){
            case BOX_OFFICE:
                currentUrl =  Constant.URL_BOX_OFFICE + Constant.CHAR_Q +
                              Constant.PARAM_API_KEY + Constant.API_KEY +
                              Constant.CHAR_AND + Constant.PARAM_LIMIT;
                break;

            case SEARCH:
                break;

            case UPCOMING:
                break;
        }
    }

    public static String getUrl(URL_TYPE url_type){
        new Constant(url_type);
        return currentUrl;
    }
}

