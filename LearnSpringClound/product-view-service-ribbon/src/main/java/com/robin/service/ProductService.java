package com.robin.service;

import com.robin.client.ProductClientRibbon;
import com.robin.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductClientRibbon productClientRibbon;

    public List<Product> listProdcuts() {
        return productClientRibbon.listProdcuts();
    }
}
