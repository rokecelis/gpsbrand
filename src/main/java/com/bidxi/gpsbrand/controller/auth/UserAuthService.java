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
package com.bidxi.gpsbrand.controller.auth;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author gerardo.roque
 */
public interface UserAuthService
{
    /**
     * El usuario actualmente loggeao
     *
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserTransfer getUser() throws Exception;

    /**
     * Autentica al usuario y proprociona el token
     *
     * @param username
     * @param password
     * @return
     */
    @Path("authenticate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public TokenTransfer authenticate(@FormParam("username") String username, @FormParam("password") String password);
}
