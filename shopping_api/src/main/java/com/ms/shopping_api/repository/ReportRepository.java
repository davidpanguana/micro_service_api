package com.ms.shopping_api.repository;

import com.ms.shopping_api.dto.ShopReportDTO;
import com.ms.shopping_api.model.ShopModel;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository {

    public List<ShopModel> getShopByFilters(
            Date dataInicio,
            Date dataFim,
            Float valorMinimo);
    public ShopReportDTO getReportByDate(
            Date dataInicio,
            Date dataFim);

}
