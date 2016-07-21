package com.mycomp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Александр on 18.07.2016.
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    List<UsersEntity> list = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
        DaoService service = (DaoService) context.getBean("storageService");
        List<UsersEntity> list = service.getAll();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());

        }


        request.getRequestDispatcher("/index.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
