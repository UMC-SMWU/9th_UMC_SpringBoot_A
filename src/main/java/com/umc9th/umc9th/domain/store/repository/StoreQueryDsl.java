package com.umc9th.umc9th.domain.store.repository;

import com.umc9th.umc9th.domain.store.entity.Store;

import java.util.List;

public interface StoreQueryDsl {
    public List<Store> searchStore(String location, String storeName,
                                   int offset, int size);
}
