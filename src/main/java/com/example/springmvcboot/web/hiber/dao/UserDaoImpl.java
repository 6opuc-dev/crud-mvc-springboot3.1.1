package com.example.springmvcboot.web.hiber.dao;
import org.springframework.stereotype.Repository;
import com.example.springmvcboot.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUserById(String id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser(String id, User user) {
        User userToBeUpdated = getUserById(id);
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setLastname(user.getLastname());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setRoles(user.getRoles());
        addUser(userToBeUpdated);
    }

    @Override
    public User getUserByName(String s) {
        return entityManager.find(User.class, s);
    }
}
