package com.ritndev.agcv.services;

import com.ritndev.agcv.form.FormProfil;
import com.ritndev.agcv.model.*;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IProfilService {
    
    //Methode Profil :
    public int saveProfil(FormProfil newProfil);
    public List<Profil> listProfil();
    public Profil findByIdProfil(Long id);
    public int supprProfil(Long id);
    public int updateProfil(FormProfil editProfil);
    
}
