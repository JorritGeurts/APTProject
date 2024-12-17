package com.example.regeringservice;

import com.example.regeringservice.dto.RegeringRequest;
import com.example.regeringservice.dto.RegeringResponse;
import com.example.regeringservice.model.Regering;
import com.example.regeringservice.repository.RegeringRepository;
import com.example.regeringservice.service.RegeringService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegeringServiceUnitTest {

    @InjectMocks
    private RegeringService regeringService;

    @Mock
    private RegeringRepository regeringRepository;

    @Test
    public void testGetAllRegeringen() {
        // Arrange
        Regering regering1 = new Regering();
        regering1.setId("1");
        regering1.setNaam("Federaal");
        regering1.setBeschrijving("Beschrijving");
        regering1.setWebsite("Website");

        Regering regering2 = new Regering();
        regering2.setId("2");
        regering2.setNaam("Vlaams");
        regering2.setBeschrijving("Beschrijving");
        regering2.setWebsite("Website");

        List<Regering> regeringen = Arrays.asList(regering1, regering2);

        when(regeringRepository.findAll()).thenReturn(regeringen);

        // Act
        List<RegeringResponse> responses = regeringService.getAllRegeringen();

        // Assert
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Federaal", responses.get(0).getNaam());
        assertEquals("Beschrijving", responses.get(0).getBeschrijving());
        assertEquals("Website", responses.get(0).getWebsite());
        assertEquals("2", responses.get(1).getId());
        assertEquals("Vlaams", responses.get(1).getNaam());
        assertEquals("Beschrijving", responses.get(1).getBeschrijving());
        assertEquals("Website", responses.get(1).getWebsite());

        // Verify
        verify(regeringRepository, times(1)).findAll();
    }

    @Test
    public void testGetRegeringByNaam() {
        // Arrange
        Regering regering1 = new Regering();
        regering1.setId("1");
        regering1.setNaam("Federaal");
        regering1.setBeschrijving("Beschrijving");
        regering1.setWebsite("Website");

        // Mock the repository to return the Regering object when searching by name
        when(regeringRepository.findByNaam("Federaal")).thenReturn(Optional.of(regering1));

        // Act: Call the method in the service to get the regering by name
        RegeringResponse regeringResponse = regeringService.getRegeringByNaam("Federaal");

        // Assert: Verify that the service returns the correct regering response
        assertEquals("Federaal", regeringResponse.getNaam());

        // Verify that the repository's findByNaam method was called once
        verify(regeringRepository, times(1)).findByNaam("Federaal");
    }
}
