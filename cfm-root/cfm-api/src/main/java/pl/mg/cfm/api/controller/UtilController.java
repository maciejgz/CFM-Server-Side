package pl.mg.cfm.api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Util controller with admin tools and test methods
 *
 * @author mg
 */
@RestController(value = "/util")
public class UtilController {

    @GetMapping(value = "/test")
    public ResponseEntity<String> testAccessGet() {
        return ResponseEntity.ok("api works");
    }
}
