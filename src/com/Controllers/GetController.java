package com.Controllers;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.*;
import com.Product;

import com.service.*;


@RestController
@RequestMapping("product")
public class GetController {

	@Autowired
    //private GetService  getService;
	private GetService	getServiceimpltest;
	
	
    public GetService getGetService() {
		return getServiceimpltest;
	}



	public void setGetService(GetService getService) {
		this.getServiceimpltest = getService;
	}



	// @Secured("ROLE_CUSTOMER")
    @RequestMapping(value = "{xid}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8" })
    public ResponseEntity<Product> getProductJSON(@RequestHeader("AuthToken") String authToken, @PathVariable("xid") String xid)
            throws UnsupportedEncodingException,  Exception {
    	long totalTime = System.currentTimeMillis();

    	
    	
        Product productResponse = null;
       
        if (authToken == null) {
            return new ResponseEntity<Product>(productResponse, null, HttpStatus.UNAUTHORIZED);
        }
        
        try {
            productResponse = getServiceimpltest.doGet(authToken, xid);
            
        } catch (RuntimeException re) {
        	
        } 
        totalTime = System.currentTimeMillis() - totalTime;
        
        ResponseEntity<Product> response = new ResponseEntity<Product>(productResponse, null, HttpStatus.OK);

        //_LOGGER.info("Successfully GET product " + xid + " in " + totalTime + " Ms");
        return response;
    }

}
