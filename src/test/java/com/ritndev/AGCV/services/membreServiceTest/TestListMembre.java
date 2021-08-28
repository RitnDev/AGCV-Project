package com.ritndev.AGCV.services.membreServiceTest;

import com.ritndev.agcv.InterfaceService.IMembreService;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.model.Membre;
import com.ritndev.agcv.repository.MembreRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


/**
 *
 * @author Ritn
 */
@SpringBootTest
public class TestListMembre {
    
    @Autowired private IMembreService membreService;
    @Autowired private MembreRepository membreRep;
    
    @Test
    public void returnListMembre() {
        
        FormMembre testfMembre1 = new FormMembre("Prenom1", "Nom1");
        FormMembre testfMembre2 = new FormMembre("Prenom2", "Nom2");
        FormMembre testfMembre3 = new FormMembre("Prenom3", "Nom3");
        FormMembre testfMembre4 = new FormMembre("Prenom4", "Nom4");
        FormMembre testfMembre5 = new FormMembre("Prenom5", "Nom5");
        
        Membre testMembre1 = new Membre(testfMembre1.getPrenom(), testfMembre1.getNom());
        Membre testMembre2 = new Membre(testfMembre2.getPrenom(), testfMembre2.getNom());
        Membre testMembre3 = new Membre(testfMembre3.getPrenom(), testfMembre3.getNom());
        Membre testMembre4 = new Membre(testfMembre4.getPrenom(), testfMembre4.getNom());
        Membre testMembre5 = new Membre(testfMembre5.getPrenom(), testfMembre5.getNom());
        
        membreService.saveMembre(testfMembre1);
        membreService.saveMembre(testfMembre2);
        membreService.saveMembre(testfMembre3);
        membreService.saveMembre(testfMembre4);
        membreService.saveMembre(testfMembre5);
        
        //Test de listMembre()
        List<Membre> testlistResult = membreService.listMembre();
        Assert.notNull(testlistResult, "La list de membre est null");
        Assert.isTrue(!testlistResult.isEmpty(), 
                "La list de membre ne doit pas être vide");
        Assert.isTrue(testlistResult.indexOf(testMembre1)!=-1, 
                "Le membre testMembre1 doit exister dans la list de membre : (index:" + testlistResult.indexOf(testMembre1) + ")");
        Assert.isTrue(testlistResult.indexOf(testMembre2)!=-1, 
                "Le membre testMembre2 doit exister dans la list de membre : (index:" + testlistResult.indexOf(testMembre2) + ")");
        Assert.isTrue(testlistResult.indexOf(testMembre3)!=-1, 
                "Le membre testMembre3 doit exister dans la list de membre : (index:" + testlistResult.indexOf(testMembre3) + ")");
        Assert.isTrue(testlistResult.indexOf(testMembre4)!=-1, 
                "Le membre testMembre4 doit exister dans la list de membre : (index:" + testlistResult.indexOf(testMembre4) + ")");
        Assert.isTrue(testlistResult.indexOf(testMembre5)!=-1, 
                "Le membre testMembre5 doit exister dans la list de membre : (index:" + testlistResult.indexOf(testMembre5) + ")");
        
        //RAZ BDD
        membreRep.deleteAll();
    }
     
    
}
