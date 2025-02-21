package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Visitor;

import java.util.Optional;


@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    @Query("SELECT v " +
            "FROM Visitor v " +
            "WHERE concat(v.firstName,' ',v.lastName) = :fullName OR v.personalData.id = :personalDataId ")
    Optional<Visitor> findByFullNameOrPersonalDataId(String fullName, long personalDataId);
}
