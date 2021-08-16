package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormCompet;
import com.ritndev.agcv.model.Competition;

/**
 *
 * @author Ritn
 */
public interface ICompetitionService {
    
    //Methode Competition
    public Reponse saveCompetition(FormCompet newCompet);
    public Competition findByIdCompetition(Long id);
    public Reponse supprCompetition(Long id);
    public Reponse updateCompetition(FormCompet editCompet);
    
}
