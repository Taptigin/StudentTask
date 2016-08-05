package com.mycomp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycomp.shared.DaoService;
import com.mycomp.shared.UsersEntity;
import com.mycomp.client.TableService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
public class TableServiceImpl extends RemoteServiceServlet implements TableService {



    @Override
    public List<UsersEntity> getAll(int firstId, int lastId) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
        DaoService service = (DaoService) context.getBean("storageService");
        List<UsersEntity> list = service.getAll(firstId,lastId);
        return list;
    }
}
