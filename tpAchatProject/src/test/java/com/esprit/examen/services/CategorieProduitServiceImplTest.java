package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;
import org.mockito.MockitoAnnotations;


public class CategorieProduitServiceImplTest {

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void retrieveAllCategorieProduits() {
        // Arrange
        List<CategorieProduit> categorieProduits = new ArrayList<>();
        categorieProduits.add(CategorieProduit.builder().idCategorieProduit(1L).codeCategorie("code").libelleCategorie("lib").produits(null).build());
        when(categorieProduitRepository.findAll()).thenReturn(categorieProduits);

        // Act
        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();

        // Assert
        assertThat(result).isEqualTo(categorieProduits);
    }

    @Test
    public void addCategorieProduit() {
        // Arrange
        CategorieProduit cp = new CategorieProduit(1L,"code1","lib1",null);
        when(categorieProduitRepository.save(cp)).thenReturn(cp);

        // Act
        CategorieProduit result = categorieProduitService.addCategorieProduit(cp);

        // Assert
        assertThat(result).isEqualTo(cp);
    }

    @Test
    public void deleteCategorieProduit() {
        // Arrange
        Long id = 1L;

        // Act
        categorieProduitService.deleteCategorieProduit(id);

        // Assert
        assertThat(categorieProduitRepository.findById(id)).isEmpty();
    }

    @Test
    public void updateCategorieProduit() {
        // Arrange
        CategorieProduit cp = new CategorieProduit(1L,"code1","lib1",null);
        when(categorieProduitRepository.save(cp)).thenReturn(cp);

        // Act
        CategorieProduit result = categorieProduitService.updateCategorieProduit(cp);

        // Assert
        assertThat(result).isEqualTo(cp);
    }

    @Test
    public void retrieveCategorieProduit() {
        // Arrange
        Long id = 1L;
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setIdCategorieProduit(id);
        categorieProduit.setCodeCategorie("CAT001");
        categorieProduit.setLibelleCategorie("Category 1");
        categorieProduit.setProduits(null);
        when(categorieProduitRepository.findById(id)).thenReturn(Optional.of(categorieProduit));

        // Act
        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(id);

        // Assert
        assertThat(result).isEqualTo(categorieProduit);
    }
}