package com.ritndev.agcv.services;

import com.ritndev.agcv.form.*;
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
    @Override public Membre saveMembre(FormMembre newMembre) {
        return membreRep.save(new Membre(newMembre.getPrenom(), newMembre.getNom()));
    }
    @Override public List<Membre> listMembre() {return membreRep.findAll();}
    @Override public Membre findByIdMembre(Long id) {return membreRep.getOne(id);}
    @Override public void supprMembre(Long id) {
        Membre m = membreRep.getOne(id);
        membreRep.delete(m);
    }
    @Override public void updateByIdMembre(FormMembre editMembre) {
        Membre m = membreRep.getOne(editMembre.getId());
        if(m != null) {
            m.setNom(editMembre.getNom());
            m.setPrenom(editMembre.getPrenom());
            
            membreRep.save(m);
        }
    }
    
    
    // -------------------   FONCTIONS PRIX-TUBES ---------------------
    @Override public PrixTube savePrixTube (PrixTube newPrixTube) {return prixTubeRep.save(newPrixTube);}
    @Override public List<PrixTube> listPrixTube() {return prixTubeRep.findAll();}
    @Override public PrixTube findByIdPrixTube(Long id) {
        if (prixTubeRep.existsById(id)){
            return prixTubeRep.getOne(id);
        }else{
            return null;
        }   
    }
    @Override public void supprPrixTube(Long id) {
        if (prixTubeRep.existsById(id)){
            PrixTube pt = prixTubeRep.getOne(id);
            prixTubeRep.delete(pt);
        }    
    }
    @Override public void updateByIdPrixTube(Long id, PrixTube editPrixTube) {
        PrixTube pt = null;
        if (prixTubeRep.existsById(id)){
            pt = prixTubeRep.getOne(id);
        }

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
    @Override public int saveCompetition(FormCompet newCompet) {
        int resultVal = 0;
        //Récupération de la Main-Data active (md)
        MainData md = returnMainData();
        //Vérification qu'il y a bien une main-data de créée
        if(md.getId() != 0){
            if (md.getIdSaison()>0) {
                if (newCompet != null
                && newCompet.getNbTubesUtilises() != 0
                && !newCompet.getNom().equals(""))
                    competitionRep.save(new Competition(md.getIdSaison(), newCompet.getNbTubesUtilises(), newCompet.getNom()));
                    resultVal = 3;
            }else{
                resultVal = 2; // pas de saison active actuellement.
            }
        }else{
            resultVal = 1; // Pas de main-data créée.
        }
        return resultVal;
    }
    @Override public List<Competition> listCompetitionBySaison(Long idSaison) {
        return competitionRep.findByIdSaison(idSaison);
    }
    @Override public Competition findByIdCompetition(Long id) {
        if(competitionRep.existsById(id)){
            return competitionRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override public int supprCompetition(Long id) {
        int resultVal = 0;
        if(competitionRep.existsById(id)){
            Competition c = competitionRep.getOne(id);
            competitionRep.delete(c);
            resultVal = 1;
        }
        return resultVal;
    }
    @Override public int updateCompetition(FormCompet editCompet) {
        int resultVal = 0;
        if(competitionRep.existsById(editCompet.getId())){
            Competition c = competitionRep.getOne(editCompet.getId());
            c.setNom(editCompet.getNom());
            c.setNbTubesUtilises(editCompet.getNbTubesUtilises());
            
            competitionRep.save(c);
            resultVal = 1;
        }
               
        return resultVal;
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
    @Override public int newStock() {
        int resultVal = 0;
        if (stockCompetRep.findAll().isEmpty()){
            Long id = stockCompetRep.save(new StockCompetition()).getId();
            
            // ----- MISE A JOUR MAIN-DATA -----
            
                MainData md = returnMainData();
                //Vérification qu'il y a bien une main-data de créée
                if(md.getId() == 0){
                    resultVal = 1; //Pas de main-data créée (mise à jour non éffectuée) 
                }else{
                    md.setIdStockCompet(id);
                    mainDataRep.save(md);
                    resultVal = 2;      //mise à jour de la main-data ok
                }   
        }
        return resultVal;
    }
    @Override public List<StockCompetition> listStock() {
        return stockCompetRep.findAll();
    }
    @Override public StockCompetition findByIdStock(Long id) {
        if (stockCompetRep.existsById(id)){
            return stockCompetRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override public int supprStock(Long id) {
        int resultVal = 0;
        if (stockCompetRep.existsById(id)){
            StockCompetition sc = stockCompetRep.getOne(id);
            stockCompetRep.delete(sc);
            
            // ----- MISE A JOUR MAIN-DATA -----
            
                MainData md = returnMainData();
                //Vérification qu'il y a bien une main-data de créée
                if(md.getId() == 0){
                    resultVal = 1; //Pas de main-data créée (mise à jour non éffectuée) 
                }else{
                    md.setIdStockCompet(0);
                    mainDataRep.save(md);
                    resultVal = 2;      //mise à jour de la main-data ok
                }
        }
        return resultVal;
    }
    @Override public int updateStock(FormStock editStockCompet) {
        int resultVal = 0;
        if (stockCompetRep.existsById(editStockCompet.getId())){
            StockCompetition sc = stockCompetRep.getOne(editStockCompet.getId());
            sc.setStock(editStockCompet.getStock());   
            stockCompetRep.save(sc);
            resultVal = 1;
        }
        return resultVal;
    }
    

    // -------------------   FONCTIONS SAISON ---------------------
    /*
    Return : long resultVal : 
                0 = Saison non créée car existe déjà.
                1 = Saison créée mais pas mise à jour dans le Main-Data car non existant.
                2 = Saison créée avec succès (mis à jour dans le main-data ok).
    */
    @Override public int saveSaison(FormSaison newSaison) {
        
        
        int resultVal = 0; 
        
        if(newSaison != null 
                && !"".equals(newSaison.toString())
                && !"".equals(newSaison.getBudget())
                ){
            
            long id = 0;
            //On créée la saison si elle n'existe pas
            if (!saisonRep.existsByAnneeDebut(newSaison.getAnnee_debut())) {
                Saison s = saisonRep.save(new Saison(newSaison.getAnnee_debut(), newSaison.getBudgetDouble(), newSaison.isActuelle()));
                id = s.getId();
            }

            // ----- MISE A JOUR MAIN-DATA -----

            //Vérification que la saison na bien été créée
            if (id>0) {
                resultVal = 2; //Saison créée avec succès
                if (saisonRep.getOne(id).isActuelle()){ //Est-ce la nouvelle saison actuelle ?
                    //Récupération de la Main-Data active (md)
                    MainData md = returnMainData();
                    //Vérification qu'il y a bien une main-data de créée
                    if(md.getId() == 0){
                        resultVal = 1; //Pas de main-data créée (mise à jour non éffectuée)
                    }else{
                        //Mise à jour de l'ancienne Saison enregistré dans main-data
                        if (saisonRep.existsById(md.getIdSaison())){ // Vérifie que l'id existe en BDD
                            //si existe on met à jour l'ancienne saison
                            Saison sOld = saisonRep.getOne(md.getIdSaison());
                            sOld.setActuelle(false); // Passage en saison non actuelle
                            saisonRep.save(sOld);    //sauvegarde des modifications
                            md.setIdSaison(id);      //mise à jour du main-data avec le nouvel ID saison
                            mainDataRep.save(md);
                        }else{
                            md.setIdSaison(id);      //mise à jour du main-data avec le nouvel ID saison
                            mainDataRep.save(md);
                        }
                    }
                }
            }
        }
        return resultVal;
    }
    @Override public List<Saison> listSaison() {return saisonRep.findAll();}
    @Override public Saison findByIdSaison(Long id) {return saisonRep.getOne(id);}
    @Override public void supprSaison(Long id) {
        if (saisonRep.existsById(id)){
            Saison saison = saisonRep.getOne(id);
            boolean actif = saison.isActuelle();
            saisonRep.delete(saison);
            
            // Mise à jour du Main-Data (si la saison été actuelle)
            if(actif){
                MainData md = returnMainData();
                if(md.getId()>0){
                    //si c'est bien la saison actuelle qui est supprimée
                    //on met à 0 l'idSaison du main-data
                    if(id == md.getIdSaison()){
                        md.setIdSaison(0);
                        mainDataRep.save(md);
                    }
                }
            }
        }
    }
    @Override public int updateSaison(FormSaison editSaison) {
        int resultVal = 0; //mise à jour non effectuée
        
        if(editSaison != null 
                && !"".equals(editSaison.getBudget())
                ){
        
            //Vérifi que la saison existe en base
            if (saisonRep.existsById(editSaison.getId())){

                //On récupère la saison et modifie les attributs concernés
                System.out.println(">> (debug) ID Saison edit : " + editSaison.getId());
                Saison saison = saisonRep.getOne(editSaison.getId());
                saison.setBudget(editSaison.getBudgetDouble());

                //On save si la saison est l'actuelle avant changement
                boolean actif = saison.isActuelle(); //La saison modifié est elle active ?
                saison.setActuelle(editSaison.isActuelle());
                saisonRep.save(saison);
                resultVal = 1; //Mise à jour effectuée (aucune mise à jour faite dans Main-data)
               
                // --- Mise à jour du Main-Data ---
                
                //La saison a-t-elle changé d'état (actuelle)
                if(actif != editSaison.isActuelle()){
                    //Si actif = true alors il va passer à false (et vice-versa)
                    if(actif){ 
                        
                        // MAIN DATA : ID SAISON PASSE à 0
                        MainData md = returnMainData();
                        if(md.getId()>0){
                            //on met à 0 l'idSaison du main-data
                            md.setIdSaison(0);
                            mainDataRep.save(md);
                            resultVal = 2; // mise à jour éffectue et désactivation de la saison dans main-data
                        }
                        
                    }else{
                        
                        //MAIN DATA : ID SAISON CHANGE
                        MainData md = returnMainData();
                        if(md.getId()>0){ 
                            //Vérification que l'ID SAISON du Main-data n'est pas à 0    
                            if(md.getIdSaison()!=0){
                                //Récupération de l'ancienn saison active pour la passer inactive
                                Saison sOld = saisonRep.getOne(md.getIdSaison());
                                sOld.setActuelle(false);    
                                saisonRep.save(sOld);
                            }
                            //On inscrit la nouvelle ID_saison dans le main-data
                            md.setIdSaison(editSaison.getId());
                            mainDataRep.save(md);
                            resultVal = 3; // mise à jour éffectue et activation de la saison dans main-data
                        }                   
                    }
                }else{
                    resultVal = 4; // mise à jour du main-data non necessaire
                }
            }
        }
        return resultVal;
    }
    @Override public Long lastIdSaison() {
        //changer la fonction !!!
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
    @Override public boolean updateMainData(FormData editMainData) {
        boolean result = false;
        if(mainDataRep.existsById(editMainData.getId())) {
            MainData md = mainDataRep.getOne(editMainData.getId());
            md.setIdSaison(editMainData.getIdSaison());
            md.setIdStockCompet(editMainData.getIdStockCompet());
            
            //Si on doit passer la data en actif alors qu'elle ne l'était pas avant
            if(editMainData.isActif() == true && md.isActif() == false) {
                List<MainData> data = mainDataRep.findAll();
                //Si elle n'est pas vide on doit vérifier si les datas sont inactive
                boolean actif = false;
                for(MainData d : data){
                    if(d.isActif() && (d.getId() != editMainData.getId())){
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
                    md = d;
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
