package com.authentication.nubnasirauth.controller;

import com.authentication.nubnasirauth.config.JwtTokenUtil;
import com.authentication.nubnasirauth.exceptions.BadRequestException;
import com.authentication.nubnasirauth.exceptions.UnAuthorizedException;
import com.authentication.nubnasirauth.model.dto.UserDto;
import com.authentication.nubnasirauth.model.request.LoginRequest;
import com.authentication.nubnasirauth.model.response.TokenResponse;
import com.authentication.nubnasirauth.service.AuthUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authentication")
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthUserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenUtil jwtTokenUtil,
                                    AuthUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public TokenResponse createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new TokenResponse(token);
    }

    @PostMapping("/register")
    public UserDto saveUser(@RequestBody UserDto user) throws BadRequestException {
        return userDetailsService.save(user.getUsername(), user.getPassword(), user.getUsername()+"@xyz.com", "CUSTOMER", "ACTIVE");
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UnAuthorizedException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new UnAuthorizedException("INVALID_CREDENTIALS", e);
        }
    }
}