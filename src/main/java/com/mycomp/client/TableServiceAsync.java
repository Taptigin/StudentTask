package com.mycomp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface TableServiceAsync {
    void getAll(AsyncCallback<List<UsersEntity>> callback);
}
