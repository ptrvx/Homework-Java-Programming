package rs.edu.raf.njp.rafcloud.repository;

import org.springframework.data.repository.CrudRepository;
import rs.edu.raf.njp.rafcloud.domain.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}
