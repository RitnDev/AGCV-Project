package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.model.Membre;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IMembreService {
    
    //Methode Membres :
    public Reponse saveMembre(FormMembre newMembre);
    public List<Membre> listMembre();
    public List<Membre> listMembreActif();
    public Membre findByIdMembre(Long id);
    public Reponse supprMembre(Long id);
    public Reponse updateMembre(FormMembre editMembre);

}
