package com.mycomp.GWT.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RangeChangeEvent;
import com.mycomp.GWT.shared.UsersEntityDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
public class TableGWT implements EntryPoint {

    private static DataGrid<UsersEntityDTO> table = new DataGrid<>();
    private MyAsyncDataProvider dataProvider = new MyAsyncDataProvider();
    private SimplePager pager;
    private ColumnSortEvent.AsyncHandler sortHandler= new ColumnSortEvent.AsyncHandler(table);
    private static String columnSortName = "ID";

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



        table.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {

                columnSortName = table.getColumnSortList().get(0).toString();


            }
        });


        table.addColumnSortHandler(sortHandler);



    }

    private void createPager() {
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);

        pager.setDisplay(table);
        pager.setPageSize(50);

    }

    public void onModuleLoad() {
        createPager();
        createTable();

        dataProvider.addDataDisplay(table);


        DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);

        panel.addNorth(new HTMLPanel("h1", "Список студентов"), 60);
        panel.addNorth(pager, 70);
        panel.add(table);


        RootLayoutPanel.get().add(panel);


    }


    private TextColumn<UsersEntityDTO> getLastName() {
        TextColumn<UsersEntityDTO> lastName = new TextColumn<UsersEntityDTO>() {
            @Override
            public String getValue(UsersEntityDTO object) {
                return object.getLastName();
            }
        };
        lastName.setSortable(true);
        return lastName;
    }

    private TextColumn<UsersEntityDTO> getFirstName() {
        TextColumn<UsersEntityDTO> firstName = new TextColumn<UsersEntityDTO>() {
            @Override
            public String getValue(UsersEntityDTO object) {
                return object.getFirstName();
            }
        };
        firstName.setSortable(true);
        return firstName;
    }

    private TextColumn<UsersEntityDTO> getMiddleName() {
        TextColumn<UsersEntityDTO> middleName = new TextColumn<UsersEntityDTO>() {
            @Override
            public String getValue(UsersEntityDTO object) {
                return object.getMiddleName();
            }
        };
        middleName.setSortable(true);
        return middleName;
    }

    private TextColumn<UsersEntityDTO> getSex() {
        TextColumn<UsersEntityDTO> sex = new TextColumn<UsersEntityDTO>() {
            @Override
            public String getValue(UsersEntityDTO object) {
                return object.getSex();
            }
        };
        sex.setSortable(true);
        return sex;
    }

    private Column<UsersEntityDTO, Date> getEnrollmentDate() {
        DateCell dateCell = new DateCell();
        Column<UsersEntityDTO, Date> enrollmentDate = new Column<UsersEntityDTO, Date>(dateCell) {
            @Override
            public Date getValue(UsersEntityDTO object) {
                return object.getEnrollmentDate();
            }
        };
        enrollmentDate.setSortable(true);
        return enrollmentDate;
    }

    private Column<UsersEntityDTO, Date> getReleaseDate() {
        DateCell dateCell = new DateCell();
        Column<UsersEntityDTO, Date> releaseDate = new Column<UsersEntityDTO, Date>(dateCell) {
            @Override
            public Date getValue(UsersEntityDTO object) {
                return object.getReleaseDate();
            }
        };
        releaseDate.setSortable(true);
        return releaseDate;
    }

    private TextColumn<UsersEntityDTO> getGroupName() {
        TextColumn<UsersEntityDTO> groupName = new TextColumn<UsersEntityDTO>() {
            @Override
            public String getValue(UsersEntityDTO object) {
                return object.getGroupName();
            }
        };
        groupName.setSortable(true);
        return groupName;
    }

    private TextColumn<UsersEntityDTO> getAge() {
        TextColumn<UsersEntityDTO> age = new TextColumn<UsersEntityDTO>() {
            @Override
            public String getValue(UsersEntityDTO object) {
                return object.getAge().toString();
            }
        };
        age.setSortable(true);
        return age;
    }

    private TextColumn<UsersEntityDTO> getFacultyName() {
        TextColumn<UsersEntityDTO> facultyName = new TextColumn<UsersEntityDTO>() {
            @Override
            public String getValue(UsersEntityDTO object) {
                return object.getFacultyName();
            }
        };
        facultyName.setSortable(true);
        return facultyName;
    }


    private static class MyAsyncDataProvider extends AsyncDataProvider<UsersEntityDTO> {


        @Override
        protected void onRangeChanged(HasData<UsersEntityDTO> display) {
            Range range = display.getVisibleRange();
            final int start = range.getStart();
            int length = range.getLength();

            TableServiceAsync swc = GWT.create(TableService.class);

            AsyncCallback<Long> asyncCallback = new AsyncCallback<Long>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert("Не сработало возвращение RowCount");
                }

                @Override
                public void onSuccess(Long result) {
                    table.setRowCount(result.intValue());

                }
            };
            swc.getRowCount(asyncCallback);

            AsyncCallback<List<UsersEntityDTO>> callback = new AsyncCallback<List<UsersEntityDTO>>() {
                @Override
                public void onFailure(Throwable caught) {

                }

                @Override
                public void onSuccess(List<UsersEntityDTO> result) {

                    updateRowData(start,result);
                }
            };


            swc.getAll(start, length + start, columnSortName, callback);


        }
    }

}


