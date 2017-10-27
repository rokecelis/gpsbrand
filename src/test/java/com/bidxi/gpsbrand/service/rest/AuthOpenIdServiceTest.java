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
package com.bidxi.gpsbrand.service.rest;

import com.bidxi.gpsbrand.controller.UserRestControllerImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gerardo.roque
 */
public class AuthOpenIdServiceTest
{

    public AuthOpenIdServiceTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of endPoint method, of class AuthOpenIdService.
     */
    @Test
    public void testEndPoint() throws ServiceException, Exception
    {
        System.out.println("endPoint");
        MultivaluedMap<String, String> params = null;
        HttpServletRequest httpRequest = null;
        HttpServletResponse httpResponse = null;
        UserRestControllerImpl instance = new UserRestControllerImpl();
        String expResult = "";
        String result = instance.endPoint(params, httpRequest, httpResponse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endPoint method, of class AuthOpenIdService.
     */
    @Test
    public void testGetCatFamilia() throws ServiceException, Exception
    {
        System.out.println("endPoint");
        MultivaluedMap<String, String> params = null;
        HttpServletRequest httpRequest = null;
        HttpServletResponse httpResponse = null;
        UserRestControllerImpl instance = new UserRestControllerImpl();
        String expResult = "";
        String result = instance.endPoint(params, httpRequest, httpResponse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserInJSON method, of class AuthOpenIdService.
     */
    @Test
    public void testGetUserInJSON()
    {
        System.out.println("getUserInJSON");
        UserRestControllerImpl instance = new UserRestControllerImpl();
        User expResult = null;
//        User result = instance.getUserInJSON();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTrackInJSON method, of class AuthOpenIdService.
     */
    @Test
    public void testCreateTrackInJSON()
    {
        System.out.println("createTrackInJSON");
        User user = null;
        UserRestControllerImpl instance = new UserRestControllerImpl();
        Response expResult = null;
        Response result = instance.createTrackInJSON(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class AuthOpenIdService.
     */
    @Test
    public void testList() throws ServiceException, Exception
    {
        System.out.println("list");
        UserRestControllerImpl instance = new UserRestControllerImpl();
        String expResult = "";
        User result = instance.read(1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class AuthOpenIdService.
     */
    @Test
    public void testRead() throws ServiceException, Exception
    {
        System.out.println("read");
        Integer id = null;
        UserRestControllerImpl instance = new UserRestControllerImpl();
        User expResult = null;
        User result = instance.read(1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class AuthOpenIdService.
     */
    @Test
    public void testCreate()
    {
        System.out.println("create");
        User newsEntry = null;
        UserRestControllerImpl instance = new UserRestControllerImpl();
        User expResult = null;
        User result = instance.create(newsEntry);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class AuthOpenIdService.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Long id = null;
        User newsEntry = null;
        UserRestControllerImpl instance = new UserRestControllerImpl();
        User expResult = null;
        User result = instance.update(id, newsEntry);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class AuthOpenIdService.
     */
    @Test
    public void testDelete()
    {
        System.out.println("delete");
        Long id = null;
        UserRestControllerImpl instance = new UserRestControllerImpl();
        instance.delete(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
