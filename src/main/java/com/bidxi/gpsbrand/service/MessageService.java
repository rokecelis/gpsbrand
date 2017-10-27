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

/*******************************************************************************
 * @author gerardo.roque  8/10/2014 01:03:44 PM  
 * cima  MessageService.java   
 * Description: 
 *******************************************************************************
 */
public interface MessageService
{

    /**
     * Mensaje informativo
     *
     * @param title
     * @param description
     */
    public void addInfo(String title, String description);

    /**
     * Mensaje de error
     *
     * @param title
     * @param description
     */
    public void addError(String title, String description);

    /**
     * Mensajes de advertencia
     *
     * @param title
     * @param description
     */
    public void addWarn(String title, String description);

    /**
     *
     * @param title
     * @param description
     */
    public void addFatal(String title, String description);
}
