package com.ms.shopping_api.repository;

import com.ms.shopping_api.model.ShopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<ShopModel, Long>, ReportRepository{

    public List<ShopModel> findAllByUserIdentifier(
            String userIdentifier);
    public List<ShopModel> findAllByTotalGreaterThan(Float total);

    List<ShopModel> findAllByDateGreaterThanEqual(Date date);

}
