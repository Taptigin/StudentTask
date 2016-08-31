package com.mycomp.service;

import com.mycomp.dao.UserDAO;
import com.mycomp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The implementation of the DAO service.
 *
 * Created by Alexandr on 20.07.2016.
 */
@Service("storageService")
public class UserServiceImpl implements UserService {
    /**
     * The location of the spring service.
     */
    @Autowired
    private UserDAO dao;

    /**
     * @see UserService
     * @param startIndex     The index at which to begin sampling.
     * @param pageSize       The size of the sample.
     * @param columnName     The name of the column to sort.
     * @param isAscending    The direction of the sort.
     * @return Returns a collection with the data type User.
     */
    @Override
    public List<User> getAll(int startIndex, int pageSize, String columnName, boolean isAscending) {
        return dao.getAll(startIndex, pageSize, columnName, isAscending);
    }

    /**
     * @see UserService
     * @return
     */
    @Override
    public Long getRowCount() {
        return dao.getRowCount();
    }
}
