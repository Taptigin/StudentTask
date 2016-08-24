package com.mycomp.service;

import com.mycomp.dao.UserDAO;
import com.mycomp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 * Имплементация ДАО сервиса.
 */
@Service("storageService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;


    @Override
    public List<User> getAll(int startIndex, int pageSize, String columnName, boolean isAscending) {
        return dao.getAll(startIndex, pageSize, columnName, isAscending);
    }

    @Override
    public Long getRowCount() {
        return dao.getRowCount();
    }
}
