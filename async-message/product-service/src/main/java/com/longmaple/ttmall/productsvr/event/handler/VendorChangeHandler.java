package com.longmaple.ttmall.productsvr.event.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.longmaple.ttmall.productsvr.event.CustomChannels;
import com.longmaple.ttmall.productsvr.event.VendorChangeModel;
import com.longmaple.ttmall.productsvr.repository.VendorRedisRepository;

@EnableBinding(CustomChannels.class)
public class VendorChangeHandler {
	
	@Autowired
	private VendorRedisRepository vendorRedisRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(
			VendorChangeHandler.class);

	@StreamListener("inboundVendorChanges")
	public void loggerSink(VendorChangeModel vendorChange) {
		String vid = vendorChange.getVendorId();
		logger.debug("接受到新消息：vendor id {}", vid);
		switch(vendorChange.getAction()) {
		case "UPDATE":
			logger.debug("接收到来自vendor service的Update事件: {}。从Redis缓存删除该陈旧数据。", vid);
			vendorRedisRepo.deleteVendor(vid);
			break;
		case "DELETE":
			logger.debug("接收到来自vendor service的Delete事件: {}。从Redis缓存删除该纪录。", vid);
			vendorRedisRepo.deleteVendor(vid);
			break;
		default:
			logger.debug("unsupported action: {}", vendorChange.getAction());
			break;
		}
	}
}
