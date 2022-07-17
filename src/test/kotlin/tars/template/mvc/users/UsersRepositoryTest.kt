package tars.template.mvc.users

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UsersRepositoryTest {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Test
    @Transactional
    fun `querydsl test`() {
        usersRepository.save(Users.create("manman@test.com", "12345"))
        usersRepository.query()
    }
}