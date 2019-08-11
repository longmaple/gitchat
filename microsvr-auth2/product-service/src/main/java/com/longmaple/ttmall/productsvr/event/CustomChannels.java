package com.longmaple.ttmall.productsvr.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {
	
	@Input("inboundVendorChanges")
	SubscribableChannel vendors();
}
