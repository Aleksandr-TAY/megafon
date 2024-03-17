package com.hotric.megafon.models.repository;

import com.hotric.megafon.models.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "role-graph")
    Optional <UserEntity> findByUsername(String username);
}
