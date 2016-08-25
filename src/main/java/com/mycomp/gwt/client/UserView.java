package com.mycomp.gwt.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.view.client.RangeChangeEvent;
import com.mycomp.gwt.shared.UserDTO;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Александр on 28.07.2016.
 * gwt клиент для общения с пользователем.
 */
public class UserView  {

    private DataGrid<UserDTO> table = new DataGrid<>();
    static Logger logger = Logger.getLogger("test");
    private static String columnSortName = "id";
    private static boolean isAscending = true;
    private SimplePager pager;
    private ColumnSortEvent.AsyncHandler sortHandler = new ColumnSortEvent.AsyncHandler(table);


    public  DataGrid<UserDTO> getTable() {
        return table;
    }



    /**
     * Создание таблицы.
     */
    public void createTable() {
        logger.addHandler(new ConsoleLogHandler());
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

        /**
         * Метод получающий от gwt колонку по которой производится сортировка и asc/desc
         */
        table.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {

                columnSortName = table.getColumnSortList().get(0).getColumn().getDataStoreName();
                isAscending = table.getColumnSortList().get(0).isAscending();
                //provider.setAscending(isAscending);
                //provider.setColumnSortName(columnSortName);

            }
        });


        table.addColumnSortHandler(sortHandler);
        logger.info("table created");

    }

    public static String getColumnSortName() {
        return columnSortName;
    }

    public static boolean isAscending() {
        return isAscending;
    }

    /**
     * создание пейджера
     */
    public void createPager() {
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);

        pager.setDisplay(table);
        pager.setPageSize(50);

    }

    /**
     * Меотод запуска gui, в котором создаются и располагаются элементы gui на web странице.
     */
    public SimplePager getPager() {
        return pager;
    }

    /**
     * Создаем колонки для таблицы, устанавливаем им имена, даём разрешение на сортировку.
     *
     * @return Возвращает {@link TextColumn}
     */
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


