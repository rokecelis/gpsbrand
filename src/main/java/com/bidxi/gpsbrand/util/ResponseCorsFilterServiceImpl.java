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
package com.bidxi.gpsbrand.util;

import javax.ws.rs.core.Response;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 *
 * @author gerardo.roque
 */
public class ResponseCorsFilterServiceImpl implements ContainerResponseFilter
{

    @Override
    public ContainerResponse filter(ContainerRequest req, ContainerResponse contResp)
    {
        Response.ResponseBuilder resp = Response.fromResponse((Response) contResp.getResponse());
        resp.header("Access-Control-Allow-Origin", (Object) "*").header("Access-Control-Allow-Methods", (Object) "PI, CRUNCHIFYGET, GET, POST, PUT, UPDATE, OPTIONS, DELETE, HEAD");
        String reqHead = req.getHeaderValue("Access-Control-Request-Headers");
        if (null != reqHead && !reqHead.equals(""))
        {
            resp.header("Access-Control-Allow-Headers", (Object) reqHead);
        }
        contResp.setResponse(resp.build());
        return contResp;
    }
}
