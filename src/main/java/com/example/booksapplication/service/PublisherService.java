package com.example.booksapplication.service;

import com.example.booksapplication.dao.entity.PublisherEntity;
import com.example.booksapplication.dao.repository.BookRepository;
import com.example.booksapplication.dao.repository.PublisherRepository;
import com.example.booksapplication.mapper.BookMapper;
import com.example.booksapplication.mapper.PublisherMapper;
import com.example.booksapplication.model.constants.ExceptionConstants;
import com.example.booksapplication.model.dto.BookDto;
import com.example.booksapplication.model.dto.PublisherAndBooksListDto;
import com.example.booksapplication.model.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class PublisherService {


    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    public void addBook(Long id, BookDto bookDto) {
        log.info("ActionLog.addBook.start id: {}", id);


        var bookEntity = BookMapper.mapDtoToEntity(bookDto);
        bookEntity.setPublisher(fetchPublisherIfExist(id));
        bookRepository.save(bookEntity);

        log.info("ActionLog.addBook.success id: {}", id);
    }


    public PublisherAndBooksListDto getAllBooksByPublisher(Long id) {
        log.info("ActionLog.getAllBooksBySpecificPublisher.start id: {}", id);

        var publisherWithHisBooksDto = PublisherMapper
                .mapEntityToPublisherBooksDto(fetchPublisherIfExist(id));

        log.info("ActionLog.getAllBooksBySpecificPublisher.success id: {}", id);
        return publisherWithHisBooksDto;
    }


            private PublisherEntity fetchPublisherIfExist(Long id) {
                return publisherRepository.findById(id)
                        .orElseThrow(() -> {
                            log.error("ActionLog.fetchPublisherIfExist.error id: {}", id);
                            return new NotFoundException(String.format(ExceptionConstants.NOT_FOUND_MESSAGE, "Publisher", id),
                                    String.format(ExceptionConstants.NOT_FOUND_CODE, "PUBLISHER"));
                        });
            }





}
