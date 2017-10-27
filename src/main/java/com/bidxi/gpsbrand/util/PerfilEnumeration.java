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

/**
 * *****************************************************************************
 * @author gerardo.roque 8/10/2014 01:03:44 PM cima PerfilEnumeration.java
 * Description:
 * ******************************************************************************
 */
public enum PerfilEnumeration
{

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMINISTRADOR");

    private String constante;

    private PerfilEnumeration(String constante)
    {
        this.constante = constante;
    }

    /**
     * @return the constante
     */
    public String getConstante()
    {
        return constante;
    }

    /**
     * @param constante the constante to set
     */
    public void setConstante(String constante)
    {
        this.constante = constante;
    }
}
