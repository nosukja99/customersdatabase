package com.example.customersdatabase;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
    Iterable <Customer> findAllByLastnameContainingIgnoreCase(String lastname);
    Iterable <Customer> findAllByCityContainingIgnoreCase(String city);
}
