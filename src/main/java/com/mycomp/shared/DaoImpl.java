package com.mycomp.shared;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 */
@Repository
public class DaoImpl implements DaoInterface{


    @PersistenceContext
    private EntityManager em;

    @Override
    public List<UsersEntity> getAll(int firstId, int lastId) {

        Query query = em.createQuery("from UsersEntity u where u.id >= :firstId and id<= :lastID", UsersEntity.class);
        query.setParameter("firstId",firstId);
        query.setParameter("lastID",lastId);

        List<UsersEntity> list = query.getResultList();

        return list;

    }

    @Override
    public Integer getRowCount() {

        Query query = em.createQuery("from UsersEntity",UsersEntity.class);
        Integer rowCount = query.getResultList().size();


        return rowCount;

    }
}
