package com.c0220i1.group.service.products.Impl;

import com.c0220i1.group.model.CustomerInfo;
import com.c0220i1.group.repository.account.CustomerInfoRepository;
import com.c0220i1.group.service.products.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
  public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Override
    public void save(CustomerInfo customerInfo) {
        customerInfoRepository.save(customerInfo);
    }

  @Override
  public CustomerInfo findByAccount_Username(String username) {
    return customerInfoRepository.findByAccount_Username(username);
  }
}