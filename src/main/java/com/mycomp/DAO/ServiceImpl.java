package com.mycomp.DAO;

import com.mycomp.EntityModel.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 * Имплементация ДАО сервиса.
 */
@Service("storageService")
public class ServiceImpl implements DaoService {

    @Autowired
    private DaoService dao;


    @Override
    public List<UsersEntity> getAll(int firstId, int lastId, String columnName, boolean isAscending) {
        return dao.getAll(firstId, lastId, columnName, isAscending);
    }

    @Override
    public Long getRowCount() {
        return dao.getRowCount();
    }
}
