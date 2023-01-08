package com.eticaret.secondHand.repository;

import com.eticaret.secondHand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
