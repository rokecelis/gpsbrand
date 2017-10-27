/*
 ********************************************************************************
 Copyright (C) 2014 gerardo.roque.

 The SOFTWARE PRODUCT is protected by copyright laws and international 
 copyright treaties, as well as other intellectual property laws and treaties. 
 The SOFTWARE PRODUCT is license, you may not copy, modify, sublicense, link 
 with, or distribute the Library except as expressly provided under this 
 License.
 verifica. bidxi Corp
 México D.F.
 *******************************************************************************
 */
package com.bidxi.gpsbrand.service;

import java.util.List;
import com.bidxi.gpsbrand.exception.PersistenceException;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.model.Rol;
import com.bidxi.gpsbrand.model.User;
import com.bidxi.gpsbrand.persistence.ProfileMapper;
import com.bidxi.gpsbrand.persistence.UserMapper;
import com.bidxi.gpsbrand.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * *****************************************************************************
 * @author gerardo.roque 8/10/2014 01:03:44 PM cima
 * AutenticacionServiceImpl.java Description:
 * ******************************************************************************
 */
public class AutenticacionServiceImpl implements UserDetailsService, AutenticacionService
{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private MessageService messageService;
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * spring: Realiza el match del usuario, clave introducida con el usuario,
     * clave disponible en BD
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = null;
        try
        {
            user = this.userMapper.getUserByUsername(username);

            if (user != null)//si el usuario existe
            {
                if (user.isAccountNonLocked())
                {
                    List<Rol> roles = this.profileMapper.getProfileFromUser(user);
                    user.getRoles().addAll(roles);
                    this.sessionService.createSession(Constant.USUARIO_SESSION, user);
                    this.sessionService.createSession(Constant.ROLES_SESSION, roles);
                } else
                {
                    this.messageService.addInfo("Usuario Bloqueado", " El usuario con el que intenta autenticarse se encuentra bloqueado");
                }
            }
        } catch (PersistenceException | ServiceException e)
        {
            this.log.error("Ha ocurrido un error en la autenticación", e);
            this.messageService.addError("Error el la autenticación", " Ha ocurrido un error en la autenticación");

        }
        return user;
    }

    /**
     * Eliminar los datos del usuario
     */
    @Override
    public void removeSessionUser()
    {
        try
        {
            this.sessionService.removeObjectFromSession(Constant.USUARIO_SESSION);
        } catch (ServiceException e)
        {
            this.log.error("Ha ocurrido un error al dar de baja la session del usuario", e);
        }
    }

}
