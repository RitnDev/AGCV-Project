package com.ritndev.agcv.services;

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
    public int saveUser(FormUser newUser);
    public List<AppUser> listUser();
    public AppUser findByIdUser(Long id);
    public AppUser findByUsernameUser(String User);
    public int supprUser(Long id);
    public int updateUser(FormUser editUser);
    public int updateMdpUser(FormUser editUser);

    
    //Methode AppRole :
    public List<AppRole> listRole();
    public String findRoleByUsername(String userName);
    public Long findRoleIdByUserId(Long userId);
            
}
