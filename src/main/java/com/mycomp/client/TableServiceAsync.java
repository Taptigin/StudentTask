package com.mycomp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mycomp.shared.UsersEntity;

import java.util.List;

public interface TableServiceAsync {
    void getAll(AsyncCallback<List<UsersEntity>> callback);
}
