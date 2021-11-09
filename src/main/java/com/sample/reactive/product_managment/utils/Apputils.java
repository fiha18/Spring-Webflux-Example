package com.sample.reactive.product_managment.utils;

import org.springframework.beans.BeanUtils;

import com.sample.reactive.product_managment.dto.ProductDto;
import com.sample.reactive.product_managment.entity.Product;

public class Apputils {

	public static ProductDto entityToDto(Product product) {
		ProductDto productDto = new ProductDto();
		BeanUtils.copyProperties(product, productDto);
		return productDto;
	}
	
	public static Product dtoToEntity(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return product;
	}
}
