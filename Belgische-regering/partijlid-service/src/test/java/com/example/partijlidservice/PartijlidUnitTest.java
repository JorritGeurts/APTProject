package com.example.partijlidservice;

import com.example.partijlidservice.dto.PartijlidRequest;
import com.example.partijlidservice.dto.PartijlidResponse;
import com.example.partijlidservice.model.Partijlid;
import com.example.partijlidservice.repository.PartijlidRepository;
import com.example.partijlidservice.service.PartijlidService;
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
public class PartijlidUnitTest {

    @InjectMocks
    private PartijlidService partijlidService;

    @Mock
    private PartijlidRepository partijlidRepository;

    @Test
    public void testCreatePartijlid() {
        // Arrange
        PartijlidRequest request = new PartijlidRequest();
        request.setNaam("Test Name");
        request.setEmail("test@example.com");
        request.setPartijNaam("Test Party");
        request.setRegeringNaam("Test Government");

        Partijlid partijlid = Partijlid.builder()
                .naam(request.getNaam())
                .email(request.getEmail())
                .partijNaam(request.getPartijNaam())
                .regeringNaam(request.getRegeringNaam())
                .build();

        when(partijlidRepository.save(any(Partijlid.class))).thenReturn(partijlid);

        // Act
        partijlidService.createPartijlid(request);

        // Assert
        verify(partijlidRepository, times(1)).save(any(Partijlid.class));
    }

    @Test
    public void testEditPartijlid() {
        // Arrange
        long partijlidId = 1L;
        PartijlidRequest editRequest = new PartijlidRequest();
        editRequest.setNaam("Edited Name");
        editRequest.setEmail("edited@example.com");
        editRequest.setPartijNaam("Edited Party");
        editRequest.setRegeringNaam("Edited Government");

        Partijlid existingPartijlid = Partijlid.builder()
                .id(partijlidId)
                .naam("Original Name")
                .email("original@example.com")
                .partijNaam("Original Party")
                .regeringNaam("Original Government")
                .build();

        when(partijlidRepository.findById(partijlidId)).thenReturn(Optional.of(existingPartijlid));

        // Act
        partijlidService.editPartijlid(partijlidId, editRequest);

        // Assert
        assertEquals("Edited Name", existingPartijlid.getNaam());
        assertEquals("edited@example.com", existingPartijlid.getEmail());
        assertEquals("Edited Party", existingPartijlid.getPartijNaam());
        assertEquals("Edited Government", existingPartijlid.getRegeringNaam());
        verify(partijlidRepository, times(1)).save(existingPartijlid);
    }

    @Test
    public void testGetAllPartijleden() {
        // Arrange
        Partijlid partijlid1 = Partijlid.builder().id(1L).naam("Name1").email("email1@example.com").partijNaam("Party1").regeringNaam("Government1").build();
        Partijlid partijlid2 = Partijlid.builder().id(2L).naam("Name2").email("email2@example.com").partijNaam("Party2").regeringNaam("Government2").build();

        when(partijlidRepository.findAll()).thenReturn(Arrays.asList(partijlid1, partijlid2));

        // Act
        List<PartijlidResponse> responseList = partijlidService.getAllPartijleden();

        // Assert
        assertEquals(2, responseList.size());
        assertEquals("Name1", responseList.get(0).getNaam());
        assertEquals("Name2", responseList.get(1).getNaam());
        verify(partijlidRepository, times(1)).findAll();
    }

    @Test
    public void testGetPartijlidById() {
        // Arrange
        long partijlidId = 1L;
        Partijlid partijlid = Partijlid.builder()
                .id(partijlidId)
                .naam("Test Name")
                .email("test@example.com")
                .partijNaam("Test Party")
                .regeringNaam("Test Government")
                .build();

        when(partijlidRepository.findById(partijlidId)).thenReturn(Optional.of(partijlid));

        // Act
        PartijlidResponse response = partijlidService.getPartijlidById(partijlidId);

        // Assert
        assertEquals("Test Name", response.getNaam());
        assertEquals("test@example.com", response.getEmail());
        verify(partijlidRepository, times(1)).findById(partijlidId);
    }

    @Test
    public void testGetPartijlidByNaam() {
        // Arrange
        String naam = "Test Name";
        Partijlid partijlid = Partijlid.builder()
                .id(1L)
                .naam(naam)
                .email("test@example.com")
                .partijNaam("Test Party")
                .regeringNaam("Test Government")
                .build();

        when(partijlidRepository.findByNaam(naam)).thenReturn(Optional.of(partijlid));

        // Act
        PartijlidResponse response = partijlidService.getPartijlidByNaam(naam);

        // Assert
        assertEquals("Test Name", response.getNaam());
        assertEquals("test@example.com", response.getEmail());
        verify(partijlidRepository, times(1)).findByNaam(naam);
    }

    @Test
    void testDeletePartijlid() {
        // Setup: create a Partijlid object to be deleted.
        Partijlid partijlidToDelete = Partijlid.builder()
                .id(1L)  // Setting the ID for the partijlid
                .build();  // Building the Partijlid object.

        // Mock behavior of the repository
        when(partijlidRepository.existsById(1L)).thenReturn(true);

        // Call the service method that you want to test
        partijlidService.deletePartijlid(1L);

        // Verify that the repository's deleteById method was called once with the correct ID
        verify(partijlidRepository, times(1)).deleteById(1L);  // Corrected to match actual interaction

        // You could add additional assertions if necessary
        // For example: verify if no other repository methods were called
        verify(partijlidRepository, times(1)).existsById(1L);
    }
}
