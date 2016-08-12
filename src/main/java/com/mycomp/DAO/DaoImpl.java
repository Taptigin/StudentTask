package com.mycomp.DAO;


import com.mycomp.EntityModel.UsersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 */

@Repository
public class DaoImpl implements DaoInterface {
    String columnName;
    String direction = "asc";

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<UsersEntity> getAll(int firstId, int lastId, boolean descending, String columnName) {
        System.out.println("column Name is - " + columnName);
        this.columnName = columnName;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UsersEntity> query = criteriaBuilder.createQuery(UsersEntity.class);
        Root<UsersEntity> root = query.from(UsersEntity.class);
        query.select(root);

        getColumnToSort(this.columnName);

        List<UsersEntity> list;
        if (direction.equals("asc")) {

            query.orderBy(criteriaBuilder.asc(root.get(getColumnToSort(this.columnName))));
            list = em.createQuery(query).getResultList();
        }
        else {
            query.orderBy(criteriaBuilder.desc(root.get(getColumnToSort(this.columnName))));
            list = em.createQuery(query).getResultList();
        }

        em.close();
        return list;


    }

    @Override
    public Long getRowCount() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(UsersEntity.class)));
        Long l = em.createQuery(criteriaQuery).getSingleResult();
        em.close();
        return l;

    }

    private String getColumnToSort (String columnName){
        switch (columnName){
            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@574":
                columnName = "sex";
                direction = "asc";
                break;
            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@573":
                columnName = "sex";
                direction = "desc";
                break;
            default: columnName = "id";break;
        }
        return columnName;
    }
}
