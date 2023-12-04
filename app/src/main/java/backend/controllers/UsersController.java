package backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.DTO.UsersDTO;
import backend.model.validators.UsersValidator;
import backend.services.UsersService;
import lombok.Data;

@RestController
@RequestMapping(path = "/api/v1/users", produces = { "application/json" })
// @RequiredArgsConstructor
@Data
public class UsersController {

    private final UsersService usersService;

    // @GetMapping("/users/getAll")
    // public Users getAllEmployees() {
    // // usersList.put("1", new Users());
    // return usersList.get("1");
    // }

    @GetMapping("/login")
    public String login() {
        return "Logined successfully";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsersDTO req) {
        UsersValidator validator = new UsersValidator().validatePassword(req);

        return ControllerExecutor.execute(validator, () -> {
            return ResponseEntity.status(HttpStatus.CREATED).body(usersService.register(req));
        });
    }

}
