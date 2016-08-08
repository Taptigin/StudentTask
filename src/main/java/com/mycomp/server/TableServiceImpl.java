package com.mycomp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycomp.client.TableService;
import com.mycomp.shared.DaoService;
import com.mycomp.shared.UsersEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ejb.Singleton;
import java.util.List;

/**
 * Created by Александр on 28.07.2016.
 */
@Singleton
public class TableServiceImpl extends RemoteServiceServlet implements TableService {

    private ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
    private DaoService service = (DaoService) context.getBean("storageService");


    @Override
    public List<UsersEntity> getAll(int firstId, int lastId) {

        List<UsersEntity> list = service.getAll(firstId, lastId + 1);
        return list;
    }

    @Override
    public Long getRowCount() {
        return service.getRowCount();
    }
}
