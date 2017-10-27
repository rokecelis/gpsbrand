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
package com.bidxi.gpsbrand.controller;

import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.model.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author gerardo.roque
 */
public interface UserRestController
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() throws ServiceException;

    @POST
    @Path("/o2")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String endPoint(MultivaluedMap<String, String> params, @Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;

    @GET
    @Path("/reportuser")
    @Produces("application/vnd.ms-excel")
    public Response getListUserReport() throws ServiceException;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(User user);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public User read(@PathParam("id") Integer id) throws ServiceException;

    @POST
    @Path("/usersbycriteria")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersByCriteria(MultivaluedMap<String, String> params, @Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User create(User newsEntry);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public User update(@PathParam("id") Long id, User newsEntry);

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void delete(@PathParam("id") Long id);

    @GET
    @Path("/file")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createfile() throws ServiceException;
}
