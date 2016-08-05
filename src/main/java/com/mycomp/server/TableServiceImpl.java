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

    private ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
    private DaoService service = (DaoService) context.getBean("storageService");


    @Override
    public List<UsersEntity> getAll(int firstId, int lastId) {

        List<UsersEntity> list = service.getAll(firstId,lastId);
        return list;
    }

    @Override
    public Integer getRowCount() {
        return service.getRowCount();
    }
}
