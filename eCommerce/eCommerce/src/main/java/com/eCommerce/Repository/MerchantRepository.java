package com.eCommerce.Repository;

import com.eCommerce.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository  extends JpaRepository<Merchant,Long> {
}
