package com.mycomp.dao;

import com.mycomp.model.User;

import java.util.List;

/**
 * Spring interface to access the database.
 * <p>
 * Created by Alexandr on 19.08.2016.
 */
public interface UserDAO {
    /**
     * A method of obtaining data from the database.
     *
     * @param startIndex  The starting index from which begin the requested data.
     * @param pageSize    The sample length.
     * @param columnName  The name of the column on which to sort.
     * @param isAscending The direction of the sort asc/desc.
     * @return Returns a collection with the data type User.
     */
    public List<User> getAll(int startIndex, int pageSize, String columnName, boolean isAscending);

    /**
     * A method of obtaining the number of records taken from DB.
     *
     * @return the number of records taken from DB.
     */
    public Long getRowCount();
}
