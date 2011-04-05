package org.echotech.famousity.server;

import java.util.LinkedList;
import java.util.List;

import org.echotech.famousity.shared.FbFriend;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FBFriends implements IsSerializable {

    private List<FbFriend> data = new LinkedList<FbFriend>();

    public List<FbFriend> getData() {
        return data;
    }

    public void setData(final List<FbFriend> data) {
        this.data = data;
    }

}
