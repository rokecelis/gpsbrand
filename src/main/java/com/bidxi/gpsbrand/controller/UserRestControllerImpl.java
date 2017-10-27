/*
 *******************************************************************************
 * Copyright (C) 2015 gerardo.roque.
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

import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import com.bidxi.gpsbrand.model.User;
import javax.ws.rs.core.Response.ResponseBuilder;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gerardo.roque
 */
@Component
@Path("/cataloguser")
public class UserRestControllerImpl implements UserRestController
{

    @Autowired
    private UserService userService;

    @Override
    public List<User> list() throws ServiceException
    {
        List<User> users = this.userService.getListUsers();
        return users;
    }

    @Override
    public String endPoint(MultivaluedMap<String, String> params, @Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException
    {
        return null;
    }

    @Override
    public Response getListUserReport() throws ServiceException
    {
        InputStream report = this.userService.getUserListReport();
        ResponseBuilder response = Response.ok((Object) report);
        response.header("Content-Disposition", "attachment; filename=reporte-usuarios.pdf");
        return response.build();
    }

    @Override
    public Response createTrackInJSON(User user)
    {

        String result = "Track saved : " + user;
        return Response.status(201).entity(result).build();

    }

    @Override
    public User read(@PathParam("id") Integer id) throws ServiceException
    {
        User user = new User();
        user.setId(id);
        user = this.userService.getUserById(user);
        return user;
    }

    @Override
    public Response usersByCriteria(MultivaluedMap<String, String> params, @Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException
    {
        List<User> users = this.userService.getListUsers();
        return Response.ok(users).build();
    }

    @Override
    public User create(User newsEntry)
    {

        return new User();
    }

    @Override
    public User update(@PathParam("id") Long id, User newsEntry)
    {

        return new User();
    }

    @Override
    public void delete(@PathParam("id") Long id)
    {

    }

    @Override
    public Response createfile() throws ServiceException
    {
        return Response.status(201).entity("ok").build();
    }

}
