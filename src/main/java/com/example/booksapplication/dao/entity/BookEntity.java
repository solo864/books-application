package com.example.booksapplication.dao.entity;

import com.example.booksapplication.model.enums.Genre;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "publishing_year")
    private Integer publishingYear;


    @Column(name = "page_count")
    private Integer pageCount;

    @Enumerated(EnumType.STRING)
    private Genre genre;


    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @ManyToMany(fetch = FetchType.LAZY)
    private List<AuthorEntity> authors;


    @ManyToOne(cascade = CascadeType.ALL)
    private PublisherEntity publisher;



            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                BookEntity that = (BookEntity) o;
                return Objects.equals(id, that.id);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }
}
