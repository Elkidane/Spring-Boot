package com.eCommerce.controller;

import com.eCommerce.Model.Product;
import com.eCommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<String>  saveProduct(@RequestBody Product product){
          productService.saveProduct(product);
          return ResponseEntity.ok("Product saved successfully");
    }
    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable Long id){
        return productService.getById(id);
    }
    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateStudent(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct );
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product Deleted successfully!");
    }
}
