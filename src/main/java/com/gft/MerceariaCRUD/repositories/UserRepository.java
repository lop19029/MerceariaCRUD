package com.gft.MerceariaCRUD.repositories;

import com.gft.MerceariaCRUD.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
