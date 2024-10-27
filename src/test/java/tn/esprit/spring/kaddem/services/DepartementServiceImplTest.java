package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

@ExtendWith(MockitoExtension.class)
 class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    private Departement departement;

    @BeforeEach
    public void setup() {
        // Initialize mocks (useful if @ExtendWith(MockitoExtension.class) isn’t working as expected)
        MockitoAnnotations.openMocks(this);

        // Setup a sample departement object
        departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("IT Department");
    }

    @Test
    void testRetrieveAllDepartements() {
        List<Departement> departements = Arrays.asList(departement);
        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> result = departementService.retrieveAllDepartements();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("IT Department", result.get(0).getNomDepart());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
     void testAddDepartement() {
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement result = departementService.addDepartement(departement);

        assertNotNull(result);
        assertEquals(departement.getIdDepart(), result.getIdDepart());
        assertEquals("IT Department", result.getNomDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testUpdateDepartement() {
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement result = departementService.updateDepartement(departement);

        assertNotNull(result);
        assertEquals(departement.getNomDepart(), result.getNomDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testRetrieveDepartement() {
        when(departementRepository.findById(departement.getIdDepart())).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveDepartement(departement.getIdDepart());

        assertNotNull(result);
        assertEquals(departement.getIdDepart(), result.getIdDepart());
        verify(departementRepository, times(1)).findById(departement.getIdDepart());
    }

    @Test
    void testDeleteDepartement() {
        when(departementRepository.findById(departement.getIdDepart())).thenReturn(Optional.of(departement));

        departementService.deleteDepartement(departement.getIdDepart());

        verify(departementRepository, times(1)).delete(departement);
    }
}
