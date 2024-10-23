
package tn.esprit.spring.kaddem.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;
    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EquipeRepository equipeRepository;
    @Mock
    private DepartementRepository departementRepository;

    private Etudiant etudiant1;
    private Etudiant etudiant2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Creating two concrete student examples for testing
        etudiant1 = new Etudiant();
        etudiant1.setId(1);
        etudiant1.setNom("Roua Ons");
        etudiant1.setEmail("roua.ons@example.com");

        etudiant2 = new Etudiant();
        etudiant2.setId(2);
        etudiant2.setNom("Rihab riri");
        etudiant2.setEmail("rihab.riri@example.com");
    }

    @Test
    void testRetrieveAllEtudiants() {
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant1, etudiant2));

        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        assertEquals(2, etudiants.size());
    }

    @Test
    void testAddEtudiant() {
        when(etudiantRepository.save(etudiant1)).thenReturn(etudiant1);

        Etudiant result = etudiantService.addEtudiant(etudiant1);
        assertNotNull(result);
        assertEquals("Roua Ons", result.getNom());
        verify(etudiantRepository).save(etudiant1);
    }

    @Test
    void testUpdateEtudiant() {
        when(etudiantRepository.save(etudiant1)).thenReturn(etudiant1);

        Etudiant result = etudiantService.updateEtudiant(etudiant1);
        assertNotNull(result);
        verify(etudiantRepository).save(etudiant1);
    }

    @Test
    void testRetrieveEtudiant() {
        Integer id = 1;
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant1));

        Etudiant result = etudiantService.retrieveEtudiant(id);
        assertNotNull(result);
        assertEquals("Roua Ons", result.getNom());
    }

    @Test
    void testRemoveEtudiant() {
        Integer id = 1;
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant1));

        etudiantService.removeEtudiant(id);
        verify(etudiantRepository).delete(etudiant1);
    }

    @Test
    void testAssignEtudiantToDepartement() {
        Integer etudiantId = 1;
        Integer departementId = 1;
        Departement d = new Departement();

        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(etudiant1));
        when(departementRepository.findById(departementId)).thenReturn(Optional.of(d));

        etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
        assertEquals(d, etudiant1.getDepartement());
        verify(etudiantRepository).save(etudiant1);
    }

    @Test
    void testAddAndAssignEtudiantToEquipeAndContract() {
        Integer contratId = 1;
        Integer equipeId = 1;
        Contrat c = new Contrat();
        Equipe eq = new Equipe();

        when(contratRepository.findById(contratId)).thenReturn(Optional.of(c));
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(eq));

        etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant1, contratId, equipeId);

        assertEquals(etudiant1, c.getEtudiant());
        assertTrue(eq.getEtudiants().contains(etudiant1));
    }

    @Test
    void testGetEtudiantsByDepartement() {
        Integer departementId = 1;
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(departementId)).thenReturn(Arrays.asList(etudiant1, etudiant2));

        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(departementId);
        assertEquals(2, result.size());
    }
}
