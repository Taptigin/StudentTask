package com.mycomp.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycomp.gwt.shared.UserDTO;
import com.mycomp.gwt.shared.service.TableService;
import com.mycomp.model.User;
import com.mycomp.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * GWT servlet access spring code to retrieve data from the database.
 * Converting data to a DTO object.
 * <p>
 * Created by Alexandr on 28.07.2016.
 */
@Singleton
public class TableServiceImpl extends RemoteServiceServlet implements TableService {

    String columnSortName;
    private ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
    private UserService service = (UserService) context.getBean("storageService");

    /**
     * Data output from the database in GWT through the conversion to the DTO object.
     *
     * @param startIndex     The index at which to begin sampling.
     * @param pageSize       The size of the sample.
     * @param columnSortName The name of the column to sort.
     * @param isAscending    The direction of the sort.
     */
    @Override
    public List<UserDTO> getAll(int startIndex, int pageSize, String columnSortName, boolean isAscending) {
        this.columnSortName = columnSortName;
        List<UserDTO> listDto = new ArrayList<>();
        List<User> list = service.getAll(startIndex, pageSize, columnSortName, isAscending);

        for (User user : list) {
            UserDTO userDto = new UserDTO();
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


        return listDto;

    }

    /**
     * Returns the number of records from the database.
     *
     * @return Long
     */
    @Override
    public Long getRowCount() {
        return service.getRowCount();
    }
}
