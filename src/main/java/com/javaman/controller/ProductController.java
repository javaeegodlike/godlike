package com.javaman.controller;

import com.alibaba.fastjson.JSON;
import com.javaman.dao.ProductMapper;
import com.javaman.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chx
 * @description com.javaman.controller
 * @date 2018/8/25
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Resource
    private ProductMapper productMapper;

    /**
     * 查找全部内容
     * @return
     */
    @RequestMapping(value = "/es",method = RequestMethod.GET)
    public String showProduct(){
        List<Product> products = productMapper.findAll();
        return JSON.toJSONString(products);
    }

    /**
     * 根据id找产品
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String findByOne(@PathVariable int id){
        Product product = productMapper.selectById(id);
        return JSON.toJSONString(product);
    }

    /**
     * 插入一条数据
     * @param product
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public String addProduct(Product product){
        try {
            productMapper.addProduct(product);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 根据id删除记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String delProduct(@PathVariable int id){
        try {
            productMapper.delProduct(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 更新数据
     * @param product
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public String updatePro(Product product){
        try {
            productMapper.updateProduct(product);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }


}
