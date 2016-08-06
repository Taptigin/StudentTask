package com.mycomp.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.layout.client.Layout;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import com.mycomp.shared.UsersEntity;

import java.util.Date;
import java.util.List;

import static com.google.gwt.dom.client.Style.Unit.PCT;

/**
 * Created by Александр on 28.07.2016.
 */
public class TableGWT implements EntryPoint {

    DataGrid<UsersEntity> table = new DataGrid<UsersEntity>();
    SimplePager pager;
    Long rowCount;
    List<UsersEntity> list;
    int counter;



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


                list = result;

                SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
                pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);


                table.addColumn(getFirstName(),"Имя");
                table.addColumn(getMiddleName(),"Отчество");
                table.addColumn(getLastName(),"Фамилия");
                table.addColumn(getSex(),"Пол");
                table.addColumn(getEnrollmentDate(),"Дата поступления");
                table.addColumn(getReleaseDate(),"Дата окончания");

                updateTable();

                table.setRowCount(rowCount.intValue(), true);

                table.addRangeChangeHandler(new RangeChangeEvent.Handler() {
                    @Override
                    public void onRangeChange(RangeChangeEvent event) {
                        updateTable();


                    }
                });
                table.setWidth("100%");

                pager.setDisplay(table);
                pager.setPageSize(50);

                DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);

                panel.addNorth(new HTMLPanel("h1", "Список студентов"), 60);
                panel.addNorth(table,500);
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
                rowCount = result;

            }
        };


        swc.getAll(1,200,callback);
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

    private void updateTable(){
        table.setRowData(0,list);

    }




}
