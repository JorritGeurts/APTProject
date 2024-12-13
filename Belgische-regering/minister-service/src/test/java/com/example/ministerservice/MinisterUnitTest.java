package com.example.ministerservice;

import com.example.ministerservice.dto.MinisterRequest;
import com.example.ministerservice.dto.MinisterResponse;
import com.example.ministerservice.model.Minister;
import com.example.ministerservice.repository.MinisterRepository;
import com.example.ministerservice.service.MinisterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MinisterUnitTest {

    @InjectMocks
    private MinisterService ministerService;

    @Mock
    private MinisterRepository ministerRepository;

    @Test
    public void testCreateMinister() {
        // Arrange
        MinisterRequest request = new MinisterRequest();
        request.setNaam("Test Name");
        request.setPartijlidNaam("Test Party");
        request.setRegeringNaam("Test Government");

        Minister minister = Minister.builder()
                .naam(request.getNaam())
                .partijlidNaam(request.getPartijlidNaam())
                .regeringNaam(request.getRegeringNaam())
                .build();

        when(ministerRepository.save(any(Minister.class))).thenReturn(minister);

        // Act
        ministerService.createMinister(request);

        // Assert
        verify(ministerRepository, times(1)).save(any(Minister.class));
    }

    @Test
    public void testEditMinister() {
        // Arrange
        long ministerId = 1L;
        MinisterRequest editRequest = new MinisterRequest();
        editRequest.setNaam("Edited Name");
        editRequest.setPartijlidNaam("Edited Party");
        editRequest.setRegeringNaam("Edited Government");

        Minister existingMinister = Minister.builder()
                .id(ministerId)
                .naam("Original Name")
                .partijlidNaam("Original Party")
                .regeringNaam("Original Government")
                .build();

        when(ministerRepository.findById(ministerId)).thenReturn(Optional.of(existingMinister));

        // Act
        ministerService.editMinister(ministerId, editRequest);

        // Assert
        assertEquals("Edited Name", existingMinister.getNaam());
        assertEquals("Edited Party", existingMinister.getPartijlidNaam());
        assertEquals("Edited Government", existingMinister.getRegeringNaam());
        verify(ministerRepository, times(1)).save(existingMinister);
    }

    @Test
    public void testGetAllMinisters() {
        // Arrange
        Minister minister1 = Minister.builder().id(1L).naam("Name1").partijlidNaam("Party1").regeringNaam("Government1").build();
        Minister minister2 = Minister.builder().id(2L).naam("Name2").partijlidNaam("Party2").regeringNaam("Government2").build();

        when(ministerRepository.findAll()).thenReturn(Arrays.asList(minister1, minister2));

        // Act
        List<MinisterResponse> responseList = ministerService.getAllMinisters();

        // Assert
        assertEquals(2, responseList.size());
        assertEquals("Name1", responseList.get(0).getNaam());
        assertEquals("Party2", responseList.get(1).getPartijlidNaam());
        verify(ministerRepository, times(1)).findAll();
    }

    @Test
    public void testGetMinisterById() {
        // Arrange
        long ministerId = 1L;
        Minister minister = Minister.builder()
                .id(ministerId)
                .naam("Test Name")
                .partijlidNaam("Test Party")
                .regeringNaam("Test Government")
                .build();

        when(ministerRepository.findById(ministerId)).thenReturn(Optional.of(minister));

        // Act
        MinisterResponse response = ministerService.getMinisterById(ministerId);

        // Assert
        assertEquals("Test Name", response.getNaam());
        assertEquals("Test Party", response.getPartijlidNaam());
        assertEquals("Test Government", response.getRegeringNaam());
        verify(ministerRepository, times(1)).findById(ministerId);
    }

    @Test
    void testDeleteMinister() {
        long ministerId = 1L;
        // Setup: create a Partijlid object to be deleted.
        Minister ministerToDelete = Minister.builder()
                .id(ministerId)  // Setting the ID for the partijlid
                .build();  // Building the Partijlid object.

        // Mock behavior of the repository
        when(ministerRepository.existsById(ministerId)).thenReturn(true);

        // Call the service method that you want to test
        ministerService.deleteMinister(ministerId);

        // Verify that the repository's deleteById method was called once with the correct ID
        verify(ministerRepository, times(1)).deleteById(ministerId);  // Corrected to match actual interaction

        // You could add additional assertions if necessary
        // For example: verify if no other repository methods were called
        verify(ministerRepository, times(1)).existsById(ministerId);
    }
}
