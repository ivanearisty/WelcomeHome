package PDS.Project3.Controller;

import PDS.Project3.Domain.DTO.UserDTO;
import PDS.Project3.Domain.HTTPResponse;
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

import java.net.http.HttpResponse;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    //    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @PostMapping("/test")
    public ResponseEntity<HTTPResponse> test() {
        return ResponseEntity.ok().body(
                HTTPResponse.builder()
                        .timeStamp(now().toString())
                        .message("Hello There")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @PostMapping("/inserttest")
    public ResponseEntity<HTTPResponse> inserttest() {

        return
    }

}
