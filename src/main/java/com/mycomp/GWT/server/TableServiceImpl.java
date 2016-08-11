package com.mycomp.GWT.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycomp.DAO.DaoService;
import com.mycomp.EntityModel.UsersEntity;
import com.mycomp.GWT.client.TableService;
import com.mycomp.GWT.shared.UsersEntityDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
@Singleton
public class TableServiceImpl extends RemoteServiceServlet implements TableService {

    String sortList;
    private ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
    private DaoService service = (DaoService) context.getBean("storageService");

    @Override
    public List<UsersEntityDTO> getAll(int firstId, int lastId, String sortList) {
        this.sortList = sortList;
        List<UsersEntityDTO> listDto = new ArrayList<>();
        List<UsersEntity> list = service.getAll(1, 9999);

        Comparator<UsersEntityDTO> compSexUp = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o1.getSex().compareTo(o2.getSex());
            }
        };
        Comparator<UsersEntityDTO> compSexDown = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o2.getSex().compareTo(o1.getSex());
            }
        };
        Comparator<UsersEntityDTO> compAgeUp = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        };
        Comparator<UsersEntityDTO> compAgeDown = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o2.getAge().compareTo(o1.getAge());
            }
        };
        Comparator<UsersEntityDTO> compFirstNameUp = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        };
        Comparator<UsersEntityDTO> compFirstNameDown = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o2.getFirstName().compareTo(o1.getFirstName());
            }
        };
        Comparator<UsersEntityDTO> compMiddlenameUp = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o1.getMiddleName().compareTo(o2.getMiddleName());
            }
        };
        Comparator<UsersEntityDTO> compMiddlenameDown = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o2.getMiddleName().compareTo(o1.getMiddleName());
            }
        };
        Comparator<UsersEntityDTO> compLastNameUp = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        };
        Comparator<UsersEntityDTO> compLastNameDown = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o2.getLastName().compareTo(o1.getLastName());
            }
        };
        Comparator<UsersEntityDTO> compEnrollmentDateUp = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o1.getEnrollmentDate().compareTo(o2.getEnrollmentDate());
            }
        };
        Comparator<UsersEntityDTO> compEnrollmentDateDown = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o2.getEnrollmentDate().compareTo(o1.getEnrollmentDate());
            }
        };
        Comparator<UsersEntityDTO> compReleaseDateUp = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o1.getReleaseDate().compareTo(o2.getReleaseDate());
            }
        };
        Comparator<UsersEntityDTO> compReleaseDateDown = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o2.getReleaseDate().compareTo(o1.getReleaseDate());
            }
        };
        Comparator<UsersEntityDTO> compGroupNameUp = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o1.getGroupName().compareTo(o2.getGroupName());
            }
        };
        Comparator<UsersEntityDTO> compGroupNameDown = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o2.getGroupName().compareTo(o1.getGroupName());
            }
        };
        Comparator<UsersEntityDTO> compFacultyNameUp = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o1.getFacultyName().compareTo(o2.getFacultyName());
            }
        };
        Comparator<UsersEntityDTO> compFacultyNameDown = new Comparator<UsersEntityDTO>() {
            @Override
            public int compare(UsersEntityDTO o1, UsersEntityDTO o2) {
                return o2.getFacultyName().compareTo(o1.getFacultyName());
            }
        };

        for (int i = 0; i < list.size(); i++) {
            UsersEntityDTO userDto = new UsersEntityDTO();
            userDto.setFirstName(list.get(i).getFirstName());
            userDto.setMiddleName(list.get(i).getMiddleName());
            userDto.setLastName(list.get(i).getLastName());
            userDto.setAge(list.get(i).getAge());
            userDto.setId(list.get(i).getId());
            userDto.setSex(list.get(i).getSex());
            userDto.setGroupName(list.get(i).getGroupName());
            userDto.setFacultyName(list.get(i).getFacultyName());
            userDto.setEnrollmentDate(list.get(i).getEnrollmentDate());
            userDto.setReleaseDate(list.get(i).getReleaseDate());
            listDto.add(userDto);
        }


//        switch (this.sortList) {
//            case "ID":
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@574":
//                listDto.sort(compSexUp);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@573":
//                listDto.sort(compSexDown);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@555":
//                listDto.sort(compAgeUp);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@554":
//                listDto.sort(compAgeDown);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@4f8":
//                listDto.sort(compFirstNameUp);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@4f7":
//                listDto.sort(compFirstNameDown);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@517":
//                listDto.sort(compMiddlenameUp);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@516":
//                listDto.sort(compMiddlenameDown);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@536":
//                listDto.sort(compLastNameUp);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@535":
//                listDto.sort(compLastNameDown);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@593":
//                listDto.sort(compEnrollmentDateUp);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@592":
//                listDto.sort(compEnrollmentDateDown);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5b2":
//                listDto.sort(compReleaseDateUp);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5b1":
//                listDto.sort(compReleaseDateDown);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5d1":
//                listDto.sort(compGroupNameUp);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5d0":
//                listDto.sort(compGroupNameDown);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5f0":
//                listDto.sort(compFacultyNameUp);
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5ef":
//                listDto.sort(compFacultyNameDown);
//                break;
//            default:
//                break;
//        }

        List<UsersEntityDTO> dto = new ArrayList<>();
        for (int i = firstId; i <= lastId; i++) {
            dto.add(listDto.get(i));
        }
        return dto;

    }

    @Override
    public Long getRowCount() {
        return service.getRowCount();
    }
}
