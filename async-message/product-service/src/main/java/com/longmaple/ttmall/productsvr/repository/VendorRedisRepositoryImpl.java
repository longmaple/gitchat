package com.longmaple.ttmall.productsvr.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.longmaple.ttmall.productsvr.model.Vendor;

import javax.annotation.PostConstruct;

@Repository
public class VendorRedisRepositoryImpl implements VendorRedisRepository {
	
    private static final String HASH_NAME = "vendor";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Vendor> hashOperations;
    
    public VendorRedisRepositoryImpl() {
        super();
    }

    @Autowired
    private VendorRedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveVendor(Vendor vendor) {
        hashOperations.put(HASH_NAME, vendor.getVendorId(), vendor);
    }

    @Override
    public void updateVendor(Vendor vendor) {
        hashOperations.put(HASH_NAME, vendor.getVendorId(), vendor);
    }

    @Override
    public void deleteVendor(String vid) {
        hashOperations.delete(HASH_NAME, vid);
    }

    @Override
    public Vendor findVendor(String vid) {
       return (Vendor) hashOperations.get(HASH_NAME, vid);
    }
}
