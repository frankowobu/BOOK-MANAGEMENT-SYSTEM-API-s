package com.example.libraryApplication.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "BORROWED_BOOKS")
public class BorrowedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date borrowedDate;
    @NonNull
    private Long returnDays;
    @NonNull
    private Date expectedReturnDate;
    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private Books books;

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Long getReturnDays() {
        return returnDays;
    }

    public void setReturnDays(Long returnDays) {
        this.returnDays = returnDays;
    }

    public Date getexpectedReturnDate() {
        Date borrowedDate = getBorrowedDate();
        long borrowedTime = borrowedDate.getTime();
        long returnDaysInMillis = getReturnDays()*24 * 60 * 60 * 1000;
        long expectedReturnTime = borrowedTime + returnDaysInMillis;
        return new Date(expectedReturnTime);
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }
}
