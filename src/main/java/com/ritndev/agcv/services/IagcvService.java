package com.ritndev.agcv.services;

import com.ritndev.agcv.model.*;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IagcvService {
    
    //Methode Membres :
    public Membre saveMembre(Membre newMembre);
    public List<Membre> listMembre();
    public Membre findByIdMembre(Long id);
    public void supprMembre(Membre delMembre);
    public void updateByIdMembre(Long id, Membre editMembre);
    
    //Methode PrixTube
    public PrixTube savePrixTube(PrixTube newPrixTube);
    public List<PrixTube> listPrixTube();
    public PrixTube findByIdPrixTube(Long id);
    public void supprPrixTube(PrixTube delPrixTube);
    public void updateByIdPrixTube(Long id, PrixTube editPrixTube);
    
    //Methode Commande
    public Commande saveCommande(Commande newCommande);
    public List<Commande> listCommande();
    public Commande findByIdCommande(Long id);
    public void supprCommande(Commande delCommande);
    public void updateByIdCommande(Long id, Commande editCommande);
    
    //Methode Competition
    public Competition saveCompetition(Competition newCompetition);
    public List<Competition> listCompetition();
    public Competition findByIdCompetition(Long id);
    public void supprCompetition(Competition delCompetition);
    public void updateByIdCompetition(Long id, Competition editCompetition);
    
    //Methode ConsoMois
    public ConsoMois saveConsoMois(ConsoMois newConsoMois);
    public List<ConsoMois> listConsoMois();
    public ConsoMois findByIdConsoMois(Long id);
    public void supprConsoMois(ConsoMois delConsoMois);
    public void updateByIdConsoMois(Long id, ConsoMois editConsoMois);
    
    //Methode SacCompetition
    public SacCompetition saveSacCompetition(SacCompetition newSacCompetition);
    public SacCompetition findByIdSacCompetition(Long id);
    public void supprSacCompetition(SacCompetition delSacCompetition);
    public void updateByIdSacCompetition(Long id, SacCompetition editSacCompetition);
    
    //Methode Saison
    public Saison saveSaison(Saison newSaison);
    public List<Saison> listSaison();
    public Saison findByIdSaison(Long id);
    public void supprSaison(Saison delSaison);
    public void updateByIdSaison(Long id, Saison editSaison);
    
    //Methode TypeVolant
    public TypeVolant saveTypeVolant(TypeVolant TypeVolant);
    public List<TypeVolant> listTypeVolant();
    public TypeVolant findByIdTypeVolant(Long id);
    public void supprTypeVolant(TypeVolant delTypeVolant);
    public void updateByIdTypeVolant(Long id, TypeVolant editTypeVolant);
    
}
