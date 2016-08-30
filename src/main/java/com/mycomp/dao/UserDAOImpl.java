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
 * Implementing a spring interface
 * which refer directly to the database for the records.
 * <p>
 * Created by Alexandr on 20.07.2016.
 */

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * A method of obtaining data from the database.
     *
     * @param startIndex  The starting index from which begin the requested data.
     * @param pageSize    The sample length.
     * @param columnName  The name of the column on which to sort.
     * @param isAscending The direction of the sort asc/desc.
     * @return Returns a collection with the data type User.
     */
    @Override
    public List<User> getAll(int startIndex, int pageSize, String columnName, boolean isAscending) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);


        List<User> list;
        if (isAscending == true) {

            query.orderBy(criteriaBuilder.asc(root.get(columnName)));
            list = em.createQuery(query)
                    .setFirstResult(startIndex)
                    .setMaxResults(pageSize)
                    .getResultList();
        } else {
            query.orderBy(criteriaBuilder.desc(root.get(columnName)));
            list = em.createQuery(query)
                    .setFirstResult(startIndex)
                    .setMaxResults(pageSize)
                    .getResultList();
        }

        em.close();
        return list;


    }

    /**
     * A method of obtaining the number of records taken from DB.
     *
     * @return The number of records.
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
