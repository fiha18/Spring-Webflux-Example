package com.sample.reactive.product_managment.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.reactive.product_managment.dto.ProductDto;
import com.sample.reactive.product_managment.entity.Product;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

	Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
	
 
}
