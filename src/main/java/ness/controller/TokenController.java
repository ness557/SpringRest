package ness.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import ness.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping("/token")
public class TokenController {

    private TokenService tokenService;

    @Autowired
    public TokenController(@Qualifier("theTokenService") TokenService tokenService){
        this.tokenService = tokenService;
    }


    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUser(@RequestParam String token){

        return tokenService.getUser(token);
    }

    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public String getToken(@RequestParam String username,
                           @RequestParam String password){

        String token = null;
        try {
            token = tokenService.getToken(username, password);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return token;
    }
}
