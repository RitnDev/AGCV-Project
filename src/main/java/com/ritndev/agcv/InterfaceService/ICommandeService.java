package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.model.Commande;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface ICommandeService {
    
    //Methode Commande
    public Reponse saveCommande(FormCommande newCommande);
    public List<Commande> listCommande();
    public Commande findByIdCommande(Long id);
    public Reponse supprCommande(Long id);
    public Reponse updateCommande(FormCommande editCommande);
    
}
