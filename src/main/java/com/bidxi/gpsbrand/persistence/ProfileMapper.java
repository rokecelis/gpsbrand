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
 *******************************************************************************
 */
package com.bidxi.gpsbrand.persistence;

import java.util.List;
import com.bidxi.gpsbrand.exception.PersistenceException;
import com.bidxi.gpsbrand.model.Rol;
import com.bidxi.gpsbrand.model.User;

/**
 * *****************************************************************************
 * @author gerardo.roque 15/10/2014 05:17:43 PM cima PerfilMapper.java
 * Description:
 * *****************************************************************************
 */
public interface ProfileMapper
{

    public List<Rol> getProfileFromUser(User user) throws PersistenceException;
}
