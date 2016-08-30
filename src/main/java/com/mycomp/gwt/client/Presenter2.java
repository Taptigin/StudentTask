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
 * Created by Александр on 30.08.2016.
 */
public class Presenter2 {
    View view;
    boolean isInit;

    public interface View extends IsWidget{
        void setDataProvider(AbstractDataProvider<UserDTO> provider);
        ColumnSortList.ColumnSortInfo getSortInfo();
    }

    public Presenter2(View view) {
        this.view = view;
    }

    void go(HasWidgets container){
        if(!isInit){
            view.setDataProvider(new DataProvider());
            isInit = true;
        }
        container.add(view.asWidget());
    }

    public class DataProvider extends AsyncDataProvider<UserDTO>{

        @Override
        protected void onRangeChanged(HasData<UserDTO> display) {
            Range range = display.getVisibleRange();
            final int start = range.getStart();
            int length = range.getLength();

            ColumnSortList.ColumnSortInfo sortInfo = view.getSortInfo();

            String columnSortName = "id";
            boolean isAscending = true;

            if (sortInfo != null) {
                columnSortName = sortInfo.getColumn().getDataStoreName();
                isAscending = sortInfo.isAscending();
            }


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
                    //table.setRowCount(result.intValue());
                    updateRowCount(result.intValue(),true);

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
             * Вызов ветода из GWT сервиса.
             *          @param start C какой записи будут данные выводится в таблицу.
             *          @param length По какую будут данные выводится в таблицу.
             *          @param columnSortName сортировка
             *          @param isAscending направление сортировки
             */
            swc.getAll(start, length, columnSortName, isAscending, callback);
        }
    }
}
