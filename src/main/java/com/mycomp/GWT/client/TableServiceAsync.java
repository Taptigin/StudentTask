package com.mycomp.GWT.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mycomp.GWT.shared.UsersEntityDTO;

import java.util.List;

public interface TableServiceAsync {
    void getAll(int firstId, int lastId, String columnSortName, boolean isAscending , AsyncCallback<List<UsersEntityDTO>> callback);


    void getRowCount(AsyncCallback<Long> async);
}
