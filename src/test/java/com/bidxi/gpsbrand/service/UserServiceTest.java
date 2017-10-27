/*
*******************************************************************************
 * Copyright (C) 2016 gerardo.roque.
 *
 * The SOFTWARE PRODUCT is protected by copyright laws and international 
 * copyright treaties, as well as other intellectual property laws and treaties. 
 * The SOFTWARE PRODUCT is license, you may not copy, modify, sublicense, link 
 * with, or distribute the Library except as expressly provided under this 
 * License.
 * verifica. 
 * México D.F.
*******************************************************************************
 */
package com.bidxi.gpsbrand.service;

import java.util.List;
import com.bidxi.gpsbrand.exception.PersistenceException;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 *
 * @author gerardo.roque
 */
@ContextConfiguration(locations =
{
    "classpath:com/bidxi/gpsbrand/spring/applicationContext-main.xml",
    "classpath:com/bidxi/gpsbrand/spring/applicationContext-persistence.xml",
    "classpath:com/bidxi/gpsbrand/spring/applicationContext-security.xml"
})

public class UserServiceTest extends AbstractTestNGSpringContextTests
{

    @Autowired
    private UserService userService;

    @Test(enabled = true)
    public void listUsers() throws ServiceException, PersistenceException
    {
        List<User> users = this.userService.getListUsers();
        assert !users.isEmpty();
        System.out.println("->" + users.toString());
    }

}
