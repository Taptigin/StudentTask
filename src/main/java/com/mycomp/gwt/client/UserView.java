package com.mycomp.gwt.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AbstractDataProvider;
import com.mycomp.gwt.shared.UserDTO;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Create View class.
 *
 * Created by Alexandr on 30.08.2016.
 */
public class UserView implements  UserPresenter.View{
    private DataGrid<UserDTO> table = new DataGrid<>();
    private DockLayoutPanel panel;
    private SimplePager pager;
    static Logger logger = Logger.getLogger("test");


    public UserView() {
        panel = createDataGrid();
    }

    // Create a Pager to control the table.
    public void createPager() {
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);

        pager.setDisplay(table);
        pager.setPageSize(50);

    }
    // Create table
    private DockLayoutPanel createDataGrid() {
        logger.addHandler(new ConsoleLogHandler());
        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        // The add column displays the name.
        table.addColumn(getFirstName(), "Имя");
        //The add column displays the middle name.
        table.addColumn(getMiddleName(), "Отчество");
        //The add column displays the last name.
        table.addColumn(getLastName(), "Фамилия");
        //The add column displays the age.
        table.addColumn(getAge(), "Возраст");
        //The add column displays the sex.
        table.addColumn(getSex(), "Пол");
        //The add column displays the enrollment date.
        table.addColumn(getEnrollmentDate(), "Дата поступления");
        //The add column displays the release date.
        table.addColumn(getReleaseDate(), "Дата окончания");
        //The add column displays the group name.
        table.addColumn(getGroupName(), "Группа");
        //The add column displays the faculty name.
        table.addColumn(getFacultyName(), "Факультет");

        table.setAutoHeaderRefreshDisabled(true);

        table.setWidth("100%");

        createPager();

        logger.info("table created");

        //Add the Handler responsible for sorting.
        ColumnSortEvent.AsyncHandler handler = new
                ColumnSortEvent.AsyncHandler(table);
        table.addColumnSortHandler(handler);

        panel = new DockLayoutPanel(Style.Unit.PX);
        panel.addNorth(new HTMLPanel("h1", "Список студентов"), 60);
        panel.addNorth(pager, 70);
        panel.add(table);

        return panel;

    }
    //Connection data provider
    @Override
    public void setDataProvider(AbstractDataProvider<UserDTO> provider) {
        provider.addDataDisplay(table);
    }

    //Add sorting
    @Override
    public ColumnSortList.ColumnSortInfo getSortInfo() {
        ColumnSortList sortList = table.getColumnSortList();
        if (sortList.size() > 0) return sortList.get(0);
        else return null;


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
     * Create columns for the table, set them names, give permission to the sorting.
     *
     * @return  {@link Column}
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
