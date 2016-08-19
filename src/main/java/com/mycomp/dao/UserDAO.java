package com.mycomp.dao;

import com.mycomp.model.User;

import java.util.List;

/**
 * Created by Александр on 19.08.2016.
 */
public interface UserDAO {
    public List<User> getAll(int firstId, int lastId, String columnName, boolean isAscending);

    public Long getRowCount();
}
