package com.umc9th.umc9th.domain.store.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc9th.umc9th.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.umc9th.umc9th.domain.store.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class StoreQueryDslImpl implements StoreQueryDsl{

    private JPAQueryFactory queryFactory;

    public List<Store> searchStore(String location, String storeName,
                                   int offset, int size){
        return queryFactory
                .selectFrom(store)
                .where(
                        storeLocationNameEq(location),
                        storeNameContains(storeName)
                        )
                .offset(offset)
                .limit(size)
                .orderBy(
                        store.name.asc(),
                        store.createdAt.desc()
                )
                .fetch();
    }

    private static BooleanExpression storeLocationNameEq(String location) {
        return store.location.name.eq(location);
    }

    private static BooleanExpression storeNameContains(String storeName) {
        String[] storeNames = storeName.split(" ");
        String firstStoreName = storeNames[0];
        String secondStoreName = storeNames[1];
        return store.name.contains(firstStoreName)
                .or(store.name.contains(secondStoreName));
    }


}
