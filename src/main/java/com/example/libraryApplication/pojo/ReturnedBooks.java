package com.example.libraryApplication.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "RETURNED_BOOKS")
public class ReturnedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "date_returned")
    private Date dateReturned;
    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private Books books;

}
