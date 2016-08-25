package com.mycomp.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Класс контроллер соединяющий вьюху и презентер.
 * <p>
 * Created by Александр on 24.08.2016.
 */
public class Controller implements EntryPoint {
    /**
     * Метод запускающий создание gui.
     * Сначала создаются "пустые" элементы,
     * затем презентер их заполняет данными
     * и происходит вывод на экран.
     */
    @Override
    public void onModuleLoad() {
        UserView view = new UserView();
        view.createPager();
        view.createTable();

        UserPresenter presenter = new UserPresenter(view.getTable());
        presenter.start();

        DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);
        panel.addNorth(new HTMLPanel("h1", "Список студентов"), 60);
        panel.addNorth(view.getPager(), 70);
        panel.add(presenter.getTable());
        RootLayoutPanel.get().add(panel);

    }
}
