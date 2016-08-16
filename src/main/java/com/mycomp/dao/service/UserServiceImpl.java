package com.mycomp.dao.service;

import com.mycomp.dao.UserService;
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
    private UserService dao;


    @Override
    public List<User> getAll(int firstId, int lastId, String columnName, boolean isAscending) {
        return dao.getAll(firstId, lastId, columnName, isAscending);
    }

    @Override
    public Long getRowCount() {
        return dao.getRowCount();
    }
}
