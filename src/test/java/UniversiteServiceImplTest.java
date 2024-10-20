import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteServiceImpl;  // Service under test

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    public void testRetrieveAllUniversites() {
        // Arrange
        List<Universite> universites = Arrays.asList(new Universite(), new Universite());
        when(universiteRepository.findAll()).thenReturn(universites);

        // Act
        List<Universite> result = universiteServiceImpl.retrieveAllUniversites();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testAddUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setNomUniv("ESPRIT");
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite created = universiteServiceImpl.addUniversite(universite);

        // Assert
        assertNotNull(created);
        assertEquals("ESPRIT", created.getNomUniv());
        verify(universiteRepository).save(universite);
    }

    @Test
    public void testUpdateUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setIdUniv(1);
        universite.setNomUniv("ESPRIT Updated");
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite updated = universiteServiceImpl.updateUniversite(universite);

        // Assert
        assertNotNull(updated);
        assertEquals("ESPRIT Updated", updated.getNomUniv());
        verify(universiteRepository).save(universite);
    }

    @Test
    public void testRetrieveUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setIdUniv(1);
        universite.setNomUniv("ESPRIT");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        // Act
        Universite found = universiteServiceImpl.retrieveUniversite(1);

        // Assert
        assertNotNull(found);
        assertEquals("ESPRIT", found.getNomUniv());
    }

    @Test
    public void testDeleteUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setIdUniv(1);
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        // Act
        universiteServiceImpl.deleteUniversite(1);

        // Assert
        verify(universiteRepository).delete(universite);
    }

    @Test
    public void testAssignUniversiteToDepartement() {
        // Arrange
        Universite universite = new Universite();
        universite.setIdUniv(1);
        universite.setDepartements(new HashSet<>());

        Departement departement = new Departement();
        departement.setIdDepart(1);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // Act
        universiteServiceImpl.assignUniversiteToDepartement(1, 1);

        // Assert
        assertTrue(universite.getDepartements().contains(departement));
        verify(universiteRepository).save(universite);
    }

    @Test
    public void testRetrieveDepartementsByUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setIdUniv(1);
        Set<Departement> departements = new HashSet<>();
        departements.add(new Departement());
        universite.setDepartements(departements);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        // Act
        Set<Departement> result = universiteServiceImpl.retrieveDepartementsByUniversite(1);

        // Assert
        assertEquals(1, result.size());
    }
}