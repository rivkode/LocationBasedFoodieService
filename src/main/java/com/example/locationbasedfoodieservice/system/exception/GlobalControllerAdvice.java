package com.example.locationbasedfoodieservice.system.exception;


import com.example.locationbasedfoodieservice.system.dto.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiResponseDto> handlerCustomException(CustomException e) {
        ApiResponseDto restApiException =
                new ApiResponseDto(e.getErrorCode().getErrorCode(), e.getErrorCode().getErrorMessage());
        return ResponseEntity.status(restApiException.getStatusCode()).body(restApiException);
    }

}
