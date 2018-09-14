package com.codecool.bfsexample.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAO implements UserDAOInterface {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bfsExampleUnit");
    private EntityManager em = emf.createEntityManager();

    public UserNode getById(long id) {
        return em.getReference(UserNode.class, id);
    }
}
