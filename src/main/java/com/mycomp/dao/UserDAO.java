package com.mycomp.dao;

import com.mycomp.model.User;

import java.util.List;

/**
 * Спринговый интерфейс для обращения к БД.
 * <p>
 * Created by Александр on 19.08.2016.
 */
public interface UserDAO {
    /**
     * Метод получения данных из БД.
     *
     * @param startIndex  Стартовый индекс с которого начинают запрашиваться данные.
     * @param pageSize    Длинна выборки.
     * @param columnName  Имя столбца по которому производится сортировка.
     * @param isAscending Направление сортировки asc/desc.
     * @return Возвращает коллекцию с данными типа User.
     */
    public List<User> getAll(int startIndex, int pageSize, String columnName, boolean isAscending);

    /**
     * Метод получения количества записей взятых из БД.
     *
     * @return количество записей взятых из БД.
     */
    public Long getRowCount();
}
