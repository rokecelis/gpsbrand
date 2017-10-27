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

import org.springframework.security.core.userdetails.UserDetails;

/**
 * *****************************************************************************
 * @author gerardo.roque 8/10/2014 01:03:44 PM cima AutenticacionService.java
 * Description:
 * ******************************************************************************
 */
public interface AutenticacionService
{

    public UserDetails loadUserByUsername(String correo);

    public void removeSessionUser();
}
