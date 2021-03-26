package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CusRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Customer> getAllCustomers(){
//        TypedQuery<Customer> query = entityManager.createNamedQuery("Customer.findAll", Customer.class);
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    public Customer getCustomerById(Long id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);

        criteria.select(root).where(cb.equal(root.get("id"), id));

        return entityManager.createQuery(criteria).getSingleResult();
    }

    public Customer save(Customer customer){
        entityManager.persist(customer);
        return customer;
    }

    public Customer getOwnerByPet(Pet pet){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);

        criteria.select(root).where(cb.isMember(pet, root.get("pets")));
        return entityManager.createQuery(criteria).getSingleResult();
    }
}
