package com.umc9th.umc9th.domain.store.repository;

import com.umc9th.umc9th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreQueryRepository extends JpaRepository<Store, Long>, StoreQueryDsl {
}
