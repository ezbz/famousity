package org.echotech.famousity.server;

import org.echotech.famousity.client.GoogleService;
import org.echotech.famousity.server.google.ResponseDataContainer;
import org.echotech.famousity.shared.FbFriend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
public class GoogleServiceImpl extends RemoteServiceServlet implements GoogleService {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    private static final String GOOGLE_API_URL = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";

    private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public Long findFriendRanking(final FbFriend friend) {
        final Key key = KeyFactory.createKey("profile", friend.getId());
        try {
            final Entity profile = datastore.get(key);
            final Object result = profile.getProperty("estimatedResultCount");
            return (Long) result;
        } catch (final EntityNotFoundException e) {
            logger.debug("Could not find an entry for {} in the datastore.", friend.getId());
            final Long resultCount = findResultCount(friend.getName());
            final Entity newProfile = new Entity(key);
            newProfile.setProperty("profileName", friend.getName());
            newProfile.setProperty("estimatedResultCount", resultCount);
            datastore.put(newProfile);
            return resultCount;
        }
    }

    public Long findResultCount(final String name) {
        final String queryUrl = GOOGLE_API_URL + Util.encode("\"" + name + "\"");
        logger.debug("Executing google query {}", queryUrl);
        final String resultJson = Util.fetchURL(queryUrl);

        final ResponseDataContainer container = new Gson().fromJson(resultJson,
                ResponseDataContainer.class);

        if (null != container && null != container.getResponseData()
                && null != container.getResponseData().getCursor()) {
            final Long resultCount = container.getResponseData().getCursor()
                    .getEstimatedResultCount();
            return resultCount;
        } else {
            return -1L;
        }
    }

}
