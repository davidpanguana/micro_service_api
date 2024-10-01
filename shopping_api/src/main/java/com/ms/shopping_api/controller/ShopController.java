package com.ms.shopping_api.controller;

import com.ms.shopping_api.dto.ShopDTO;
import com.ms.shopping_api.dto.ShopDTO2;
import com.ms.shopping_api.dto.ShopReportDTO;
import com.ms.shopping_api.model.ShopModel;
import com.ms.shopping_api.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Shopping_API")
public class ShopController {


    @Autowired
    private ShopService shopService;

    @Operation(summary = "List all shops", description = "List all shops who are registered on dataBase", tags = "Shopping_API")
    @GetMapping("/shopping")
    public ResponseEntity<List<ShopModel>> getAllShops() {
        return shopService.findAll();
    }

    @Operation(summary = "Search shop by id", description = "Return only one shop", tags = "Shopping_API")
    @GetMapping("/shopping/{id}")
    public ResponseEntity<ShopModel> getShopById(@PathVariable("id") long id) {
        return shopService.findById(id);
    }

    @Operation(summary = "Create Shop", description = "Register shop on dataBase", tags = "Shopping_API")
    @PostMapping("/shopping")
    public ResponseEntity<ShopModel> createShop(@RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO);
    }

    @Operation(summary = "Update shop", description = "update shop date", tags = "Shopping_API")
    @PutMapping("/shopping/{id}")
    public ResponseEntity<Object> updateShop(
            @PathVariable("id") long id,
            @RequestBody ShopDTO shopDTO) {
        return shopService.update(id, shopDTO);
    }

    @Operation(summary = "Delete shop", description = "given in id, delete all information about that user", tags = "Shopping_API")
    @DeleteMapping("/shopping/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable("id") long id) {
        return shopService.deleteById(id);
    }

    @Operation(summary = "Search shop by UserIdentifier", description = "Return all shops of that user", tags = "Shopping_API")
    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO2> getShops(
            @PathVariable String userIdentifier) {
        List<ShopDTO2> produtos =
                shopService.getByUser(userIdentifier);
        return produtos;
    }

    @Operation(summary = "Search shop by Date", description = "given an period, get all information about that shop ond that period", tags = "Shopping_API")
    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(
            @RequestParam(name = "dataInicio", required=true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @RequestParam(name = "dataFim", required=false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
            @RequestParam(name = "valorMinimo", required=false)
            Float valorMinimo) {
        return shopService.getShopsByFilter(dataInicio, dataFim,
                valorMinimo);
    }

    @Operation(summary = "report", description = "Return all report of shopping", tags = "Shopping_API")
    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(
            @RequestParam(name = "dataInicio", required=true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @RequestParam(name = "dataFim", required=true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {
        return shopService.getReportByDate(dataInicio, dataFim);
    }

}
