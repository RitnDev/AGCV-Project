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
        PrixTube pt = prixTubeRep.getOne(id);
        if(pt != null) {
            pt.setMarque(editPrixTube.getMarque());
            pt.setPrix(editPrixTube.getPrix());
            pt.setPrixMembre(editPrixTube.getPrixMembre());
            pt.setIdTypeTube(editPrixTube.getIdTypeTube());
            pt.setActif(editPrixTube.isActif());
            
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
            c.setIdMembre(editCommande.getIdMembre());
            c.setIdPrixTube(editCommande.getIdPrixTube());
            c.setIdSaison(editCommande.getIdSaison());
            c.setNbTubeCommande(editCommande.getNbTubeCommande());
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
        Competition c = competitionRep.getOne(id);
        if(c != null) {
            c.setIdSaison(editCompetition.getIdSaison());
            c.setNom(editCompetition.getNom());
            c.setNbTubesUtilises(editCompetition.getNbTubesUtilises());
            
            competitionRep.save(c);
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
            cm.setIdConsoTube(editConsoMois.getIdConsoTube());
            cm.setIdPrixTube(editConsoMois.getIdConsoTube());
            cm.setNbTubeCommande(editConsoMois.getNbTubeCommande());
            cm.setNbTubeUtilise(editConsoMois.getNbTubeUtilise());
            cm.setNom(editConsoMois.getNom());
                        
            consoMoisRep.save(cm);
        }
    }


    // -------------------   FONCTIONS STOCK-COMPETITION ---------------------
    @Override public StockCompetition saveStockCompetition(StockCompetition newStock) {return stockCompetRep.save(newStock);}
    @Override public StockCompetition findByIdStockCompetition(Long id) {return stockCompetRep.getOne(id);}
    @Override public void supprStockCompetition(Long id) {
        StockCompetition sc = stockCompetRep.getOne(id);
        stockCompetRep.delete(sc);
    }
    @Override public void updateByIdStockCompetition(Long id, StockCompetition editStockCompet) {
        StockCompetition sc = stockCompetRep.getOne(id);
        if(sc != null) {
            sc.setStock(editStockCompet.getStock());
            
            stockCompetRep.save(sc);
        }
    }


    // -------------------   FONCTIONS SAISON ---------------------
    @Override public long saveSaison(Saison newSaison) {
        
        // Remplacer par un recherche par annee_debut
        // Créer la methode dans le repository
        long id = 0;  
        boolean result = true;
        List<Saison> saisons = saisonRep.findAll();
        //Vérification que la saison n'existe pas déjà
        for(Saison s : saisons) {
            if (s.toString().equals(newSaison.toString())) {
                result = false;
            }
        }
        //On créée la saison si elle n'existe pas
        if (result){
            Saison s = saisonRep.save(newSaison);
            id = s.getId();
        }
        
        return id;
    }
    @Override public List<Saison> listSaison() {return saisonRep.findAll();}
    @Override public Saison findByIdSaison(Long id) {return saisonRep.getOne(id);}
    @Override public void supprSaison(Long id) {
        Saison saison = saisonRep.getOne(id);
        saisonRep.delete(saison);
    }
    @Override public void updateByIdSaison(Long id, Saison editSaison) {
        Saison saison = saisonRep.getOne(id);
        if(saison != null) {
            saison.setBudget(editSaison.getBudget());
            saison.setAnneeDebut(editSaison.getAnneeDebut());
            saison.setAnneeFin(editSaison.getAnneeDebut() + 1);
            
            saisonRep.save(saison);
        }
    }
    @Override public Long lastIdSaison() {
        return saisonRep.findLastId();
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
        ConsoTube ct = consoTubeRep.getOne(id);
        if(ct != null) {
            ct.setIdSaison(editTypeVolant.getIdSaison());
            ct.setIdTypeTube(editTypeVolant.getIdTypeTube());
            ct.setInitTube(editTypeVolant.getInitTube());
            
            consoTubeRep.save(ct);
        }
    }

    
    // -------------------   FONCTIONS MAIN-DATA ---------------------
    @Override public boolean newMainData(){
        boolean result = false;
        List<MainData> data = mainDataRep.findAll();
        if (data.isEmpty()){
            //Si la liste est vide on peut créer une MainData
            MainData main = new MainData();
            mainDataRep.save(main);
            result = true;
        }else{
            //Si elle n'est pas vide on doit vérifier si les datas sont inactive
            boolean actif = false;
            for(MainData d : data){
                if(d.isActif()){
                    actif = true;
                }
            }
            if(!actif){
                //Si elles sont toutes inactives ont peut créer une nouvelle MainData
                MainData main = new MainData();
                mainDataRep.save(main);
                result = true;
            }
        }
        return result;
    }
    @Override public MainData saveMainData(MainData mainData) {return mainDataRep.save(mainData);}
    @Override public List<MainData> listMainData() {return mainDataRep.findAll();}
    @Override public MainData findByIdMainData(Long id) {return mainDataRep.getOne(id);}
    @Override public void supprMainData(Long id) {
        MainData data = mainDataRep.getOne(id);
        mainDataRep.delete(data);
    }
    @Override public boolean updateByIdMainData(Long id, MainData editMainData) {
        MainData md = mainDataRep.getOne(id);
        boolean result = false;
        if(md != null) {
            md.setIdSaison(editMainData.getIdSaison());
            md.setIdStockCompet(editMainData.getIdStockCompet());
            
            //Si on doit passer la data en actif alors qu'elle ne l'était pas avant
            if(editMainData.isActif() == true && md.isActif() == false) {
                List<MainData> data = mainDataRep.findAll();
                //Si elle n'est pas vide on doit vérifier si les datas sont inactive
                boolean actif = false;
                for(MainData d : data){
                    if(d.isActif() && (d.getId() != id)){
                        actif = true;
                    }
                }
                if(!actif){
                    //Si elles sont toutes inactives on peut passer la data en actif
                    md.setActif(editMainData.isActif());
                    result = true;
                }
            }else{
                md.setActif(editMainData.isActif());
                result = true;
            }
                        
            mainDataRep.save(md);
        }
        return result;
    }
    @Override public MainData returnMainData() {
        List<MainData> data = mainDataRep.findAll();
        MainData md = new MainData(0);
        if (!data.isEmpty()){
            //Si la liste est non vide on vérifi laquelle est active
            for(MainData d : data){
                if(d.isActif()){
                    //on retourne la data active
                    return d;
                }
            }
        }
        return md;
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
        TypeTube tb = typeTubeRep.getOne(id);
        
        if(tb != null) {
            tb.setNom(editTypeTube.getNom());
            tb.setCommande(editTypeTube.isCommande());
            tb.setActif(editTypeTube.isActif());
            
            typeTubeRep.save(tb);
        }
    }
    
    
    
}
