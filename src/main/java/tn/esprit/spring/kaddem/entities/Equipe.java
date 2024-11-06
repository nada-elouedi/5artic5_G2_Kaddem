package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor // Constructeur sans paramètres
@AllArgsConstructor
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEquipe;
    private String nomEquipe;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Etudiant> etudiants = new HashSet<>(); // Initialisation de l'ensemble pour éviter NullPointerException

    @OneToOne
    private DetailEquipe detailEquipe;
    public Equipe() {
    // Constructeur par défaut
}

    // Constructeur avec seulement idEquipe et nomEquipe
    public Equipe(Integer idEquipe, String nomEquipe) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
    }

    // Constructeur avec seulement nomEquipe
    public Equipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    // Constructeur avec nomEquipe et niveau
    public Equipe(String nomEquipe, Niveau niveau) {
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
    }
}
