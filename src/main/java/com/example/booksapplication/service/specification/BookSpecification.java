package com.example.booksapplication.service.specification;

import com.example.booksapplication.dao.entity.BookEntity;
import com.example.booksapplication.model.criteria.BookCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookSpecification implements Specification<BookEntity> {

    private BookCriteria bookCriteria;

    public BookSpecification(BookCriteria bookCriteria) {
        this.bookCriteria = bookCriteria;
    }

    @Override
    public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (bookCriteria != null) {
            if (bookCriteria.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"),
                        "%" + bookCriteria.getName().toUpperCase(Locale.ROOT) + "%"));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
