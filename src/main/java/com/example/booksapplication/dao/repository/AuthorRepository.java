package com.example.booksapplication.dao.repository;

import com.example.booksapplication.dao.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AuthorRepository extends JpaRepository<AuthorEntity,Long> {
}
