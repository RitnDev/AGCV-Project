package com.ritndev.AGCV.services.membreServiceTest;

import com.ritndev.agcv.InterfaceService.IMembreService;
import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.model.Membre;
import com.ritndev.agcv.repository.MembreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 *
 * @author Ritn
 */
@SpringBootTest
public class TestUpdateMembre {
    
    @Autowired private IMembreService membreService;
    @Autowired private MembreRepository membreRep;
    
    
    @Test
    public void returnReponse(){
        
        Reponse resultReponse = new Reponse("membre", "edit", 2);
        Membre testmembre = membreRep.save(new Membre("Prenom", "Nom"));
        
        Assert.notNull(testmembre, "testMembre ne doit pas être null");
        Assert.isTrue(testmembre.isActif(), "testMembre doit être actif");
        
        //Modification du Membre
        FormMembre testfMembre = new FormMembre(testmembre.getId(), "PrenomEdit", "NomEdit", false);
        
        //Test updateMembre()
        Reponse testReponse = membreService.updateMembre(testfMembre);
        Assert.isTrue(testReponse.getTypeReponse().equals(resultReponse.getTypeReponse()), 
                "Le Membre n'a pas été mise à jour dans la BDD");
        
        testmembre = membreRep.getOne(testmembre.getId());
        Assert.notNull(testmembre, 
                "testmembre ne doit toujours pas être null");
        Assert.isTrue(!testmembre.isActif(), "testmembre ne doit pas être actif");
        Assert.isTrue(testmembre.getPrenom().equals(testfMembre.getPrenom()), 
                "testMembre.getPrenom() doit renvoyé : " + testfMembre.getPrenom());
        Assert.isTrue(testmembre.getNom().equals(testfMembre.getNom()), 
                "testMembre.getNom() doit renvoyé : " + testfMembre.getNom());
        
        
        resultReponse = new Reponse("membre", "edit", 1);
        testfMembre = new FormMembre(0L, "PrenomEdit", "NomEdit", false);
        
        testReponse = membreService.updateMembre(testfMembre);
        Assert.isTrue(testReponse.getTypeReponse().equals(resultReponse.getTypeReponse()), 
                "Le Membre n'est pas censé exister en BDD");
        
        
        //RAZ BDD
        membreRep.deleteAll();
    
    }
    
}
