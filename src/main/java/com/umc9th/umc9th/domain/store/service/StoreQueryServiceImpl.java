package com.umc9th.umc9th.domain.store.service;

import com.umc9th.umc9th.domain.store.entity.Store;
import com.umc9th.umc9th.domain.store.repository.StoreQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreQueryRepository storeQueryRepository;

    public List<Store> searchStore(String location, String storeName, int offset, int size){
        List<Store> stores = storeQueryRepository.searchStore(location, storeName, offset, size);
        return stores;
    }
}
