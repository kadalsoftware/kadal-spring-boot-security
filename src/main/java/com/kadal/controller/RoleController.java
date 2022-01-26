package com.kadal.controller;

import com.kadal.entity.Role;
import com.kadal.response.Response;
import com.kadal.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.kadal.enums.ResponseEnum.*;

@RestController
@RequestMapping("role")
@AllArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @PreAuthorize("hasAuthority('role:read')")
    @GetMapping
    public ResponseEntity<Response> getRoles(){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), roleService.getRoles());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('role:read')")
    @GetMapping("getRole/{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(),roleService.getRole(id));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('role:write')")
    @PostMapping
    public ResponseEntity<Response> createRole(@RequestBody Role Role){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(),roleService.createRole(Role));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('role:write')")
    @PutMapping
    public ResponseEntity<Response> updateRole(@RequestBody Role Role){
        Role RoleResponse = roleService.updateRole(Role);

        if (RoleResponse != null) {
            Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), RoleResponse);
            return ResponseEntity.ok(response);
        } else {
            Response response = new Response(ERROR_MESSAGE.getResponse(), STATUS_FALSE.getResponse(), null);
            return ResponseEntity.ok(response);
        }
    }

    @PreAuthorize("hasAuthority('role:write')")
    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteRole(@PathVariable("id") Long id){
        Long responseId = roleService.deleteRole(id);
        if (responseId != null) {
            Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), responseId);
            return ResponseEntity.ok(response);
        } else {
            Response response = new Response(ERROR_MESSAGE.getResponse(), STATUS_FALSE.getResponse(), null);
            return ResponseEntity.ok(response);
        }

    }

}
