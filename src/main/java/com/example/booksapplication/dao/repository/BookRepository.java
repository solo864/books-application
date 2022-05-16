package com.example.booksapplication.dao.repository;

import com.example.booksapplication.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {

    @Query(nativeQuery = true,value = "SELECT * FROM books WHERE id =:bookId AND publisher_id =:publisherId")
    Optional<BookEntity> findByBookIdAndPublisherId(Long bookId, Long publisherId);

    Optional<BookEntity> findByName(String name);

}
