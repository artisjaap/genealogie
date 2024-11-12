package be.genealogie.domein.entiteit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "DOCUMENT")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_TYPE_ID")
    private DocumentType documentType;

    @Column(name = "PATH")
    private String pathNaarDocument;

    @Column(name = "TRANSCRIPT")
    private String transcript;

    @ManyToOne
    @JoinColumn(name = "PERSOON_ID")
    private NatuurlijkPersoon natuurlijkPersoon;

    @ManyToOne
    @JoinColumn(name = "RELATIE_ID")
    private Relatie relatie;
}
