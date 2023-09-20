package com.maxtrain.bootcamp.spring.prs.request;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface RequestRepository extends CrudRepository <Request, Integer>{
	Iterable<Request> findAllByUserIdNot(int userId);
}
