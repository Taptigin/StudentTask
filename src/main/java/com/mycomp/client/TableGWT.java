package com.mycomp.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import com.mycomp.shared.UsersEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
public class TableGWT implements EntryPoint {

    private static DataGrid<UsersEntity> table = new DataGrid<UsersEntity>();
    private SimplePager pager;
    private Long rowCount;

    MyAsyncDataProvider dataProvider = new MyAsyncDataProvider();

    TableServiceAsync swc = GWT.create(TableService.class);
    private List<UsersEntity> list = new ArrayList<>();



    private List<UsersEntity> getData(int firstId,int lastId){

        AsyncCallback<List<UsersEntity>> callback = new AsyncCallback<List<UsersEntity>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Сервис не запустился.");
            }

            @Override
            public void onSuccess(List<UsersEntity> result) {
                 list = result;
            }
        };
        swc.getAll(firstId,lastId,callback); //Window.alert("callBack");Window.alert("last" + lastId);
        //Window.alert(list.get(lastId-5).toString());
        return list;
    }

    private void createTable(){
        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);

        table.addColumn(getFirstName(),"Имя");
        table.addColumn(getMiddleName(),"Отчество");
        table.addColumn(getLastName(),"Фамилия");
        table.addColumn(getSex(),"Пол");
        table.addColumn(getEnrollmentDate(),"Дата поступления");
        table.addColumn(getReleaseDate(),"Дата окончания");

        table.setAutoHeaderRefreshDisabled(true);

        //table.setRowCount(rowCount.intValue(), true);
        table.setWidth("100%");
        //updateTable(1,50);
        dataProvider.onRangeChanged(table);

        Window.alert("1");
        table.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {
                dataProvider.onRangeChanged(table);
            }
        });

        Window.alert("2");

//        Window.alert("pager" + pager.getDisplay().getVisibleRange().getStart() + " " +
//                pager.getDisplay().getVisibleRange().getStart());
    }

    private void createPager(){
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);

        pager.setDisplay(table);
        pager.setPageSize(50);
        Window.alert("pager" + pager.getDisplay());
    }

    public void onModuleLoad() {

        createPager();
        createTable();
        dataProvider.onRangeChanged(table);




        DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);

        panel.addNorth(new HTMLPanel("h1", "Список студентов"), 60);
        panel.addNorth(pager,70);
        panel.add(table);


        RootLayoutPanel.get().add(panel);



            }













    private TextColumn<UsersEntity> getLastName(){
        TextColumn<UsersEntity> lastName = new TextColumn<UsersEntity>() {
            @Override
            public String getValue(UsersEntity object) {
                return object.getLastName();
            }
        };
        return lastName;
    }

    private TextColumn<UsersEntity> getFirstName(){
        TextColumn<UsersEntity> firstName = new TextColumn<UsersEntity>() {
            @Override
            public String getValue(UsersEntity object) {
                return object.getFirstName();
            }
        };
        return firstName;
    }

    private TextColumn<UsersEntity> getMiddleName(){
        TextColumn<UsersEntity> middleName = new TextColumn<UsersEntity>() {
            @Override
            public String getValue(UsersEntity object) {
                return object.getMiddleName();
            }
        };
        return middleName;
    }

    private TextColumn<UsersEntity> getSex(){
        TextColumn<UsersEntity> sex = new TextColumn<UsersEntity>() {
            @Override
            public String getValue(UsersEntity object) {
                return object.getSex();
            }
        };
        return sex;
    }

    private Column<UsersEntity,Date> getEnrollmentDate(){
        DateCell dateCell = new DateCell();
        Column<UsersEntity,Date> enrollmentDate = new Column<UsersEntity, Date>(dateCell) {
            @Override
            public Date getValue(UsersEntity object) {
                return object.getEnrollmentDate();
            }
        };
        return enrollmentDate;
    }

    private Column<UsersEntity,Date> getReleaseDate(){
        DateCell dateCell = new DateCell();
        Column<UsersEntity,Date> releaseDate = new Column<UsersEntity, Date>(dateCell) {
            @Override
            public Date getValue(UsersEntity object) {
                return object.getReleaseDate();
            }
        };
        return releaseDate;
    }

    private void updateTable(int firstId, int lastId){

        Window.alert("В таблицу пришел FIRST = " + firstId + "LAST = " + lastId);
        table.setRowData(0,getData(firstId, lastId));


    }



    private static class MyAsyncDataProvider extends AsyncDataProvider<UsersEntity>{

        @Override
        protected void onRangeChanged(HasData<UsersEntity> display) {
            Range range = display.getVisibleRange();
            final int start = range.getStart();
            int length = range.getLength();

            TableServiceAsync swc = GWT.create(TableService.class);

            AsyncCallback<Long> asyncCallback = new AsyncCallback<Long>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert("Не сработало возвращение RowCount");
                }

                @Override
                public void onSuccess(Long result) {
                    table.setRowCount(result.intValue());

                }
            };
            swc.getRowCount(asyncCallback);

            AsyncCallback<List<UsersEntity>> callback = new AsyncCallback<List<UsersEntity>>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert("Сервис не запустился.");
                }

                @Override
                public void onSuccess(List<UsersEntity> result) {
                   // updateRowData(start,result);

                    table.setRowData(start,result);

                    String s = String.valueOf(result.size());
                    Window.alert(s);

                }
            };
            swc.getAll(start,length-start,callback);



        }
    }

}


