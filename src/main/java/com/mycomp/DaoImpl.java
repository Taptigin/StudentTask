package com.mycomp;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Александр on 20.07.2016.
 */
@Repository
public class DaoImpl implements DaoInterface{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<UsersEntity> getAll() {
        return em.createQuery("from UsersEntity",UsersEntity.class).getResultList();
    }
}
