package tars.template.mvc.users

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class Users(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @Enumerated(EnumType.STRING) val state: State,
    @CreatedDate val createdAt: LocalDateTime,
    @LastModifiedDate val updatedAt: LocalDateTime,
) {

    enum class State {
        ACTIVE,
        DEACTIVATED,
    }
}