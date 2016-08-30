package com.mycomp.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Class controller connects the view and the presenter.
 *
 * Created by Александр on 30.08.2016.
 */
public class UserController implements EntryPoint {
    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        UserView view = new UserView();
        UserPresenter presenter = new UserPresenter(view);
        presenter.go(RootLayoutPanel.get());
    }
}
