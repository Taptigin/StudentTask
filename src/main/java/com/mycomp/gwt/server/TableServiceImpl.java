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
 * Created by Александр on 28.07.2016.
 * Сервлет GWT обращающийся к спринговому коду для получения данных из БД.
 * Конвертация данных в объект DTO.
 */
@Singleton
public class TableServiceImpl extends RemoteServiceServlet implements TableService {

    String columnSortName;
    private ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
    private UserService service = (UserService) context.getBean("storageService");

    /**
     * Вывод данных из БД в GWT через конвертацию в DTO объект.
     *
     * @param startIndex     Индекс с которого начинается выборка.
     * @param pageSize       Размер выборки.
     * @param columnSortName Имя колонки для сортировки.
     * @param isAscending    Направление сортировки.
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
     * Возвращает количество записей из БД.
     *
     * @return Long
     */
    @Override
    public Long getRowCount() {
        return service.getRowCount();
    }
}
