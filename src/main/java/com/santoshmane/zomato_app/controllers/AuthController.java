package com.santoshmane.zomato_app.controllers;

import com.santoshmane.zomato_app.dtos.*;
import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.exceptions.BadRequestException;
import com.santoshmane.zomato_app.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto) {
        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto,
                                           HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String tokens[] = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        Cookie cookie = new Cookie("refreshToken", tokens[1]);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new AuthenticationServiceException("Refresh token not found inside the Cookies");
        }
        String refreshToken = Arrays.stream(request.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies"));

        String accessToken = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(new LoginResponseDto(accessToken));
    }

    //Logout end-point
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {

        // Check if the cookies array is present and contains the refreshToken
        Cookie[] cookies = request.getCookies();
        if (cookies == null || Arrays.stream(cookies).noneMatch(cookie -> "refreshToken".equals(cookie.getName()))) {
            // If no refresh token is found in the cookies, return a message indicating that the user is already logged out
            throw new BadRequestException("User is already logged out.");
        }
        // Get refresh token from the cookie
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies"));
        // Remove the refreshToken from the cookie
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0); // Set the cookie's max age to 0 to delete it
        cookie.setPath("/"); // Ensure the correct cookie is removed
        response.addCookie(cookie);
        //
        return ResponseEntity.noContent().build(); // Return 204 No Content status
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/onBoardNewRestaurantPartner/{userId}")
    ResponseEntity<RestaurantPartnerDto> onBoardNewRestaurantPartner(@PathVariable Long userId, @RequestBody OnBoardRestaurantPartnerDto onBoardRestaurantPartnerDto) {
        return new ResponseEntity<>(authService.onBoardRestaurantPartner(userId,onBoardRestaurantPartnerDto), HttpStatus.CREATED);
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/onBoardNewDeliveryPartner/{userId}")
    ResponseEntity<DeliveryPartnerDto> onBoardNewDeliveryPartner(@PathVariable Long userId, @RequestBody OnBoardDeliveryPartnerDto onBoardDeliveryPartnerDto) {
        return new ResponseEntity<>(authService.onBoardDeliveryPartner(userId,onBoardDeliveryPartnerDto), HttpStatus.CREATED);
    }
}
