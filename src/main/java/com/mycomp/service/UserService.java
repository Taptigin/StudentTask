package com.mycomp.service;

import com.mycomp.model.User;

import java.util.List;

/**
 * Service for pulling from the database records and the number of records stored in the database.
 *
 * Created by Alexandr on 20.07.2016.
 */
public interface UserService {
    /**
     * The method of obtaining the records from the database.
     * @param startIndex     The index at which to begin sampling.
     * @param pageSize       The size of the sample.
     * @param columnName     The name of the column to sort.
     * @param isAscending    The direction of the sort.
     * @return Returns a collection with the data type User.
     */
    public List<User> getAll(int startIndex, int pageSize, String columnName, boolean isAscending);

    /**
     * A method of obtaining the number of records taken from DB.
     * @return The number of records.
     */
    public Long getRowCount();
}
