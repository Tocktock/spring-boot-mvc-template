package tars.template.mvc.users

import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import tars.template.mvc.jwt.JwtFactory
import javax.transaction.Transactional

@SpringBootTest
internal class AuthServiceTest {
    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var jwtFactory: JwtFactory

    private val jyEmail = "test@jy-test.com"
    private val jyPW = "mypassw0rd"

    @Test
    @Transactional
    fun `signUp test`() {
        val users = authService.signUp(jyEmail, jyPW)
        assertNotEquals(jyPW, users.password)
    }

    @Test
    @Transactional
    fun `signIn test`() {
        authService.signUp(jyEmail, jyPW)
        val token = authService.signIn(jyEmail, jyPW)
        assertNotEquals(jyPW, jwtFactory.verify(token)["email"])
    }
}