package tars.template.mvc.users

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.security.crypto.bcrypt.BCrypt
import java.time.LocalDateTime
import java.time.ZoneId
import javax.persistence.*

@Entity
@Table(name = "users")
class Users(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @Column(unique = true) val email: String,
    password: String,
    @Enumerated(EnumType.STRING) val state: State,
    @CreatedDate val createdAt: LocalDateTime,
    @LastModifiedDate val updatedAt: LocalDateTime,
) {
    companion object {
        fun create(
            email: String,
            password: String,
        ) = Users(
            id = 0,
            email = email,
            password = password,
            state = State.DEACTIVATED,
            createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul")),
            updatedAt = LocalDateTime.now(ZoneId.of("Asia/Seoul")),
        )
    }

    var password = password
        private set

    enum class State {
        ACTIVE,
        DEACTIVATED,
    }

    @PrePersist
    fun hashingPassword() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt())
    }
}