package com.mycomp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mycomp.UsersEntity;

import java.util.List;

public interface GwtTableServiceAsync {
    void getAll(AsyncCallback <List<UsersEntity>> callback);


}
