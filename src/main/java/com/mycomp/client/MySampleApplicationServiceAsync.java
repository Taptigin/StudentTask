package com.mycomp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mycomp.UsersEntity;

import java.util.List;

public interface MySampleApplicationServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
    void getRow (AsyncCallback<String> callback);
    void allRows (AsyncCallback<List<UsersEntity>> callbackAllRec);
}
