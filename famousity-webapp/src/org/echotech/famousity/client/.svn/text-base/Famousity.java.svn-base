package org.echotech.famousity.client;

import java.util.List;

import org.echotech.famousity.shared.FacebookUtil;
import org.echotech.famousity.shared.FbFriend;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Header;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Text;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Famousity implements EntryPoint {

    private final FacebookServiceAsync facebookService = GWT.create(FacebookService.class);
    private final GoogleServiceAsync googleService = GWT.create(GoogleService.class);

    private MessageBox messageBox;

    public void onModuleLoad() {
        final String authToken = Location.getParameter("code");
        final String error = Location.getParameter("error_reason");

        if (null != error && error.equals("user_denied")) {
            userDenied();
        } else if (authToken == null || "".equals(authToken)) {
            redirect(FacebookUtil.getAuthorizeUrl());
        } else {
            messageBox = MessageBox.wait("Famousity", "", "Analyzing friend rankings...");

            facebookService.login(authToken, new AsyncCallback<String>() {
                public void onFailure(final Throwable caught) {
                    handleError(caught);
                }

                public void onSuccess(final String authToken) {
                    rankFriends(authToken);
                }
            });
        }
    }

    private void userDenied() {
        MessageBox.confirm("Error authrizing famousity",
                "You have deined famousity from installing correctly, click 'Yes' to try again",
                new Listener<MessageBoxEvent>() {

                    public void handleEvent(final MessageBoxEvent be) {
                        if (be.getButtonClicked().getText().equals("Yes")) {
                            redirect(FacebookUtil.getAuthorizeUrl());
                        } else {
                            be.getMessageBox().close();
                        }
                    }
                });
    }

    private void rankFriends(final String authToken) {

        final RootPanel rootPanel = RootPanel.get();
        facebookService.findFriends(authToken, new AsyncCallback<List<FbFriend>>() {
            public void onFailure(final Throwable caught) {
                Window.alert(caught.getMessage());
            }

            public void onSuccess(final List<FbFriend> friends) {
                closeMessageBox();
                final Header header = new Header();
                header.setText("How famous are your friends");
                rootPanel.add(header);

                for (final FbFriend friend : friends) {
                    final Panel friendPanel = new HorizontalPanel();

                    friendPanel.setStyleName("panel");
                    final Image profilePic = new Image(getProfilePictureUrl(friend));
                    profilePic.setStyleName("profilePic");
                    friendPanel.add(profilePic);
                    friendPanel.add(new Hidden(friend.getId().toString()));
                    friendPanel.add(new Text(friend.getName()));
                    final Text countText = new Text("");
                    friendPanel.add(countText);
                    rootPanel.add(friendPanel);

                    googleService.findFriendRanking(friend, new AsyncCallback<Long>() {

                        public void onFailure(final Throwable caught) {
                            handleError(caught);
                        }

                        public void onSuccess(final Long result) {
                            if (result > 0) {
                                countText.setText(result.toString());
                            } else {
                                countText.getParent().setVisible(false);
                            }
                        }
                    });
                }
            }

        });

    }

    private void handleError(final Throwable caught) {
        Window.alert(caught.getMessage());
    }

    private String getProfilePictureUrl(final FbFriend friend) {
        return "http://graph.facebook.com/" + friend.getId() + "/picture";
    }

    private void closeMessageBox() {
        if (messageBox != null) {
            messageBox.close();
            messageBox = null;
        }
    }

    // redirect the browser to the given url
    public static native void redirect(String url)/*-{
		$wnd.location = url;
    }-*/;
}
