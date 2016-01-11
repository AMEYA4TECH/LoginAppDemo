package com.service;

import com.ExternalAPIResponse;
import com.Product;

public interface PostService {

	
	public ExternalAPIResponse postProduct(String authToken,Product product );
}
