package com.eCommerce.Service;

import com.eCommerce.Model.Product;
import com.eCommerce.Repository.ProductRepository;
import com.eCommerce.ResponseDTO.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseDto saveProduct(Product product){
        ResponseDto responseDto = new ResponseDto();
try {


       if (product != null && product.getName() != null && !product.getName().trim().isEmpty()){
           productRepository.save(product);
        responseDto.setMessage("Product saved successfully");
        responseDto.setStatus(true);
       }
       else{
           responseDto.setMessage("cannot save product. Check your input");
           responseDto.setStatus(false);

       }}catch (Exception e){
    System.out.println("Error " +e);
    responseDto.setMessage("An error occurred while saving product");
    responseDto.setStatus(false);
       }
        return responseDto;

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
        Optional<Product> product = productRepository.findById(id);
        ResponseDto responseDto = new ResponseDto();

          try{
           if(product.isPresent()){
               productRepository.deleteById(id);
               responseDto.setMessage("Product deleted successfully");
               responseDto.setStatus(true);
        }else{
              responseDto.setMessage("Product not found");
              responseDto.setStatus(false);
       }}catch (Exception e){
              System.out.println("Error" +e);
              responseDto.setStatus(false);
             responseDto.setMessage("An error ");
    }
      }
}
