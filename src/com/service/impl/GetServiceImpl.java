package com.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import com.AccessBean;
import com.Product;
import com.service.GetService;




public class GetServiceImpl implements GetService{
	private static final Integer AUTH_TOKEN_EXPIRE_MINUTES = 65;
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SZ";
//private static final AccessBean AccessBean = null;
  Product product = null;
  //AccessBean accessBean = null;
  private Logger              _LOGGER              = Logger.getLogger(getClass());
  //AccessBean accessBean1 = null;
  private RestTemplate        restTemplateClass;
  private String getApiURL;
  
  
  
	public String getGetApiURL() {
	return getApiURL;
}

public void setGetApiURL(String getApiURL) {
	this.getApiURL = getApiURL;
}

	public RestTemplate getRestTemplateClass() {
	return restTemplateClass;
}

public void setRestTemplateClass(RestTemplate restTemplateClass) {
	this.restTemplateClass = restTemplateClass;
}

	private final String USER_AGENT = "application/json";
	private final String USER_AGENT1 = "application/json";
	
	public Product doGet(String authToken, String xid) {
		// TODO Auto-generated method stub

		String url = getApiURL+xid;
//7797574-550364295";

        Product product = null;
        try {
        	
        	HttpHeaders header = new HttpHeaders();
        	//header.add("Authorization", scheme + " " + authToken);
        	
        	header.setContentType(MediaType.APPLICATION_JSON);
        	header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        	header.add("AuthToken",authToken);
        	HttpEntity<String> requestEntity = new HttpEntity<String>(header);
            
            _LOGGER.info("Getting RADAR Product - XID: " +xid);
            _LOGGER.info("With Authentication Token: " + authToken);
            
            ResponseEntity<Product> response = restTemplateClass.exchange(url, HttpMethod.GET, requestEntity,
            		Product.class, xid);
            if(response != null && response.getBody() != null) {
            	product = response.getBody();
            }

            _LOGGER.info("RADAR served API from URL: " + url + " with Response Code: " + response.getStatusCode());

}catch(Exception e){
	e.printStackTrace();
}
return product;
}
	}


