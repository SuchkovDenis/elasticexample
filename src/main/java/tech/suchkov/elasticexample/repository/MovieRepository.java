package tech.suchkov.elasticexample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.suchkov.elasticexample.entity.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("""
                SELECT m FROM Movie m
                WHERE LOWER(m.movie) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(m.overview) LIKE LOWER(CONCAT('%', :query, '%'))
                ORDER BY m.ratingBall DESC
            """)
    Page<Movie> searchByQuery(@Param("query") String query, Pageable pageable);
}