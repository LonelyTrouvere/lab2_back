package uni.LonelyTrouvere.lab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @Column(name = "id")
    private String Id;
    @Column(name = "race_id")
    private String raceId;
    @Column(name = "brigade_id")
    private String brigadeId;
    @Column(name = "plane_id")
    private String planeId;
}
