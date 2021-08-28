package com.ritndev.AGCV.services.membreServiceTest;

import com.ritndev.agcv.InterfaceService.IMembreService;
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
public class TestFindByIdMembre {
    
    @Autowired private IMembreService membreService;
    @Autowired private MembreRepository membreRep;
    
    
    @Test
    public void returnMembre(){
        
        Membre testmembre = membreRep.save(new Membre("Prenom", "Nom"));
        long id = testmembre.getId();
        
        Assert.notNull(testmembre, "testMembre ne doit pas être null");
        Assert.isTrue(testmembre.isActif(), "testMembre doit être actif");
        
               
        //Test findByIdMembre()
        Membre resultMembre = membreService.findByIdMembre(id);
        Assert.notNull(resultMembre, "testMembre ne doit pas être null");
        Assert.isTrue(resultMembre.equals(testmembre), 
                "resultMembre et testMembre doit être égaux");
        Assert.isTrue(resultMembre.isActif(), "testmembre ne doit pas être actif");
        Assert.isTrue(resultMembre.getId()==testmembre.getId(), 
                "resultMembre.getId() doit renvoyé : " + testmembre.getId());
        Assert.isTrue(resultMembre.getPrenom().equals(testmembre.getPrenom()), 
                "resultMembre.getPrenom() doit renvoyé : " + testmembre.getPrenom());
        Assert.isTrue(resultMembre.getNom().equals(testmembre.getNom()), 
                "resultMembre.getNom() doit renvoyé : " + testmembre.getNom());
        
        resultMembre = membreService.findByIdMembre(0L);
        Assert.isTrue(resultMembre==null, "(test result = 1 : testMembre doit être null");
        
        //RAZ BDD
        membreRep.deleteAll();
                    
    }
    
}
