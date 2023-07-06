package htwberlin.backend_kasse;

import htwberlin.backend_kasse.api.Mitarbeiter;
import htwberlin.backend_kasse.api.MitarbeiterManipulationRequest;
import htwberlin.backend_kasse.entities.KassenbuchungEntity;
import htwberlin.backend_kasse.entities.MitarbeiterEntity;
import htwberlin.backend_kasse.repos.MitarbeiterRepository;
import htwberlin.backend_kasse.services.MitarbeiterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MitarbeiterTest {

    @Mock
    private MitarbeiterRepository mitarbeiterRepository;

    @InjectMocks
    private MitarbeiterService mitarbeiterService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate_Success() {
        // Arrange
        MitarbeiterManipulationRequest request = new MitarbeiterManipulationRequest();
        request.setVorname("John");
        request.setNachname("Doe");
        request.setStudiengang("Computer Science");

        MitarbeiterEntity savedEntity = new MitarbeiterEntity();
        savedEntity.setId(1);
        savedEntity.setVorname("John");
        savedEntity.setNachname("Doe");
        savedEntity.setStudiengang("Computer Science");

        when(mitarbeiterRepository.save(any(MitarbeiterEntity.class))).thenReturn(savedEntity);

        // Act
        Mitarbeiter result = mitarbeiterService.create(request);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John", result.getVorname());
        assertEquals("Doe", result.getNachname());
        assertEquals("Computer Science", result.getStudiengang());

        verify(mitarbeiterRepository).save(any(MitarbeiterEntity.class));
    }

    @Test
    public void testFindById_ExistingId_ReturnsMitarbeiter() {
        // Arrange
        MitarbeiterEntity entity = new MitarbeiterEntity();
        entity.setId(1);
        entity.setVorname("John");
        entity.setNachname("Doe");
        entity.setStudiengang("Computer Science");

        when(mitarbeiterRepository.findById(1)).thenReturn(Optional.of(entity));

        // Act
        Mitarbeiter result = mitarbeiterService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John", result.getVorname());
        assertEquals("Doe", result.getNachname());
        assertEquals("Computer Science", result.getStudiengang());

        verify(mitarbeiterRepository).findById(1);
    }

    @Test
    public void testFindById_NonExistingId_ReturnsNull() {
        // Arrange
        when(mitarbeiterRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Mitarbeiter result = mitarbeiterService.findById(1);

        // Assert
        assertNull(result);

        verify(mitarbeiterRepository).findById(1);
    }

    @Test
    public void testFindAll_ReturnsListOfMitarbeiters() {
        // Arrange
        List<MitarbeiterEntity> entities = new ArrayList<>();
        MitarbeiterEntity entity1 = new MitarbeiterEntity();
        entity1.setId(1);
        entity1.setVorname("John");
        entity1.setNachname("Doe");
        entity1.setStudiengang("Computer Science");
        entities.add(entity1);

        MitarbeiterEntity entity2 = new MitarbeiterEntity();
        entity2.setId(2);
        entity2.setVorname("Jane");
        entity2.setNachname("Smith");
        entity2.setStudiengang("Business Administration");
        entities.add(entity2);

        when(mitarbeiterRepository.findAll()).thenReturn(entities);

        // Act
        List<Mitarbeiter> result = mitarbeiterService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        Mitarbeiter result1 = result.get(0);
        assertEquals(1, result1.getId());
        assertEquals("John", result1.getVorname());
        assertEquals("Doe", result1.getNachname());
        assertEquals("Computer Science", result1.getStudiengang());

        Mitarbeiter result2 = result.get(1);
        assertEquals(2, result2.getId());
        assertEquals("Jane", result2.getVorname());
        assertEquals("Smith", result2.getNachname());
        assertEquals("Business Administration", result2.getStudiengang());

        verify(mitarbeiterRepository).findAll();
    }

    @Test
    public void testUpdate_ExistingId_ReturnsUpdatedMitarbeiter() {
        // Arrange
        MitarbeiterManipulationRequest request = new MitarbeiterManipulationRequest();
        request.setVorname("John");
        request.setNachname("Doe");
        request.setStudiengang("Computer Science");

        MitarbeiterEntity existingEntity = new MitarbeiterEntity();
        existingEntity.setId(1);
        existingEntity.setVorname("John");
        existingEntity.setNachname("Doe");
        existingEntity.setStudiengang("Computer Science");

        MitarbeiterEntity updatedEntity = new MitarbeiterEntity();
        updatedEntity.setId(1);
        updatedEntity.setVorname("John");
        updatedEntity.setNachname("Smith");
        updatedEntity.setStudiengang("Business Administration");

        when(mitarbeiterRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(mitarbeiterRepository.save(any(MitarbeiterEntity.class))).thenReturn(updatedEntity);

        // Act
        Mitarbeiter result = mitarbeiterService.update(1, request);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John", result.getVorname());
        assertEquals("Smith", result.getNachname());
        assertEquals("Business Administration", result.getStudiengang());

        verify(mitarbeiterRepository).findById(1);
        verify(mitarbeiterRepository).save(any(MitarbeiterEntity.class));
    }

    @Test
    public void testUpdate_NonExistingId_ReturnsNull() {
        // Arrange
        MitarbeiterManipulationRequest request = new MitarbeiterManipulationRequest();
        request.setVorname("John");
        request.setNachname("Doe");
        request.setStudiengang("Computer Science");

        when(mitarbeiterRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Mitarbeiter result = mitarbeiterService.update(1, request);

        // Assert
        assertNull(result);

        verify(mitarbeiterRepository).findById(1);
        verify(mitarbeiterRepository, never()).save(any(MitarbeiterEntity.class));
    }

    @Test
    public void testDelete_ExistingId_ReturnsTrue() {
        // Arrange
        when(mitarbeiterRepository.findById(1)).thenReturn(Optional.of(new MitarbeiterEntity()));

        // Act
        boolean result = mitarbeiterService.delete(1);

        // Assert
        assertTrue(result);

        verify(mitarbeiterRepository).findById(1);
        verify(mitarbeiterRepository).delete(any(MitarbeiterEntity.class));
    }

    @Test
    public void testDelete_NonExistingId_ReturnsFalse() {
        // Arrange
        when(mitarbeiterRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        boolean result = mitarbeiterService.delete(1);

        // Assert
        assertFalse(result);

        verify(mitarbeiterRepository).findById(1);
        verify(mitarbeiterRepository, never()).delete(any(MitarbeiterEntity.class));
    }
}

