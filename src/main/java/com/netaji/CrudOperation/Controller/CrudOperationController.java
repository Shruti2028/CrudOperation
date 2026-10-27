package com.netaji.CrudOperation.Controller;


import com.netaji.CrudOperation.mapper.ApiResponse;
import com.netaji.CrudOperation.mapper.UserRequest;
import com.netaji.CrudOperation.mapper.UserResponse;
import com.netaji.CrudOperation.model.UserRegs;
import com.netaji.CrudOperation.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CrudOperationController {

    @Autowired
    UserOperationService userOperationService;

    // http://localhost:8080/crud/post
    @PostMapping("/post")
    public ResponseEntity<?> postUser(@RequestBody UserRequest userReq){

        UserResponse userResponse = userOperationService.postUser(userReq);
       // System.out.println(userResponse);
        ApiResponse res=new ApiResponse();
        res.setStatus_code(201);
        res.setMessage("register succesfully");
        res.setError(null);
        res.setResponse(userResponse);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    // http://localhost:8080/user/get-all
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUser(){
         List<UserRegs> users  = userOperationService.getAllUser();
        ApiResponse res=new ApiResponse();
        res.setStatus_code(200);
        res.setMessage("User get succussfully");
        res.setError(null);
        res.setResponse(users);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    //  http://localhost:8080/user/1791220834074
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
       Optional<UserRegs> user=userOperationService.getUserById(id);
        ApiResponse res=new ApiResponse();
      
        res.setStatus_code(200);
        res.setMessage("User Data Get Successfully");
        res.setError(null);
        res.setResponse(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}