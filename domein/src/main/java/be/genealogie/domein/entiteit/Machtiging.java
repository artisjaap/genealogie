package be.genealogie.domein.entiteit;

import be.genealogie.domein.GebruikerRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "MACHTIGINGEN")
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Machtiging {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="MACHTIGING", nullable = false)
    @Enumerated(EnumType.STRING)
    private GebruikerRole machtiging;

}
