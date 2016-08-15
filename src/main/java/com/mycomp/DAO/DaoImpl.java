package com.mycomp.DAO;


import com.mycomp.EntityModel.UsersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 */

@Repository
public class DaoImpl implements DaoInterface {
    String columnName;
    boolean direction = true;

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<UsersEntity> getAll(int firstId, int lastId, String columnName, boolean isAscending) {

        this.columnName = columnName;
        this.direction = isAscending;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UsersEntity> query = criteriaBuilder.createQuery(UsersEntity.class);
        Root<UsersEntity> root = query.from(UsersEntity.class);
        query.select(root);

        //getColumnToSort(this.columnName);

        List<UsersEntity> list;
        if (direction == true) {

            query.orderBy(criteriaBuilder.asc(root.get(this.columnName)));
            list = em.createQuery(query).getResultList();
        } else {
            query.orderBy(criteriaBuilder.desc(root.get(this.columnName)));
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

//    private String getColumnToSort(String columnName) {
//        switch (columnName) {
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@574":
//                columnName = "sex";
//                direction = "asc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@573":
//                columnName = "sex";
//                direction = "desc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@555":
//                columnName = "age";
//                direction = "asc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@554":
//                columnName = "age";
//                direction = "desc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@4f8":
//                columnName = "firstName";
//                direction = "asc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@4f7":
//                columnName = "firstName";
//                direction = "desc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@517":
//                columnName = "middleName";
//                direction = "asc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@516":
//                columnName = "middleName";
//                direction = "desc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@536":
//                columnName = "lastName";
//                direction = "asc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@535":
//                columnName = "lastName";
//                direction = "desc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@593":
//                columnName = "enrollmentDate";
//                direction = "asc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@592":
//                columnName = "enrollmentDate";
//                direction = "desc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5b2":
//                columnName = "releaseDate";
//                direction = "asc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5b1":
//                columnName = "releaseDate";
//                direction = "desc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5d1":
//                columnName = "groupName";
//                direction = "asc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5d0":
//                columnName = "groupName";
//                direction = "desc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5f0":
//                columnName = "facultyName";
//                direction = "asc";
//                break;
//            case "com.google.gwt.user.cellview.client.ColumnSortList$ColumnSortInfo@5ef":
//                columnName = "facultyName";
//                direction = "desc";
//                break;
//
//            default:
//                columnName = "id";
//                direction = "asc";
//                break;
//        }
//        return columnName;
//    }
}
