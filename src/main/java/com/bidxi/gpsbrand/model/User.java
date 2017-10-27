/*
 *******************************************************************************
 * Copyright (C) 2014 gerardo.roque.
 *
 * The SOFTWARE PRODUCT is protected by copyright laws and international 
 * copyright treaties, as well as other intellectual property laws and treaties. 
 * The SOFTWARE PRODUCT is license, you may not copy, modify, sublicense, link 
 * with, or distribute the Library except as expressly provided under this 
 * License.
 * verifica. bidxi Corp
 * México D.F.
 ********************************************************************************
 */
package com.bidxi.gpsbrand.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * *****************************************************************************
 * @author gerardo.roque 9/10/2014 07:12:05 PM cima Usuario.java
 * Description: Entidad usuario
 * ******************************************************************************
 */
public class User implements UserDetails, Serializable
{

    private Integer id;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Integer failedAttemptsCounter;
    private Date lastAccess;
    private Date lastChangePassword;
    private List<GrantedAuthority> roles;

    public User()
    {
        this.roles = new ArrayList<GrantedAuthority>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.getRoles();
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled()
    {
        return this.enabled;
    }

    /**
     * @return the roles
     */
    public List<GrantedAuthority> getRoles()
    {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<GrantedAuthority> roles)
    {
        this.roles = roles;
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
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @param accountNonExpired the accountNonExpired to set
     */
    public void setAccountNonExpired(boolean accountNonExpired)
    {
        this.accountNonExpired = accountNonExpired;
    }

    /**
     * @param accountNonLocked the accountNonLocked to set
     */
    public void setAccountNonLocked(boolean accountNonLocked)
    {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * @param credentialsNonExpired the credentialsNonExpired to set
     */
    public void setCredentialsNonExpired(boolean credentialsNonExpired)
    {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * @return the failedAttemptsCounter
     */
    public Integer getFailedAttemptsCounter()
    {
        return failedAttemptsCounter;
    }

    /**
     * @param failedAttemptsCounter the failedAttemptsCounter to set
     */
    public void setFailedAttemptsCounter(Integer failedAttemptsCounter)
    {
        this.failedAttemptsCounter = failedAttemptsCounter;
    }

    /**
     * @return the lastAccess
     */
    public Date getLastAccess()
    {
        return lastAccess;
    }

    /**
     * @param lastAccess the lastAccess to set
     */
    public void setLastAccess(Date lastAccess)
    {
        this.lastAccess = lastAccess;
    }

    /**
     * @return the lastChangePassword
     */
    public Date getLastChangePassword()
    {
        return lastChangePassword;
    }

    /**
     * @param lastChangePassword the lastChangePassword to set
     */
    public void setLastChangePassword(Date lastChangePassword)
    {
        this.lastChangePassword = lastChangePassword;
    }
}
