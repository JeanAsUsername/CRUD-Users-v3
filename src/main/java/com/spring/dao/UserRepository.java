package com.spring.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);
	
	@Query("SELECT u FROM User u join u.languages l where l.name= :languageName")
	public Iterable<User> findUsersByLanguage(@Param("languageName") String name);

}
