package com.ritndev.agcv.services;

import com.ritndev.agcv.form.*;
import com.ritndev.agcv.model.*;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IagcvService {
    
    //Methode Membres :
    public Membre saveMembre(FormMembre newMembre);
    public List<Membre> listMembre();
    public Membre findByIdMembre(Long id);
    public void supprMembre(Long id);
    public void updateByIdMembre(FormMembre editMembre);
    
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
    public int saveCompetition(FormCompet newCompet);
    public List<Competition> listCompetitionBySaison(Long idSaison);
    public Competition findByIdCompetition(Long id);
    public int supprCompetition(Long id);
    public int updateCompetition(FormCompet editCompet);
    
    //Methode ConsoMois
    public ConsoMois saveConsoMois(ConsoMois newConsoMois);
    public List<ConsoMois> listConsoMois();
    public ConsoMois findByIdConsoMois(Long id);
    public void supprConsoMois(Long id);
    public void updateByIdConsoMois(Long id, ConsoMois editConsoMois);
    
    //Methode StockCompetition
    public int newStock();
    public List<StockCompetition> listStock();
    public StockCompetition findByIdStock(Long id);
    public int supprStock(Long id);
    public int updateStock(FormStock editStock);
    
    //Methode Saison
    public int saveSaison(FormSaison newSaison);
    public List<Saison> listSaison();
    public Saison findByIdSaison(Long id);
    public void supprSaison(Long id);
    public int updateSaison(FormSaison editSaison);
    public Long lastIdSaison();
    
    
    //Methode ConsoTube
    public ConsoTube saveConsoTube(ConsoTube consoTube);
    public List<ConsoTube> listConsoTube();
    public ConsoTube findByIdConsoTube(Long id);
    public void supprConsoTube(Long id);
    public void updateByIdConsoTube(Long id, ConsoTube editConsoTube);
    
    //Methode MainData
    public boolean newMainData();
    public MainData saveMainData(MainData mainData);
    public List<MainData> listMainData();
    public MainData findByIdMainData(Long id);
    public void supprMainData(Long id);
    public boolean updateMainData(FormData editMainData);
    public MainData returnMainData();
    
    //Methode TypeTube
    public TypeTube saveTypeTube(TypeTube typeTube);
    public List<TypeTube> listTypeTube();
    public TypeTube findByIdTypeTube(Long id);
    public void supprTypeTube(Long id);
    public void updateByIdTypeTube(Long id, TypeTube editTypeTube);

    
    
}
