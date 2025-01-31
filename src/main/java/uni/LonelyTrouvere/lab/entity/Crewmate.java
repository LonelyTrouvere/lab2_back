package uni.LonelyTrouvere.lab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crews")
public class Crewmate {
//    public static enum Qualification{
//        STUART,
//        PILOT,
//        RADIO_OFFICER
//    }
    @Id
    @Column(name = "id")
    private String Id;
    @Column(name = "name")
    private String name;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "brigade")
    private String brigadeId;
}
