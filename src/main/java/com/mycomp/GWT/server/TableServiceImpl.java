package com.mycomp.GWT.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycomp.DAO.DaoService;
import com.mycomp.EntityModel.UsersEntity;
import com.mycomp.GWT.client.TableGWT;
import com.mycomp.GWT.client.TableService;
import com.mycomp.GWT.shared.UsersEntityDTO;
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

    private ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
    private DaoService service = (DaoService) context.getBean("storageService");



    @Override
    public List<UsersEntityDTO> getAll(int firstId, int lastId) {
        List<UsersEntityDTO> listDto = new ArrayList<>();
        List<UsersEntity> list = service.getAll(firstId, lastId + 1);
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
        return listDto;
    }

    @Override
    public Long getRowCount() {
        return service.getRowCount();
    }
}
