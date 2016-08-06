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

    DataGrid<UsersEntity> table = new DataGrid<UsersEntity>();
    SimplePager pager;
    Long rowCount;

    TableServiceAsync swc = GWT.create(TableService.class);
    List<UsersEntity> list = new ArrayList<>();



    private List<UsersEntity> getData(int firstId,int lastId){

        AsyncCallback<List<UsersEntity>> callback = new AsyncCallback<List<UsersEntity>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Сервис не запустился.");
            }

            @Override
            public void onSuccess(List<UsersEntity> result) {
                 list = result; Window.alert(list.get(2).toString());
            }
        };
        swc.getAll(firstId,lastId,callback); Window.alert("callBack");
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

        table.setRowCount(rowCount.intValue(), true);
        table.setWidth("100%");
        updateTable(1,50);


        table.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {
                updateTable(pager.getDisplay().getVisibleRange().getStart(),
                        pager.getDisplay().getVisibleRange().getLength()-pager.getDisplay().getVisibleRange().getStart());
            }
        });

        Window.alert("pager" + pager.getDisplay().getVisibleRange().getStart() + " " +
                pager.getDisplay().getVisibleRange().getStart());
    }

    private void createPager(){
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);

        pager.setDisplay(table);
        pager.setPageSize(50);
    }

    public void onModuleLoad() {

        getRowCount(); Window.alert("rowCount sucsess");

        createTable(); Window.alert("Table created");
        createPager(); Window.alert("pager created");

        DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);

        panel.addNorth(new HTMLPanel("h1", "Список студентов"), 60);
        panel.addNorth(table,500);
        panel.add(pager);

        RootLayoutPanel.get().add(panel);



            }


        private void getRowCount(){
            AsyncCallback<Long> asyncCallback = new AsyncCallback<Long>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert("Не сработало возвращение RowCount");
                }

                @Override
                public void onSuccess(Long result) {
                    rowCount = result;

                }
            };


            swc.getRowCount(asyncCallback);
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





}
