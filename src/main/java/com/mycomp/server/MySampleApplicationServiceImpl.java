package com.mycomp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycomp.DaoService;
import com.mycomp.UsersEntity;
import com.mycomp.client.MySampleApplicationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
    public String getRow(){

        ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
        DaoService service = (DaoService) context.getBean("storageService");
        List<UsersEntity> list = service.getAll();
        String oneRow = list.get(0).toString();

        return oneRow;
    }


    public List<UsersEntity> allRows() {

        ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
        DaoService service = (DaoService) context.getBean("storageService");
        List<UsersEntity> list = service.getAll();
        return list;
    }
}