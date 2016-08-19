package com.mycomp.dao;


import com.mycomp.model.User;
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
public class UserDAOImpl implements UserDAO {
    String columnName;
    boolean direction = true;

    @PersistenceContext
    private EntityManager em;

    /**
     * Получение данных из БД.
     *
     * @param firstId     Первый айдишник.
     * @param lastId      Последний айдишник.
     * @param columnName  Имя колонки по которой производится сортировка.
     * @param isAscending Направление сортировки.
     * @return Лист с записями из БД.
     */
    @Override
    public List<User> getAll(int firstId, int lastId, String columnName, boolean isAscending) {

        this.columnName = columnName;
        this.direction = isAscending;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);


        List<User> list;
        if (direction == true) {

            query.orderBy(criteriaBuilder.asc(root.get(this.columnName)));
            list = em.createQuery(query)
                    .setFirstResult(firstId)
                    .setMaxResults(lastId)
                    .getResultList();
        } else {
            query.orderBy(criteriaBuilder.desc(root.get(this.columnName)));
            list = em.createQuery(query)
                    .setFirstResult(firstId)
                    .setMaxResults(lastId)
                    .getResultList();
        }

        em.close();
        return list;


    }

    /**
     * Получаем общее количество записей содержащихся в БД.
     *
     * @return Количество записей.
     */
    @Override
    public Long getRowCount() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(User.class)));
        Long l = em.createQuery(criteriaQuery).getSingleResult();
        em.close();
        return l;

    }


}
