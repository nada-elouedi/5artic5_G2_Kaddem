package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EquipeServiceTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks
    }

    @Test
    public void testRetrieveAllEquipes() {
        // Préparer des données simulées
        List<Equipe> mockEquipes = new ArrayList<>();
        mockEquipes.add(new Equipe(1, "Equipe A"));
        mockEquipes.add(new Equipe(2, "Equipe B"));

        // Configurer le comportement du mock
        when(equipeRepository.findAll()).thenReturn(mockEquipes);

        // Exécuter la méthode à tester
        List<Equipe> result = equipeService.retrieveAllEquipes();

        // Vérifier les résultats
        assertEquals(2, result.size());
        assertEquals("Equipe A", result.get(0).getNomEquipe());
        assertEquals("Equipe B", result.get(1).getNomEquipe());

        // Vérifier que la méthode findAll du repository a bien été appelée
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    public void testAddEquipe() {
        // Préparer des données simulées
        Equipe equipe = new Equipe(1, "Equipe C");

        // Configurer le comportement du mock
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        // Exécuter la méthode à tester
        Equipe result = equipeService.addEquipe(equipe);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals("Equipe C", result.getNomEquipe());

        // Vérifier que la méthode save du repository a bien été appelée
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    public void testDeleteEquipe() {
        // Préparer des données simulées
        Equipe equipe = new Equipe(1, "Equipe F");

        // Configurer le comportement du mock pour la méthode retrieveEquipe
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Exécuter la méthode à tester
        equipeService.deleteEquipe(1);

        // Vérifier que la méthode delete du repository a bien été appelée
        verify(equipeRepository, times(1)).delete(equipe);
    }

    @Test
    public void testDeleteEquipe_NotFound() {
        // Configurer le comportement du mock pour retourner un Optional vide
        when(equipeRepository.findById(1)).thenReturn(Optional.empty());

        // Vérifier que la méthode deleteEquipe lève une exception
        assertThrows(IllegalArgumentException.class, () -> {
            equipeService.deleteEquipe(1);
        });

        // Vérifier que la méthode findById a été appelée une fois
        verify(equipeRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateEquipe() {
        // Préparer des données simulées
        Equipe equipe = new Equipe(1, "Equipe D");

        // Configurer le comportement du mock
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        // Exécuter la méthode à tester
        Equipe result = equipeService.updateEquipe(equipe);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals("Equipe D", result.getNomEquipe());

        // Vérifier que la méthode save du repository a bien été appelée
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    public void testRetrieveEquipe() {
        // Préparer des données simulées
        Equipe equipe = new Equipe(1, "Equipe E");

        // Configurer le comportement du mock
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Exécuter la méthode à tester
        Equipe result = equipeService.retrieveEquipe(1);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals("Equipe E", result.getNomEquipe());

        // Vérifier que la méthode findById du repository a bien été appelée
        verify(equipeRepository, times(1)).findById(1);
    }
}
