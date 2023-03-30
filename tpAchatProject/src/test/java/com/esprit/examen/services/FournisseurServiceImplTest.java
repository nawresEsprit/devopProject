package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;

@SpringBootTest
public class FournisseurServiceImplTest {

    private FournisseurServiceImpl fournisseurService;
    private FournisseurRepository fournisseurRepository;
    private DetailFournisseurRepository detailFournisseurRepository;

    @BeforeEach
    void setUp() {
        fournisseurRepository = mock(FournisseurRepository.class);
        detailFournisseurRepository = mock(DetailFournisseurRepository.class);
        fournisseurService = new FournisseurServiceImpl(fournisseurRepository, detailFournisseurRepository);
    }

    @Test
    void testRetrieveAllFournisseurs() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        Fournisseur f1 = new Fournisseur();
        f1.setIdFournisseur(1L);
        f1.setCode("F001");
        f1.setLibelle("Fournisseur 1");
        Fournisseur f2 = new Fournisseur();
        f2.setIdFournisseur(2L);
        f2.setCode("F002");
        f2.setLibelle("Fournisseur 2");
        fournisseurs.add(f1);
        fournisseurs.add(f2);
        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        assertEquals(2, result.size());
        assertEquals("F001", result.get(0).getCode());
        assertEquals("Fournisseur 2", result.get(1).getLibelle());
    }

    @Test
    void testAddFournisseur() {
        Fournisseur f = new Fournisseur();
        f.setIdFournisseur(1L);
        f.setCode("F001");
        f.setLibelle("Fournisseur 1");
        DetailFournisseur df = new DetailFournisseur();
        df.setDateDebutCollaboration(new Date());
        f.setDetailFournisseur(df);
        when(fournisseurRepository.save(f)).thenReturn(f);

        Fournisseur result = fournisseurService.addFournisseur(f);

        assertEquals("F001", result.getCode());
        assertEquals("Fournisseur 1", result.getLibelle());
        assertEquals(new Date(), result.getDetailFournisseur().getDateDebutCollaboration());
    }

    // Add more tests for the other methods as needed

}
