package com.longmaple.ttmall.vendorsvr.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.longmaple.ttmall.vendorsvr.model.Vendor;
import com.longmaple.ttmall.vendorsvr.service.VendorService;


@RestController
@RequestMapping(value="/vendors")
public class VendorController {
	
	private static final Logger logger = LoggerFactory.getLogger(VendorController.class);
			
    @Autowired
    private VendorService vendorService;

    @GetMapping("/{vendorId}")
    public Vendor getVendor( @PathVariable("vendorId") String vendorId) {
    	logger.info("调用 VendorService 接口根据 vendorId 获取 Vendor 数据");
        return vendorService.getVendor(vendorId);
    }

    @RequestMapping(value="/{vendorId}",method = RequestMethod.PUT)
    public void updateVendor( @PathVariable("vendorId") String vendorId, @RequestBody Vendor vendor) {
        vendorService.updateVendor(vendor);
    }

    @RequestMapping(value="/{vendorId}",method = RequestMethod.POST)
    public void saveVendor(@RequestBody Vendor vendor) {
       vendorService.saveVendor(vendor);
    }

    @RequestMapping(value="/{vendorId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVendor( @PathVariable("vendorId") String vendorId,  @RequestBody Vendor vendor) {
        vendorService.deleteVendor(vendor);
    }
}