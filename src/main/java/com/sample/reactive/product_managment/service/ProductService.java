package com.sample.reactive.product_managment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.sample.reactive.product_managment.dto.ProductDto;
import com.sample.reactive.product_managment.repository.ProductRepository;
import com.sample.reactive.product_managment.utils.Apputils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	
	public Flux<ProductDto> getProducts()
	{
		return repo.findAll().map(Apputils::entityToDto);
	}
	
	public Mono<ProductDto> getProduct(String id) {
		return repo.findById(id).map(Apputils::entityToDto);
	}
	
	public Flux<ProductDto> getProductsInRange(double min, double max)
	{
		return repo.findByPriceBetween(Range.closed(min, max));
	}
	
	public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
		return productDtoMono.map(Apputils::dtoToEntity)
				.flatMap(repo::insert)
				.map(Apputils::entityToDto);
	}

	public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
		 return repo.findById(id)
				.flatMap(p->productDtoMono.map(Apputils::dtoToEntity)
				.doOnNext(e -> e.setId(id)))
				.flatMap(repo::save)
				.map(Apputils::entityToDto);
	}
	
	 public Mono<Void> deleteProduct(String id){
	        return repo.deleteById(id);
	    }
}
