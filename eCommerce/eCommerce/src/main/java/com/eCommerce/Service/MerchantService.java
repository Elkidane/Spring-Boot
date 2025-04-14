package com.eCommerce.Service;

import com.eCommerce.Model.Merchant;
import com.eCommerce.Repository.MerchantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.metal.MetalComboBoxUI;
import java.util.Optional;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    public Merchant saveMerchant(Merchant merchant){
        return merchantRepository.save(merchant);
    }
    public Merchant updateMerchant(Long id, Merchant merchantDetails){
        return merchantRepository.findById(id).map(merchant -> {
           merchant.setFullName(merchantDetails.getFullName());
           merchant.setEmail(merchantDetails.getEmail());
           merchant.setPhone(merchantDetails.getPhone());
           return merchantRepository.save(merchant);
        }).orElseThrow(()-> new EntityNotFoundException("Merchant not found."));
    }
    public Optional<Merchant> getMerchantById(Long id){
        return merchantRepository.findById(id);
    }
    public void deleteById(Long id){
        try{
            if(merchantRepository.existsById(id)){
                merchantRepository.deleteById(id);
            }else{
                System.out.println("Merchant with id" + id +"not found");
            }
        }catch (Exception e){
            System.out.println("Error " +e);
        }

    }
}
