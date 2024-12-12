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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegeringServiceUnitTest {

    @InjectMocks
    private RegeringService regeringService;

    @Mock
    private RegeringRepository regeringRepository;

    @Test
    public void testEditRegering() {
        // Arrange
        String regeringId = "1";
        RegeringRequest editRequest = new RegeringRequest();
        editRequest.setNaam("Edited Regering Name");

        Regering existingRegering = new Regering();
        existingRegering.setId(regeringId);
        existingRegering.setNaam("Original Regering Name");

        when(regeringRepository.findById(regeringId)).thenReturn(Optional.of(existingRegering));

        // Act
        regeringService.editRegering(regeringId, editRequest);

        // Assert
        assertEquals("Edited Regering Name", existingRegering.getNaam(), "The name should be updated to the new value");

        // Verify
        verify(regeringRepository, times(1)).save(existingRegering);
    }

    @Test
    public void testGetAllRegeringen() {
        // Arrange
        Regering regering1 = new Regering();
        regering1.setId("1");
        regering1.setNaam("Federaal");

        Regering regering2 = new Regering();
        regering2.setId("2");
        regering2.setNaam("Vlaams");

        List<Regering> regeringen = Arrays.asList(regering1, regering2);

        when(regeringRepository.findAll()).thenReturn(regeringen);

        // Act
        List<RegeringResponse> responses = regeringService.getAllRegeringen();

        // Assert
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Federaal", responses.get(0).getNaam());
        assertEquals("2", responses.get(1).getId());
        assertEquals("Vlaams", responses.get(1).getNaam());

        // Verify
        verify(regeringRepository, times(1)).findAll();
    }
}
