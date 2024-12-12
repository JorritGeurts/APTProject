package com.example.regeringservice;

import com.example.regeringservice.dto.RegeringRequest;
import com.example.regeringservice.model.Regering;
import com.example.regeringservice.repository.RegeringRepository;
import com.example.regeringservice.service.RegeringService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
