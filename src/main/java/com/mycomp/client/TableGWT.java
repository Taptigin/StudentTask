package com.mycomp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
public class TableGWT implements EntryPoint {

    private String s;
    private final FlexTable t = new FlexTable();


    public void onModuleLoad() {

        TableServiceAsync swc = GWT.create(TableService.class);

        AsyncCallback<List<UsersEntity>> callback = new AsyncCallback<List<UsersEntity>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Fail getAllUsers");
            }

            @Override
            public void onSuccess(List<UsersEntity> result) {

                s = result.get(1).toString();
                Window.alert(s);

                for (int i = 0; i < 250; i++) {
                    t.setText(i,1,result.get(i).getFirstName());
                    t.setText(i,2,result.get(i).getLastName());
                    t.setText(i,3,result.get(i).getSex());
                    t.setText(i,4,result.get(i).getEnrollmentDate().toString());

                }


            }
        };

        swc.getAll(callback);

        // Put some text at the table's extremes.  This forces the table to be
        // 3 by 3.
        t.setText(0, 0, "upper-left corner");
        t.setText(2, 2, "bottom-right corner");

        // Let's put a button in the middle...
        t.setWidget(1, 0, new Button("Wide Button"));

        // ...and set it's column span so that it takes up the whole row.
        t.getFlexCellFormatter().setColSpan(1, 0, 3);



        t.setText(2,2,s);
        RootPanel.get().add(t);

    }






}
