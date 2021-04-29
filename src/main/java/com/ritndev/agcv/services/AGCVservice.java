package com.ritndev.agcv.services;

import com.ritndev.agcv.model.Membre;
import com.ritndev.agcv.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ritn
 */
@Service
public class AGCVservice implements IagcvService {
    
    @Autowired
    CommandeRepository commandeRep;
    @Autowired
    CompetitionRepository competitionRep;
    @Autowired
    ConsoMoisRepository consoMoisRep;
    @Autowired
    MembreRepository membreRep;
    @Autowired
    PrixTubeRepository prixTubeRep;
    @Autowired
    SacCompetitionRepository sacCompetitionRep;
    @Autowired
    SaisonRepository saisonRep;
    @Autowired
    TypeVolantRepository typeVolantRep;
    
    
    // Initialisation des Repositories 
    public void setCommandeRep (CommandeRepository commandeRep) {this.commandeRep = commandeRep;}
    public void setCompetitionRep (CompetitionRepository competitionRep) {this.competitionRep = competitionRep;}
    public void setConsoMoisRep (ConsoMoisRepository consoMoisRep) {this.consoMoisRep = consoMoisRep;}
    public void setMembreRepository (MembreRepository membreRep) {this.membreRep = membreRep;}
    public void setPrixTubeRep (PrixTubeRepository prixTubeRep) {this.prixTubeRep = prixTubeRep;}
    public void setSacCompetitionRep (SacCompetitionRepository sacCompetitionRep) {this.sacCompetitionRep = sacCompetitionRep;}
    public void setSaisonRep (SaisonRepository saisonRep) {this.saisonRep = saisonRep;}
    public void setTypeVolantRep (TypeVolantRepository typeVolantRep) {this.typeVolantRep = typeVolantRep;}

    
    
    // -------------------   FONCTIONS MEMBRES ---------------------
    @Override public Membre saveMembre(Membre newMembre) {return membreRep.save(newMembre);}
    @Override public List<Membre> listMembre() {return membreRep.findAll();}
    @Override public Membre findByIdMembre(Long id) {return membreRep.getOne(id);}
    @Override public void supprMembre(Membre delMembre) {membreRep.delete(delMembre);}
    @Override public void updateByIdMembre(Long id, Membre editMembre) {
        Membre m = membreRep.getOne(id);
        if(m != null) {
            m.setNom(editMembre.getNom());
            m.setPrenom(editMembre.getPrenom());
            membreRep.save(m);
        }
    }
    
}
