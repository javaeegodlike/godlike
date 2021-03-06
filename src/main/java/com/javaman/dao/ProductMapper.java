package com.javaman.dao;

import com.javaman.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


public interface  ProductMapper {

    void addProduct(Product product);

    Product selectById(int id);

    List<Product> findAll();

    void delProduct(int id);

    void updateProduct(Product product);

    void insertByBatch(List<Product> products);

    List<Product> selectByBatch(int[] ids);
}