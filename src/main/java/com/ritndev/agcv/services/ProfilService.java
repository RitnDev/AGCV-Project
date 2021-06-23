package com.ritndev.agcv.services;

import com.ritndev.agcv.form.FormProfil;
import com.ritndev.agcv.model.Profil;
import com.ritndev.agcv.repository.ProfilRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ritn
 */
@Service
public class ProfilService implements IProfilService{

    @Autowired ProfilRepository profilRep;
    @Autowired IagcvService service;
    
    // Initialisation des Repositories 
    public void setCommandeRep (ProfilRepository profilRep) {this.profilRep = profilRep;}
    
    
    
    @Override
    public int saveProfil(FormProfil newProfil) {
        int resultVal = 0;
        Profil p = profilRep.save(new Profil(newProfil.getPseudo(), service.findByIdMembre(newProfil.getIdMembre())));
        if (p!=null) resultVal = 2;
        return resultVal;
    }
    @Override public List<Profil> listProfil() {return profilRep.findAll();}
    @Override public Profil findByIdProfil(Long id) {
        if(profilRep.existsById(id)){
            return profilRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override
    public int supprProfil(Long id) {
        int resultVal = 0;
        if(profilRep.existsById(id)){
            profilRep.delete(profilRep.getOne(id));
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return resultVal;
    }
    @Override
    public int updateProfil(FormProfil editProfil) {
        int resultVal = 0;
        if(profilRep.existsById(editProfil.getId())){
            Profil profil = profilRep.getOne(editProfil.getId());
            
            profil.setPseudo(editProfil.getPseudo());
            profil.setIdMembre(service.findByIdMembre(editProfil.getIdMembre()));
            
            profilRep.save(profil);
            
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return resultVal;
    }
    
}
