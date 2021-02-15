package com.appgate.mathoperations.api.model;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

}
