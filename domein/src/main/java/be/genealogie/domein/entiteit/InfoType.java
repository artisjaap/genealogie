package be.genealogie.domein.entiteit;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "INFO_TYPE")
@Entity
public class InfoType {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
}
