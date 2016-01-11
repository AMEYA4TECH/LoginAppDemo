package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClientExample {

	private final String USER_AGENT = "application/json";
	private final String USER_AGENT1 = "application/json";

	public static void main(String[] args) throws Exception {

		HttpClientExample http = new HttpClientExample();

		System.out.println("Testing 1 - Send Http GET request");
		//http.sendGet();

		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}

	// HTTP GET request
	private void sendGet() throws Exception {
		
		String url = "https://sandbox-productservice.asicentral.com/v1/product/7797574-550364295";

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("Accept", USER_AGENT);
		request.addHeader("Content-Type", USER_AGENT);
		request.addHeader("AuthToken","eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkEyNTZHQ00iLCJrZXlpZCI6IlZFTE8ifQ.kKA9vhpxAnjDcj6CLNceA-6MFJF4DhMSwN7KesiQXxiwOXrJyKQv65ojzXqJsDxJjeJIXybw2SpDGq1HVli3dkRjBO41UeXYDgqd1fjB0nHW1A41WINKVhfHsD22mc7OmrQ6fYYDhBgKqz__3QinKyCvkTcWOtH39NKv_4MB9Yw.CifoMkKbDfoJYrj6.ckCyeDk48DRafTIpKxSdQ_a1bHL0Eb2DiMxK1-bpzO-mhcalgd-GSiqetve0ZYDI44Elxs5aaIieyPySqoP9mHTKc2qpgUO6WkuPlZGzeK62K6NsFDMdMGDdAr22szEczZj9cK4UuWhYaJDQb_2yc9YYsOvRsbsFXS1Eiq4i_seLn93HHd1Jg_8BA-pdFqlHueVxiClPR3SP6HSp0vy27WMga-d40cgwwyIyULcKASkDyKRUL2kmmpQPN0TYwfqOjMGCG5Od4oLvHcUChW4kC9c-oWoCS0lThVWcMBoco4xdecFdPcvKPxGi_Dvzkss_mqe-T4aIXFRRZqsXrNYJZuS7upUD-T2q2K6VnFhc_mHajLgYvxqp5ZJZTukxv2hUv0aosxBPAq0Q892_WJvP6whKqSIKliIXyiiHET8i6LsYLpTIWMGRci4_xt8RnpFHkb6o5ShrFyry-rY0cbeVpkeCP62BVkwdxU5bqK_ay6FaYp8VGnEFjpWrYw.yW3WlgM66Y5RIEkXqXtN9A");
	

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

		System.out.println(result.toString());

	}

	// HTTP POST request
	private void sendPost1() throws Exception {

		String url = "https://sandbox-productservice.asicentral.com/v1/product/";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		// add header
		//post.setHeader("Accept", USER_AGENT);
		File file = new File("C:\\Users\\Amey\\Desktop\\A4 doc\\File.txt");
		String content = FileUtils.readFileToString(file);
		post.addHeader("Accept", USER_AGENT);
		post.addHeader("Content-Type", USER_AGENT);
		post.addHeader("AuthToken","eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkEyNTZHQ00iLCJrZXlpZCI6IlZFTE8ifQ.kKA9vhpxAnjDcj6CLNceA-6MFJF4DhMSwN7KesiQXxiwOXrJyKQv65ojzXqJsDxJjeJIXybw2SpDGq1HVli3dkRjBO41UeXYDgqd1fjB0nHW1A41WINKVhfHsD22mc7OmrQ6fYYDhBgKqz__3QinKyCvkTcWOtH39NKv_4MB9Yw.CifoMkKbDfoJYrj6.ckCyeDk48DRafTIpKxSdQ_a1bHL0Eb2DiMxK1-bpzO-mhcalgd-GSiqetve0ZYDI44Elxs5aaIieyPySqoP9mHTKc2qpgUO6WkuPlZGzeK62K6NsFDMdMGDdAr22szEczZj9cK4UuWhYaJDQb_2yc9YYsOvRsbsFXS1Eiq4i_seLn93HHd1Jg_8BA-pdFqlHueVxiClPR3SP6HSp0vy27WMga-d40cgwwyIyULcKASkDyKRUL2kmmpQPN0TYwfqOjMGCG5Od4oLvHcUChW4kC9c-oWoCS0lThVWcMBoco4xdecFdPcvKPxGi_Dvzkss_mqe-T4aIXFRRZqsXrNYJZuS7upUD-T2q2K6VnFhc_mHajLgYvxqp5ZJZTukxv2hUv0aosxBPAq0Q892_WJvP6whKqSIKliIXyiiHET8i6LsYLpTIWMGRci4_xt8RnpFHkb6o5ShrFyry-rY0cbeVpkeCP62BVkwdxU5bqK_ay6FaYp8VGnEFjpWrYw.yW3WlgM66Y5RIEkXqXtN9A");
		//post.addHeader("Product",content);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		
		urlParameters.add(new BasicNameValuePair("Payload",content));
		//urlParameters.add(new BasicNameValuePair("Username", "ARizvi"));
		//urlParameters.add(new BasicNameValuePair("Password", "password1"));
		//urlParameters.add(new BasicNameValuePair("caller", ""));
		//urlParameters.add(new BasicNameValuePair("num", "12345"));
		
		
		//urlParameters.add(new BasicNameValuePair("Payload", content));
		//post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
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

		System.out.println(result.toString());

	}
	
	// HTTP POST request
		private void sendPost() throws Exception {

			String url = "http://sandbox-espupdates.asicentral.com/api/api/Login";

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);

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
			System.out.println("\nSending 'POST' request to URL : " + url);
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

			System.out.println(result.toString());

		}


}
