package com.matrix.springpracticeapp.repository;

import com.matrix.springpracticeapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * FROM Author WHERE id = :id", nativeQuery = true)
    Optional<Author> findByAuthorId(@Param("id") long id);
}
