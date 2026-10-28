package com.netaji.CrudOperation.Controller;


import com.netaji.CrudOperation.mapper.ApiResponse;
import com.netaji.CrudOperation.mapper.LoginRequest;
import com.netaji.CrudOperation.mapper.UserRequest;
import com.netaji.CrudOperation.mapper.UserResponse;
import com.netaji.CrudOperation.model.UserRegs;
import com.netaji.CrudOperation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

@CrossOrigin("*")
public class CrudOperationLoginController {

    @Autowired
    LoginService loginService;

    //http://localhost:8080/user/login
    //CREATE PROFILE
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean loginResult = loginService.LoginMethodService(loginRequest);
        if (loginResult == true) {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus_code(200);
            apiResponse.setMessage("Login successfully");
            apiResponse.setError(null);
            apiResponse.setResponse("login");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus_code(401);
            apiResponse.setMessage("Invalid Credential");
            apiResponse.setError(null);
            apiResponse.setResponse("Invalid login");
            return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    //DELETE PROFILE
    //http://localhost:8080/user/delete/
    @PatchMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserProfile(@PathVariable long id) {
        boolean deleteResult = loginService.DeleteMethodService(id);
        if (deleteResult == true) {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus_code(200);
            apiResponse.setMessage("Delete successfully");
            apiResponse.setError(null);
            apiResponse.setResponse("delete");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus_code(204);
            apiResponse.setMessage("Data not found");
            apiResponse.setError(null);
            apiResponse.setResponse("NULL");
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        }

    }
    //Update profile
    //http:/localhost:8088/user/Update/
    @PostMapping("/Update/{id}")
    public ResponseEntity<?> updateprofile(@PathVariable long id, @RequestBody UserRequest userRequest){
        UserResponse userRes = loginService.updateUserService(userRequest,id);
        if(userRes != null){
            ApiResponse res = new ApiResponse();
            res.setStatus_code(200);
            res.setMessage("Update successfully");
            res.setError(null);
            res.setResponse(userRes);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }else{
            ApiResponse res = new ApiResponse();
            res.setStatus_code(204);
            res.setMessage("Data not found");
            res.setError(null);
            res.setResponse("Null");
            return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
        }

    }

}
//