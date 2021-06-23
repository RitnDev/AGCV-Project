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
    public int saveCommande(FormCommande newCommande);
    public List<Commande> listCommande();
    public Commande findByIdCommande(Long id);
    public int supprCommande(Long id);
    public int updateCommande(FormCommande editCommande);
    
    //Methode Competition
    public int saveCompetition(FormCompet newCompet);
    public Competition findByIdCompetition(Long id);
    public int supprCompetition(Long id);
    public int updateCompetition(FormCompet editCompet);
    
    //Methode ConsoMois
    public int saveConsoMois(FormConsoMois newConsoMois);
    public List<ConsoMois> listConsoMois();
    public ConsoMois findByIdConsoMois(Long id);
    public int supprConsoMois(Long id);
    public int updateConsoMois(FormConsoMois editConsoMois);
    
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
    
    
    //Methode TypeVolant
    public int saveTypeVolant(FormTypeVolant newTypeVolant);
    public List<TypeVolant> listTypeVolant();
    public TypeVolant findByIdTypeVolant(Long id);
    public int supprTypeVolant(Long id);
    public int updateTypeVolant(FormTypeVolant editTypeVolant);
    
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
    public List<TypeTube> listDataTypeTube();
    public TypeTube findByIdTypeTube(Long id);
    public int supprTypeTube(Long id);
    public int updateTypeTube(FormTypeTube editTypeTube);

    
    
}
