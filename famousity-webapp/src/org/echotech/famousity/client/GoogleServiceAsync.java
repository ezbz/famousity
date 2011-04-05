package org.echotech.famousity.client;

import org.echotech.famousity.shared.FbFriend;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GoogleService</code>.
 */
public interface GoogleServiceAsync {
	public void findFriendRanking(FbFriend friend,
			final AsyncCallback<Long> asyncCallback);
}
