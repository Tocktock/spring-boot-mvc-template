package tars.template.mvc.users

import org.hibernate.validator.constraints.Length
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Email

@RestController("auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid body: SignUpReqDTO) {
        authService.signUp(body.email, body.password)
    }
}

data class SignUpReqDTO(
    @field:Email val email: String,
    @field:Length(min = 8) val password: String,
)