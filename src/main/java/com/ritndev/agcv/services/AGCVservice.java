package com.ritndev.agcv.services;

import com.ritndev.agcv.model.*;
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
    @Override public void supprMembre(Long id) {
        Membre m = membreRep.getOne(id);
        membreRep.delete(m);
    }
    @Override public void updateByIdMembre(Long id, Membre editMembre) {
        Membre m = membreRep.getOne(id);
        if(m != null) {
            m.setNom(editMembre.getNom());
            m.setPrenom(editMembre.getPrenom());
            
            membreRep.save(m);
        }
    }
    
    
    // -------------------   FONCTIONS PRIX-TUBES ---------------------
    @Override public PrixTube savePrixTube (PrixTube newPrixTube) {return prixTubeRep.save(newPrixTube);}
    @Override public List<PrixTube> listPrixTube() {return prixTubeRep.findAll();}
    @Override public PrixTube findByIdPrixTube(Long id) {return prixTubeRep.getOne(id);}
    @Override public void supprPrixTube(Long id) {
        PrixTube pt = prixTubeRep.getOne(id);
        prixTubeRep.delete(pt);
    }
    @Override public void updateByIdPrixTube(Long id, PrixTube editPrixTube) {
        PrixTube pt = prixTubeRep.getOne(id);
        if(pt != null) {
            pt.setMarque(editPrixTube.getMarque());
            pt.setPrix(editPrixTube.getPrix());
            pt.setPrixMembre(editPrixTube.getPrixMembre());
            pt.setTypeTube(editPrixTube.getTypeTube());
            
            prixTubeRep.save(pt);
        }
    }
    
    
    // -------------------   FONCTIONS COMMANDES ---------------------
    @Override public Commande saveCommande(Commande newCommande) {return commandeRep.save(newCommande);}
    @Override public List<Commande> listCommande() {return commandeRep.findAll();}
    @Override public Commande findByIdCommande(Long id) {return commandeRep.getOne(id);}
    @Override public void supprCommande(Long id) {
        Commande c = commandeRep.getOne(id);
        commandeRep.delete(c);
    }
    @Override public void updateByIdCommande(Long id, Commande editCommande) {
        Commande c = commandeRep.getOne(id);
        if(c != null) {
            c.setMembre(editCommande.getMembre());
            c.setNbTubeCommande(editCommande.getNbTubeCommande());
            c.setPrixTube(editCommande.getPrixTube());
            c.setRegler(editCommande.isRegler());
            
            commandeRep.save(c);
        }
    }
 
    
    // -------------------   FONCTIONS COMPETITION ---------------------
    @Override public Competition saveCompetition(Competition newCompetition) {return competitionRep.save(newCompetition);}
    @Override public List<Competition> listCompetition() {return competitionRep.findAll();}
    @Override public Competition findByIdCompetition(Long id) {return competitionRep.getOne(id);}
    @Override public void supprCompetition(Long id) {
        Competition c = competitionRep.getOne(id);
        competitionRep.delete(c);
    }
    @Override public void updateByIdCompetition(Long id, Competition editCompetition) {
        Competition co = competitionRep.getOne(id);
        if(co != null) {
            co.setNom(editCompetition.getNom());
            co.setTubeUtilise(editCompetition.getTubeUtilise());
            
            competitionRep.save(co);
        }
    }

    
    // -------------------   FONCTIONS CONSO-MOIS ---------------------
    @Override public ConsoMois saveConsoMois(ConsoMois newConsoMois) {return consoMoisRep.save(newConsoMois);}
    @Override public List<ConsoMois> listConsoMois() {return consoMoisRep.findAll();}
    @Override public ConsoMois findByIdConsoMois(Long id) {return consoMoisRep.getOne(id);}
    @Override public void supprConsoMois(Long id) {
        ConsoMois cm = consoMoisRep.getOne(id);
        consoMoisRep.delete(cm);
    }
    @Override public void updateByIdConsoMois(Long id, ConsoMois editConsoMois) {
        ConsoMois cm = consoMoisRep.getOne(id);
        if(cm != null) {
            cm.setNomMois(editConsoMois.getNomMois());
            cm.setPrixTube(editConsoMois.getPrixTube());
            cm.setTubeCommande(editConsoMois.getTubeCommande());
            cm.setTubeUtilise(editConsoMois.getTubeUtilise());
            
            consoMoisRep.save(cm);
        }
    }


    // -------------------   FONCTIONS CONSO-MOIS ---------------------
    @Override public SacCompetition saveSacCompetition(SacCompetition newSacCompetition) {return sacCompetitionRep.save(newSacCompetition);}
    @Override public SacCompetition findByIdSacCompetition(Long id) {return sacCompetitionRep.getOne(id);}
    @Override public void supprSacCompetition(Long id) {
        SacCompetition sc = sacCompetitionRep.getOne(id);
        sacCompetitionRep.delete(sc);
    }
    @Override public void updateByIdSacCompetition(Long id, SacCompetition editSacCompetition) {
        SacCompetition sc = sacCompetitionRep.getOne(id);
        if(sc != null) {
            sc.setNbTube(editSacCompetition.getNbTube());
            
            sacCompetitionRep.save(sc);
        }
    }


    // -------------------   FONCTIONS SAISON ---------------------
    @Override public Saison saveSaison(Saison newSaison) {return saisonRep.save(newSaison);}
    @Override public List<Saison> listSaison() {return saisonRep.findAll();}
    @Override public Saison findByIdSaison(Long id) {return saisonRep.getOne(id);}
    @Override public void supprSaison(Long id) {
        Saison saison = saisonRep.getOne(id);
        saisonRep.delete(saison);
    }
    @Override public void updateByIdSaison(Long id, Saison editSaison) {
        Saison s = saisonRep.getOne(id);
        if(s != null) {
            s.setNom(editSaison.getNom());
            s.setBudgetPrevisionnelle(editSaison.getBudgetPrevisionnelle());
            
            saisonRep.save(s);
        }
    }
   
    
    // -------------------   FONCTIONS TYPE-VOLANT ---------------------
    @Override public TypeVolant saveTypeVolant(TypeVolant newTypeVolant) {return typeVolantRep.save(newTypeVolant);}
    @Override public List<TypeVolant> listTypeVolant() {return typeVolantRep.findAll();}
    @Override public TypeVolant findByIdTypeVolant(Long id) {return typeVolantRep.getOne(id);}
    @Override public void supprTypeVolant(Long id) {
        TypeVolant tv = typeVolantRep.getOne(id);
        typeVolantRep.delete(tv);
    }
    @Override public void updateByIdTypeVolant(Long id, TypeVolant editTypeVolant) {
        TypeVolant tv = typeVolantRep.getOne(id);
        if(tv != null) {
            tv.setTypeTube(editTypeVolant.getTypeTube());
            tv.setStockTubeInit(editTypeVolant.getStockTubeInit());
            
            typeVolantRep.save(tv);
        }
    }
    
     
    
}
