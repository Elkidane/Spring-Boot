package com.eCommerce.Service;

import com.eCommerce.Model.Product;
import com.eCommerce.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> getById(Long id){
        return productRepository.findById(id);
    }
 public List<Product> getAllProduct(){
        return productRepository.findAll();
 }
   public Product updateProduct(Long id, Product productDetails) {
       return productRepository.findById(id).map(product -> {
           product.setName(productDetails.getName());
           product.setBrand(productDetails.getBrand());
           product.setDescription(productDetails.getDescription());
           product.setPrice(productDetails.getPrice());
           product.setQuantity(productDetails.getQuantity());
           return productRepository.save(product);

       }).orElseThrow(() -> new EntityNotFoundException("product not found!"));
   }

   public void deleteProduct(Long id){
        productRepository.deleteById(id);
   }


}
