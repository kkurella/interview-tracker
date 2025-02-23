package com.iview.practise.repository;

import com.iview.practise.entity.CodeSnippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnippetRepository extends JpaRepository<CodeSnippet, Long> {
}
