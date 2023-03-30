package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.services.OperateurServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceImplTest {

    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    @Test
    public void testRetrieveAllOperateurs() {
        // Given
        List<Operateur> list = new ArrayList<>();
        Operateur operateur1 = new Operateur(1L, "John", "Doe", "password", null);
        Operateur operateur2 = new Operateur(2L, "Jane", "Doe", "password", null);
        list.add(operateur1);
        list.add(operateur2);
        when(operateurRepository.findAll()).thenReturn(list);

        // When
        List<Operateur> result = operateurService.retrieveAllOperateurs();

        // Then
        assertThat(result).containsExactly(operateur1, operateur2);
    }

    @Test
    public void testAddOperateur() {
        // Given
        Operateur operateur = new Operateur(null, "John", "Doe", "password", null);
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        // When
        Operateur result = operateurService.addOperateur(operateur);

        // Then
        assertThat(result).isEqualTo(operateur);
    }

    @Test
    public void testDeleteOperateur() {
        // Given
        Long id = 1L;

        // When
        operateurService.deleteOperateur(id);

        // Then
        assertThat(operateurRepository.findById(id)).isEmpty();
    }

    @Test
    public void testUpdateOperateur() {
        // Given
        Operateur operateur = new Operateur(1L, "John", "Doe", "password", null);
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        // When
        Operateur result = operateurService.updateOperateur(operateur);

        // Then
        assertThat(result).isEqualTo(operateur);
    }

    @Test
    public void testRetrieveOperateur() {
        // Given
        Long id = 1L;
        Operateur operateur = new Operateur(id, "John", "Doe", "password", null);
        when(operateurRepository.findById(id)).thenReturn(Optional.of(operateur));

        // When
        Operateur result = operateurService.retrieveOperateur(id);

        // Then
        assertThat(result).isEqualTo(operateur);
    }

}
