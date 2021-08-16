package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormTypeVolant;
import com.ritndev.agcv.model.TypeVolant;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface ITypeVolantService {
    
    //Methode TypeVolant
    public Reponse saveTypeVolant(FormTypeVolant newTypeVolant);
    public List<TypeVolant> listTypeVolant();
    public TypeVolant findByIdTypeVolant(Long id);
    public Reponse supprTypeVolant(Long id);
    public Reponse updateTypeVolant(FormTypeVolant editTypeVolant);
    
}
