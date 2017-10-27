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
package com.bidxi.gpsbrand.service;

import java.io.InputStream;
import java.util.List;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.model.User;

/**
 * *****************************************************************************
 * @author gerardo.roque 16/10/2014 05:36:50 PM cima UserService.java
 * Description:
 * ******************************************************************************
 */
public interface UserService
{

    public User getUserByUsername(String user) throws ServiceException;

    public User getUserById(User user) throws ServiceException;

    public List<User> getListUsers() throws ServiceException;

    public void insertUser(User user) throws ServiceException;

    public InputStream getUserListReport() throws ServiceException;

}
