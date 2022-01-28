package com.kadal.controller;

import com.kadal.dto.UserDTO;
import com.kadal.entity.*;
import com.kadal.response.Response;
import com.kadal.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;;
import org.springframework.web.bind.annotation.*;

import static com.kadal.enums.ResponseEnum.*;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class CustomUserController {

    private final IUserService userService;

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping
    public ResponseEntity<Response> getUsers(){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), userService.getUsers());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("getUser/{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(),userService.getUser(id));

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody CustomUser user){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(),userService.createUser(user));
       return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PutMapping
    public ResponseEntity<Response> updateUser(@RequestBody CustomUser user){
        UserDTO userDTO = userService.updateUser(user);

        if (userDTO != null) {
            Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), userDTO);
            return ResponseEntity.ok(response);
        } else {
            Response response = new Response(ERROR_MESSAGE.getResponse(), STATUS_FALSE.getResponse(), null);
            return ResponseEntity.ok(response);
        }

    }

    @PreAuthorize("hasAuthority('user:write')")
    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable("id") Long id){
        Long responseId = userService.deleteUser(id);
        if (responseId != null) {
            Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), responseId);
            return ResponseEntity.ok(response);
        } else {
            Response response = new Response(ERROR_MESSAGE.getResponse(), STATUS_FALSE.getResponse(), null);
            return ResponseEntity.ok(response);
        }

    }



}
