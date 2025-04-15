package com.eCommerce.Service;

import com.eCommerce.Model.Merchant;
import com.eCommerce.Repository.MerchantRepository;
import com.eCommerce.ResponseDTO.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    public ResponseDto saveMerchant(Merchant merchant){
        ResponseDto responseDto = new ResponseDto();

        try{
        if(merchant != null || merchant.getFullName() != null || merchant.getFullName().trim().isEmpty()){
            merchantRepository.save(merchant);
           responseDto.setMessage("Merchant saved successfully");
           responseDto.setStatus(true);
        }else{
            responseDto.setMessage("Cannot save Merchant. Check your input");
            responseDto.setStatus(false);
    }}catch (Exception e){
            System.out.println("Error" + e);
            responseDto.setMessage("An error occurred.");
            responseDto.setStatus(false);
        }
        return responseDto;
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
    public ResponseDto deleteById(Long id){
        ResponseDto responseDto=new ResponseDto();
        try{
            if(merchantRepository.existsById(id)){
                merchantRepository.deleteById(id);
                responseDto.setMessage("Deleted successfully");
                responseDto.setStatus(true);
            }else{
                responseDto.setStatus(false);
                responseDto.setMessage("Merchant with id" + id +"not found");
            }
        }catch (Exception e){
            System.out.println("Error " +e);
            responseDto.setMessage("An error occurred");
            responseDto.setStatus(true);
        }
         return responseDto;
    }
}
