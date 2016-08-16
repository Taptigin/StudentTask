package com.mycomp.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycomp.dao.UserService;
import com.mycomp.model.User;
import com.mycomp.gwt.shared.service.TableService;
import com.mycomp.gwt.shared.UsersEntityDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
@Singleton
public class TableServiceImpl extends RemoteServiceServlet implements TableService {

    String columnSortName;
    private ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
    private UserService service = (UserService) context.getBean("storageService");

    @Override
    public List<UsersEntityDTO> getAll(int firstId, int lastId, String columnSortName, boolean isAscending) {
        this.columnSortName = columnSortName;
        List<UsersEntityDTO> listDto = new ArrayList<>();
        List<User> list = service.getAll(1, 10000, columnSortName, isAscending);

        for (User user : list) {
            UsersEntityDTO userDto = new UsersEntityDTO();
            userDto.setFirstName(user.getFirstName());
            userDto.setMiddleName(user.getMiddleName());
            userDto.setLastName(user.getLastName());
            userDto.setAge(user.getAge());
            userDto.setId(user.getId());
            userDto.setSex(user.getSex());
            userDto.setGroupName(user.getGroupName());
            userDto.setFacultyName(user.getFacultyName());
            userDto.setEnrollmentDate(user.getEnrollmentDate());
            userDto.setReleaseDate(user.getReleaseDate());
            listDto.add(userDto);
        }


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
