package PDS.Project3.Controller;

import PDS.Project3.Domain.DTO.UserDTO;
import PDS.Project3.Domain.HTTPResponse;
import PDS.Project3.Domain.User;
import PDS.Project3.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpResponse;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    //    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;

    @PostMapping("/test")
    public ResponseEntity<HTTPResponse> test() {
        return ResponseEntity.ok().body(
                HTTPResponse.builder()
                        .timeStamp(now().toString())
                        .message("Hello There")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping("/inserttest")
    public ResponseEntity<HTTPResponse> inserttest() {
        User testUser =
                User.builder()
                        .userName("test")
                        .email("ivanearisty@gmail.com")
                        .password("5ome5ecurePa1123")
                        .firstName("Ivan")
                        .lastName("Aristy")
                        .build();
        userService.createUser(testUser);
        return ResponseEntity.created(getURI()).body(
                HTTPResponse.builder()
                        .timeStamp(now().toString())
                        .message("Created user: " + testUser.toString())
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    private URI getURI() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()); //TODO: create get method
//        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get/<userName>").toUriString()); //TODO: create get method
    }

}
