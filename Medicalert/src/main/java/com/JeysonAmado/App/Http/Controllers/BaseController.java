package com.JeysonAmado.App.Http.Controllers;

import com.JeysonAmado.App.Http.Config.JWTUtilities;

public class BaseController {

    private final JWTUtilities jwtUtilities;

    public BaseController(JWTUtilities jwtUtilities) {
        this.jwtUtilities = jwtUtilities;
    }

    public Long getCurrentUserId(String authorizationToken) {
        String jwt = authorizationToken.split(" ")[1].trim();
        return jwtUtilities.getUserId(jwt);
    }
}
