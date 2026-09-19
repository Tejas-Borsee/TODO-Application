package todo.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.common.constants.ApiConstants;

@RestController
@RequestMapping(ApiConstants.BASE_URI + ApiConstants.ADMIN_URI + ApiConstants.TEST_URI)
public class TestController {

    @GetMapping("/private-api")
    public ResponseEntity<String> privateApi(){
        return ResponseEntity.ok("This is private API need Token to access");
    }

}
