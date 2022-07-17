package tars.template.mvc.users

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class AuthServiceTest(
    private val authService: AuthService
) : StringSpec({
    "SignUp 테스트" {
        val users = authService.signUp("test@TTest.com", "mypassw0rd")
        users.password shouldNotBe "mypassw0rd"
    }
})