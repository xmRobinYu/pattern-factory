package com.robin.client;

import com.robin.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "product-data-provide")
public interface ProductClientFeign {
    @GetMapping("/products")
    public List<Product> listProdcuts();
}
