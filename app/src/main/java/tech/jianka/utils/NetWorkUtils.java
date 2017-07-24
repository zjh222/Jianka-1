package tech.jianka.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Richa on 2017/7/24.
 */

public class NetWorkUtils  {
    final static String JIANKA_BASE_URL = "https://user.jianka.tech/repositories";
    final static String PARAM_QUERY = "q";

    final static String PARAM_SORT = "sort";
    final static String sortBy = "time";

    /**
     *
     * @param syncQuery
     * @return
     */
    public static URL buildUrl(String syncQuery) {
        Uri builtUri = Uri.parse(JIANKA_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, syncQuery)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();
        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
        return  url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}
