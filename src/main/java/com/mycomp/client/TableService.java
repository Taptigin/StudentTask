package com.mycomp.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mycomp.shared.UsersEntity;

import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
@RemoteServiceRelativePath("TableService")
public interface TableService extends RemoteService {
    List<UsersEntity> getAll(int firstId, int lastId);
    Integer getRowCount();

}
