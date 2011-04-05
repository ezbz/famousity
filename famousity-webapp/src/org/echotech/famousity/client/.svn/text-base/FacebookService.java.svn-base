package org.echotech.famousity.client;

import java.util.List;

import org.echotech.famousity.shared.FbFriend;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("facebook")
public interface FacebookService extends RemoteService {
    public String login(String authToken);

    public List<FbFriend> findFriends(String authToken);
}
