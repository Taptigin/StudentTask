package com.mycomp.gwt.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AbstractDataProvider;
import com.mycomp.gwt.shared.UserDTO;

import java.util.Date;

/**
 * Created by Александр on 30.08.2016.
 */
public class View2 implements  Presenter2.View{
    private DataGrid<UserDTO> table = new DataGrid<>();
    private DockLayoutPanel panel;
    private SimplePager pager;

    public View2() {
        panel = createDataGrid();
    }

    public void createPager() {
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);

        pager.setDisplay(table);
        pager.setPageSize(50);

    }

    private DockLayoutPanel createDataGrid() {
        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        table.addColumn(getFirstName(), "Имя");
        table.addColumn(getMiddleName(), "Отчество");
        table.addColumn(getLastName(), "Фамилия");
        table.addColumn(getAge(), "Возраст");
        table.addColumn(getSex(), "Пол");
        table.addColumn(getEnrollmentDate(), "Дата поступления");
        table.addColumn(getReleaseDate(), "Дата окончания");
        table.addColumn(getGroupName(), "Группа");
        table.addColumn(getFacultyName(), "Факультет");

        table.setAutoHeaderRefreshDisabled(true);

        table.setWidth("100%");
        createPager();

        ColumnSortEvent.AsyncHandler handler = new
                ColumnSortEvent.AsyncHandler(table);
        table.addColumnSortHandler(handler);

        panel = new DockLayoutPanel(Style.Unit.PX);
        panel.addNorth(new HTMLPanel("h1", "Список студентов"), 60);
        panel.addNorth(pager, 70);
        panel.add(table);

        return panel;

    }

    @Override
    public void setDataProvider(AbstractDataProvider<UserDTO> provider) {
        provider.addDataDisplay(table);
    }

    @Override
    public ColumnSortList.ColumnSortInfo getSortInfo() {
        ColumnSortList sortList = table.getColumnSortList();
        return sortList.size() > 0 ? sortList.get(0) : null;

    }

    @Override
    public Widget asWidget() {
        return panel;
    }

    private TextColumn<UserDTO> getLastName() {
        TextColumn<UserDTO> lastName = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO object) {
                return object.getLastName();
            }
        };
        lastName.setSortable(true);
        lastName.setDataStoreName("lastName");
        return lastName;
    }

    private TextColumn<UserDTO> getFirstName() {
        TextColumn<UserDTO> firstName = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO object) {
                return object.getFirstName();
            }
        };
        firstName.setSortable(true);
        firstName.setDataStoreName("firstName");
        return firstName;
    }

    private TextColumn<UserDTO> getMiddleName() {
        TextColumn<UserDTO> middleName = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO object) {
                return object.getMiddleName();
            }
        };
        middleName.setSortable(true);
        middleName.setDataStoreName("middleName");
        return middleName;
    }

    private TextColumn<UserDTO> getSex() {
        TextColumn<UserDTO> sex = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO object) {
                return object.getSex();
            }
        };
        sex.setSortable(true);
        sex.setDataStoreName("sex");
        return sex;
    }

    /**
     * Создаем колонки для таблицы, устанавливаем им имена, даём разрешение на сортировку.
     *
     * @return Возвращает {@link Column}
     */
    private Column<UserDTO, Date> getEnrollmentDate() {
        DateCell dateCell = new DateCell();
        Column<UserDTO, Date> enrollmentDate = new Column<UserDTO, Date>(dateCell) {
            @Override
            public Date getValue(UserDTO object) {
                return object.getEnrollmentDate();
            }
        };
        enrollmentDate.setSortable(true);
        enrollmentDate.setDataStoreName("enrollmentDate");
        return enrollmentDate;
    }

    private Column<UserDTO, Date> getReleaseDate() {
        DateCell dateCell = new DateCell();
        Column<UserDTO, Date> releaseDate = new Column<UserDTO, Date>(dateCell) {
            @Override
            public Date getValue(UserDTO object) {
                return object.getReleaseDate();
            }
        };
        releaseDate.setSortable(true);
        releaseDate.setDataStoreName("releaseDate");
        return releaseDate;
    }

    private TextColumn<UserDTO> getGroupName() {
        TextColumn<UserDTO> groupName = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO object) {
                return object.getGroupName();
            }
        };
        groupName.setSortable(true);
        groupName.setDataStoreName("groupName");
        return groupName;
    }

    private TextColumn<UserDTO> getAge() {
        TextColumn<UserDTO> age = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO object) {
                return object.getAge().toString();
            }
        };
        age.setSortable(true);
        age.setDataStoreName("age");
        return age;
    }

    private TextColumn<UserDTO> getFacultyName() {
        TextColumn<UserDTO> facultyName = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO object) {
                return object.getFacultyName();
            }
        };
        facultyName.setSortable(true);
        facultyName.setDataStoreName("facultyName");
        return facultyName;
    }
}
