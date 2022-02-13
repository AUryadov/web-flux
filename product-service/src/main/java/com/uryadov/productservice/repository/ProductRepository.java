package com.uryadov.productservice.repository;

import com.uryadov.productservice.entity.ProductEntity;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {
//    Flux<ProductEntity> findByPriceBetween(Integer min, Integer max);

    Flux<ProductEntity> findByPriceBetween(Range<Integer> range);
}
