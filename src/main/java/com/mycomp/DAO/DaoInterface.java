package com.mycomp.DAO;

import com.mycomp.EntityModel.UsersEntity;

import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 */
public interface DaoInterface {

    public List<UsersEntity> getAll(int firstId, int lastId, String columnName, boolean isAscending);

    public Long getRowCount();
}
