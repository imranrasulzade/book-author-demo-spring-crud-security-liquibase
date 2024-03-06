package com.matrix.springpracticeapp.repository;

import com.matrix.springpracticeapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.price > :price")
    ArrayList<Book> findByPriceGreaterThan(@Param("price") int price);

    @Query(value = "SELECT * FROM BookDao WHERE price < :maxPrice", nativeQuery = true)
    List<Book> findBooksByPriceLowerThan(@Param("maxPrice") int maxPrice);

}
