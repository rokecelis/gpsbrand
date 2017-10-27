
/*
 * Copyright (C) 2013 roque
 *
 * The SOFTWARE PRODUCT includes entitlement to limited technical support services  
 * support services
 * SOFTWARE PRODUCT will perform substantially in accordance with 
 * the accompanying written materials.
 */
package com.bidxi.gpsbrand.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.bidxi.gpsbrand.exception.ServiceException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/* *****************************************************************************
 * @author grc  07-ago-2013 11:06:18  Description: 
 * CommonReportService.java 
 * *****************************************************************************
 */
public interface CommonReportService
{

    public void descargaPdf(byte[] arrDatos, HttpServletResponse res, String content, String nomArchivo) throws ServiceException;

    public void descargaXls(JasperReport jasperReport, HashMap parms, JRBeanCollectionDataSource dataSource, HttpServletResponse res, String nomArchivo) throws ServiceException;

    public byte[] runReportToRtf(JasperReport jasperReport, HashMap parms, JRBeanCollectionDataSource dataSource) throws ServiceException;

    public void downloadReportOnXlsFormat(byte[] flusByte, JRXlsExporter jRXlsExporter, HttpServletResponse res, String nomArchivo) throws ServiceException;

    public void downloadXlsWithJsfServlet(byte[] flusByte, JRXlsExporter jRXlsExporter, HttpServletResponse res, String nomArchivo) throws ServiceException;
    
    public void reportToXls(List<Map<String,Object>> out, String pathFile, String nameFile)throws ServiceException;
}
