package today.sesac.versebyverse.global.advice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.global.config.MessageConfig;
import today.sesac.versebyverse.global.config.TestSecurityConfig;
import today.sesac.versebyverse.global.exception.LoginRequiredException;
import today.sesac.versebyverse.global.exception.PermissionRequiredException;

/**
 * 전역 예외 처리 테스트.
 */
@Import({GlobalExceptionHandler.class, TestSecurityConfig.class,
        MessageConfig.class})
//TODO: 테스트 커스텀 유저 추가 애노테이션 구현 필요
@WebMvcTest(controllers = GlobalExceptionHandlerTest.TestController.class)
class GlobalExceptionHandlerTest {

    static final String IS_SUCCESS = "$.isSuccess";

    static final String ERROR_NAME = "$.error.name";

    static final String ERROR_MESSAGE = "$.error.message";

    static final String ERROR_PARAM = "$.error.param";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("[예외]-RuntimeException 처리 테스트")
    void handleRuntimeException() throws Exception {

        mockMvc.perform(get("/test/error/runtime").header("Accept-Language", "ko") // 언어 설정
                )
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath(IS_SUCCESS).value(false))
                .andExpect(jsonPath(ERROR_NAME).value("INTERNAL_SERVER"))
                .andExpect(jsonPath(ERROR_MESSAGE).value("알 수 없는 문제가 발생했습니다. 잠시 후 다시 시도해 주세요."))
                .andExpect(jsonPath(ERROR_PARAM).isEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("[예외]-LoginRequiredException 처리 테스트")
    void handleLoginRequiredException() throws Exception {

        mockMvc.perform(get("/test/error/login-required"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath(IS_SUCCESS).value(false))
                .andExpect(jsonPath(ERROR_NAME).value("LOGIN_REQUIRED"))
                .andExpect(jsonPath(ERROR_MESSAGE).value("로그인이 필요합니다."))
                .andExpect(jsonPath(ERROR_PARAM).value("member"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("[예외]-PermissionRequiredException 처리 테스트")
    void handlePermissionRequiredException() throws Exception {

        mockMvc.perform(get("/test/no-permission"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath(IS_SUCCESS).value(false))
                .andExpect(jsonPath(ERROR_NAME).value("PERMISSION_REQUIRED"))
                .andExpect(jsonPath(ERROR_MESSAGE).value("권한이 없습니다. 다시 확인해 주세요."))
                .andExpect(jsonPath(ERROR_PARAM).value("member"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("[예외]-NoHandlerFoundException 처리 테스트")
    void handleNoHandlerFoundException() throws Exception {

        mockMvc.perform(get("/test/not-found")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Accept-Language", "ko") // 언어 설정
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath(IS_SUCCESS).value(false))
                .andExpect(jsonPath(ERROR_NAME).value("NOT_FOUND"))
                .andExpect(jsonPath(ERROR_MESSAGE).value("요청하신 URL을 찾을 수 없습니다."))
                .andExpect(jsonPath(ERROR_PARAM).isEmpty());
    }

    @Test
    @DisplayName("[예외]-header에 ko를 따로 설정하지 않은 경우 시스템 로케일을 사용해야 한다.")
    void handleNoHandlerFoundExceptionInKorean() throws Exception {
        // 현재 시스템 로케일이 한국어로 설정되어 있다고 가정
        Locale.setDefault(Locale.KOREAN); // 기본 로케일을 한국어로 설정

        mockMvc.perform(get("/test/not-found")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Accept-Language", "ja")
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath(IS_SUCCESS).value(false))
                .andExpect(jsonPath(ERROR_NAME).value("NOT_FOUND"))
                .andExpect(jsonPath(ERROR_MESSAGE).value("요청하신 URL을 찾을 수 없습니다."))
                .andExpect(jsonPath(ERROR_PARAM).isEmpty());
    }

    @Test
    @DisplayName("[예외]-header에 정의하지 않은 로케일이 온 경우, 시스템 로케일을 사용하고, 없다면 기본 Messages를 참고해야 한다.")
    void handleNoHandlerFoundExceptionInEnglish() throws Exception {

        Locale.setDefault(Locale.ENGLISH); // 기본 로케일을 영어로 설정

        mockMvc.perform(get("/test/not-found")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Accept-Language", "en")
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath(IS_SUCCESS).value(false))
                .andExpect(jsonPath(ERROR_NAME).value("NOT_FOUND"))
                .andExpect(jsonPath(ERROR_MESSAGE).value("The requested URL was not found."))
                .andExpect(jsonPath(ERROR_PARAM).isEmpty());
    }

    @Test
    @DisplayName("[예외]-MethodArgumentNotValidException 처리 테스트")
    void handleMethodArgumentNotValidException() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        String invalidJson = objectMapper.writeValueAsString(new TestReq("invalidEmailFormat"));

        mockMvc.perform(post("/test/valid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(IS_SUCCESS).value(false))
                .andExpect(jsonPath("$.error[0].name").value("INVALID_REQUEST"))
                .andExpect(jsonPath("$.error[0].message").value("유효하지 않은 형식입니다. 요청 값을 다시 확인해 주세요."))
                .andExpect(jsonPath("$.error[0].param").value("email"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 테스트용 컨트롤러.
     */
    @RestController
    static class TestController {

        @GetMapping("/test/error/runtime")
        public void testEndpoint() {

            throw new RuntimeException("Test Runtime Exception");
        }

        @GetMapping("/test/error/login-required")
        public void loginRequiredEndpoint() {

            throw new LoginRequiredException("member", "로그인이 필요합니다. - stack trace에 기록된 메시지");
        }

        @GetMapping("/test/no-permission")
        public void noPermissionEndpoint() {

            throw new PermissionRequiredException("member", "권한이 없습니다.");
        }

        @PostMapping("/test/valid")
        public void validTest(@Valid @RequestBody TestReq request) {
            // 요청이 유효하면 아무 일도 하지 않음
        }

    }

    /**
     * 테스트용 요청 DTO.
     *
     * @param email 이메일
     */
    record TestReq(
            @Email(message = "유효하지 않은 형식입니다. 요청 값을 다시 확인해 주세요.")
            String email
    ) {

    }

    /**
     * 테스트용 컨트롤러를 빈으로 등록하는 설정 클래스.
     */
    @TestConfiguration
    static class TestConfig {

        @Bean
        public TestController testController() {

            return new TestController();
        }
    }
}