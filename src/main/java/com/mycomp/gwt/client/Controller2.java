package com.mycomp.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Class controller connects the view and the presenter.
 *
 * Created by Александр on 30.08.2016.
 */
public class Controller2 implements EntryPoint {
    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        View2 view = new View2();
        Presenter2 presenter = new Presenter2(view);
        presenter.go(RootLayoutPanel.get());
    }
}
