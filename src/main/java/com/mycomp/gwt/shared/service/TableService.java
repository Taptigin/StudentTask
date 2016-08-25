package com.mycomp.gwt.shared.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mycomp.gwt.shared.UserDTO;

import java.util.List;

/**
 * Асинхронный сервис для подтягивания данных из БД.
 * <p>
 * Created by Александр on 28.07.2016.
 */
@RemoteServiceRelativePath("TableService")
public interface TableService extends RemoteService {
    /**
     * Метод получения данных из БД.
     *
     * @param startIndex     Стартовый индекс с которого начинают запрашиваться данные.
     * @param pageSize       Длинна выборки.
     * @param columnSortName Имя столбца по которому производится сортировка.
     * @param isAscending    Направление сортировки asc/desc.
     * @return Возвращает коллекцию с данными типа User.
     */
    List<UserDTO> getAll(int startIndex, int pageSize, String columnSortName, boolean isAscending);

    /**
     * Метод получения количества записей взятых из БД.
     *
     * @return количество записей взятых из БД.
     */
    Long getRowCount();

}
