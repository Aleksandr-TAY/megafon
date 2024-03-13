package com.hotric.megafon.repository;

import com.hotric.megafon.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "role-graph")
    User findByUsername(String username);
}
