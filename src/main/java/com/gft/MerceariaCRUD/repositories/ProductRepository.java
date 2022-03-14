package com.gft.MerceariaCRUD.repositories;

import com.gft.MerceariaCRUD.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContains(String name);

}
