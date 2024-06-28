package com.maven.services.impl;

import com.maven.entities.User;
import lombok.Data;
import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import com.maven.services.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.ResultSet;

@Component
@Data
public class UserServiceImpl implements UserService {

    private HibernateTemplate hibernateTemplate;
    @Override
    @Transactional
    public Serializable addUser(User user) {
        return hibernateTemplate.save(user);
    }

    @Override
    @Transactional
    public User loginUser(User user) {
        String query = "FROM User WHERE email = :email AND password = :password";
        Query<User> namedQuery = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query, User.class);
        namedQuery.setParameter("email",user.getEmail());
        namedQuery.setParameter("password", user.getPassword());
        return namedQuery.uniqueResult();
    }

}
