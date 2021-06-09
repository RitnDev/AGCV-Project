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
    public int saveMembre(FormMembre newMembre);
    public List<Membre> listMembre();
    public Membre findByIdMembre(Long id);
    public int supprMembre(Long id);
    public int updateMembre(FormMembre editMembre);
    
    //Methode PrixTube
    public int savePrixTube(FormPrixTube newPrixTube);
    public List<PrixTube> listPrixTube();
    public FormPrixTube findByIdPrixTube(Long id);
    public int supprPrixTube(Long id);
    public int updatePrixTube(FormPrixTube editPrixTube);
    
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
    public int supprSaison(Long id);
    public int updateSaison(FormSaison editSaison);
    public Long lastIdSaison();
    
    
    //Methode ConsoTube
    public ConsoTube saveConsoTube(ConsoTube consoTube);
    public List<ConsoTube> listConsoTube();
    public ConsoTube findByIdConsoTube(Long id);
    public void supprConsoTube(Long id);
    public void updateByIdConsoTube(Long id, ConsoTube editConsoTube);
    
    //Methode MainData
    public int newMainData();
    public List<MainData> listMainData();
    public MainData findByIdMainData(Long id);
    public int supprMainData(Long id);
    public int updateMainData(FormData editMainData);
    public MainData returnMainData();
    
    //Methode TypeTube
    public int saveTypeTube(FormTypeTube formTypeTube);
    public List<TypeTube> listTypeTube();
    public TypeTube findByIdTypeTube(Long id);
    public int supprTypeTube(Long id);
    public int updateTypeTube(FormTypeTube editTypeTube);

    
    
}
