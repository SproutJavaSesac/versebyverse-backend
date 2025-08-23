package today.sesac.versebyverse.ai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.ai.dto.request.PostAiRequestDto;
import today.sesac.versebyverse.ai.dto.response.PostAiResponseDto;
import today.sesac.versebyverse.ai.prompt.PromptType;
import today.sesac.versebyverse.ai.service.PostAiService;
import today.sesac.versebyverse.ai.util.GenrePromptMapper;
import today.sesac.versebyverse.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/proofreads")
@Slf4j
public class AiController {

    private final PostAiService postAiService;

    @PostMapping()
    public ApiResponse<PostAiResponseDto> preview(@RequestBody PostAiRequestDto postAiRequestDto) {

        log.info("postAiRequestDto: @@@@@@@@@@@{}", postAiRequestDto);
        PromptType promptType =
                GenrePromptMapper.getPostPromptType(postAiRequestDto.getGenreType());
        PostAiResponseDto postAiResponseDto =
                postAiService.executeAiWithValidation(postAiRequestDto, promptType);
        return ApiResponse.success(postAiResponseDto);
    }

}
