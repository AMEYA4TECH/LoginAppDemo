package com.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import com.AccessBean;
import com.AccessData;
import com.Product;
import com.service.GetService;

public class GetServiceImpl implements GetService{
	private static final Integer AUTH_TOKEN_EXPIRE_MINUTES = 65;
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SZ";
//private static final AccessBean AccessBean = null;
  Product product = null;
  //AccessBean accessBean = null;
  
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
try{
		String url = getApiURL+xid;
//7797574-550364295";

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		ObjectMapper mapper = new ObjectMapper();
		// add request header
		request.addHeader("Accept", USER_AGENT);
		request.addHeader("Content-Type", USER_AGENT);
		request.addHeader("AuthToken",authToken);
	

		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + 
                       response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
		
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		product= mapper.readValue(result.toString(), Product.class);
		System.out.println(result.toString());

}catch(Exception e){
	e.printStackTrace();
}
return product;
	}

}
