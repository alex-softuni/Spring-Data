package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Attraction;

import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Optional<Attraction> findByName(String name);

    @Query("SELECT a FROM Attraction a " +
            "WHERE a.type IN('historical site','archaeological site') " +
            "AND a.elevation >= 300")
    Attraction findByTypeAndElevationGreaterThan();
}
