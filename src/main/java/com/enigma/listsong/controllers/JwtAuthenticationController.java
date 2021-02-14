package com.enigma.listsong.controllers;

import com.enigma.listsong.configs.jwt.JwtTokenUtil;
import com.enigma.listsong.entities.User;
import com.enigma.listsong.exceptions.UsernameExistException;
import com.enigma.listsong.messages.ResponseMessage;
import com.enigma.listsong.models.jwt.JwtRequest;
import com.enigma.listsong.models.jwt.JwtResponse;
import com.enigma.listsong.models.user.UserModel;
import com.enigma.listsong.repositories.UserRepository;
import com.enigma.listsong.services.impls.JwtUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseMessage<UserModel> saveUser(@RequestBody UserModel model) throws Exception {

        User entity = modelMapper.map(model, User.class);

        User user = userRepository.findByUsername(entity.getUsername());
        if (user != null) {
            throw new UsernameExistException();
        }

        entity = userDetailsService.save(entity);

        UserModel data = modelMapper.map(entity, UserModel.class);
        return ResponseMessage.success(data);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
