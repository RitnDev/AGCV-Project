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
public class TestSupprMembre {
    
    @Autowired private IMembreService membreService;
    @Autowired private MembreRepository membreRep;
    
    
    @Test
    public void returnReponse(){
        
        Reponse resultReponse = new Reponse("membre", "remove", 2);
        Membre testmembre = membreRep.save(new Membre("Prenom", "Nom"));
        long id = testmembre.getId();
        
        Assert.notNull(testmembre, "testMembre ne doit pas être null");
        Assert.isTrue(testmembre.isActif(), "testMembre doit être actif");
        
        //Test supprMembre()
        Reponse testReponse = membreService.supprMembre(id);
        Assert.isTrue(testReponse.getTypeReponse().equals(resultReponse.getTypeReponse()), "Le Membre n'a pas été supprimé de la BDD");
        
        resultReponse = new Reponse("membre", "remove", 1);
        
        testReponse = membreService.supprMembre(0L);
        Assert.isTrue(testReponse.getTypeReponse().equals(resultReponse.getTypeReponse()), 
                "Le Membre n'est pas censé exister en BDD");
        
      
        //RAZ BDD
        membreRep.deleteAll();
               
    }
    
}
