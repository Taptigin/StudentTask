package com.mycomp.service;

import com.mycomp.model.User;

import java.util.List;

/**
 * Created by Alexandr on 20.07.2016.
 * Service for pulling from the database records and the number of records stored in the database.
 */
public interface UserService {
    public List<User> getAll(int startIndex, int pageSize, String columnName, boolean isAscending);

    public Long getRowCount();
}
