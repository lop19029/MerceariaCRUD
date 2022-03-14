package com.gft.MerceariaCRUD.services;

import com.gft.MerceariaCRUD.entities.Product;
import com.gft.MerceariaCRUD.entities.Supplier;
import com.gft.MerceariaCRUD.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct (Product product) {

        return productRepository.save(product);
    }

    public List<Product> listProducts(String name) {
        if (name!=null){
            return listProductsByName(name);
        } else {
            return listAllProducts();
        }
    }

    private List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    private List<Product> listProductsByName(String name) {
        return productRepository.findByNameContains(name);
    }

    public Product getProduct(Long id) throws Exception {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()) {
            throw new Exception("Produto não encontrado.");
        }
        return product.get();
    }

    public void deleteProduct (Long id) {
        productRepository.deleteById(id);
    }

}
