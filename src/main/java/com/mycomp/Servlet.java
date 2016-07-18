package com.mycomp;

import org.hibernate.Query;
import org.hibernate.Session;

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

        Session session = HibernateUtil.getSessionFactory().openSession();


        org.hibernate.Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from UsersEntity ").setMaxResults(10000);

        tx.commit();
        list = query.list();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());

        }
        request.setAttribute("list", list);




        request.getRequestDispatcher("/index.jsp").forward(request,response);

        session.flush();
        session.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
