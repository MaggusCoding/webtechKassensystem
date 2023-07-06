package htwberlin.backend_kasse;

import htwberlin.backend_kasse.api.Kassenbuchung;
import htwberlin.backend_kasse.api.KassenbuchungManipulationRequest;
import htwberlin.backend_kasse.entities.KassenbuchungEntity;
import htwberlin.backend_kasse.entities.MitarbeiterEntity;
import htwberlin.backend_kasse.repos.KassenbuchungRepository;
import htwberlin.backend_kasse.repos.MitarbeiterRepository;
import htwberlin.backend_kasse.services.KassenbuchungService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class KassenbuchungTest {

    @Mock
    private KassenbuchungRepository kassenbuchungRepository;

    @Mock
    private MitarbeiterRepository mitarbeiterRepository;

    @InjectMocks
    private KassenbuchungService kassenbuchungService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @ExtendWith(SpringExtension.class)
    public void testCreateKassenbuchung_Success() {
        // Arrange
        KassenbuchungManipulationRequest request = new KassenbuchungManipulationRequest();
        request.setBuchungsbetrag(BigDecimal.TEN);
        request.setMitarbeiter_id(1);
        request.setComment("Test comment");
        MitarbeiterEntity mitarbeiterEntity = new MitarbeiterEntity();
        mitarbeiterEntity.setId(1);
        KassenbuchungEntity savedEntity = new KassenbuchungEntity();
        savedEntity.setId(1);
        savedEntity.setBuchungsbetrag(BigDecimal.TEN);
        savedEntity.setComment("Test comment");
        savedEntity.setTimestamp(Instant.now());
        savedEntity.setBuchender(mitarbeiterEntity);

        when(mitarbeiterRepository.findById(1)).thenReturn(Optional.of(new MitarbeiterEntity()));
        when(kassenbuchungRepository.save(any(KassenbuchungEntity.class))).thenReturn(savedEntity);

        // Act
        Kassenbuchung result = kassenbuchungService.create(request);
        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(BigDecimal.TEN, result.getBuchungsbetrag());
        assertEquals("Test comment", result.getComment());
        assertNotNull(result.getTimestamp());

        verify(mitarbeiterRepository).findById(1);
        verify(kassenbuchungRepository).save(any(KassenbuchungEntity.class));
    }


    @Test
    public void testFindById_ExistingId_ReturnsKassenbuchung() {
        // Arrange
        MitarbeiterEntity mitarbeiterEntity = new MitarbeiterEntity();
        mitarbeiterEntity.setId(1);
        KassenbuchungEntity entity = new KassenbuchungEntity();
        entity.setId(1);
        entity.setBuchungsbetrag(BigDecimal.TEN);
        entity.setComment("Test comment");
        entity.setTimestamp(Instant.now());
        entity.setBuchender(mitarbeiterEntity);

        when(kassenbuchungRepository.findById(1)).thenReturn(Optional.of(entity));

        // Act
        Kassenbuchung result = kassenbuchungService.findById(1);

        // Assert        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(BigDecimal.TEN, result.getBuchungsbetrag());
        assertEquals("Test comment", result.getComment());
        assertNotNull(result.getTimestamp());

        verify(kassenbuchungRepository).findById(1);
    }

    @Test
    public void testFindById_NonExistingId_ReturnsNull() {
        // Arrange
        when(kassenbuchungRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Kassenbuchung result = kassenbuchungService.findById(1);

        // Assert
        assertNull(result);

        verify(kassenbuchungRepository).findById(1);
    }

    @Test
    public void testFindAll_ReturnsListOfKassenbuchungen() {
        // Arrange
        MitarbeiterEntity mitarbeiterEntity = new MitarbeiterEntity();
        mitarbeiterEntity.setId(1);
        List<KassenbuchungEntity> entities = new ArrayList<>();
        KassenbuchungEntity entity1 = new KassenbuchungEntity();
        entity1.setId(1);
        entity1.setBuchungsbetrag(BigDecimal.TEN);
        entity1.setComment("Test comment 1");
        entity1.setTimestamp(Instant.now());
        entity1.setBuchender(mitarbeiterEntity);
        entities.add(entity1);

        KassenbuchungEntity entity2 = new KassenbuchungEntity();
        entity2.setId(2);
        entity2.setBuchungsbetrag(BigDecimal.valueOf(20));
        entity2.setComment("Test comment 2");
        entity2.setTimestamp(Instant.now());
        entity2.setBuchender(mitarbeiterEntity);
        entities.add(entity2);

        when(kassenbuchungRepository.findAll()).thenReturn(entities);

        // Act
        List<Kassenbuchung> result = kassenbuchungService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        Kassenbuchung result1 = result.get(0);
        assertEquals(1, result1.getId());
        assertEquals(BigDecimal.TEN, result1.getBuchungsbetrag());
        assertEquals("Test comment 1", result1.getComment());
        assertNotNull(result1.getTimestamp());

        Kassenbuchung result2 = result.get(1);
        assertEquals(2, result2.getId());
        assertEquals(BigDecimal.valueOf(20), result2.getBuchungsbetrag());
        assertEquals("Test comment 2", result2.getComment());
        assertNotNull(result2.getTimestamp());

        verify(kassenbuchungRepository).findAll();
    }

    @Test
    public void testUpdate_ExistingId_ReturnsUpdatedKassenbuchung() {
        // Arrange
        KassenbuchungManipulationRequest request = new KassenbuchungManipulationRequest();
        request.setBuchungsbetrag(BigDecimal.valueOf(30));
        request.setComment("Updated comment");

        MitarbeiterEntity mitarbeiterEntity = new MitarbeiterEntity();
        mitarbeiterEntity.setId(1);

        KassenbuchungEntity existingEntity = new KassenbuchungEntity();
        existingEntity.setId(1);
        existingEntity.setBuchungsbetrag(BigDecimal.TEN);
        existingEntity.setComment("Test comment");
        existingEntity.setTimestamp(Instant.now());
        existingEntity.setBuchender(mitarbeiterEntity);

        KassenbuchungEntity updatedEntity = new KassenbuchungEntity();
        updatedEntity.setId(1);
        updatedEntity.setBuchungsbetrag(BigDecimal.valueOf(30));
        updatedEntity.setComment("Updated comment");
        updatedEntity.setTimestamp(existingEntity.getTimestamp());
        updatedEntity.setBuchender(mitarbeiterEntity);

        when(kassenbuchungRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(kassenbuchungRepository.save(any(KassenbuchungEntity.class))).thenReturn(updatedEntity);

        // Act
        Kassenbuchung result = kassenbuchungService.update(1, request);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(BigDecimal.valueOf(30), result.getBuchungsbetrag());
        assertEquals("Updated comment", result.getComment());
        assertNotNull(result.getTimestamp());

        verify(kassenbuchungRepository).findById(1);
        verify(kassenbuchungRepository).save(any(KassenbuchungEntity.class));
    }
}

