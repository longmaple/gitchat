package com.longmaple.ttmall.vendorsvr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.longmaple.ttmall.vendorsvr.data.VendorRepo;
import com.longmaple.ttmall.vendorsvr.model.Vendor;
import java.util.Random;

@Service
public class VendorService {
	
    @Autowired
    private VendorRepo vendorRepository;
    
    public Vendor getVendor(String vendorId) {
    	runLongRandomly();
        return vendorRepository.findById(vendorId).get();
    }
    
    private void runLongRandomly() {
    	Random random = new Random();
    	if (random.nextInt(2) == 1) {
    		try {
    			Thread.sleep(5000);
    		} catch (InterruptedException e) {
    		}
    	}
    }
}