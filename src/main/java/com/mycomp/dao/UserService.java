package com.mycomp.dao;

import com.mycomp.model.User;

import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 * Сервис для вытаскивания из базы данных записей и количества записей хранящихся в БД.
 */
public interface UserService {
    public List<User> getAll(int firstId, int lastId, String columnName, boolean isAscending);

    public Long getRowCount();
}