package com.mycomp.GWT.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mycomp.GWT.shared.UsersEntityDTO;

import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
@RemoteServiceRelativePath("TableService")
public interface TableService extends RemoteService {
    List<UsersEntityDTO> getAll(int firstId, int lastId);

    Long getRowCount();

}
