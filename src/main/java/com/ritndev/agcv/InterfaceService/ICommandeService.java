package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.form.FormCommande;
import com.ritndev.agcv.model.Commande;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface ICommandeService {
    
    //Methode Commande
    public int saveCommande(FormCommande newCommande);
    public List<Commande> listCommande();
    public Commande findByIdCommande(Long id);
    public int supprCommande(Long id);
    public int updateCommande(FormCommande editCommande);
    
}
