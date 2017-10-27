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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.model.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * *****************************************************************************
 * @author gerardo.roque 8/10/2014 01:03:44 PM controlpanel
 * SessionServiceImpl.java Description:
 * ******************************************************************************
 */
@Service
public class SessionServiceImpl implements SessionService
{

    @Override
    public void createSession(String nameSession, Object objectSession) throws ServiceException
    {
        SessionServiceImpl.session().setAttribute(nameSession, objectSession);
    }

    @Override
    public Object getSessionFromContext(String nameSession) throws ServiceException
    {
        Object session = SessionServiceImpl.session().getAttribute(nameSession);
        return session;

    }

    @Override
    public void removeObjectFromSession(String nameSession) throws ServiceException
    {
        SessionServiceImpl.session().removeAttribute(nameSession);
    }

    /**
     * Obtiene la session
     * @return 
     */
    public static HttpSession session()
    {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
    
    public static ServletContext getServletContext()
    {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true).getServletContext();
    }
   /**
    * Get Request
    * @return 
    */
    public static HttpServletRequest getServletRequest()
    {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }
    /**
     * Get Response
     * @return 
     */
    public static HttpServletResponse getServletResponse()
    {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }

    /**
     * Los roles del usuario
     *
     * @param userDetails
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Boolean> createRoleMap(UserDetails userDetails) throws ServiceException
    {
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        for (GrantedAuthority authority : userDetails.getAuthorities())
        {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }
        return roles;
    }

    /**
     * La lista de roles de la sesion
     * @param rols
     * @return
     * @throws ServiceException 
     */
    @Override
    public Map<String, Boolean> createRoleMap(List<Rol> rols) throws ServiceException
    {
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        for (Rol role : rols)
        {
            roles.put(role.getAuthority(), Boolean.FALSE);
        }
        return roles;
    }

}
