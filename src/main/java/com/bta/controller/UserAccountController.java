package com.bta.controller;

import com.bta.dto.CustomerDTO;
import com.bta.dto.UserAccountDTO;
import com.bta.repository.UserAccountRepository;
import com.bta.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user-account")
@RestController
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/create")
   public ResponseEntity<?> create (@RequestBody UserAccountDTO userAccount){
        userAccountService.create(userAccount);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/activate")
    public  ResponseEntity<?> activate (@RequestParam String code){
        userAccountService.activate(code);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestBody UserAccountDTO userAccount){
        userAccountService.delete(userAccount);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
