package tars.template.mvc.users

import org.hibernate.validator.constraints.Length
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@RestController("auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid body: SignUpReqDTO) {
        authService.signUp(body.email, body.password)
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody @Valid body: SignInReqDTO): String {
        return authService.signIn(body.email, body.password)
    }
}

data class SignUpReqDTO(
    @field:Email val email: String,
    @field:Length(min = 8) val password: String,
)

data class SignInReqDTO(
    @field:Email val email: String,
    @field:NotBlank val password: String,
)