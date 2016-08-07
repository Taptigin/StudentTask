package com.mycomp.shared;

import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 */
public interface DaoInterface {

    public List<UsersEntity> getAll(int firstId, int lastId);

    public Long getRowCount();
}
