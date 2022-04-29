package com.acr.apps.ecommerce.security.controller;

import com.acr.apps.ecommerce.security.dto.JwtToken;
import com.acr.apps.ecommerce.security.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @PostMapping
    public ResponseEntity<JwtToken> login(@RequestBody UserDTO user) {
        return
                null;
    }

}
