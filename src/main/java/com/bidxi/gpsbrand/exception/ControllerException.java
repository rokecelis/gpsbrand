/*
********************************************************************************
 * Copyright (C) 2016 gerardo.roque.
 *
 * The SOFTWARE PRODUCT is protected by copyright laws and international 
 * copyright treaties, as well as other intellectual property laws and treaties. 
 * The SOFTWARE PRODUCT is license, you may not copy, modify, sublicense, link 
 * with, or distribute the Library except as expressly provided under this 
 * License.
 * verifica. 
 * México D.F.
********************************************************************************
 */
package com.bidxi.gpsbrand.exception;

/**
 *
 * @author gerardo.roque
 */
public class ControllerException extends Exception
{

    private final String message;

    public ControllerException(String message, Throwable cause)
    {
        this.message = message;

    }

    /**
     * @return the message
     */
    @Override
    public String getMessage()
    {
        return message;
    }

}
