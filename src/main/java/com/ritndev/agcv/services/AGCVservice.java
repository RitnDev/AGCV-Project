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
    
    @Autowired CommandeRepository commandeRep;
    @Autowired CompetitionRepository competitionRep;
    @Autowired ConsoMoisRepository consoMoisRep;
    @Autowired ConsoTubeRepository consoTubeRep;
    @Autowired MainDataRepository mainDataRep;
    @Autowired MembreRepository membreRep;
    @Autowired PrixTubeRepository prixTubeRep;
    @Autowired SaisonRepository saisonRep;
    @Autowired StockCompetitionRepository stockCompetRep;
    @Autowired TypeTubeRepository typeTubeRep;
    
    
    
    // Initialisation des Repositories 
    public void setCommandeRep (CommandeRepository commandeRep) {this.commandeRep = commandeRep;}
    public void setCompetitionRep (CompetitionRepository competitionRep) {this.competitionRep = competitionRep;}
    public void setConsoMoisRep (ConsoMoisRepository consoMoisRep) {this.consoMoisRep = consoMoisRep;}
    public void setMembreRepository (MembreRepository membreRep) {this.membreRep = membreRep;}
    public void setPrixTubeRep (PrixTubeRepository prixTubeRep) {this.prixTubeRep = prixTubeRep;}
    public void setStockCompetitionRep (StockCompetitionRepository stockCompetitionRep) {this.stockCompetRep = stockCompetitionRep;}
    public void setSaisonRep (SaisonRepository saisonRep) {this.saisonRep = saisonRep;}
    public void setConsoTubeRep (ConsoTubeRepository consoTubeRep) {this.consoTubeRep = consoTubeRep;}
    public void setMainDataRep (MainDataRepository mainDataRep) {this.mainDataRep = mainDataRep;}
    public void setTypeTubeRep (TypeTubeRepository typeTubeRep) {this.typeTubeRep = typeTubeRep;}
    
    
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
        //WIP
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
        //WIP
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
        //WIP
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
        //WIP
    }


    // -------------------   FONCTIONS STOCK-COMPETITION ---------------------
    @Override public StockCompetition saveStockCompetition(StockCompetition newStock) {return stockCompetRep.save(newStock);}
    @Override public StockCompetition findByIdStockCompetition(Long id) {return stockCompetRep.getOne(id);}
    @Override public void supprStockCompetition(Long id) {
        StockCompetition sc = stockCompetRep.getOne(id);
        stockCompetRep.delete(sc);
    }
    @Override public void updateByIdStockCompetition(Long id, StockCompetition editSacCompetition) {
        //WIP
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
        //WIP
    }
    @Override public Long lastIdSaison() {
        //WIP
        return -1L;
    }
    
    
    // -------------------   FONCTIONS TYPE-VOLANT ---------------------
    @Override public ConsoTube saveConsoTube(ConsoTube newConsoTube) {return consoTubeRep.save(newConsoTube);}
    @Override public List<ConsoTube> listConsoTube() {return consoTubeRep.findAll();}
    @Override public ConsoTube findByIdConsoTube(Long id) {return consoTubeRep.getOne(id);}
    @Override public void supprConsoTube(Long id) {
        ConsoTube ct = consoTubeRep.getOne(id);
        consoTubeRep.delete(ct);
    }
    @Override public void updateByIdConsoTube(Long id, ConsoTube editTypeVolant) {
        //WIP
    }

    
    // -------------------   FONCTIONS MAIN-DATA ---------------------
    @Override public MainData saveMainData(MainData mainData) {return mainDataRep.save(mainData);}
    @Override public MainData findByIdMainData(Long id) {return mainDataRep.getOne(id);}
    @Override public void supprMainData(Long id) {
        MainData data = mainDataRep.getOne(id);
        mainDataRep.delete(data);
    }
    @Override public void updateByIdMainData(Long id, MainData editMainData) {
        //WIP
    }

    
    // -------------------   FONCTIONS TYPE-TUBES ---------------------
    @Override public TypeTube saveTypeTube(TypeTube typeTube) {return typeTubeRep.save(typeTube);}
    @Override public List<TypeTube> listTypeTube() {return typeTubeRep.findAll();}
    @Override public TypeTube findByIdTypeTube(Long id) {return typeTubeRep.getOne(id);}
    @Override public void supprTypeTube(Long id) {
        TypeTube ct = typeTubeRep.getOne(id);
        typeTubeRep.delete(ct);
    }
    @Override public void updateByIdTypeTube(Long id, TypeTube editTypeTube) {
        //WIP
    }
    
    
    
}
