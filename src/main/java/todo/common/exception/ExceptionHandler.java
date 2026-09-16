package todo.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import todo.common.domain.response.AppResponse;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    ObjectMapper mapper = new ObjectMapper();

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public AppResponse<String> handleNotFoundException(final NotFoundException ex) {
        log.error("Not found error: {} ", ex.getMessage());
        ex.printStackTrace();
        return new AppResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public AppResponse<String> handleNotFoundException(final ModelNotFoundException ex) {
        log.error("Model not found error: {} ", ex.getMessage());
        ex.printStackTrace();
        return new AppResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnprocessableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public AppResponse<String> handleUnProcessableException(final UnprocessableException ex) {
        log.error("Failed to process exception thrown; {} ", ex.getMessage());
        ex.printStackTrace();
        return new AppResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
                null, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PageableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse<String> handlePageableException(final PageableException ex) {
        log.error("Pageable exception: {} ", ex.getMessage());
        ex.printStackTrace();
        return new AppResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                null, ex.getMessage());
    }
}
