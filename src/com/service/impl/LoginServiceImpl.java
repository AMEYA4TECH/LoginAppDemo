package com.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;





import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
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
import org.apache.log4j.Logger;
public class LoginServiceImpl implements LoginService{
	private Logger              _LOGGER              = Logger.getLogger(getClass());
	 private static final Integer AUTH_TOKEN_EXPIRE_MINUTES = 65;
	    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SZ";
	//private static final AccessBean AccessBean = null;
	  AccessBean accessBean = null;
      //AccessBean accessBean = null;
      //AccessBean accessBean1 = null;
      private RestTemplate        restTemplateClass;
      
      

  	private final String USER_AGENT = "application/json";
  	private final String USER_AGENT1 = "application/json";
      
  	private String logApiURL;
  	
  	
	public String getLogApiURL() {
		return logApiURL;
	}

	public void setLogApiURL(String logApiURL) {
		this.logApiURL = logApiURL;
	}

	public RestTemplate getRestTemplateClass() {
		return restTemplateClass;
	}

	public void setRestTemplateClass(RestTemplate restTemplateClass) {
		this.restTemplateClass = restTemplateClass;
	}
	//AccessBean accessBean = null;
	@Override
	public AccessBean doLogin(Login log) {
		 
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        AccessData accessData = null;
        AccessBean accessBean = null;
        try {
        	HttpHeaders header = new HttpHeaders();
            header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            header.setContentType(MediaType.APPLICATION_JSON);
          
            
            Map<String, String> body = new HashMap<String, String>();     
            body.put("Asi", log.getAsi_number());
            body.put("Username", log.getUsername());
            body.put("Password", log.getPassword());

            // Note the body object as first parameter!
            HttpEntity<?> httpEntity = new HttpEntity<Object>(body, header);
            _LOGGER.info("Hitting ASI endpoint endpoint");
            ResponseEntity<AccessBean> response = restTemplateClass.exchange(logApiURL, HttpMethod.POST, httpEntity, AccessBean.class);
            accessBean = new AccessBean();
            accessBean = response.getBody();
            _LOGGER.info("ASI response recieved");
            accessBean.setAccessToken(accessBean.getAccessToken());

            Calendar expireTimeCalculator = Calendar.getInstance();
            expireTimeCalculator.add(Calendar.MINUTE, AUTH_TOKEN_EXPIRE_MINUTES);
            
            DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            accessBean.setTokenExpirationTime(dateFormatter.format(expireTimeCalculator.getTime()));
            
            
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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

