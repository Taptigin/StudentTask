package com.mycomp.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.mycomp.gwt.shared.UserDTO;
import com.mycomp.gwt.shared.service.TableService;
import com.mycomp.gwt.shared.service.TableServiceAsync;

import java.util.List;
import java.util.logging.Level;

/**
 * Created by Александр on 17.08.2016.
 * Класс аснхронного дата провайдера extends {@link AsyncDataProvider}
 * Необходим для асинхронной работы с БД.
 */
public class UserPresenter extends AsyncDataProvider<UserDTO> {
    private DataGrid<UserDTO> table;


    public UserPresenter(DataGrid<UserDTO> table) {
        this.table = table;
    }

    void start() {


        addDataDisplay(table);

    }


    public DataGrid<UserDTO> getTable() {
        return table;
    }

    /**
     * Метод обращающийся к асинхронным сервисам gwt, при изменении рейнджа таблицы.
     */
    @Override
    protected void onRangeChanged(HasData<UserDTO> display) {
        Range range = display.getVisibleRange();
        final int start = range.getStart();
        int length = range.getLength();

        String columnSortName = UserView.getColumnSortName();
        boolean isAscending = UserView.isAscending();


        TableServiceAsync swc = GWT.create(TableService.class);

        AsyncCallback<Long> asyncCallback = new AsyncCallback<Long>() {
            @Override
            public void onFailure(Throwable caught) {
                UserView.logger.log(Level.ALL, "Не сработало возвращение RowCount");
                GWT.log("Не сработало возвращение RowCount");
            }

            /**
             *
             * @param result Результат, содержащий общее количество строк вернувшихся из запроса к бд.
             *               Нужен для установления у таблицы длинны.
             */
            @Override
            public void onSuccess(Long result) {
                table.setRowCount(result.intValue());

            }
        };
        swc.getRowCount(asyncCallback);

        AsyncCallback<List<UserDTO>> callback = new AsyncCallback<List<UserDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                //Window.alert("Callback not work");
                UserView.logger.log(Level.ALL, "Callback not work");
                GWT.log("Callback not work");
            }

            /**
             *
             * @param result Результат содержашй записи в формате DTO полученные из БД.
             */
            @Override
            public void onSuccess(List<UserDTO> result) {

                updateRowData(start, result);

            }
        };

        /**
         * Вызом ветода из GWT сервиса.
         *          @param start C какой записи будут данные выводится в таблицу.
         *          @param length По какую будут данные выводится в таблицу.
         *          @param columnSortName сортировка
         *          @param isAscending направление сортировки
         */
        swc.getAll(start, length, columnSortName, isAscending, callback);


    }


}
