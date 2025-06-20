package today.sesac.shoutify.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	@GetMapping("/status")
	public ResponseEntity<?> checkAuthStatus(@AuthenticationPrincipal OAuth2User oAuth2User) {
		String email = oAuth2User.getAttribute("email");
		Map<String, String> response = new HashMap<>();
		response.put("email", email);
		return ResponseEntity.ok(response);
	}
}
