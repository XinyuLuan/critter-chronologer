package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;

@Repository
@Transactional
public class EmpRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Employee> getEmployeesByIds(List<Long> employeeIds){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);

        criteria.select(root).where(root.get("id").in(employeeIds));
        return entityManager.createQuery(criteria).getResultList();
    }

    public Employee save(Employee employee){
        entityManager.persist(employee);
        return employee;
    }

    public List<Employee> getAllByAvailableDays(DayOfWeek day){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);

        criteria.select(root).where(cb.isMember(day, root.get("daysAvailable")));
        return entityManager.createQuery(criteria).getResultList();
    }
}
