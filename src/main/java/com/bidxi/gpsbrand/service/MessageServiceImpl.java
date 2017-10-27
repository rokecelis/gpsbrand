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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * *****************************************************************************
 * @author gerardo.roque 8/10/2014 01:03:44 PM cima
 * MessageServiceIml.java Description:
 * ******************************************************************************
 */
@Service
public class MessageServiceImpl implements MessageService
{

    private final Logger log = Logger.getLogger(MessageServiceImpl.class);

    @Override
    public void addInfo(String title, String description)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addError(String title, String description)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addWarn(String title, String description)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addFatal(String title, String description)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
