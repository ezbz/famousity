package org.echotech.famousity.shared;

public class FacebookUtil {
    private static final String API_KEY = "xxx";
    private static final String SECRET = "xxx";
    private static final String APPLICATION_ID = "xxx";

    private static final String FB_GRAPH_URL = "https://graph.facebook.com/";
    private static final String FB_OAUTH_URL = FB_GRAPH_URL + "oauth/";
    private static final String FB_FRIENDS_URL = FB_GRAPH_URL + "me/friends?";

    // private static final String REDIRECT_URL =
    // "http://apps.facebook.com/famousity/";

    // localhost is for testing
    private static final String REDIRECT_URL = "http://localhost:8888/";

    public static String getApplicationId() {
        return APPLICATION_ID;
    }

    public static String getAPIKey() {
        return API_KEY;
    }

    public static String getSecret() {
        return SECRET;
    }

    public static String getAuthorizeUrl() {
        final StringBuilder sb = new StringBuilder(FB_OAUTH_URL);
        sb.append("authorize?client_id=").append(APPLICATION_ID);
        sb.append("&display=page&redirect_uri=").append(REDIRECT_URL);
        sb.append("&scope=user_status,publish_stream,offline_access,email");
        return sb.toString();
    }

    public static String getAccessTokenUrl(final String authCode) {
        final StringBuilder sb = new StringBuilder(FB_OAUTH_URL);
        sb.append("access_token?canvas=1&fbconnect=0&type=user_agent&client_id=").append(APPLICATION_ID);
        sb.append("&redirect_uri=").append(REDIRECT_URL);
        sb.append("&client_secret=").append(SECRET);
        sb.append("&code=").append(authCode);
        System.out.println(sb.toString());
        System.out.println(getAuthorizeUrl());
        return sb.toString();
    }

    public static String getFriendsUrl(final String authToken) {
        return FB_FRIENDS_URL + authToken;
    }
}