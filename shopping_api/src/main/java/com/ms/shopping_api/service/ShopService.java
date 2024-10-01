package com.ms.shopping_api.service;
/*
import com.ms.product_api.dto.ProductDTO;
import com.ms.product_api.service.ProductService;
import com.ms.user_api.service.UserService;
 */
import com.ms.shopping_api.converter.DTOConverter;
import com.ms.shopping_api.dto.ItemDTO;
import com.ms.shopping_api.dto.ShopDTO;
import com.ms.shopping_api.dto.ShopDTO2;
import com.ms.shopping_api.dto.ShopReportDTO;
import com.ms.shopping_api.model.ItemModel;
import com.ms.shopping_api.model.ShopModel;
import com.ms.shopping_api.repository.ReportRepository;
import com.ms.shopping_api.repository.ShopRepository;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    @Qualifier("shopRepository")
    private ShopRepository shopRepository;

    @Autowired
    final ReportRepository reportRepository;
    @Autowired

    /*
    private ProductService productService;
    @Autowired
    private UserService userService;
*/
    public ShopService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public ResponseEntity<List<ShopModel>> findAll() {
        List<ShopModel> shops = shopRepository.findAll();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

    public ResponseEntity<ShopModel> findById(long id) {
        Optional<ShopModel> shopOpt = shopRepository.findById(id);
        return shopOpt.map(shop -> new ResponseEntity<>(shop, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<ShopModel> save(ShopDTO shopDTO) {
        ShopModel shop = new ShopModel();
        BeanUtils.copyProperties(shopDTO, shop, "items");

        // Mapeia os itens do DTO para a entidade ItemModel
        List<ItemModel> items = shopDTO.items().stream()
                .map(itemDTO -> {
                    ItemModel item = new ItemModel();
                    BeanUtils.copyProperties(itemDTO, item);
                    return item;
                }).collect(Collectors.toList());

        shop.setItems(items);

        float total = calculateTotal(items);
        shop.setTotal(total);

        shop.setDate(Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant()
        ));

        ShopModel savedShop = shopRepository.save(shop);
        return new ResponseEntity<>(savedShop, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> update(long id, ShopDTO shopDTO) {
        Optional<ShopModel> existingShopOpt = shopRepository.findById(id);

        if (!existingShopOpt.isEmpty()) {
            ShopModel existingShop = existingShopOpt.get();
            BeanUtils.copyProperties(shopDTO, existingShop, "items");

            List<ItemModel> items = shopDTO.items().stream()
                    .map(itemDTO -> {
                        ItemModel item = new ItemModel();
                        BeanUtils.copyProperties(itemDTO, item);
                        return item;
                    }).collect(Collectors.toList());

            existingShop.setItems(items);

            ShopModel updatedShop = shopRepository.save(existingShop);
            return ResponseEntity.status(HttpStatus.OK).body(updatedShop);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id not found");
        }
    }

    public ResponseEntity<Void> deleteById(long id) {
        if (shopRepository.existsById(id)) {
            shopRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<ShopDTO2> getByUser(String userIdentifier) {
        List<ShopModel> shops = shopRepository
                .findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(ShopDTO2::convert)
                .collect(Collectors.toList());
    }



    private float calculateTotal(List<ItemModel> items){
        return (float) items.stream()
                .mapToDouble(item -> item.getPrice())
                .sum();
    }

    public List<ShopDTO> getShopsByFilter(
            Date dataInicio,
            Date dataFim,
            Float valorMinimo) {
        List<ShopModel> shopsModel =
                reportRepository
                        .getShopByFilters(dataInicio, dataFim, valorMinimo);


        List<ShopDTO> shopDTOS = shopsModel.stream()
                .map(shopModel -> {

                    return new ShopDTO(
                            shopModel.getIdShop(),
                            shopModel.getUserIdentifier(),
                            shopModel.getTotal(),
                            shopModel.getDate(),
                            shopModel.getItems()
                    );
                })
                .collect(Collectors.toList());

        return shopDTOS;
    }
    public ShopReportDTO getReportByDate(
            Date dataInicio,
            Date dataFim) {
        return reportRepository
                .getReportByDate(dataInicio, dataFim);
    }
/*
    public ShopDTO2 save(ShopDTO2 shopDTO) {
        if (userService
                .getUserByCpf(shopDTO.getUserIdentifier()) == null) {
            return null;
        }
        if (!validateProducts(shopDTO.getItems())) {
            return null;
        }
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.price())
                .reduce((float) 0, Float::sum));
        ShopModel shop = ShopModel.convert(shopDTO);
        shop.setDate(new Date());
        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            ProductDTO productDTO = productService
                    .getProductByIdentifier(
                            item.productIdentifier());
            if (productDTO == null) {
                return false;
            }
            item.price();
        }
        return true;
    }
*/
}
