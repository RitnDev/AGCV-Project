package com.ritndev.agcv.services;

import com.ritndev.agcv.InterfaceService.ICompetitionService;
import com.ritndev.agcv.InterfaceService.ITypeTubeService;
import com.ritndev.agcv.InterfaceService.ISaisonService;
import com.ritndev.agcv.InterfaceService.IMainDataService;
import com.ritndev.agcv.InterfaceService.IPrixTubeService;
import com.ritndev.agcv.InterfaceService.ITypeVolantService;
import com.ritndev.agcv.InterfaceService.IConsoMoisService;
import com.ritndev.agcv.InterfaceService.ICommandeService;
import com.ritndev.agcv.InterfaceService.IMembreService;
import com.ritndev.agcv.InterfaceService.IRestockService;
import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.*;
import com.ritndev.agcv.model.*;
import com.ritndev.agcv.model.enumeration.NomMois;
import com.ritndev.agcv.repository.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Ritn
 */
@Service
public class AGCVservice implements IMembreService, ICommandeService, ICompetitionService, IConsoMoisService,
    IMainDataService, IPrixTubeService, ISaisonService, ITypeTubeService, ITypeVolantService, IRestockService {
    
    @Autowired CommandeRepository commandeRep;
    @Autowired CompetitionRepository competitionRep;
    @Autowired ConsoMoisRepository consoMoisRep;
    @Autowired TypeVolantRepository typeVolantRep;
    @Autowired MainDataRepository mainDataRep;
    @Autowired MembreRepository membreRep;
    @Autowired PrixTubeRepository prixTubeRep;
    @Autowired SaisonRepository saisonRep;
    @Autowired TypeTubeRepository typeTubeRep;
    @Autowired RestockRepository restockRep;
    

    
    // -------------------   FONCTIONS MEMBRES ---------------------
    @Override public Reponse saveMembre(FormMembre newMembre) {
        int resultVal = 0;
        Membre m = membreRep.save(new Membre(newMembre.getPrenom(), newMembre.getNom()));
        if (m!=null) resultVal = 2;
        return new Reponse("membre", "create", resultVal);
    }
    @Override public List<Membre> listMembre() {return membreRep.findAll();}
    @Override public List<Membre> listMembreActif() {return membreRep.findByActifTrue();}
    @Override public Membre findByIdMembre(Long id) {
        if (membreRep.existsById(id)){
            return membreRep.getOne(id); 
        }else{
            return null;
        }
    }
    @Override public Reponse supprMembre(Long id) {
        int resultVal = 0;
        if (membreRep.existsById(id)){
            Membre m = membreRep.getOne(id);
            //vérifie que la liste des commandes est vide avant de supprimer le membre
            if(m.getCommandes().isEmpty()){
                membreRep.deleteById(id);
                resultVal = 2;
            }
        }else{
            resultVal = 1;
        }
        return new Reponse("membre", "remove", resultVal);
    }
    @Override public Reponse updateMembre(FormMembre editMembre) {
        int resultVal = 0;
        if (membreRep.existsById(editMembre.getId())){
            Membre m = membreRep.getOne(editMembre.getId());
            m.setNom(editMembre.getNom());
            m.setPrenom(editMembre.getPrenom());
            m.setActif(editMembre.isActif()); 
            membreRep.save(m);
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("membre", "edit", resultVal);
    }
    
    
    // -------------------   FONCTIONS PRIX-TUBES ---------------------
    @Override public Reponse savePrixTube (FormPrixTube newPrixTube) {
        int resultVal = 0;
        if (newPrixTube != null
        && newPrixTube.getIdTypeTube()>0
        && newPrixTube.getPrixDouble()>0
        && newPrixTube.getPrixMembreDouble()>=0){

            prixTubeRep.save(new PrixTube(newPrixTube.getMarque(),
                                newPrixTube.getPrixDouble(), 
                                newPrixTube.getPrixMembreDouble(),
                                typeTubeRep.getOne(newPrixTube.getIdTypeTube()),
                                true)); // Actif par défaut à la création
            resultVal = 2;
        }
        return new Reponse("prixtube", "create", resultVal);
    }
    @Override public List<PrixTube> listPrixTube() {return prixTubeRep.findAll();}
    @Override public List<PrixTube> ListPrixTubeName(String nom){
        List<PrixTube> prixTubes = prixTubeRep.findByActifTrue();
        List<PrixTube> prixTubeName = new ArrayList<>();
        for(PrixTube pt : prixTubes){
            if(pt.getIdTypeTube().getNom().equals(nom)){
                prixTubeName.add(pt);
            }
        }
        return prixTubeName;
    }
    @Override public List<PrixTube> ListPrixTubeActif() {
        return prixTubeRep.findByActifTrue();
    }
    @Override public PrixTube findByIdPrixTube(Long id) {
        if (prixTubeRep.existsById(id)){
            return prixTubeRep.getOne(id);
        }else{
            return null;
        }   
    }
    @Override public Reponse supprPrixTube(Long id) {
        int resultVal = 0;
        if (prixTubeRep.existsById(id)){
            PrixTube pt = prixTubeRep.getOne(id);
            if (!pt.isActif() || !pt.isDefaut()){
                prixTubeRep.deleteById(id);
                resultVal = 2;
            }else{
                resultVal = 3;
            }
        }else{
            resultVal = 1;
        } 
        return new Reponse("prixtube", "remove", resultVal);
    }
    @Override public Reponse updatePrixTube(FormPrixTube editPrixTube) {
        int resultVal = 0;// erreur de modification
        if (prixTubeRep.existsById(editPrixTube.getId())){
            
            PrixTube pt = prixTubeRep.getOne(editPrixTube.getId()); // je récupère le prix tube
            
            pt.setMarque(editPrixTube.getMarque());
            pt.setPrix(editPrixTube.getPrixDouble());
            pt.setPrixMembre(editPrixTube.getPrixMembreDouble());
            pt.setActif(editPrixTube.isActif());
            
            prixTubeRep.save(pt);
            resultVal = 2; // Modification OK
        }else{
            resultVal = 1;
        }
        return new Reponse("prixtube", "edit", resultVal);
    }
    
    
    
    // -------------------   FONCTIONS COMMANDES ---------------------
    @Override public Reponse saveCommande(FormCommande newCommande) {
        int resultVal = 0;
        Commande c = commandeRep.save(new Commande(
                                        membreRep.getOne(newCommande.getIdMembre()),
                                        newCommande.getNbTubeCommande(), 
                                        newCommande.isRegler(),
                                        returnMainData().getIdSaison(),
                                        consoMoisRep.getOne(newCommande.getIdConsoMois()),
                                        prixTubeRep.getOne(newCommande.getIdPrixTube())));
        if(c!=null) resultVal = 2;
        
        return new Reponse("commande", "create", resultVal);
    }
    @Override public List<Commande> listCommande() {return commandeRep.findAll();}
    @Override public Commande findByIdCommande(Long id) {
        if (commandeRep.existsById(id)){
            return commandeRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override public Reponse supprCommande(Long id) {
        int resultVal = 0;
        if (commandeRep.existsById(id)){
            commandeRep.deleteById(id);
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("commande", "remove", resultVal);
    }
    @Override public Reponse updateCommande(FormCommande editCommande) {
        int resultVal = 0;
        if (commandeRep.existsById(editCommande.getId())){
            Commande c = commandeRep.getOne(editCommande.getId());
            
            c.setRegler(editCommande.isRegler());
            commandeRep.save(c);
            
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("commande", "edit", resultVal);
    }
 
    
    // -------------------   FONCTIONS COMPETITION ---------------------
    @Override public Reponse saveCompetition(FormCompet newCompet) {
        int resultVal = 0;
        //Récupération de la Main-Data active (md)
        MainData md = returnMainData();
        //Vérification qu'il y a bien une main-data de créée
        if(md.getId() != 0){
            if (md.getIdSaison()!=null) {
            //if (md.getIdSaison()>0) {
                if (newCompet != null
                && newCompet.getNbTubesUtilises() != 0
                && !newCompet.getNom().equals("")){
                    competitionRep.save(new Competition(md.getIdSaison(), md, newCompet.getNbTubesUtilises(), newCompet.getNom()));
                    resultVal = 2;
                }
            }else{
                resultVal = 3; // pas de saison active actuellement.
            }
        }else{
            resultVal = 1; // Pas de main-data créée.
        }
        return new Reponse("compet", "create", resultVal);
    }
    @Override public Competition findByIdCompetition(Long id) {
        if(competitionRep.existsById(id)){
            return competitionRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override public Reponse supprCompetition(Long id) {
        int resultVal = 0;
        if(competitionRep.existsById(id)){
            competitionRep.deleteById(id);
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("compet", "remove", resultVal);
    }
    @Override public Reponse updateCompetition(FormCompet editCompet) {
        int resultVal = 0;//erreur lors de la mise à jour
        if(competitionRep.existsById(editCompet.getId())){
            Competition c = competitionRep.getOne(editCompet.getId());
            c.setNom(editCompet.getNom());
            c.setNbTubesUtilises(editCompet.getNbTubesUtilises());
            
            competitionRep.save(c);
            resultVal = 2; //Mise à jour réussie
        }else{
            resultVal = 1; //La competition n'existe pas en BDD.
        }
               
        return new Reponse("compet", "edit", resultVal);
    }

    
    // -------------------   FONCTIONS CONSO-MOIS ---------------------
    @Override public Reponse saveConsoMois(FormConsoMois newConsoMois) {
        int resultVal = 0;
        if(!newConsoMois.getNom().equals("")
            && newConsoMois.getIdPrixTube()!=0
            && newConsoMois.getIdTypeVolant()!=0){
            ConsoMois cm = consoMoisRep.save(new ConsoMois(
                    newConsoMois.getNom(),
                    prixTubeRep.getOne(newConsoMois.getIdPrixTube()),
                    typeVolantRep.getOne(newConsoMois.getIdTypeVolant()),
                    newConsoMois.getNbTubeUtilise(),
                    newConsoMois.getNbTubeCommande()));
            if(cm!=null) resultVal = 2;
        }
        return new Reponse("consomois", "create", resultVal);
    }
    @Override public List<ConsoMois> listConsoMois() {return consoMoisRep.findAll();}
    @Override public ConsoMois findByIdConsoMois(Long id) {
        if(consoMoisRep.existsById(id)){
            return consoMoisRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override public Reponse supprConsoMois(Long id) {
        int resultVal = 0;
        if(consoMoisRep.existsById(id)){
            consoMoisRep.deleteById(id);
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("consomois", "remove", resultVal);
    }
    @Override public Reponse updateConsoMoisPrixtube(FormConsoMois editConsoMois){
        int resultVal = 0;
        if(consoMoisRep.existsById(editConsoMois.getId())){
            //Recup du conso mois
            ConsoMois cm = consoMoisRep.getOne(editConsoMois.getId());
            //MaJ du prix tube
            cm.setIdPrixTube(prixTubeRep.getOne(editConsoMois.getIdPrixTube()));
            
            boolean suivant = false;
            if(editConsoMois.isSuivant()){
                TypeVolant tv = cm.getIdTypeVolant();
                for (NomMois mois : Arrays.asList(NomMois.values())){
                    if(suivant){
                        tv.getConsoMoisName(mois.toString()).setIdPrixTube(prixTubeRep.getOne(editConsoMois.getIdPrixTube()));
                    }
                    if(editConsoMois.getNom().equals(mois.toString())){
                        suivant = true;
                    }    
                }
                typeVolantRep.save(tv);
            }
            
            consoMoisRep.save(cm); 
            resultVal = 2;
        }else{
            resultVal = 3;
        }
        return new Reponse("prixtube", "edit", resultVal);
    }
    @Override public Reponse updateConsoMoisNbUtilises(FormConsoMois editConsoMois){
        int resultVal = 0;
        if(consoMoisRep.existsById(editConsoMois.getId())){
            //Recup du conso mois
            ConsoMois cm = consoMoisRep.getOne(editConsoMois.getId());
            //MaJ du nbTubeUtilise
            cm.setNbTubeUtilise(editConsoMois.getNbTubeUtilise());
            consoMoisRep.save(cm); 
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("nbutilises", "edit", resultVal);
    }
    @Override public Reponse updateConsoMoisNbCommandes(FormConsoMois editConsoMois){
        int resultVal = 0;
        if(consoMoisRep.existsById(editConsoMois.getId())){
            //Recup du conso mois
            ConsoMois cm = consoMoisRep.getOne(editConsoMois.getId());
            //MaJ du nbTubeCommande
            cm.setNbTubeCommande(editConsoMois.getNbTubeCommande());
            consoMoisRep.save(cm); 
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("nbcommandes", "edit", resultVal);
    }
    @Override public Reponse updateConsoMois(FormConsoMois editConsoMois) {
        int resultVal = 0;
        if(consoMoisRep.existsById(editConsoMois.getId())){
            ConsoMois cm = consoMoisRep.getOne(editConsoMois.getId());
            cm.setNbTubeCommande(editConsoMois.getNbTubeCommande());
            cm.setNbTubeUtilise(editConsoMois.getNbTubeUtilise());
            
            consoMoisRep.save(cm); 
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("consomois", "edit", resultVal);
    }

    

    // -------------------   FONCTIONS SAISON ---------------------
    /*
    Return : long resultVal : 
                0 = Saison non créée.
                1 = Pas de Main-Data !
                2 = Saison créée avec succès !
                3 = Cette saison existe déjà ! (année de début)
                4 = Saison créée avec succès. Cette saison devient la nouvelle saison active !
    */
    @Override public Reponse saveSaison(FormSaison newSaison) {
        int resultVal = 0;
        long idSaison = 0;
        
        //Est-ce possible de créer la saison ?
        if(newSaison != null 
                && !"".equals(newSaison.toString())
                && !"".equals(newSaison.getBudget())
                ){
            
            if (!saisonRep.existsByAnneeDebut(newSaison.getAnnee_debut())) {
                Saison sNew = saisonRep.save(new Saison(newSaison.getAnnee_debut(), newSaison.getBudget(), newSaison.isActuelle()));
                idSaison = sNew.getId(); //recupération de l'id pour la création des TypeVolant
                resultVal = 2;
                
                if (sNew.isActuelle()){
                    
                    // ----- MISE A JOUR MAIN-DATA -----

                    //Récupération de la Main-Data active (md)
                    MainData md = returnMainData();
                    
                    //Vérification qu'il y a bien une main-data de créée
                    if(md.getId() == 0){
                        //Création d'un Main-Data et enregistrement de la saison
                        newMainData();
                        md = returnMainData();
                        md.setIdSaison(sNew);
                        mainDataRep.save(md);
                        resultVal = 1;
                    }else{
                        if(md.getIdSaison().getId()==1){
                            //On enregistre la nouvelle saison comme actuelle
                            md.setIdSaison(sNew);
                            mainDataRep.save(md);
                            resultVal = 4;
                        }else{
                            if (saisonRep.existsById(md.getIdSaison().getId())){
                                Saison sOld = md.getIdSaison();
                                sOld.setActuelle(false); // Passage en saison non actuelle
                                saisonRep.save(sOld);    //sauvegarde des modifications
                                md.setIdSaison(sNew);      //mise à jour du main-data avec la nouvelle saison
                                mainDataRep.save(md);
                                resultVal = 4;
                            }else{
                                //On enregistre la nouvelle saison comme actuelle
                                md.setIdSaison(sNew);
                                mainDataRep.save(md);
                                resultVal = 4;
                            }    
                        }
                    }
                } 
            }else{
                resultVal = 3; //Saison existe déjà (année de début)
            }
        }
        
        //Si Saison est sauvegardé avec succès
        if(resultVal==4 || resultVal ==2){
            createTypeVolant(idSaison);
        }
        return new Reponse("saison", "create", resultVal);
    }
    @Override public List<Saison> listSaison() {return saisonRep.findAll();}
    @Override public Saison findByIdSaison(Long id) {
        if (saisonRep.existsById(id)){
            return saisonRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override public Saison findByAnneeSaison(int anneeDebut) {
        if (saisonRep.existsByAnneeDebut(anneeDebut)) {
            return saisonRep.findByAnneeDebut(anneeDebut);
        }else{
            return null;
        }
    }
    @Override public Saison findByAnneeFinSaison(int anneeFin) {
        if (saisonRep.existsByAnneeFin(anneeFin)) {
            return saisonRep.findByAnneeFin(anneeFin);
        }else{
            return null;
        }
    }
    @Override public List<Saison> listSaisonHisto() {
        return saisonRep.findByActuelleFalseOrderByAnneeDebutDesc();
    }
    @Override public Reponse supprSaison(Long id) {
        int resultVal = 0;
        
        if(id!=1){
            if (saisonRep.existsById(id)){
                Saison saison = saisonRep.getOne(id);
                
                // Mise à jour du Main-Data (si la saison été actuelle)
                MainData md = returnMainData();
                if(md.getId()>0){
                    if(id == md.getIdSaison().getId()){
                        Long saison0 = 1L;
                        md.setIdSaison(saisonRep.getOne(saison0));
                        saisonRep.delete(saison);
                        resultVal = 3;
                    }else{
                        saisonRep.delete(saison);
                        resultVal = 2;
                    }
                }
                
            }else{
                resultVal = 1; //Cette saison n'existe pas en BDD.
            }
        }       
        return new Reponse("saison", "remove", resultVal);
    }
    
/*
    Return : long resultVal : 
                0 = Saison non modifiée.
                2 = Saison créée avec succès !
                3 = Saison mise à jour et désactivation de la saison dans main-data
                4 = Saison mise à jour et activation de la saison dans main-data
*/
    @Override public Reponse updateSaison(FormSaison editSaison) {
        int resultVal = 0; //mise à jour non effectuée
        
        if(editSaison != null 
                && !"".equals(editSaison.getBudget())
                ){
        
            //Vérifie que la saison existe en base
            if (saisonRep.existsById(editSaison.getId())){

                //On récupère la saison et modifie les attributs concernés
                System.out.println(">> (debug) ID Saison edit : " + editSaison.getId());
                Saison saison = saisonRep.getOne(editSaison.getId());
                saison.setBudget(editSaison.getBudget());

                //On save si la saison est l'actuelle avant changement
                boolean actif = saison.isActuelle(); //La saison modifié est elle active ?
                saison.setActuelle(editSaison.isActuelle());
                saisonRep.save(saison);
                resultVal = 2; //Mise à jour effectuée.
               
                // --- Mise à jour du Main-Data ---
                
                //La saison a-t-elle changé d'état (actuelle)
                if(actif != editSaison.isActuelle()){
                    //Si actif = true alors il va passer à false (et vice-versa)
                    if(actif){ 
                        
                        // MAIN DATA : ID SAISON PASSE à 0
                        MainData md = returnMainData();
                        if(md.getId()>0){
                            //on met à 0 l'idSaison du main-data
                            md.setIdSaison(null);
                            mainDataRep.save(md);
                            resultVal = 3; // mise à jour éffectue et désactivation de la saison dans main-data
                        }
                        
                    }else{
                        
                        //MAIN DATA : ID SAISON CHANGE
                        MainData md = returnMainData();
                        if(md.getId()>0){ 
                            //Vérification que l'ID SAISON du Main-data n'est pas à 0    
                            if(md.getIdSaison()!=null){
                                //Récupération de l'ancienn saison active pour la passer inactive
                                Saison sOld = md.getIdSaison();
                                if (sOld.getId()!=0){
                                    sOld.setActuelle(false);  
                                }
                                saisonRep.save(sOld);
                            }
                            //On inscrit la nouvelle ID_saison dans le main-data
                            md.setIdSaison(saison);
                            mainDataRep.save(md);
                            resultVal = 4; // mise à jour éffectue et activation de la saison dans main-data
                        }                   
                    }
                }
            }
        }
        return new Reponse("saison", "edit", resultVal);
    }
    
    
    @Override public Reponse saveRestock(FormRestock newRestock) {
        int resultVal = 0;
        if(newRestock.getValeur()!=0){
            Restock rs = restockRep.save(new Restock(
                                newRestock.getValeur(),
                                saisonRep.getOne(newRestock.getIdSaison()),
                                returnMainData(),
                                consoMoisRep.getOne(newRestock.getIdConsoMois())));
            if(rs!=null) resultVal = 2;
        }
        return new Reponse("stock", "create", resultVal);
    }
    @Override public List<Restock> listRestock() {return restockRep.findAll();}
    @Override public Restock findByIdRestock(Long id) {
        if (restockRep.existsById(id)){
            return restockRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override public Reponse supprRestock(Long id) {
        int resultVal = 0;
        if (restockRep.existsById(id)){
            restockRep.deleteById(id);
            resultVal = 2;
        }else{
            resultVal = 1; //N'existe pas dans la BDD.
        }     
        return new Reponse("stock", "remove", resultVal);
    }

    @Override public Reponse updateRestock(FormRestock editRestock) {
        int resultVal = 0;
        if(restockRep.existsById(editRestock.getId())){
            Restock rs = restockRep.getOne(editRestock.getId());
            rs.setValeur(editRestock.getValeur());
            rs.setIdConsoMois(consoMoisRep.getOne(editRestock.getIdConsoMois()));
            
            restockRep.save(rs);
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("stock", "edit", resultVal);
    }
    
    
    
    
    // -------------------   FONCTIONS TYPE-VOLANT ---------------------
    @Override public Reponse saveTypeVolant(FormTypeVolant newTypeVolant) {
        int resultVal = 0;
        //Si Saison = Actuelle, idSaison=0, du coup recuperation depuis MainData
        long idSaison = newTypeVolant.getIdSaison();
        if (idSaison == 0) {
            idSaison = returnMainData().getIdSaison().getId();
        }
        
            TypeVolant t = typeVolantRep.save(new TypeVolant(
                                                saisonRep.getOne(idSaison), 
                                                typeTubeRep.getOne(newTypeVolant.getIdTypeTube()), 
                                                newTypeVolant.getStock()));
            if(t!=null){
                resultVal = 2;
                createConsoMois(t);
            }
        return new Reponse("typevolant", "create", resultVal);
    }
    @Override public List<TypeVolant> listTypeVolant() {return typeVolantRep.findAll();}
    @Override public TypeVolant findByIdTypeVolant(Long id) {
        if (typeVolantRep.existsById(id)){
            return typeVolantRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override public Reponse supprTypeVolant(Long id) {
        int resultVal = 0;
        if (typeVolantRep.existsById(id)){     
            typeVolantRep.deleteById(id);
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("typevolant", "remove", resultVal);
    }
    @Override public Reponse updateTypeVolant(FormTypeVolant editTypeVolant) {
        int resultVal = 0;
        if (typeVolantRep.existsById(editTypeVolant.getId())){  
            TypeVolant ct = typeVolantRep.getOne(editTypeVolant.getId());
                ct.setStock(editTypeVolant.getStock());

                typeVolantRep.save(ct);
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        return new Reponse("typevolant", "edit", resultVal);
    }

    
    // -------------------   FONCTIONS MAIN-DATA ---------------------
    @Override public Reponse newMainData(){
        int resultVal = 0;
        if(mainDataRep.findByActifTrue().isEmpty()){
            //Si elles sont toutes inactives ont peut créer une nouvelle MainData
            MainData main = new MainData();
            mainDataRep.save(main);
            resultVal = 2;
        }
        return new Reponse("data", "create", resultVal);
    }
    @Override public List<MainData> listMainData() {return mainDataRep.findAll();}
    @Override public MainData findByIdMainData(Long id) {
        if (mainDataRep.existsById(id)){
            return mainDataRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override public Reponse supprMainData(Long id) {
        int resultVal = 0;
        if(mainDataRep.existsById(id)) {
            resultVal = 3;
            MainData md = mainDataRep.getOne(id);
            if (md.getIdSaison().getId()==1){
                mainDataRep.deleteById(id);
                resultVal = 2;  
            }
        }else{
            resultVal = 1;
        }
        return new Reponse("data", "remove", resultVal);
    }
    @Override public Reponse updateMainData(FormData editMainData) {
        int resultVal = 0;//erreur lors de la mise à jour de la main-data
        
        //Vérification que l'ID de la data à modifier existe en BDD
        if(mainDataRep.existsById(editMainData.getId())) {
            //Récupération de la data et mise à jour de l'ID Saison/Stock
            MainData md = mainDataRep.getOne(editMainData.getId());
            
            //Passe la saison selectionné en active
            Saison sNew = saisonRep.getOne(editMainData.getIdSaison());
            sNew.setActuelle(true);
            saisonRep.save(sNew);
            
            //Passe toutes les saisons en non active
            for(Saison s : listSaison()){
                if(s.getId()>1){
                    if(s.getId()!=sNew.getId()){
                        s.setActuelle(false);
                        saisonRep.save(s);
                    }
                }
            }
            
            md.setIdSaison(sNew);
            resultVal = 2;//mise à jour de la main-data
            
            
            //Si on doit passer la data en actif alors qu'elle ne l'était pas avant
            if(editMainData.isActif() == true && md.isActif() == false) {
                
                for(MainData d : mainDataRep.findByActifTrue()){    //Recuperation de la liste des data active
                    d.setActif(false);                          //passage de toutes ces datas à false
                    mainDataRep.save(d);                        //on save le nouvelle état
                }
                md.setActif(true);                              //On passe actif la data à modifier
                resultVal = 4;//Nouvelle main-data active
            }
            //SI on doit désactiver la data actuelle ?
            if(editMainData.isActif() == false && md.isActif() == true) {
                md.setActif(false);                              //On désactive la data à modifier
                resultVal = 3;//désactivation de la main-data
            } 
            mainDataRep.save(md);                               //On save la data
        }else{
            resultVal = 1;
        }
        return new Reponse("data", "edit", resultVal);
    }
    @Override public Reponse updateBudget(FormData editMainData) {
        int resultVal = 0;
        
        MainData md = returnMainData();
        if(md.getId() != 0){
            md.setBudgetDefault(editMainData.getBudget());
            mainDataRep.save(md);
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        
        return new Reponse("budget", "edit", resultVal);
    }
    @Override public Reponse updateSeuil(FormData editMainData) {
        int resultVal = 0;
        
        MainData md = returnMainData();
        if(md.getId() != 0){
            md.setSeuilBas(editMainData.getSeuilBas());
            mainDataRep.save(md);
            resultVal = 2;
        }else{
            resultVal = 1;
        }
        
        return new Reponse("seuil", "edit", resultVal);
    }
    @Override public MainData returnMainData() {
        List<MainData> data = mainDataRep.findAll();
        MainData md = new MainData(0);
        if (!data.isEmpty()){
            //Si la liste est non vide on vérifie laquelle est active
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
    @Override public Reponse saveTypeTube(FormTypeTube formTypeTube) {
        int resultVal = 0;
        if(formTypeTube != null
        && !formTypeTube.getNom().equals("")) {
            
            // MAIN DATA
            MainData md = returnMainData();
            if(md.getId()>0){
                TypeTube tt = typeTubeRep.save(new TypeTube(formTypeTube.getNom(), formTypeTube.isCommande()));
                changeIdTTMainData(md, tt.getNom(), tt.getId());
                mainDataRep.save(md);
                resultVal = 2; // mise à jour du main-data et creation du Type-Tube ok !
            }else{
                resultVal = 1; //Pas de main-data !
            }
            
        }else{
            resultVal = 3; //Champs incorrect !
        }
        return new Reponse("typetube", "create", resultVal);
    }
    @Override public List<TypeTube> listTypeTube() {return typeTubeRep.findAll();}
    
    @Override public List<TypeTube> listDataTypeTube() {
        List<TypeTube> typeTubes = new ArrayList<>();
        
        MainData md = returnMainData();
        if(md.getId()>0){
            if (typeTubeRep.existsById(md.getIdTTPlastique().getId())){
                typeTubes.add(md.getIdTTPlastique());
            }
            if (typeTubeRep.existsById(md.getIdTTEntrainement().getId())){
                typeTubes.add(md.getIdTTEntrainement());
            }
            if (typeTubeRep.existsById(md.getIdTTCompetition().getId())){
                typeTubes.add(md.getIdTTCompetition());
            }
        }
        return typeTubes;
    }
    @Override public TypeTube findByIdTypeTube(Long id) {
        if (typeTubeRep.existsById(id)){
            return typeTubeRep.getOne(id);
        }else{
            return null;
        }
    }
    @Override public Reponse supprTypeTube(Long id) {
        int resultVal = 0; //Suppression non OK
        if(typeTubeRep.existsById(id)){ //Verification que l'id existe en BDD
            
            MainData md = returnMainData();
            if(md.getId()>0){
                String nomTypeTube = typeTubeRep.getOne(id).getNom();
                typeTubeRep.deleteById(id);
                long value = 1;
                
                switch(nomTypeTube){
                    case "Plastique" ->{
                        if(md.getIdTTPlastique().getId()==id){
                            changeIdTTMainData(md, nomTypeTube, value);
                            mainDataRep.save(md);
                        }
                    }
                    case "Entrainement" ->{
                        if(md.getIdTTEntrainement().getId()==id){
                            changeIdTTMainData(md, nomTypeTube, value);
                            mainDataRep.save(md);
                        }
                    }
                    case "Compétition" ->{
                        if(md.getIdTTCompetition().getId()==id){
                            changeIdTTMainData(md, nomTypeTube, value);
                            mainDataRep.save(md);
                        }
                    }
                }
                resultVal = 2; // mise à jour du main-data et supprression du Type-Tube ok !
            }else{
                resultVal = 3; //Pas de main-data !
            }
                        
        }else{
            resultVal = 1; //Le Type-Tube ne se trouve pas en BDD
        }     
        return new Reponse("typetube", "remove", resultVal);
    }
    @Override public Reponse updateTypeTube(FormTypeTube editTypeTube) {
        int resultVal = 0; //Modification non OK
        if(typeTubeRep.existsById(editTypeTube.getId())){ //Verification que l'id existe en BDD
            
            MainData md = returnMainData();
            if(md.getId()>0){
                TypeTube tb = typeTubeRep.getOne(editTypeTube.getId());
                tb.setCommande(editTypeTube.isCommande());
                typeTubeRep.save(tb);
                resultVal = 2; // mise à jour du Type-Tube ok !
            }else{
                resultVal = 3; //Pas de main-data !
            }

        }else{
            resultVal = 1;
        }
        return new Reponse("typetube", "edit", resultVal);
    }

    
    
    //Change l'ID Type Tube selon le du type Tube
    private void changeIdTTMainData(MainData md, String nom, Long id){
        switch (nom){
            case "Plastique" ->     md.setIdTTPlastique(typeTubeRep.getOne(id));
            case "Entrainement" ->  md.setIdTTEntrainement(typeTubeRep.getOne(id));
            case "Compétition" ->   md.setIdTTCompetition(typeTubeRep.getOne(id));
        }
    }
    
    //Créer les 3 type Volant
    private int createTypeVolant(long idSaison) {
        int resultVal = 4;
        saveTypeVolant(new FormTypeVolant(0, returnMainData().getIdTTPlastique().getId(), idSaison));
        saveTypeVolant(new FormTypeVolant(0, returnMainData().getIdTTEntrainement().getId(), idSaison));
        saveTypeVolant(new FormTypeVolant(0, returnMainData().getIdTTCompetition().getId(), idSaison));
        return resultVal;
    }

    //Créer les 12 ConsoMois
    private int createConsoMois(TypeVolant idTypeVolant) {
        int resultVal = 2;
        PrixTube idPrixTube = idTypeVolant.getIdTypeTube().getPrixTubeActif();
        saveConsoMois(new FormConsoMois(NomMois.AOUT.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.SEPTEMBRE.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.OCTOBRE.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.NOVEMBRE.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.DECEMBRE.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.JANVIER.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.FEVRIER.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.MARS.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.AVRIL.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.MAI.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.JUIN.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        saveConsoMois(new FormConsoMois(NomMois.JUILLET.toString(), idPrixTube.getId(), idTypeVolant.getId()));
        return resultVal;
    }
    
    
}
