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
import java.util.HashMap;
import java.util.List;
import com.bidxi.gpsbrand.exception.PersistenceException;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.model.User;
import com.bidxi.gpsbrand.persistence.UserMapper;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * *****************************************************************************
 * @author gerardo.roque 16/10/2014 05:36:22 PM cima UserServiceImpl.java
 * Description:
 * *****************************************************************************
 */
@Service
public class UserServiceImpl extends AbstractReportServiceImpl implements UserService
{

    @Autowired
    private UserMapper userMapper;
    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public User getUserByUsername(String user) throws ServiceException
    {
        User userDao = null;
        try
        {
            userDao = this.userMapper.getUserByUsername(user);
        } catch (PersistenceException ex)
        {
            this.log.error("Ha ocurrido un error en AbstractReportServiceImpl.getPdfFromByte", ex);
        }
        return userDao;
    }

    @Override
    public User getUserById(User user) throws ServiceException
    {
        User userDao = null;
        try
        {
            userDao = this.userMapper.getUserById(user);
        } catch (PersistenceException ex)
        {
            this.log.error("Ha ocurrido un error en AbstractReportServiceImpl.getPdfFromByte", ex);
        }
        return userDao;
    }

    @Override
    public List<User> getListUsers() throws ServiceException
    {
        List<User> usersDao = null;
        try
        {
            usersDao = this.userMapper.getListUsers();
        } catch (PersistenceException ex)
        {
            this.log.error("Ha ocurrido un error en AbstractReportServiceImpl.getPdfFromByte", ex);
        }
        return usersDao;
    }

    @Override
    public void insertUser(User user) throws ServiceException
    {
        try
        {
            this.userMapper.insertUser(user);
        } catch (PersistenceException ex)
        {
            this.log.error("Ha ocurrido un error en AbstractReportServiceImpl.getPdfFromByte", ex);
        }
    }

    /**
     * Lista de usuarios
     *
     * @return
     * @throws com.bidxi.gpsbrand.exception.ServiceException
     */
    @Override
    public InputStream getUserListReport() throws ServiceException
    {
        InputStream inputStream = null;
        try
        {
            List<User> keysPedidoNacional = this.userMapper.getListUsers();
            HashMap<String, Object> parametersMap = new HashMap<>();//los parametros
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(keysPedidoNacional);//se crea la coleccion
            inputStream = super.getPdfStream("reporte-usuarios.jrxml", parametersMap, dataSourceCollection);

        } catch (PersistenceException ex)
        {
            this.log.error("Ha ocurrido un error en AbstractReportServiceImpl.getUserListReport", ex);
        }
        return inputStream;
    }
}
