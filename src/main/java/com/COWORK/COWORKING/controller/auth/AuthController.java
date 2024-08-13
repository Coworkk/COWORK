package com.COWORK.COWORKING.controller.auth;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import static org.springframework.http.HttpStatus.OK;

@Controller
public class AuthController {

    @GetMapping("/customers")
    public ResponseEntity<?> assignRole(){
        logAuthentication();
        return  new ResponseEntity<>("nice one",OK);
    }

    private void logAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println("User: " + authentication.getName());
        } else {
            System.out.println("No authentication found");
        }
    }
}
