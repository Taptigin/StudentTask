package com.mycomp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycomp.DaoService;
import com.mycomp.UsersEntity;
import com.mycomp.client.GwtTableService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 27.07.2016.
 */
public class GwtTableServiceImpl extends RemoteServiceServlet implements GwtTableService {

    @Override
    public List<UsersEntity> getAll() {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
        DaoService service = (DaoService) context.getBean("storageService");

        List<UsersEntity> list = service.getAll();
        return list;
    }
}
