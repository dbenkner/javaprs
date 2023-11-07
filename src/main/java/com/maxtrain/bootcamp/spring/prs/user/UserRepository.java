package com.maxtrain.bootcamp.spring.prs.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{
	Optional<User> findUserByUsernameAndPassword(String username, String password);
	@Query(value = "select top 1 score from Users Order By score desc", nativeQuery = true)
	User findTopScore();
}
