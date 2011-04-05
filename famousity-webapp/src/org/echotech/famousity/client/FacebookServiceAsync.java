package org.echotech.famousity.client;

import java.util.List;

import org.echotech.famousity.shared.FbFriend;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>FacebookServiceAsync</code>.
 */
public interface FacebookServiceAsync {
    public void login(String authToken, AsyncCallback<String> asyncCallback);

    public void findFriends(String authToken, AsyncCallback<List<FbFriend>> asyncCallback);
}
