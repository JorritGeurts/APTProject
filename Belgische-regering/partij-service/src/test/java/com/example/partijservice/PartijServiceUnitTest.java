package com.example.partijservice;

import com.example.partijservice.dto.PartijResponse;
import com.example.partijservice.model.Partij;
import com.example.partijservice.repository.PartijRepository;
import com.example.partijservice.service.PartijService;
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
public class PartijServiceUnitTest {

    @InjectMocks
    private PartijService partijService;

    @Mock
    private PartijRepository partijRepository;

    @Test
    public void testGetAllPartijen() {
        // Arrange: Create some sample Partij objects
        Partij partij1 = new Partij();
        partij1.setId("1");
        partij1.setNaam("Vlaams Belang");
        partij1.setAdres("Madouplein 8");
        partij1.setPostcode("1210");
        partij1.setStad("Brussel");
        partij1.setCoalitieVlaams(false);
        partij1.setCoalitieFederaal(false);

        Partij partij2 = new Partij();
        partij2.setId("2");
        partij2.setNaam("NVA");
        partij2.setAdres("Koningsstraat 47");
        partij2.setPostcode("1000");
        partij2.setStad("Brussel");
        partij2.setCoalitieVlaams(true);
        partij2.setCoalitieFederaal(false);

        // Mock the repository to return the list of Partij objects
        when(partijRepository.findAll()).thenReturn(Arrays.asList(partij1, partij2));

        // Act: Call the method in the service to get the list of all parties
        List<PartijResponse> partijResponses = partijService.getAllPartijen();

        // Assert: Verify that the service returns the correct response data
        assertEquals(2, partijResponses.size());
        assertEquals("Vlaams Belang", partijResponses.get(0).getNaam());
        assertEquals("NVA", partijResponses.get(1).getNaam());

        // Verify that the repository's findAll method was called once
        verify(partijRepository, times(1)).findAll();
    }

    @Test
    public void testGetPartijByNaam() {
        // Arrange: Create a sample Partij object
        Partij partij = new Partij();
        partij.setId("1");
        partij.setNaam("CD&V");
        partij.setAdres("Gare Maritime");
        partij.setPostcode("1000");
        partij.setStad("Brussel");
        partij.setCoalitieVlaams(true);
        partij.setCoalitieFederaal(true);

        // Mock the repository to return the Partij object when searching by name
        when(partijRepository.findByNaam("CD&V")).thenReturn(Optional.of(partij));

        // Act: Call the method in the service to get the partij by name
        PartijResponse partijResponse = partijService.getPartijByNaam("CD&V");

        // Assert: Verify that the service returns the correct partij response
        assertEquals("CD&V", partijResponse.getNaam());
        assertEquals("Gare Maritime", partijResponse.getAdres());
        assertEquals("1000", partijResponse.getPostcode());
        assertEquals("Brussel", partijResponse.getStad());
        assertTrue(partijResponse.isCoalitieVlaams());
        assertTrue(partijResponse.isCoalitieFederaal());

        // Verify that the repository's findByNaam method was called once
        verify(partijRepository, times(1)).findByNaam("CD&V");
    }
}
