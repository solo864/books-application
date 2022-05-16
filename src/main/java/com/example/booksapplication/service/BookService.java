package com.example.booksapplication.service;


import com.example.booksapplication.dao.entity.BookEntity;
import com.example.booksapplication.dao.repository.BookRepository;
import com.example.booksapplication.mapper.BookMapper;
import com.example.booksapplication.model.criteria.BookCriteria;
import com.example.booksapplication.model.criteria.PageCriteria;
import com.example.booksapplication.model.dto.BookDto;
import com.example.booksapplication.model.dto.PageableBookDto;
import com.example.booksapplication.model.exception.UniquenessViolationException;
import com.example.booksapplication.model.exception.UpdateBookException;
import com.example.booksapplication.service.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.booksapplication.model.constants.ExceptionConstants;



@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;


    public void updateBook(Long bookId, Long publisherId, BookDto bookDto) {
        log.info("ActionLog.updateBook.start publisherId: {}, bookId: {}", publisherId, bookId);

        var bookEntity = bookRepository.findByBookIdAndPublisherId(bookId, publisherId)
                .orElseThrow(() -> new UpdateBookException(ExceptionConstants.BOOK_UPDATE_MESSAGE, ExceptionConstants.BOOK_UPDATE_CODE));

        Optional<BookEntity> checkUnique = bookRepository.findByName(bookDto.getName());

        if (checkUnique.isEmpty()) {

            bookEntity.setName(bookDto.getName());
            bookEntity.setPublishingYear(bookDto.getPublishingYear());
            bookEntity.setPageCount(bookDto.getPageCount());

            bookRepository.save(bookEntity);

            log.info("ActionLog.updateBook.success publisherId: {}, bookId: {}", publisherId, bookId);
        } else {
            log.error("ActionLog.updateBook.error");
            throw new UniquenessViolationException(String.format(ExceptionConstants.VALIDATION_MESSAGE, " book", "", ""), ExceptionConstants.VALIDATION_EXCEPTION_CODE);
        }
    }

    public PageableBookDto getBooksWhereNameLike(BookCriteria bookCriteria, PageCriteria pageCriteria) {
        log.info("ActionLog.getAllBooksPagination.start");

        int pageNumber = pageCriteria.getPage();
        int count = pageCriteria.getCount();

        Pageable pageRequest = PageRequest.of(pageNumber, count);
        var specification = new BookSpecification(bookCriteria);

        Page<BookEntity> bookPage = bookRepository.findAll(specification, pageRequest);
        List <BookEntity> books = bookPage.getContent();
        int bookPageCounts = bookPage.getTotalPages();

        if (bookPageCounts != 0) {
            bookPageCounts -= 1;
        }
        log.info("ActionLog.getAllBooksPagination.success");
        return PageableBookDto.builder()
                .books(BookMapper.mapEntitiesToListDto(books))
                .lastPageNumber(bookPageCounts)
                .hasNextPage(bookPage.hasNext())
                .build();
    }


}







