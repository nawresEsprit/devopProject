package com.esprit.examen.services;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FactureServiceImplTest {

    @Mock
    FactureRepository factureRepository;
    @Mock
    OperateurRepository operateurRepository;
    @Mock
    DetailFactureRepository detailFactureRepository;
    @Mock
    FournisseurRepository fournisseurRepository;
    @Mock
    ProduitRepository produitRepository;

    @Mock
    private ReglementServiceImpl reglementService;

    @InjectMocks
    private FactureServiceImpl factureService;

    @BeforeEach
    void setUp() {MockitoAnnotations.openMocks(this);}

    @AfterEach
    void tearDown() {
    }

    public Facture newFacture() {
        return Facture.builder().idFacture(1L).montantRemise(1L)
                .montantFacture(1L).dateCreationFacture(new Date())
                .dateDerniereModificationFacture(new Date()).archivee(true)
                .detailsFacture(null).fournisseur(null)
                .reglements(null).build();
    }
    Facture facture = newFacture();
    @Test
    void retrieveAllFactures() {
        List<Facture> factures = new ArrayList<>();
        factures.add(facture);
        factures.add(facture);
        when(factureRepository.findAll()).thenReturn(factures);

        List<Facture> result = factureService.retrieveAllFactures();

        assertEquals(factures, result);
        verify(factureRepository,times(1)).findAll();
    }

    @Test
    void addFacture() {

        when(factureRepository.save(facture)).thenReturn(facture);

        Facture result = factureService.addFacture(facture);

        assertEquals(facture, result);
        verify(factureRepository,times(1)).save(facture);

    }

    @Test
    void cancelFacture() {

        Long id = 1L;

        factureService.cancelFacture(id);

        verify(factureRepository,times(1)).save(any(Facture.class));
    }

    @Test
    void retrieveFacture() {

        Long id = 1L;


        when(factureRepository.findById(id)).thenReturn(java.util.Optional.of(facture));

        Facture result = factureService.retrieveFacture(id);

        assertEquals(facture, result);

        verify(factureRepository,times(1)).findById(id);

    }
/*
    @Test
    void getFacturesByFournisseur() {

        Long id = 1L;

        List<Facture> factures = new ArrayList<>();

        factures.add(facture);
        factures.add(facture);


        when(factureRepository.findById(id)).thenReturn(java.util.Optional.of(factures.get(0)));

        List<Facture> result = factureService.getFacturesByFournisseur(id);

        assertArrayEquals(factures.toArray(), result.toArray());

        verify(factureRepository,times(1)).findById(id);
    }

    @Test
    void assignOperateurToFacture() {

            Long idFacture = 1L;
            Long idOperateur = 1L;

            factureService.assignOperateurToFacture(idFacture,idOperateur);

            verify(factureRepository,times(1)).save(any(Facture.class));
    }

    @Test
    void pourcentageRecouvrement() {

        Date startDate = new Date();
        Date endDate = new Date();

        factureService.pourcentageRecouvrement(startDate,endDate);

        verify(reglementService,times(1)).getChiffreAffaireEntreDeuxDate(startDate,endDate);
    }
*/

}