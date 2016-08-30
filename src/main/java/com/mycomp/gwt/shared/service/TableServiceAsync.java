package com.mycomp.gwt.shared.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mycomp.gwt.shared.UserDTO;

import java.util.List;

/**
 * Interface for GWT service.
 * <p>
 * Created by Alexandr on 28.07.2016.
 */
public interface TableServiceAsync {
    /**
     * A method of obtaining data from the database.
     *
     * @param startIndex     The starting index from which begin the requested data.
     * @param pageSize       The sample length.
     * @param columnSortName The name of the column on which to sort.
     * @param isAscending    The direction of the sort asc/desc.
     * @return Returns a collection with the data type User.
     */
    void getAll(int startIndex, int pageSize, String columnSortName, boolean isAscending, AsyncCallback<List<UserDTO>> callback);

    /**
     * A method of obtaining the number of records taken from DB.
     *
     * @return the number of records taken from DB.
     */
    void getRowCount(AsyncCallback<Long> async);
}
