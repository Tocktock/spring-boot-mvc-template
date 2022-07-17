package tars.template.mvc.users

import org.springframework.stereotype.Service

@Service
class AuthService(
    private val usersRepository: UsersRepository,
    private val jwtFactory: JwtFactory,
) {
    fun signUp(email: String, password: String): Users {
        return usersRepository.save(Users.create(email, password))
    }

    fun signIn(email: String, password: String): String {
        val user = usersRepository.findByEmail(email) ?: throw IllegalStateException("유저가 존재하지 않습니다.")
        val result = user.validatePassword(password)
        return if (result) {
            jwtFactory.generate(mapOf(email to email))
        } else {
            throw IllegalStateException("아이디 또는 비밀번호를 확인해주세요.")
        }
    }
}