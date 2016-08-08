package com.mycomp.shared;


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


    @PersistenceContext
    private EntityManager em;


    @Override
    public List<UsersEntity> getAll(int firstId, int lastId) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UsersEntity> query = criteriaBuilder.createQuery(UsersEntity.class);
        Root<UsersEntity> root = query.from(UsersEntity.class);
        query.select(root);
        query.where(criteriaBuilder.gt(root.get("id"), firstId),
                criteriaBuilder.lt(root.get("id"), lastId));
        return em.createQuery(query).getResultList();


    }

    @Override
    public Long getRowCount() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(UsersEntity.class)));

        return em.createQuery(criteriaQuery).getSingleResult();

    }
}
