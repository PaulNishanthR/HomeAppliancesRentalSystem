package com.restapi.repository;

import com.restapi.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select c from Cart c inner join c.appUser a where a.id=?1")
    Optional<List<Cart>> findUserCart(Long userId);
//    List<Cart> findByAppUserId(Long userId);

    @Query(value = "select * from cart where product_id = ?1", nativeQuery = true)
    Optional<Cart> findByProductId(Long productId);
}
