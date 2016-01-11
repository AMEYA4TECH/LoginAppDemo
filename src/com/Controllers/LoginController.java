package com.Controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




import com.AccessBean;
import com.Login;
import com.service.*;


@RestController
@RequestMapping("login")
public class LoginController {
	private Logger              _LOGGER              = Logger.getLogger(getClass());
	
	
	@Autowired
    private LoginService  loginService;
	
	@Autowired
    private LoginService  testService;
	
	
	
	
	public LoginService getLoginService() {
		return loginService;
	}


	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}


	@RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json, application/xml", produces = {
            "application/xml", "application/json" })
    public ResponseEntity<?> getAccessToken(HttpEntity<Login> requestEntity) throws Exception {
       System.out.println("In login");
		AccessBean response = null;
        
        try {
        	_LOGGER.info("login processing");
            //response = loginService.doLogin(requestEntity.getBody());
            response = testService.doLogin(requestEntity.getBody());
            if (response != null) {
            	_LOGGER.info("login auth token generated");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
         
        } catch (Exception e) {
            
        }
        return null;
    }

	
	
	

}
