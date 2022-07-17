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
}

interface UsersJpaRepository : JpaRepository<Users, Long>