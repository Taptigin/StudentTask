package com.mycomp.gwt.shared.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mycomp.gwt.shared.UserDTO;

import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 * Асинхронный сервис для подтягивания данных из БД.
 */
@RemoteServiceRelativePath("TableService")
public interface TableService extends RemoteService {
    List<UserDTO> getAll(int firstId, int lastId, String columnSortName, boolean isAscending);

    Long getRowCount();

}
