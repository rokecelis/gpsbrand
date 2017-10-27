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
package com.bidxi.gpsbrand.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidxi.gpsbrand.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * *****************************************************************************
 * @author gerardo.roque 8/10/2014 01:03:44 PM cima AuthenticationHandler.java
 * Description:
 * ******************************************************************************
 */
public class AuthenticationHandler extends SimpleUrlAuthenticationFailureHandler
{

    @Autowired
    MessageService messageService;

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth) throws ServletException, IOException
    {
        this.messageService.addWarn("Aviso:", "Usuario o clave incorrectos");
    }
}
