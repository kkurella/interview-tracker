package com.iview.practise;

import com.iview.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PractiseRepository extends JpaRepository<User, Long> {

//        List<User> findByLastName(String lastName); // TODO

        Page<User> findAllByUsername(String name, Pageable pageable);

}
