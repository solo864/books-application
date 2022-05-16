package com.example.booksapplication.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer createdYear;


    @OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<BookEntity> books;



            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PublisherEntity that = (PublisherEntity) o;
                return Objects.equals(id, that.id);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }
}
