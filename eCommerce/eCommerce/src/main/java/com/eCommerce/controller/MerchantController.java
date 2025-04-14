package com.eCommerce.controller;

import com.eCommerce.Model.Merchant;
import com.eCommerce.Service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Mer")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/save")
    public Merchant saveMerchant(@RequestBody Merchant merchant){
         return merchantService.saveMerchant(merchant);
    }

    @GetMapping("/{id}")
    public Optional<Merchant> getMerchantById(@PathVariable Long id){
        return merchantService.getMerchantById(id);
    }

    @PutMapping("/Update/{id}")
    public Merchant updateMerchant(@PathVariable Long id ,@RequestBody Merchant merchantDetails){
        Merchant updatedMerchant=merchantService.updateMerchant(id, merchantDetails);
        return ResponseEntity.ok(updatedMerchant).getBody();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        merchantService.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully");

    }
}
