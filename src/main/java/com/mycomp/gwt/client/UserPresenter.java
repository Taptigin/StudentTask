package com.mycomp.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.mycomp.gwt.shared.UserDTO;
import com.mycomp.gwt.shared.service.TableService;
import com.mycomp.gwt.shared.service.TableServiceAsync;

import java.util.List;
import java.util.logging.Level;

/**
 * Create presenter class.
 * <p>
 * Created by Alexandr on 30.08.2016.
 */
public class UserPresenter {
    View view;
    boolean isInit;

    public UserPresenter(View view) {
        this.view = view;
    }

    /**
     * initializes provider
     *
     * @param container used Widget for image display
     */

    void go(HasWidgets container) {
        if (!isInit) {
            view.setDataProvider(new DataProvider());
            isInit = true;
        }
        container.add(view.asWidget());
    }

    public interface View extends IsWidget {

        /**
         * Create provider for View
         *
         * @param provider the provider is used
         */

        void setDataProvider(AbstractDataProvider<UserDTO> provider);

        /**
         * Get a ColumnSortList.ColumnSortInfo for View
         *
         * @return an  instance
         */

        ColumnSortList.ColumnSortInfo getSortInfo();
    }

    /**
     * Create Provider class
     */
    public class DataProvider extends AsyncDataProvider<UserDTO> {

        @Override
        protected void onRangeChanged(HasData<UserDTO> display) {
            Range range = display.getVisibleRange();
            final int start = range.getStart();
            int length = range.getLength();

            ColumnSortList.ColumnSortInfo sortInfo = view.getSortInfo();

            // Column's  name for sort
            String columnSortName = "id";
            // the direction of the sort
            boolean isAscending = true;

            if (sortInfo != null) {
                columnSortName = sortInfo.getColumn().getDataStoreName();
                isAscending = sortInfo.isAscending();
            }


            TableServiceAsync swc = GWT.create(TableService.class);
            // get number of all records
            AsyncCallback<Long> asyncCallback = new AsyncCallback<Long>() {
                @Override
                public void onFailure(Throwable caught) {
                    UserView.logger.log(Level.ALL, "Не сработало возвращение RowCount");
                    GWT.log("Не сработало возвращение RowCount");
                }

                /**
                 *
                 * @param result The result that contains the total number of rows returned from a database query.
                 *               Needed to set the table length.
                 */
                @Override
                public void onSuccess(Long result) {
                    updateRowCount(result.intValue(), true);

                }
            };
            swc.getRowCount(asyncCallback);
            // retrieving data from the database.
            AsyncCallback<List<UserDTO>> callback = new AsyncCallback<List<UserDTO>>() {
                @Override
                public void onFailure(Throwable caught) {
                    UserView.logger.log(Level.ALL, "Callback not work");
                    GWT.log("Callback not work");
                }

                /**
                 *
                 * @param result The result contains entries in the format of DTO received from a DB.
                 */
                @Override
                public void onSuccess(List<UserDTO> result) {

                    updateRowData(start, result);

                }
            };

            /**
             * Calling a method from a GWT service.
             *          @param start What records will be displayed in the data table.
             *          @param length Which the data will be displayed in the table.
             *          @param columnSortName sort.
             *          @param isAscending the direction of the sort.
             */
            swc.getAll(start, length, columnSortName, isAscending, callback);
        }
    }
}
