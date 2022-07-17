package tars.template.mvc.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UsersRepository(
    private val usersJpaRepository: UsersJpaRepository,
) {
    fun save(user: Users): Users {
        return usersJpaRepository.save(user)
    }

    fun findById(id: Long): Users? {
        return usersJpaRepository.findByIdOrNull(id)
    }

    fun findByEmail(email: String): Users? {
        return usersJpaRepository.findFirstByEmail(email)
    }
}

interface UsersJpaRepository : JpaRepository<Users, Long> {
    fun findFirstByEmail(email: String): Users?
}