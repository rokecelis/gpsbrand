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
import java.util.Map;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.model.Rol;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * *****************************************************************************
 * @author gerardo.roque 8/10/2014 01:03:44 PM controlpanel SessionService.java
 * Description:
 * ******************************************************************************
 */
public interface SessionService
{

    public void createSession(String nameSession, Object objectSession) throws ServiceException;

    public Object getSessionFromContext(String nameSession) throws ServiceException;

    public void removeObjectFromSession(String nameSession) throws ServiceException;

    public Map<String, Boolean> createRoleMap(UserDetails userDetails) throws ServiceException;

    public Map<String, Boolean> createRoleMap(List<Rol> roles) throws ServiceException;

}
