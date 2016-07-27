package com.mycomp.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mycomp.UsersEntity;

import java.util.List;

/**
 * Created by Александр on 27.07.2016.
 */

@RemoteServiceRelativePath("GwtTable")
public interface GwtTableService extends RemoteService {
    List<UsersEntity> getAll();
}
