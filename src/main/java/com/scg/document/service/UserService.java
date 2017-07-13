package com.scg.document.service;

import com.scg.document.model.SAPUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by tanatloke on 7/13/2017 AD.
 */
@Service
public class UserService {

    @Value("${sap.user.service.url}")
    private String sapUserServiceURL;

    @Autowired
    RestTemplate restTemplate;

    public SAPUser getSAPUser(String xomLanID) throws Exception{

        sapUserServiceURL = sapUserServiceURL.replace("{xomLanID}",xomLanID);
        SAPUser sapUser =  restTemplate.getForObject(new URI(sapUserServiceURL), SAPUser.class);
        return  sapUser;
    }

}
