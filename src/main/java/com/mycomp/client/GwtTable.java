package com.mycomp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.mycomp.UsersEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 27.07.2016.
 */
public class GwtTable implements EntryPoint {
    private GwtTableServiceAsync serviceAsync = GWT.create(GwtTableService.class);


    public void onModuleLoad() {

        AsyncCallback<List<UsersEntity>> callback = new AsyncCallback<List<UsersEntity>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<UsersEntity> result) {
                Label label = new Label(result.get(0).toString());
                RootPanel.get().add(label);

            }
        };

        serviceAsync.getAll(callback);

    }
}
