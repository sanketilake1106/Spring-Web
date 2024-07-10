package com.maven.services.impl;

import com.maven.entities.Address;
import com.maven.entities.User;
import com.maven.services.AddressService;
import lombok.Data;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Component
@Data
public class AddressServiceImpl implements AddressService {

    private HibernateTemplate hibernateTemplate;
    @Override
    @Transactional
    public Serializable addAddress(Address address) {
        return hibernateTemplate.save(address);
    }

    @Override
    public Address getAddress(Long id) {
        return hibernateTemplate.get(Address.class, id);
    }

    @Override
    @Transactional
    public void updateAddress(Address address) {
        hibernateTemplate.saveOrUpdate(address);
    }

    @Override
    @Transactional
    public void update1Address(Address address) {
        hibernateTemplate.update(address);
    }

    @Override
    @Transactional
    public Address getSingleAddress(Address address) {
        String query = "FROM Address WHERE addressId = :addressId" ;
        Query<Address> query1 = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query, Address.class);
        query1.setParameter("addressId",address.getAddressId());
        return query1.uniqueResult();
    }

    @Override
    public List<Address> getAll() {
        return hibernateTemplate.loadAll(Address.class);
    }
}
