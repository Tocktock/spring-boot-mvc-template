package tars.template.mvc.users

import org.springframework.stereotype.Service

@Service
class AuthService(
    private val usersRepository: UsersRepository,
) {
    fun signUp(email: String, password: String): Users {
        return usersRepository.save(Users.create(email, password))
    }

    fun signIn(email: String, password: String): String {
        val user = usersRepository.findByEmail(email) ?: throw IllegalStateException("유저가 존재하지 않습니다.")
        val result = user.validatePassword(password)
        return "hehe"
    }
}