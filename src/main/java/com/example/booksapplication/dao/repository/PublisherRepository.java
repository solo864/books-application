package com.example.booksapplication.dao.repository;

import com.example.booksapplication.dao.entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublisherEntity,Long> {
}
