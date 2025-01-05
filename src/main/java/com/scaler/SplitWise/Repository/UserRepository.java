package com.scaler.SplitWise.Repository;

import com.scaler.SplitWise.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User save(User user);
    User findByMobile(String mobile);
}
