package com.mycomp.DAO;

import com.mycomp.shared.UsersEntity;

import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 */
public interface DaoService {
    public List<UsersEntity> getAll(int firstId, int lastId);

    public Long getRowCount();
}
