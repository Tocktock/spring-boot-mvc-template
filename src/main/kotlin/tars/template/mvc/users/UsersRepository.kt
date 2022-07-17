package tars.template.mvc.users

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UsersRepository(
    private val usersJpaRepository: UsersJpaRepository,
    private val queryFactory: JPAQueryFactory,
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

    fun query() {
        val users = queryFactory.select(
            QUsers.users
        ).from(QUsers.users)
            .fetch()
        println(users)
    }
}

interface UsersJpaRepository : JpaRepository<Users, Long> {
    fun findFirstByEmail(email: String): Users?
}