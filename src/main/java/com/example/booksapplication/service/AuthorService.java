package com.example.booksapplication.service;

import com.example.booksapplication.dao.entity.AuthorEntity;
import com.example.booksapplication.dao.repository.AuthorRepository;
import com.example.booksapplication.mapper.AuthorMapper;
import com.example.booksapplication.model.constants.ExceptionConstants;
import com.example.booksapplication.model.dto.AuthorAndBookDetailsDto;
import com.example.booksapplication.model.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorAndBookDetailsDto getAuthorAndDetailsAboutHisBooks(Long id) {
        log.info("ActionLog.getAuthorAndDetailsAboutHisBooks.start id: {}", id);

        var authorAndDetailsAboutHisBooks = AuthorMapper
                .mapAuthorBooksEntitiesToDtos(fetchAuthorIfExist(id));

        log.info("ActionLog.getAuthorAndDetailsAboutHisBooks.success id: {}", id);
        return authorAndDetailsAboutHisBooks;
    }


            private AuthorEntity fetchAuthorIfExist(Long id) {
                return authorRepository.findById(id).orElseThrow(() -> {
                    log.error("ActionLog.fetchAuthorIfExist.error: id: {}", id);
                    return new NotFoundException(String.format(ExceptionConstants.NOT_FOUND_MESSAGE, "Author", id),
                            String.format(ExceptionConstants.NOT_FOUND_CODE, "AUTHOR"));
                });
            }

}
