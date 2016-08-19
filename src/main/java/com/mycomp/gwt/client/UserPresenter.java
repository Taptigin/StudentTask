package com.mycomp.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.mycomp.gwt.shared.UserDTO;
import com.mycomp.gwt.shared.service.TableService;
import com.mycomp.gwt.shared.service.TableServiceAsync;

import java.util.List;

/**
 * Created by Александр on 17.08.2016.
 *
 * Класс аснхронного дата провайдера extends {@link AsyncDataProvider}
 * Необходим для асинхронной работы с БД.

 */
public class UserPresenter extends AsyncDataProvider<UserDTO> {
    String columnSortName;
    boolean isAscending;

    public UserPresenter(String columnSortName, boolean isAscending) {
        this.columnSortName = columnSortName;
        this.isAscending = isAscending;
    }

    public void setColumnSortName(String columnSortName) {
        this.columnSortName = columnSortName;
    }

    public void setAscending(boolean ascending) {
        isAscending = ascending;
    }

    @Override
    protected void onRangeChanged(HasData<UserDTO> display) {
        Range range = display.getVisibleRange();
        final int start = range.getStart();
        int length = range.getLength();


        TableServiceAsync swc = GWT.create(TableService.class);
        /**
         * Метод обращающийся к асинхронным сервисам gwt.
         */
        AsyncCallback<Long> asyncCallback = new AsyncCallback<Long>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Не сработало возвращение RowCount");
            }

            /**
             *
             * @param result Результат, содержащий общее количество строк вернувшихся из запроса к бд.
             *               Нужен для установления у таблицы длинны.
             */
            @Override
            public void onSuccess(Long result) {
                UserView.table.setRowCount(result.intValue());

            }
        };
        swc.getRowCount(asyncCallback);

        AsyncCallback<List<UserDTO>> callback = new AsyncCallback<List<UserDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Callback not work");
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
        swc.getAll(start, length + start, columnSortName, isAscending, callback);


    }



}
