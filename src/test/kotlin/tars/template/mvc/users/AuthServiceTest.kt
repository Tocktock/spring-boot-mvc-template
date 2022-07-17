package tars.template.mvc.users

import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
internal class AuthServiceTest {
    @Autowired
    lateinit var authService: AuthService

    @Test
    @Transactional
    fun `signUp test`() {
        val users = authService.signUp("test@TTest.com", "mypassw0rd")
        assertNotEquals(users.password, "mypass0rd")
    }

    @Test
    @Transactional
    fun `signIn test`() {

    }
}