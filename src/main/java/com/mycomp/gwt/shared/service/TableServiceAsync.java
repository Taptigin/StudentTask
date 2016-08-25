package com.mycomp.gwt.shared.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mycomp.gwt.shared.UserDTO;

import java.util.List;

/**
 * Интерфейс для GWT сервиса.
 * <p>
 * Created by Александр on 28.07.2016.
 */
public interface TableServiceAsync {
    /**
     * Метод получения данных из БД.
     *
     * @param startIndex     Стартовый индекс с которого начинают запрашиваться данные.
     * @param pageSize       Длинна выборки.
     * @param columnSortName Имя столбца по которому производится сортировка.
     * @param isAscending    Направление сортировки asc/desc.
     * @return Возвращает коллекцию с данными типа User.
     */
    void getAll(int startIndex, int pageSize, String columnSortName, boolean isAscending, AsyncCallback<List<UserDTO>> callback);

    /**
     * Метод получения количества записей взятых из БД.
     *
     * @return количество записей взятых из БД.
     */
    void getRowCount(AsyncCallback<Long> async);
}
