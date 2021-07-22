package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.form.FormMembre;
import com.ritndev.agcv.model.Membre;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IMembreService {
    
    //Methode Membres :
    public int saveMembre(FormMembre newMembre);
    public List<Membre> listMembre();
    public List<Membre> listMembreActif();
    public Membre findByIdMembre(Long id);
    public int supprMembre(Long id);
    public int updateMembre(FormMembre editMembre);

}
