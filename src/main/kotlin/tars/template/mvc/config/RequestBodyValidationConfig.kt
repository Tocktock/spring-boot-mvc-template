package tars.template.mvc.config

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class RequestBodyValidationConfig {
    // 자바기준 Object Not Null 타입인데 null 이나 값이 없이 들어오는 겨우 발생하는 오류
    @ExceptionHandler(value = [MissingKotlinParameterException::class])
    fun missingFormatArgumentExceptionHandler(e: MissingKotlinParameterException): ResponseEntity<Any> {
        val error = ErrorFrame(
            errors = listOf(
                ErrorContent(
                    type = "INVALID_PARAMETER",
                    message = "${e.parameter.name} is missing or null",
                    fieldName = e.parameter.name.toString(),
                    rejectedValue = null
                )
            )
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    // enum, LocalDateTime 매핑 오류.
    @ExceptionHandler(value = [InvalidFormatException::class])
    fun invalidFormatException(e: InvalidFormatException): ResponseEntity<Any> {
        val error = ErrorFrame(
            errors = listOf(
                ErrorContent(
                    type = "INVALID_PARAMETER",
                    message = "${e.value} is not acceptable",
                    fieldName = e.path.first().fieldName,
                    rejectedValue = e.value.toString()
                )
            )
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    // javax.validation.constraints 오류
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<Any> {
        val error = ErrorFrame(
            errors = e.fieldErrors.map {
                ErrorContent(
                    type = "INVALID_PARAMETER",
                    message = it.defaultMessage ?: "개발자에게 문의해주세요.",
                    fieldName = it.field,
                    rejectedValue = it.rejectedValue?.toString()
                )
            }
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    // GetMapping 에서 RequestParam missing value 검증
    @ExceptionHandler(value = [MissingServletRequestParameterException::class])
    fun missingServletRequestParameterException(e: MissingServletRequestParameterException): ResponseEntity<Any> {
        val error = ErrorFrame(
            errors = listOf(
                ErrorContent(
                    type = "INVALID_PARAMETER",
                    message = "${e.parameterName} is missing or null",
                    fieldName = e.parameterName,
                    rejectedValue = null
                )
            )
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    // GetMapping 에서 LocalDateTime, Enum 오류발생시 사용가
    @ExceptionHandler(value = [MethodArgumentTypeMismatchException::class])
    fun methodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): ResponseEntity<Any> {
        val error = ErrorFrame(
            errors = listOf(
                ErrorContent(
                    type = "INVALID_PARAMETER",
                    message = "${e.value} is not acceptable",
                    fieldName = e.name,
                    rejectedValue = e.value.toString()
                )
            )
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}

data class ErrorFrame(
    val errors: List<ErrorContent>
)

data class ErrorContent(
    val type: String,
    val message: String,
    val fieldName: String,
    val rejectedValue: String?,
)


// @RestController
// class BindErrorTestController {
//
//    @CheckApiKey
//    @PostMapping("validation-test")
//    fun sdf(@RequestBody @Valid someBody: SomeBody): String {
//        //        val a = SomeEnum.valueOf("sdf")
//        return "hehe"
//    }
//
//    @CheckApiKey
//    @GetMapping("validation-get-test")
//    fun asdf(
//        @RequestParam someEnumList: List<SomeEnum>,
//        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) someTime: LocalDateTime,
//    ): String {
//        return "hehe"
//    }
// }
//
// data class SomeBody(
//    @field:NotEmpty val someList: List<String>,
//    val name: String,
//    val someEnum: SomeEnum,
//    val myTime: LocalDateTime,
//    @JsonFormat(
//        shape = JsonFormat.Shape.STRING,
//        pattern = "yyyy-MM-dd HH:mm:ss",
//        timezone = "Asia/Seoul"
//    ) val someTime: LocalDateTime,
//    val someEnumList: List<SomeEnum>?
// )
//
// enum class SomeEnum {
//    FIRST,
//    SECOND,
//    THIRD
// }
//
// data class TestBody(
//    @field:Email val email: String,
//    @field:Min(15) @Max(30) val age: Int?,
//    @field:NotNull val name: String,
//    @field:NotNull val isHuman: Boolean?,
// )
//
// data class TestInsideClass(
//    @field:Email val insideEmail: String
// )