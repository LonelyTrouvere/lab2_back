package uni.LonelyTrouvere.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uni.LonelyTrouvere.lab.entity.Brigade;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrigadeRepo extends JpaRepository<Brigade, String> {
    Optional<List<Brigade>> findByName(String name);

    @Modifying
    @Query("UPDATE Crewmate c SET c.brigadeId = '' WHERE c.brigadeId = ?1")
    void cascadeUpdate(String Id);
    @Modifying
    @Query("DELETE Flight f WHERE f.brigadeId = ?1")
    void cascadeDelete(String Id);
}
