package com.mycomp.shared;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 */
@Repository
public class DaoImpl implements DaoInterface {


    @PersistenceContext
    private EntityManager em;



    @Override
    public List<UsersEntity> getAll(int firstId, int lastId) {

        Query query = em.createQuery("from UsersEntity u where u.id >= :firstId and id<= :lastID", UsersEntity.class);
        query.setParameter("firstId", firstId);
        query.setParameter("lastID", lastId);

        List<UsersEntity> list = query.getResultList();

        return list;



    }

    @Override
    public Long getRowCount() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(UsersEntity.class)));

        return  em.createQuery(criteriaQuery).getSingleResult();

    }
}
