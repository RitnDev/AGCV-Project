package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.form.FormCompet;
import com.ritndev.agcv.model.Competition;

/**
 *
 * @author Ritn
 */
public interface ICompetitionService {
    
    //Methode Competition
    public int saveCompetition(FormCompet newCompet);
    public Competition findByIdCompetition(Long id);
    public int supprCompetition(Long id);
    public int updateCompetition(FormCompet editCompet);
    
}
