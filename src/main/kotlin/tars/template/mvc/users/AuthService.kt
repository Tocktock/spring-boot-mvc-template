package tars.template.mvc.users

import org.springframework.stereotype.Service

@Service
class AuthService(
    private val usersRepository: UsersRepository
) {
    fun signUp(email: String, password: String): Users {
        return usersRepository.save(Users.create(email, password))
    }
}