package com.mycomp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.mycomp.UsersEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
public class TableGWT implements EntryPoint {

private TableServiceAsync swc = GWT.create(TableService.class);
    public void onModuleLoad() {
        // Tables have no explicit size -- they resize automatically on demand.
        FlexTable t = new FlexTable();

        // Put some text at the table's extremes.  This forces the table to be
        // 3 by 3.
        t.setText(0, 0, "upper-left corner");
        t.setText(2, 2, "bottom-right corner");

        // Let's put a button in the middle...
        t.setWidget(1, 0, new Button("Wide Button"));

        // ...and set it's column span so that it takes up the whole row.
        t.getFlexCellFormatter().setColSpan(1, 0, 3);

        AsyncCallback<List<UsersEntity>> callback = new AsyncCallback<List<UsersEntity>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<UsersEntity> result) {
                String s = result.get(0).toString();
                t.setText(2,2,s);

            }
        };

        RootPanel.get().add(t);

    }




}
