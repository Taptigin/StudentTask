package com.mycomp.gwt.shared.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mycomp.gwt.shared.UserDTO;

import java.util.List;

public interface TableServiceAsync {
    void getAll(int firstId, int lastId, String columnSortName, boolean isAscending , AsyncCallback<List<UserDTO>> callback);


    void getRowCount(AsyncCallback<Long> async);
}
