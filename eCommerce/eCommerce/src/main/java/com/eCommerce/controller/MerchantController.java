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
    public ResponseDTO saveMerchant(@RequestBody Merchant merchant){
        ResponseDTO responseDto=new ResponseDTO();
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
    public ResponseDTO deleteById(@PathVariable Long id){
        ResponseDto responseDto=new ResponseDTO();
        return merchantService.deleteById(id);
      
    }
}
