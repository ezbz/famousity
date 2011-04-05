package org.echotech.famousity.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FbFriend implements IsSerializable {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
