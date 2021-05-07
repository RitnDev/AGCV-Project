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
    public void supprMembre(Long id);
    public void updateByIdMembre(Long id, Membre editMembre);
    
    //Methode PrixTube
    public PrixTube savePrixTube(PrixTube newPrixTube);
    public List<PrixTube> listPrixTube();
    public PrixTube findByIdPrixTube(Long id);
    public void supprPrixTube(Long id);
    public void updateByIdPrixTube(Long id, PrixTube editPrixTube);
    
    //Methode Commande
    public Commande saveCommande(Commande newCommande);
    public List<Commande> listCommande();
    public Commande findByIdCommande(Long id);
    public void supprCommande(Long id);
    public void updateByIdCommande(Long id, Commande editCommande);
    
    //Methode Competition
    public Competition saveCompetition(Competition newCompetition);
    public List<Competition> listCompetition();
    public Competition findByIdCompetition(Long id);
    public void supprCompetition(Long id);
    public void updateByIdCompetition(Long id, Competition editCompetition);
    
    //Methode ConsoMois
    public ConsoMois saveConsoMois(ConsoMois newConsoMois);
    public List<ConsoMois> listConsoMois();
    public ConsoMois findByIdConsoMois(Long id);
    public void supprConsoMois(Long id);
    public void updateByIdConsoMois(Long id, ConsoMois editConsoMois);
    
    //Methode StockCompetition
    public StockCompetition saveStockCompetition(StockCompetition newStock);
    public StockCompetition findByIdStockCompetition(Long id);
    public void supprStockCompetition(Long id);
    public void updateByIdStockCompetition(Long id, StockCompetition editStock);
    
    //Methode Saison
    public Saison saveSaison(Saison newSaison);
    public List<Saison> listSaison();
    public Saison findByIdSaison(Long id);
    public void supprSaison(Long id);
    public void updateByIdSaison(Long id, Saison editSaison);
    public Long lastIdSaison();
    
    //Methode ConsoTube
    public ConsoTube saveConsoTube(ConsoTube consoTube);
    public List<ConsoTube> listConsoTube();
    public ConsoTube findByIdConsoTube(Long id);
    public void supprConsoTube(Long id);
    public void updateByIdConsoTube(Long id, ConsoTube editConsoTube);
    
    //Methode MainData
    public MainData saveMainData(MainData mainData);
    public MainData findByIdMainData(Long id);
    public void supprMainData(Long id);
    public void updateByIdMainData(Long id, MainData editMainData);
    
    //Methode TypeTube
    public TypeTube saveTypeTube(TypeTube typeTube);
    public List<TypeTube> listTypeTube();
    public TypeTube findByIdTypeTube(Long id);
    public void supprTypeTube(Long id);
    public void updateByIdTypeTube(Long id, TypeTube editTypeTube);
    
}
