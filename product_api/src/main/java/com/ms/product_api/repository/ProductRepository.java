package com.ms.product_api.repository;

import com.ms.product_api.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

    @Query(value = "select p "
            + "from ProductModel p "
            + "join category c on p.category.id = c.idCategory "
            + "where c.id = :categoryId ")
    public List<ProductModel> getProductByCategory(
            @Param("categoryId") long categoryId);
    public ProductModel findByProductIdentifier(
            String productIdentifier);

}
