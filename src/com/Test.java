package com;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.DefaultHttpClient;

public class Test {

 public static void main(String[] args) throws ClientProtocolException, IOException {
try{
  HttpClient client = new DefaultHttpClient();

  HttpPost post = new HttpPost("http://sandbox-espupdates.asicentral.com/api/api/Login");
  
  StringEntity input = new StringEntity("asi_number=55201");
  StringEntity input1 = new StringEntity("username=ARizvi");
  StringEntity input2 = new StringEntity("password=password1");
  //StringEntity input = new StringEntity("grant_type=password");
  //StringEntity input = new StringEntity("asi_number=55201");

  post.setEntity(input);
  post.setEntity(input1);
  post.setEntity(input2);

  HttpResponse response = client.execute(post);

  BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

  String line = "";

  while ((line = rd.readLine()) != null) {

   System.out.println(line);

  }
}catch(ClientProtocolException e){e.printStackTrace();}
 }

}
