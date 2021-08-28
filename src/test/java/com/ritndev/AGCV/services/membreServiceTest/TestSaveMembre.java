package com.ritndev.AGCV.services.membreServiceTest;


import com.ritndev.agcv.InterfaceService.IMembreService;
import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormMembre;
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
public class TestSaveMembre {
    
    @Autowired private IMembreService membreService;
    @Autowired private MembreRepository membreRep;
    
    
    @Test
    public void returnReponse(){
        
        Reponse resultReponse = new Reponse("membre", "create", 2);
        FormMembre testMembre = new FormMembre("Prenom", "Nom");
        
        //Test saveMembre()
        Reponse testReponse = membreService.saveMembre(testMembre);
        Assert.isTrue(testReponse.getTypeReponse().equals(resultReponse.getTypeReponse()), "Le Membre n'a pas été créé en BDD");
        //RAZ BDD
        membreRep.deleteAll();
    
    }
    
    
}
