package com.example.ministerservice;

import com.example.ministerservice.dto.*;
import com.example.ministerservice.model.Minister;
import com.example.ministerservice.repository.MinisterRepository;
import com.example.ministerservice.service.MinisterService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MinisterUnitTest {

    @InjectMocks
    private MinisterService ministerService;

    @Mock
    private MinisterRepository ministerRepository;

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
        ReflectionTestUtils.setField(ministerService, "partijlidServiceBaseUrl", "http://localhost:8081");
        ReflectionTestUtils.setField(ministerService, "regeringServiceBaseUrl", "http://localhost:8080");
    }

    @Test
    public void testCreateMinister_Success() {
        // Arrange
        MinisterRequest ministerRequest = new MinisterRequest("Test Minister", "TestPartijlid", "TestRegering");

        PartijlidResponse partijlidResponse = PartijlidResponse.builder()
                .naam("TestPartijlid")
                .build();

        RegeringResponse regeringResponse = RegeringResponse.builder()
                .naam("TestRegering")
                .build();

        Minister minister = Minister.builder()
                .naam("Test Minister")
                .partijlidNaam("TestPartijlid")
                .regeringNaam("TestRegering")
                .build();

        when(ministerRepository.save(any(Minister.class))).thenReturn(minister);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PartijlidResponse.class)).thenReturn(Mono.just(partijlidResponse));
        when(responseSpec.bodyToMono(RegeringResponse.class)).thenReturn(Mono.just(regeringResponse));

        // Act
        ministerService.createMinister(ministerRequest);

        // Assert
        verify(ministerRepository, times(1)).save(any(Minister.class));
    }

    @Test
    public void testGetMinisterByNaam_Success() {
        // Arrange
        String naam = "Test Minister";
        Minister minister = new Minister(1L, naam, "TestPartijlid", "TestRegering");

        when(ministerRepository.findByNaam(naam)).thenReturn(Optional.of(minister));

        // Act
        MinisterResponse result = ministerService.getMinisterByNaam(naam);

        // Assert
        assertNotNull(result);
        assertEquals(naam, result.getNaam());
        verify(ministerRepository, times(1)).findByNaam(naam);
    }

    @Test
    public void testGetMinisterByNaam_Failure() {
        // Arrange
        String naam = "NonExisting Minister";

        when(ministerRepository.findByNaam(naam)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            ministerService.getMinisterByNaam(naam);
        });
        assertEquals("Minister with naam " + naam + " not found.", thrown.getMessage());
        verify(ministerRepository, times(1)).findByNaam(naam);
    }

    @Test
    public void testEditMinister_Success() {
        // Arrange
        long id = 1L;
        MinisterRequest editRequest = new MinisterRequest("Updated Minister", "UpdatedPartijlid", "UpdatedRegering");
        Minister existingMinister = new Minister(id, "Test Minister", "TestPartijlid", "TestRegering");

        PartijlidResponse partijlidResponse = PartijlidResponse.builder()
                .naam("UpdatedPartijlid")
                .build();

        RegeringResponse regeringResponse = RegeringResponse.builder()
                .naam("UpdatedRegering")
                .build();

        when(ministerRepository.findById(id)).thenReturn(Optional.of(existingMinister));
        when(ministerRepository.save(any(Minister.class))).thenReturn(existingMinister);

        // Mock the WebClient call chain
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PartijlidResponse.class)).thenReturn(Mono.just(partijlidResponse));
        when(responseSpec.bodyToMono(RegeringResponse.class)).thenReturn(Mono.just(regeringResponse));

        // Act
        ministerService.editMinister(id, editRequest);

        // Assert
        assertEquals("Updated Minister", existingMinister.getNaam());
        assertEquals("UpdatedPartijlid", existingMinister.getPartijlidNaam());
        assertEquals("UpdatedRegering", existingMinister.getRegeringNaam());

        // Verify that webClient.get() was called once for each service (twice in total)
        verify(webClient, times(2)).get(); // Expecting two calls
        verify(requestHeadersUriSpec, times(2)).uri(anyString(), anyString()); // Verify URI was set for both services
        verify(requestHeadersSpec, times(2)).retrieve(); // Verify retrieve method is called for both services
    }



    @Test
    public void testDeleteMinister_Success() {
        // Arrange
        long id = 1L;

        when(ministerRepository.existsById(id)).thenReturn(true);

        // Act
        ministerService.deleteMinister(id);

        // Assert
        verify(ministerRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteMinister_Failure() {
        // Arrange
        long id = 1L;

        when(ministerRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            ministerService.deleteMinister(id);
        });
        assertEquals("Minister with ID " + id + " not found.", thrown.getMessage());
        verify(ministerRepository, times(0)).deleteById(id);
    }

    @Test
    public void testGetAllMinisters_Success() {
        // Arrange
        Minister minister1 = new Minister(1L, "Minister One", "TestPartijlid", "TestRegering");
        Minister minister2 = new Minister(2L, "Minister Two", "TestPartijlid", "TestRegering");

        List<Minister> ministers = Arrays.asList(minister1, minister2);
        when(ministerRepository.findAll()).thenReturn(ministers);

        // Act
        List<MinisterResponse> result = ministerService.getAllMinisters();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ministerRepository, times(1)).findAll();
    }

    @Test
    public void testGetMinisterById_Success() {
        // Arrange
        long id = 1L;
        Minister minister = new Minister(id, "Test Minister", "TestPartijlid", "TestRegering");

        when(ministerRepository.findById(id)).thenReturn(Optional.of(minister));

        // Act
        MinisterResponse result = ministerService.getMinisterById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(ministerRepository, times(1)).findById(id);
    }

    @Test
    public void testGetMinisterById_Failure() {
        // Arrange
        long id = 1L;

        when(ministerRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            ministerService.getMinisterById(id);
        });
        assertEquals("Minister with ID " + id + " not found.", thrown.getMessage());
        verify(ministerRepository, times(1)).findById(id);
    }
}
