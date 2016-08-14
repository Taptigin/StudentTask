package com.mycomp.DAO;

import com.mycomp.EntityModel.UsersEntity;

import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 * Сервис для вытаскивания из базы данных записей и количества записей хранящихся в БД.
 */
public interface DaoService {
    public List<UsersEntity> getAll(int firstId, int lastId, String columnName);

    public Long getRowCount();
}
