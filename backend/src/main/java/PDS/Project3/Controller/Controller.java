package PDS.Project3.Controller;

import PDS.Project3.Configuration.TokenProvider;
import PDS.Project3.Domain.DTO.UserDTO;
import PDS.Project3.Domain.Entities.*;
import PDS.Project3.Domain.UserPrincipal;
import PDS.Project3.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static PDS.Project3.Domain.RowMapper.RowMapperUser.toUser;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;
    private final RoleService roleService;
    private final ItemService itemService;
    private final PieceService pieceService;
    private final OrderService orderService;
    private final TokenProvider tokenProvider;

    @PostMapping("/user/register")
    public ResponseEntity<HTTPResponse> register(@RequestBody @Valid User user) {
        UserDTO userDTO = userService.createUser(user);
        return ResponseEntity.created(getURI()).body(
                HTTPResponse.builder()
                        .timeStamp(now().toString())
                        .message("Created user: " + userDTO.toString())
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @PostMapping("/user/login")
    public ResponseEntity<HTTPResponse> login(@RequestBody @Valid LoginForm loginForm) {
        log.info("Login form: " + loginForm.toString());
        Authentication authentication = authenticate(loginForm.getUserName(), loginForm.getPassword());
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = tokenProvider.createAccessToken(userPrincipal);
        String refreshToken = tokenProvider.createRefreshToken(userPrincipal);
        return ResponseEntity.ok().body(
                HTTPResponse.builder()
                        .timeStamp(now().toString())
                        .data(of(
                                "user", userPrincipal.getUser(),
                                "access_token", accessToken,
                                "refresh_token", refreshToken
                        ))
                        .message("Login was Successful")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    private Authentication authenticate(@NotEmpty(message = "Username cannot be empty") String userName, @NotEmpty(message = "Password cannot be empty") String password) {
        try {
            log.info("Authenticating user: " + userName);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
            log.info("Authenticated user: " + userName);
            return authentication;
        }catch (Exception e){
            e.printStackTrace();
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/item")
    public ResponseEntity<HTTPResponse> addItem(@RequestBody @Valid Item item) {
        return null;
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<HTTPResponse> getItem(@RequestBody @Valid Item item){
        return null;
    }

    @GetMapping("/item/piece/{id}")
    public ResponseEntity<HTTPResponse> getItemPieces(@PathVariable("id") String id){
        List<Piece> pieces = pieceService.getAllPiecesForItem(String.valueOf(id));
        return ResponseEntity.ok(
                HTTPResponse.builder()
                        .timeStamp(now().toString())
                        .data(Map.of("pieces", pieces))
                        .message("Successfully retrieved pieces for item ID: " + id)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<HTTPResponse> getOrder(@PathVariable("orderId") String id){
        Map<Item, List<Piece>> orderElements = orderService.findOrderElements(Integer.parseInt(id));
        List<ItemWithPieces> itemWithPiecesList = orderElements.entrySet().stream()
                .map(entry -> new ItemWithPieces(entry.getKey(), entry.getValue()))
                .toList();
        return ResponseEntity.ok().body(
                HTTPResponse.builder()
                        .timeStamp(now().toString())
                        .data(Map.of("items", itemWithPiecesList))
                        .message("Login was Successful")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

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

    private UserPrincipal getUserPrincipal(UserDTO userDTO) {
        return new UserPrincipal(toUser(userService.getUser(userDTO.getUserName())), roleService.getRoleByUsername(userDTO.getUserName()));
    }

    private URI getURI() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()); //TODO: create get method
//        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get/<userName>").toUriString()); //TODO: create get method
    }

}
