package com.kadal.controller;

import com.kadal.dto.UserDTO;
import com.kadal.entity.CustomUser;
import com.kadal.entity.Permission;
import com.kadal.response.Response;
import com.kadal.service.IPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.kadal.enums.ResponseEnum.*;
import static com.kadal.enums.ResponseEnum.STATUS_FALSE;


@RestController
@RequestMapping("permission")
@AllArgsConstructor
public class PermissionController {
    private final IPermissionService permissionService;

    @PreAuthorize("hasAuthority('permission:read')")
    @GetMapping
    public ResponseEntity<Response> getPermissions(){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), permissionService.getPermissions());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('permission:read')")
    @GetMapping("getPermission/{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(),permissionService.getPermission(id));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('permission:write')")
    @PostMapping
    public ResponseEntity<Response> createPermission(@RequestBody Permission permission){
        Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(),permissionService.createPermission(permission));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('permission:write')")
    @PutMapping
    public ResponseEntity<Response> updatePermission(@RequestBody Permission permission){
        Permission permissionResponse = permissionService.updatePermission(permission);

        if (permissionResponse != null) {
            Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), permissionResponse);
            return ResponseEntity.ok(response);
        } else {
            Response response = new Response(ERROR_MESSAGE.getResponse(), STATUS_FALSE.getResponse(), null);
            return ResponseEntity.ok(response);
        }
    }

    @PreAuthorize("hasAuthority('permission:write')")
    @DeleteMapping("{id}")
    public ResponseEntity<Response> deletePermission(@PathVariable("id") Long id){
        Long responseId = permissionService.deletePermission(id);
        if (responseId != null) {
            Response response = new Response(SUCCESSFUL_MESSAGE.getResponse(), STATUS_TRUE.getResponse(), responseId);
            return ResponseEntity.ok(response);
        } else {
            Response response = new Response(ERROR_MESSAGE.getResponse(), STATUS_FALSE.getResponse(), null);
            return ResponseEntity.ok(response);
        }

    }
}
