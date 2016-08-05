package com.mycomp.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.DefaultSelectionModel;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.mycomp.shared.UsersEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
public class TableGWT implements EntryPoint {

    private DataGrid<UsersEntity> table = new DataGrid<UsersEntity>();
    SimplePager pager;
    Integer rowCount;



    public void onModuleLoad() {

        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        table.setAutoHeaderRefreshDisabled(true);
        TableServiceAsync swc = GWT.create(TableService.class);

        AsyncCallback<List<UsersEntity>> callback = new AsyncCallback<List<UsersEntity>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Сервис не запустился.");

            }

            @Override
            public void onSuccess(List<UsersEntity> result) {


                ColumnSortEvent.ListHandler<UsersEntity> sortHandler = new ColumnSortEvent.ListHandler<>(result);
                table.addColumnSortHandler(sortHandler);

                SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
                pager = new SimplePager(SimplePager.TextLocation.CENTER,pagerResources,false,0,true);
                pager.setDisplay(table);



                final SelectionModel selectionModel = new MultiSelectionModel<UsersEntity>(); //!!!!!!!!
                table.setSelectionModel(selectionModel, DefaultSelectionEventManager.<UsersEntity>createCheckboxManager());

                table.addColumn(getFirstName(),"Имя");
                table.addColumn(getMiddleName(),"Отчество");
                table.addColumn(getLastName(),"Фамилия");
                table.addColumn(getSex(),"Пол");
                table.addColumn(getEnrollmentDate(),"Дата поступления");
                table.addColumn(getReleaseDate(),"Дата окончания");



                table.setRowCount(rowCount, true);
                table.setRowData(0,result);
                table.setWidth("100%");

                LayoutPanel panel = new LayoutPanel();


                panel.add(table);

                panel.add(pager);




                RootLayoutPanel.get().add(panel);


               // Window.alert(result.get(5).toString());

            }
        };

        AsyncCallback<Long> asyncCallback = new AsyncCallback<Long>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Не сработало возвращение RowCount");
            }

            @Override
            public void onSuccess(Long result) {
                rowCount = result.intValue();

            }
        };


        swc.getRowCount(asyncCallback);
        swc.getAll(0,10000,callback);









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




}
