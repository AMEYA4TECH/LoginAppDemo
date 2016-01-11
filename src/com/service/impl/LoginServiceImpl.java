package com.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;





import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

















import com.AccessBean;
import com.AccessData;
import com.Login;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.service.LoginService;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
public class LoginServiceImpl implements LoginService{
	 private static final Integer AUTH_TOKEN_EXPIRE_MINUTES = 65;
	    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SZ";
	//private static final AccessBean AccessBean = null;
	  AccessData accessData = null;
      //AccessBean accessBean = null;
      //AccessBean accessBean1 = null;
      private RestTemplate        restTemplateClass;

  	private final String USER_AGENT = "application/json";
  	private final String USER_AGENT1 = "application/json";
      
  	
  	
	public RestTemplate getRestTemplateClass() {
		return restTemplateClass;
	}

	public void setRestTemplateClass(RestTemplate restTemplateClass) {
		this.restTemplateClass = restTemplateClass;
	}
	AccessBean accessBean = null;
	@Override
	public AccessBean doLogin(Login log) {
		 
      //  ObjectMapper mapper = new ObjectMapper();
     //   mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// HttpHeaders header = new HttpHeaders();
		// header.setContentType(MediaType.APPLICATION_JSON);
		// header.add("Accept", "application/json");
         //header.setContentType(MediaType.APPLICATION_JSON);
		
		// HttpEntity<String> requestEntity = new HttpEntity<String>(loginPaylod, header);
       //  Map<String, String> requestBody = mapper.convertValue(log, Map.class);
         
         
       //  String loginPaylod = getQueryString(requestBody);
        // loginPaylod = loginPaylod.substring(0, loginPaylod.lastIndexOf('&'));
       //  loginPaylod= loginPaylod+"grant_type=password&app_code=VELO&app_version=1.0.0";
//HttpEntity<String> requestEntity = new HttpEntity<String>(loginPaylod, header);

//requestEntity.getHeaders().add("Asi", log.getAsi_number());
//requestEntity.getHeaders().add("Username", log.getUsername());
//requestEntity.getHeaders().add("Password", log.getPassword());

		
//https://sandbox-productservice.asicentral.com/v1/Login
try{//http://sandbox-espupdates.asicentral.com/api/api/Login
/*ResponseEntity<AccessData> response = restTemplateClass.exchange("http://sandbox-espupdates.asicentral.com/api/api/Login", HttpMethod.POST, requestEntity, AccessData.class);

         accessData = response.getBody();*/
	HttpClient client = new DefaultHttpClient();
	HttpPost post = new HttpPost("http://sandbox-espupdates.asicentral.com/api/api/Login");

	// add header
	post.setHeader("Accept", USER_AGENT);
	
	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	urlParameters.add(new BasicNameValuePair("Asi", "55201"));
	urlParameters.add(new BasicNameValuePair("Username", "ARizvi"));
	urlParameters.add(new BasicNameValuePair("Password", "password1"));
	
	ObjectMapper mapper = new ObjectMapper();
    // mapper.writeValueAsString(product);
    
	urlParameters.add(new BasicNameValuePair("Payload", ""));
	//urlParameters.add(new BasicNameValuePair("num", "12345"));

	post.setEntity(new UrlEncodedFormEntity(urlParameters));

	HttpResponse response = client.execute(post);
	//System.out.println("\nSending 'POST' request to URL : " + url);
	System.out.println("Post parameters : " + post.getEntity());
	System.out.println("Response Code : " + 
                                response.getStatusLine().getStatusCode());

	BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

	StringBuffer result = new StringBuffer();
	String line = "";
	while ((line = rd.readLine()) != null) {
		result.append(line);
	}

	//System.out.println(result.toString());
	//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	//AccessData user = mapper.readValue(result.toString(), AccessData.class);
	String carJson =
		    "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
		accessBean= mapper.readValue(result.toString(), AccessBean.class);
         //accessBean = new AccessBean();
	//String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accessBean);
	//System.out.println(prettyStaff1);
	//System.out.println(accessBean);
	accessBean.setAccessToken(accessBean.getAccessToken());
         

        // Calendar expireTimeCalculator = Calendar.getInstance();
         //expireTimeCalculator.add(Calendar.MINUTE, AUTH_TOKEN_EXPIRE_MINUTES);
         
       //  DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
         //accessBean.setTokenExpirationTime(dateFormatter.format(expireTimeCalculator.getTime()));
         accessBean.setTokenExpirationTime(accessBean.getTokenExpirationTime());
         //accessBean.setCompanyId(accessBean.getCompanyId());
         String jsonInString = mapper.writeValueAsString(accessBean);
         System.out.println(jsonInString);
      //   accessBean1.setCompanyId(accessBean.getCompanyId());


		
}catch(Exception e){
	e.printStackTrace();
}
		// TODO Auto-generated method stub
return accessBean;
		
	}
	
	 public static String getQueryString(Map<String, String> params) {
	        StringBuilder query = new StringBuilder();
	        char separator = '&';
	        for (Entry<String, String> param : params.entrySet()) {
	            try {
	                query.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	                if (!StringUtils.isEmpty(param.getValue())) {
	                    query.append('=');
	                    query.append(URLEncoder.encode(param.getValue(), "UTF-8"));
	                }
	                query.append(separator);
	            } catch (UnsupportedEncodingException e) {
	                throw new RuntimeException(e);
	            }
	        }
	        return query.toString();
	    }

}
