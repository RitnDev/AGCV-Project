package com.ritndev.agcv.InterfaceService;

import com.ritndev.agcv.classes.Reponse;
import com.ritndev.agcv.form.FormUser;
import com.ritndev.agcv.model.AppRole;
import com.ritndev.agcv.model.AppUser;
import java.util.List;

/**
 *
 * @author Ritn
 */
public interface IUserService {
    
    //Methode AppUser :
    public Reponse saveUser(FormUser newUser);
    public List<AppUser> listUser();
    public AppUser findByIdUser(Long id);
    public AppUser findByUsernameUser(String User);
    public Reponse supprUser(Long id);
    public Reponse updateUser(FormUser editUser);
    public Reponse updateMdpUser(FormUser editUser);

    
    //Methode AppRole :
    public List<AppRole> listRole();
    public String findRoleByUsername(String userName);
    public Long findRoleIdByUserId(Long userId);
            
}
