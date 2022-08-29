package com.company.repositories;

import com.company.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    Product getProductByCipher (String cipher);

    void deleteProductByCipher (String cipher);

    Optional<Product> findByCipher(String cipher);


    @Query ("")//для проверки
    void deleteByCipher (String cipher, String name, String productName);
}
