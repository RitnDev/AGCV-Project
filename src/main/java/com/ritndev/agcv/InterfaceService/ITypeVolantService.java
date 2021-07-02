package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.form.FormTypeVolant;
import com.ritndev.agcv.model.TypeVolant;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface ITypeVolantService {
    
    //Methode TypeVolant
    public int saveTypeVolant(FormTypeVolant newTypeVolant);
    public List<TypeVolant> listTypeVolant();
    public TypeVolant findByIdTypeVolant(Long id);
    public int supprTypeVolant(Long id);
    public int updateTypeVolant(FormTypeVolant editTypeVolant);
    
}
