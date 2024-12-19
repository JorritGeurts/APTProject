package com.example.partijlidservice;

import com.example.partijlidservice.dto.*;
import com.example.partijlidservice.model.Partijlid;
import com.example.partijlidservice.repository.PartijlidRepository;
import com.example.partijlidservice.service.PartijlidService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PartijlidUnitTest {

    @InjectMocks
    private PartijlidService partijlidService;

    @Mock
    private PartijlidRepository partijlidRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(partijlidService, "regeringServiceBaseUrl", "http://localhost:8081");
        ReflectionTestUtils.setField(partijlidService, "partijServiceBaseUrl", "http://localhost:8082");
    }

    /*@Test
    public void testCreatePartijlid_Success() {
        // Arrange
        String partijNaam = "TestPartij";
        String regeringNaam = "TestRegering";
        String naam = "Test Member";
        String email = "test@example.com";

        PartijlidRequest partijlidRequest = new PartijlidRequest();
        partijlidRequest.setNaam(naam);
        partijlidRequest.setEmail(email);
        partijlidRequest.setPartijNaam(partijNaam);
        partijlidRequest.setRegeringNaam(regeringNaam);

        PartijResponse partijResponse = new PartijResponse();
        partijResponse.setNaam(partijNaam);

        RegeringResponse regeringResponse = new RegeringResponse();
        regeringResponse.setNaam(regeringNaam);

        Partijlid partijlid = Partijlid.builder()
                .naam(naam)
                .email(email)
                .partijNaam(partijNaam)
                .regeringNaam(regeringNaam)
                .build();

        when(partijlidRepository.save(any(Partijlid.class))).thenReturn(partijlid);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PartijResponse.class)).thenReturn(Mono.just(partijResponse));
        when(responseSpec.bodyToMono(RegeringResponse.class)).thenReturn(Mono.just(regeringResponse));

        // Act
        partijlidService.createPartijlid(partijlidRequest);

        // Assert
        verify(partijlidRepository, times(1)).save(any(Partijlid.class));
    }*/

    @Test
    public void testGetPartijlidByNaam_Success() {
        // Arrange
        String naam = "Test Member";
        Partijlid partijlid = new Partijlid(1L, naam, "test@example.com", "TestPartij", "TestRegering");

        when(partijlidRepository.findByNaam(naam)).thenReturn(java.util.Optional.of(partijlid));

        // Act
        PartijlidResponse result = partijlidService.getPartijlidByNaam(naam);

        // Assert
        assertNotNull(result);
        assertEquals(naam, result.getNaam());
        verify(partijlidRepository, times(1)).findByNaam(naam);
    }

    @Test
    public void testGetPartijlidByNaam_Failure() {
        // Arrange
        String naam = "NonExisting Member";

        when(partijlidRepository.findByNaam(naam)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            partijlidService.getPartijlidByNaam(naam);
        });
        assertEquals("Partijlid with naam " + naam + " not found.", thrown.getMessage());
        verify(partijlidRepository, times(1)).findByNaam(naam);
    }

    @Test
    public void testEditPartijlid_Success() {
        // Arrange
        long id = 1L;
        String updatedNaam = "Updated Member";
        Partijlid existingPartijlid = new Partijlid(id, "Test Member", "test@example.com", "TestPartij", "TestRegering");

        PartijlidRequest editRequest = new PartijlidRequest();
        editRequest.setNaam(updatedNaam);

        when(partijlidRepository.findById(id)).thenReturn(java.util.Optional.of(existingPartijlid));
        when(partijlidRepository.save(any(Partijlid.class))).thenReturn(existingPartijlid);

        // Act
        partijlidService.editPartijlid(id, editRequest);

        // Assert
        assertEquals(updatedNaam, existingPartijlid.getNaam());
        verify(partijlidRepository, times(1)).save(any(Partijlid.class));
    }

    @Test
    public void testDeletePartijlid_Success() {
        // Arrange
        long id = 1L;

        when(partijlidRepository.existsById(id)).thenReturn(true);

        // Act
        partijlidService.deletePartijlid(id);

        // Assert
        verify(partijlidRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeletePartijlid_Failure() {
        // Arrange
        long id = 1L;

        when(partijlidRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            partijlidService.deletePartijlid(id);
        });
        assertEquals("Partijlid with ID " + id + " not found.", thrown.getMessage());
        verify(partijlidRepository, times(0)).deleteById(id);
    }

    // New test for getAllPartijleden
    @Test
    public void testGetAllPartijleden_Success() {
        // Arrange
        Partijlid partijlid1 = new Partijlid(1L, "Member One", "member1@example.com", "TestPartij", "TestRegering");
        Partijlid partijlid2 = new Partijlid(2L, "Member Two", "member2@example.com", "TestPartij", "TestRegering");

        List<Partijlid> partijleden = Arrays.asList(partijlid1, partijlid2);
        when(partijlidRepository.findAll()).thenReturn(partijleden);

        // Act
        List<PartijlidResponse> result = partijlidService.getAllPartijleden();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(partijlidRepository, times(1)).findAll();
    }

    // New test for getPartijlidById
    @Test
    public void testGetPartijlidById_Success() {
        // Arrange
        long id = 1L;
        Partijlid partijlid = new Partijlid(id, "Test Member", "test@example.com", "TestPartij", "TestRegering");

        when(partijlidRepository.findById(id)).thenReturn(java.util.Optional.of(partijlid));

        // Act
        PartijlidResponse result = partijlidService.getPartijlidById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Test Member", result.getNaam());
        verify(partijlidRepository, times(1)).findById(id);
    }

    @Test
    public void testGetPartijlidById_Failure() {
        // Arrange
        long id = 1L;

        when(partijlidRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            partijlidService.getPartijlidById(id);
        });
        assertEquals("Partijlid with ID " + id + " not found.", thrown.getMessage());
        verify(partijlidRepository, times(1)).findById(id);
    }
}
