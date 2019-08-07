package com.longmaple.ttmall.vendorsvr.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MyMessageSource {
	
    private Source source;
    private static final Logger logger = LoggerFactory.getLogger(MyMessageSource.class);

    @Autowired
    public MyMessageSource(Source source){
        this.source = source;
    }

    public void publishVendorChange(String action, String vid) {
       logger.debug("发送 Kafka 消息 {} for Vendor Id: {}", action, vid);
        VendorChangeModel change =  new VendorChangeModel(
                VendorChangeModel.class.getTypeName(), action, vid);
        Message<VendorChangeModel> msg = MessageBuilder.withPayload(change).build();
        source.output().send(msg);
    }
}
