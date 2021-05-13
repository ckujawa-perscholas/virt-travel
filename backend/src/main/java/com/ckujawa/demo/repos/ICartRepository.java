package com.ckujawa.demo.repos;

import com.ckujawa.demo.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends MongoRepository<Cart, Long> {

}
