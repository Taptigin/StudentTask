package com.mycomp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Text;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
public class TableGWT implements EntryPoint {

    private DataGrid<UsersEntity> table = new DataGrid<UsersEntity>();
    private List<UsersEntity> list = new ArrayList<>();


    public void onModuleLoad() {

        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);

        TableServiceAsync swc = GWT.create(TableService.class);

        AsyncCallback<List<UsersEntity>> callback = new AsyncCallback<List<UsersEntity>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Сервис не запустился.");

            }

            @Override
            public void onSuccess(List<UsersEntity> result) {

                table.addColumn(getFirstName(),"Имя");
                table.addColumn(getMiddleName(),"Отчество");
                table.addColumn(getLastName(),"Фамилия");



                table.setRowCount(result.size(), true);
                table.setRowData(0,result);
                table.setWidth("100%");

                LayoutPanel panel = new LayoutPanel();


                panel.add(table);



                RootLayoutPanel.get().add(panel);

                //Window.alert(result.get(5000).toString());

            }
        };


        swc.getAll(callback);







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






}
