/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritndev.agcv.services;

import com.ritndev.agcv.model.*;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IagcvService {
    
    //Methode Membres :
    public Membre saveMembre(Membre newMembre);
    public List<Membre> listMembre();
    public Membre findByIdMembre(Long id);
    public void supprMembre(Membre delMembre);
    public void updateByIdMembre(Long id, Membre editMembre);
      
}
