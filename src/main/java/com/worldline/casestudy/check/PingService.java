package com.worldline.casestudy.check;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PingService {


   protected HttpURLConnection getConnection(String address) throws IOException {
       address = address.replaceFirst("https://", "http://");
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection;
    }
    private static final String URL_REGEX =
            "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
                    "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
                    "([).!';/?:,][[:blank:]])?$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public  boolean isValidURL(String url)
    {
        if (url == null) {
            return false;
        }

        Matcher matcher = URL_PATTERN.matcher(url);
        return matcher.matches();
    }
    public boolean isOK(String url, int timeout) {

        try {
            HttpURLConnection connection = getConnection(url);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode!= HttpURLConnection.HTTP_OK) {
                return false;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
            return false;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
        return true;
    }
}
