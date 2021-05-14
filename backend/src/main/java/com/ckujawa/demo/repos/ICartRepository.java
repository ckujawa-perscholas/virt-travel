package com.ckujawa.demo.repos;

import com.ckujawa.demo.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends CrudRepository<Cart, Long> {

}
