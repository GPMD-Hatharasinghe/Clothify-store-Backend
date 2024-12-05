package edu.icet.ClothifyStor.repository;

import edu.icet.ClothifyStor.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByEmail(String email);

}
