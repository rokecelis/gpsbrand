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
package com.bidxi.gpsbrand.model;

import java.io.Serializable;
import org.springframework.security.core.GrantedAuthority;

/**
 * *****************************************************************************
 * @author gerardo.roque 8/10/2014 01:03:44 PM cima main.css
 * Description:
 * ******************************************************************************
 */
public class Rol implements GrantedAuthority, Serializable
{

    private Integer id;
    private String rol;
    private String description;
    private Integer active;

    public Rol()
    {
        super();
    }

    public Rol(String rol)
    {
        super();
        this.rol = rol;
    }

    @Override
    public String getAuthority()
    {
        return this.rol;
    }

    /**
     * @return the rol
     */
    public String getRol()
    {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol)
    {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol))
        {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rol.equals(other.rol)))
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 29 * hash + (this.rol != null ? this.rol.hashCode() : 0);
        return hash;
    }

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the active
     */
    public Integer getActive()
    {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Integer active)
    {
        this.active = active;
    }
}
