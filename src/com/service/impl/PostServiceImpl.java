package com.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ExternalAPIResponse;
import com.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.PostService;

public class PostServiceImpl implements PostService{

	Product product = null;
	  //AccessBean accessBean = null;
	  private Logger              _LOGGER              = Logger.getLogger(getClass());
	  //AccessBean accessBean1 = null;
	  private RestTemplate        restTemplateClass;
	  private String postApiURL;
	  ExternalAPIResponse extRespose=null;
	@Override
	public ExternalAPIResponse postProduct(String authToken, Product product) {
		
		 try {
	            ObjectMapper mapper = new ObjectMapper();
	            _LOGGER.info("Product Data : " + mapper.writeValueAsString(product));
	            
	        	HttpHeaders headers = new HttpHeaders();
	        	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        	headers.setContentType(MediaType.APPLICATION_JSON);
	        	//headers.add("AuthToken",authToken);
	        	//headers.add("Authorization", scheme + " " + authToken);
	        	
	        	 Map<String, Object> body = new HashMap<String, Object>();     
	             body.put("AuthToken", authToken);
	             body.put("Product", product);
	             //body.put("Password", log.getPassword());
	        	  
	            //HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);
	             HttpEntity<?> requestEntity = new HttpEntity<Object>(body, headers);
	            
//	            ResponseEntity<?> response = restTemplate.postForObject(productSearchUrl, product, ResponseEntity.class);
	            _LOGGER.info("Posting product to RADAR on url: " + postApiURL);

	            ResponseEntity<Object> response = restTemplateClass.exchange(postApiURL, HttpMethod.POST, requestEntity, Object.class);

	            _LOGGER.info("Result : " + response);
	             extRespose= (ExternalAPIResponse) response.getBody();//getExternalAPIResponse("Product Saved successfully", HttpStatus.OK, null);
	        } catch (HttpClientErrorException hce) {
	            _LOGGER.error("Exception while posting product to Radar API", hce);
	        } catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return extRespose;

	}
	
	
	private ExternalAPIResponse getExternalAPIResponse(String message, HttpStatus statusCode, Set<String> additionalInfo) {
        ExternalAPIResponse response = new ExternalAPIResponse();
        if (statusCode != null && message != null && message.toLowerCase().startsWith("{\"Message\":\"Not Valid".toLowerCase())) {
            response.setMessage("Product saved successfully but not active");
            response.setStatusCode(HttpStatus.OK);
        } else {
            response.setStatusCode(statusCode);
            response.setMessage(message);
        }
        response.setAdditionalInfo(additionalInfo);

        return response;
    }
	
	
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public RestTemplate getRestTemplateClass() {
		return restTemplateClass;
	}

	public void setRestTemplateClass(RestTemplate restTemplateClass) {
		this.restTemplateClass = restTemplateClass;
	}

	public String getPostApiURL() {
		return postApiURL;
	}

	public void setPostApiURL(String postApiURL) {
		this.postApiURL = postApiURL;
	}

	
}
