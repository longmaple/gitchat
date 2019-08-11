package com.longmaple.ttmall.productsvr.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longmaple.ttmall.productsvr.model.Vendor;
import com.longmaple.ttmall.productsvr.repository.VendorRedisRepository;

@Component
public class VendorClient {

	@Autowired
	private VendorFeignClient vendorFeignClient;

	@Autowired
	VendorRedisRepository vendorRedisRepo;

	private static final Logger logger = LoggerFactory.getLogger(VendorClient.class);

	private Vendor checkRedisCache(String vendorId) {
		try {
			return vendorRedisRepo.findVendor(vendorId);
		}
		catch (Exception ex){
			logger.error("查找Redis缓存时出错 vendor： {}。  Exception {}", vendorId, ex);
			return null;
		}
	}

	private void cacheVendorObject(Vendor vendor) {
		try {
			vendorRedisRepo.saveVendor(vendor);
		}catch (Exception ex){
			logger.error("在 Redis 缓存 vendor： {} 时出错。 Exception {}", vendor.getVendorId(), ex);
		}
	}

	public Vendor getVendor(String vendorId) {
		Vendor vendor = checkRedisCache(vendorId);
		if (vendor != null) {
			logger.debug("在 Redis 缓存中成功找到 vendor {} : {}", vendorId, vendor);
			return vendor;
		}

		logger.debug("在 Redis 缓存中找不到 vendor： {}", vendorId);
		vendor = vendorFeignClient.getVendor(vendorId);
		if (vendor != null) {
			logger.info("把调用 VendorService API 取得的数据存入Redis缓存。");
			cacheVendorObject(vendor);
		}
		return vendor;
	}


}
