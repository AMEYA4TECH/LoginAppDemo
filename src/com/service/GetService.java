package com.service;

import com.Product;

public interface GetService {
	public Product doGet(String authToken,String xid);

}
