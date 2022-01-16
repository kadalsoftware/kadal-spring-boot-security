package com.kadal.controller;

import com.kadal.dto.UserDTO;
import com.kadal.entity.ApplicationUser;
import com.kadal.enums.ResponseEnum;
import com.kadal.response.Response;
import com.kadal.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kadal.enums.ResponseEnum.*;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;


    @GetMapping
    public ResponseEntity<Response> getUsers(){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), userService.getUsers());
        return ResponseEntity.ok(response);
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(),userService.getUser(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody ApplicationUser user){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(),userService.createUser(user));
       return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Response> updateUser(@RequestBody ApplicationUser user){
        UserDTO userDTO = userService.updateUser(user);

        if (userDTO != null) {
            Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), userDTO);
            return ResponseEntity.ok(response);
        } else {
            Response response = new Response(ERROR_MESSAGE.getResponse(), STATUS_FALSE.getResponse(), null);
            return ResponseEntity.ok(response);
        }

    }

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
