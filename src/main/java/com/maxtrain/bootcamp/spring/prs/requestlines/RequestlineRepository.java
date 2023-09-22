package com.maxtrain.bootcamp.spring.prs.requestlines;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface RequestlineRepository extends CrudRepository <Requestline, Integer>{
	@Query(value = "select sum(rl.quantity * p.price) from requests r join requestlines rl on r.id = rl.requestId join products p on p.id = rl.productId where r.id = ?1", nativeQuery = true) 
	double calculateTotalCostByRequestId(int id);
	List<Requestline> findAllByrequestId(int id);
			
}
