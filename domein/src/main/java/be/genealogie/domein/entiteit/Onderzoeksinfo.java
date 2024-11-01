package be.genealogie.domein.entiteit;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "ONDERZOEKSINFO")
@Entity
public class Onderzoeksinfo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_ID")
    private NatuurlijkPersoon natuurlijkPersoon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INFO_TYPE_ID")
    private InfoType infoType;

    @Column(name = "LINK_NAAR_DOCUMENT")
    private String linkNaarDocument;

    @Column(name = "LINK_NAAR_BRON")
    private String linkNaarBron;

    @Column(name = "EXTRA_INFO")
    private String extraInfo;
}
