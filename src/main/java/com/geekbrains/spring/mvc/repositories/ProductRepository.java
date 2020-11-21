package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init(){
        this.products = new ArrayList<>();
        this.products.add(new Product(1L,"title1","description1","brand1",10.43));
        this.products.add(new Product(2L,"title2","description2","brand2",11.43));
        this.products.add(new Product(3L,"title3","description3","brand3",12.43));
        this.products.add(new Product(4L,"title4","description4","brand4",13.43));
        this.products.add(new Product(5L,"title5","description5","brand5",14.43));
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(products);
    }

    public Product findById(Long id){
        for (Product product : products){
            if (product.getId().equals(id)){
                return product;
            }
        }
        throw new RuntimeException("Product not found");
    }

    public Product saveOrUpdate(Product product){
        if(product.getId() == null){
            product.setId(products.size() + 1L);
            products.add(product);
            return product;
        }
        else{
            for(int i=0;i<products.size(); i++){
                if(products.get(i).getId().equals(product.getId())){
                    products.set(i,product);
                    return product;
                }
            }
        }
        throw new RuntimeException("Error save or update product");
    }
}
