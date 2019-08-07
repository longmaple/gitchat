package com.longmaple.ttmall.productsvr.repository;

import com.longmaple.ttmall.productsvr.model.Vendor;

public interface VendorRedisRepository {
    void saveVendor(Vendor vendor);
    void updateVendor(Vendor vendor);
    void deleteVendor(String vid);
    Vendor findVendor(String vid);
}
