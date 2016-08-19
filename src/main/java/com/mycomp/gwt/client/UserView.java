package com.mycomp.gwt.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.mycomp.gwt.shared.UserDTO;

import java.util.Date;

/**
 * Created by Александр on 28.07.2016.
 * gwt клиент для общения с пользователем.
 */
public class UserView implements EntryPoint {

    static DataGrid<UserDTO> table = new DataGrid<>();
    private static String columnSortName = "id";
    private static boolean isAscending = true;

    private SimplePager pager;
    private ColumnSortEvent.AsyncHandler sortHandler = new ColumnSortEvent.AsyncHandler(table);
    private UserPresenter provider;

    void start(){
        provider = new UserPresenter(columnSortName,isAscending);
        provider.addDataDisplay(table);
    }

    /**
     * Создание таблицы.
     */
    private void createTable() {
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
                provider.setAscending(isAscending);
                provider.setColumnSortName(columnSortName);

            }
        });


        table.addColumnSortHandler(sortHandler);


    }

    /**
     * создание пейджера
     */
    private void createPager() {
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);

        pager.setDisplay(table);
        pager.setPageSize(50);

    }

    /**
     * Меотод запуска gui, в котором создаются и располагаются элементы gui на web странице.
     */
    public void onModuleLoad() {
        createPager();
        createTable();

        start();
        //provider.addDataDisplay(table);

        DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);

        panel.addNorth(new HTMLPanel("h1", "Список студентов"), 60);
        panel.addNorth(pager, 70);
        panel.add(table);


        RootLayoutPanel.get().add(panel);


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

    /**
     * Класс аснхронного дата провайдера extends {@link AsyncDataProvider}
     * Необходим для асинхронной работы с БД.
     */
//    private static class UserPresenter extends AsyncDataProvider<UserDTO> {
//
//
//        @Override
//        protected void onRangeChanged(HasData<UserDTO> display) {
//            Range range = display.getVisibleRange();
//            final int start = range.getStart();
//            int length = range.getLength();
//
//            TableServiceAsync swc = GWT.create(TableService.class);
//            /**
//             * Метод обращающийся к асинхронным сервисам gwt.
//             */
//            AsyncCallback<Long> asyncCallback = new AsyncCallback<Long>() {
//                @Override
//                public void onFailure(Throwable caught) {
//                    Window.alert("Не сработало возвращение RowCount");
//                }
//
//                /**
//                 *
//                 * @param result Результат, содержащий общее количество строк вернувшихся из запроса к бд.
//                 *               Нужен для установления у таблицы длинны.
//                 */
//                @Override
//                public void onSuccess(Long result) {
//                    table.setRowCount(result.intValue());
//
//                }
//            };
//            swc.getRowCount(asyncCallback);
//
//            AsyncCallback<List<UserDTO>> callback = new AsyncCallback<List<UserDTO>>() {
//                @Override
//                public void onFailure(Throwable caught) {
//                    Window.alert("Callback not work");
//                }
//
//                /**
//                 *
//                 * @param result Результат содержашй записи в формате DTO полученные из БД.
//                 */
//                @Override
//                public void onSuccess(List<UserDTO> result) {
//
//                    updateRowData(start, result);
//
//                }
//            };
//
//            /**
//             * Вызом ветода из GWT сервиса.
//             *          @param start C какой записи будут данные выводится в таблицу.
//             *          @param length По какую будут данные выводится в таблицу.
//             *          @param columnSortName сортировка
//             *          @param isAscending направление сортировки
//             */
//            swc.getAll(start, length + start - 1, columnSortName, isAscending, callback);
//
//
//        }
//    }

}


