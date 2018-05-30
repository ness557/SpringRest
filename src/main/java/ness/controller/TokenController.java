package ness.controller;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import ness.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {

    private TokenService tokenService;

    @Autowired
    public TokenController(@Qualifier("theTokenService") TokenService tokenService){
        this.tokenService = tokenService;
    }


    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public JSONObject getUser(@RequestParam String token){

        User user = tokenService.getUser(token);

        if(user != null) {

            Map<String, String> map = new HashMap();
            map.put("username", user.getUsername());
            map.put("password", user.getPassword());

            return new JSONObject(map);
        }
        return null;
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
