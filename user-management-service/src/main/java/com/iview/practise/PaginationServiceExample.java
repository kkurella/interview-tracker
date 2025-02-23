package com.iview.practise;

import com.iview.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PaginationServiceExample {

    @Autowired
    PractiseRepository practiseRepository;

    @Autowired
    EntityManager entityManager;

    private void getPagedUsers(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("age").descending());
        Page<User> page = practiseRepository.findAllByUsername("karthik", pageable);

        page.getContent(); // List of users
        page.getTotalPages(); // Total number of pages

    }
    private void getNamedQueryExample() {
        List<User> users = entityManager.createNamedQuery("User.findByName", User.class)
                .setParameter("name", "karthik")
                .getResultList();
    }


}
