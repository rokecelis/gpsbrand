/**
 * Copyright (C) 2013 roque
 *
 * The SOFTWARE PRODUCT includes entitlement to limited technical support services  
 * support services
 * SOFTWARE PRODUCT will perform substantially in accordance with 
 * the accompanying written materials.
 */
package com.bidxi.gpsbrand.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.model.XslStreamSheet;
import com.bidxi.gpsbrand.util.Constant;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleCsvReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/* *****************************************************************************
 * @author grc  07-ago-2013 14:10:10  Description: 
 * AbstractReportServiceImpl.java  
 * *****************************************************************************
 */
public abstract class AbstractReportServiceImpl<E extends Serializable> {
    private final Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private CommonReportService commonReportService;

    /**
     * El reporte en pdf
     *
     * @param reportXmlSource
     *
     * @param parametersMap
     * @param dataSource
     * @param outputFileName
     * @throws com.bidxi.gpsbrand.exception.ServiceException
     */
    public void getPdfFromByte(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource, String outputFileName) throws ServiceException {

        try {
            HttpServletResponse servletResponse = SessionServiceImpl.getServletResponse();
            ServletContext servletContext = SessionServiceImpl.getServletContext();
            InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSource);//la impresion del reporte
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes

            commonReportService.descargaPdf(reportePdf, servletResponse, Constant.MEDIATYPE_APPLICATION_PDF, outputFileName);

        } catch (JRException ex) {
            LOG.error("--> Error: getPdfFromByte(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource, String outputFileName)", ex);
        }
    }

    /**
     * Obtiene el reporte en formato de excel
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @param outputFileName
     * @throws ServiceException
     */
    public void getXlsFromByte(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource, String outputFileName) throws ServiceException {

        try {
            HttpServletResponse servletResponse = SessionServiceImpl.getServletResponse();
            ServletContext servletContext = SessionServiceImpl.getServletContext();
            InputStream logo = servletContext.getResourceAsStream(Constant.REPOSITORY_LOG_DEFAULT_PATH);//el archivo de diseno
            parametersMap.put("logo", logo);
            InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseño   
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSource);
            JRXlsExporter exp = new JRXlsExporter();
            exp.setExporterInput(new SimpleExporterInput(print));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            exp.setConfiguration(configuration);
            exp.exportReport();
            byte[] bytes = os.toByteArray();

            commonReportService.downloadXlsWithJsfServlet(bytes, exp, servletResponse, outputFileName);

        } catch (JRException ex) {
            LOG.error("--> Error: void getXlsFromByte(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource, String outputFileName)", ex);
        }
    }

    /**
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @return
     * @throws ServiceException
     */
    public InputStream getXlsStream(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource) throws ServiceException {
        return getExporterIS(reportXmlSource, parametersMap, dataSource, 1);
    }

    /**
     * Exportar archivos a xlsx
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @return
     * @throws ServiceException
     */
    public InputStream getXlsxStream(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource) throws ServiceException {
        return getExporterIS(reportXmlSource, parametersMap, dataSource, 2);
    }

    /**
     * Engine: exporta documentos a csv
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @return
     * @throws ServiceException
     */
    public InputStream getCvsStream(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource) throws ServiceException {
        return getExporterIS(reportXmlSource, parametersMap, dataSource, 3);
    }

    /**
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @return
     * @throws ServiceException
     */
    public InputStream getPdfStream(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource) throws ServiceException {
        return getExporterIS(reportXmlSource, parametersMap, dataSource, 4);
    }

    /**
     *
     * @param print
     * @param os
     * @return 
     */
    public JRXlsExporter generateExporterXls(JasperPrint print, ByteArrayOutputStream os) {
        JRXlsExporter expXls = new JRXlsExporter();

        expXls.setExporterInput(new SimpleExporterInput(print));
        expXls.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
        expXls.setConfiguration(new SimpleXlsReportConfiguration());

        return expXls;
    }

    /**
     *
     * @param print
     * @param os
     * @return
     */
    public JRXlsxExporter generateExporterXlsx(JasperPrint print, ByteArrayOutputStream os) {
        JRXlsxExporter expXlsx = new JRXlsxExporter();

        expXlsx.setExporterInput(new SimpleExporterInput(print));
        expXlsx.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
        expXlsx.setConfiguration(new SimpleXlsxReportConfiguration());

        return expXlsx;
    }

    /**
     *
     * @param print
     * @param os
     * @return
     */
    public JRCsvExporter generateExporterCsv(JasperPrint print, ByteArrayOutputStream os) {
        JRCsvExporter expCsv = new JRCsvExporter();

        expCsv.setExporterInput(new SimpleExporterInput(print));
        expCsv.setExporterOutput(new SimpleWriterExporterOutput(os));
        expCsv.setConfiguration(new SimpleCsvReportConfiguration());

        return expCsv;
    }

    /**
     *
     * @param print
     * @param os
     * @return
     */
    public JRPdfExporter generateExporterPdf(JasperPrint print, ByteArrayOutputStream os) {
        JRPdfExporter expPdf = new JRPdfExporter();

        expPdf.setExporterInput(new SimpleExporterInput(print));
        expPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
        expPdf.setConfiguration(new SimplePdfReportConfiguration());

        return expPdf;
    }

    /**
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @param option
     * @return
     * @throws ServiceException
     */
    public InputStream getExporterIS(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource, Integer option) throws ServiceException {
        InputStream inputStream = null;
        String methodError = "";

        try {
            ServletContext servletContext = SessionServiceImpl.getServletContext();
            InputStream logo = servletContext.getResourceAsStream(Constant.REPOSITORY_LOG_DEFAULT_PATH);//el archivo de diseno
            parametersMap.put("logo", logo);
            InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReport(jasperDesign), parametersMap, dataSource);

            switch (option) {
                case 1:
                    generateExporterXls(print, os).exportReport();
                    break;
                case 2:
                    generateExporterXlsx(print, os).exportReport();
                    break;
                case 3:
                    generateExporterCsv(print, os).exportReport();
                    break;
                case 4:
                    generateExporterPdf(print, os).exportReport();
                    break;
                default:
                    generateExporterXlsx(print, os).exportReport();
            }

            inputStream = new ByteArrayInputStream(os.toByteArray());

        } catch (JRException ex) {
            switch (option) {
                case 1:
                    methodError = "getXlsStream : getXlsStream";
                    break;
                case 2:
                    methodError = "getXlsxStream : getXlsxStream";
                    break;
                case 3:
                    methodError = "getCvsStream : getCvsStream";
                    break;
                case 4:
                    methodError = "getPdfStream : getPdfStream";
                    break;
                default:
                    methodError = "AbstractReportServiceImpl";
            }

            LOG.error("--> Error: InputStream getExporterIS(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource, " + methodError + ")", ex);
        }

        return inputStream;
    }

    /**
     * Obtiene el archivo de template indicado.
     *
     * @param reportXmlSource
     * @param parametersMap
     * @return
     * @throws ServiceException
     */
    public InputStream getXlsStream(String reportXmlSource, Map<String, Object> parametersMap) throws ServiceException {
        InputStream inputStream = null;

        try {
            ServletContext servletContext = SessionServiceImpl.getServletContext();
            InputStream logo = servletContext.getResourceAsStream(Constant.REPOSITORY_LOG_DEFAULT_PATH);//el archivo de diseno
            parametersMap.put("logo", logo);
            InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseño

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            JasperPrint print = JRPrintXmlLoader.load(reportXmlSource);
            JRXlsExporter exp = new JRXlsExporter();
            exp.setExporterInput(new SimpleExporterInput(print));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            exp.setConfiguration(configuration);
            exp.exportReport();

            inputStream = new ByteArrayInputStream(os.toByteArray());

        } catch (JRException ex) {
            LOG.error("--> Error: InputStream getXlsStream(String reportXmlSource, Map<String, Object> parametersMap)", ex);
        }

        return inputStream;
    }

    /**
     * Exportar a hoja de excel
     *
     * @param streams
     * @return
     * @throws ServiceException
     */
    public InputStream getXslStreamSheets(List<XslStreamSheet> streams) throws ServiceException {
        InputStream inputStream = null;

        try {
            List<JasperPrint> prints = new ArrayList<>();
            ServletContext servletContext = SessionServiceImpl.getServletContext();
            InputStream logo = servletContext.getResourceAsStream(Constant.REPOSITORY_LOG_DEFAULT_PATH);//el archivo de diseno

            for (XslStreamSheet sheet : streams) {
                sheet.getParametersMap().put("logo", logo);
                InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + sheet.getReportXmlSource());//el archivo de diseno
                JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
                JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseño
                JasperPrint print = JasperFillManager.fillReport(reportPath, sheet.getParametersMap(), sheet.getDataSource());

                print.setName(sheet.getName());
                prints.add(print);
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            JRXlsExporter exporter = new JRXlsExporter();
            ExporterInput exporterInput = SimpleExporterInput.getInstance(prints);
            exporter.setExporterInput(exporterInput);
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            exporter.exportReport();

            inputStream = new ByteArrayInputStream(os.toByteArray());

        } catch (JRException ex) {
            LOG.error("--> Error: InputStream getXslStreamSheets(List<XslStreamSheet> streams)", ex);
        }

        return inputStream;
    }
//FIXME:
    /**
     * Export Sheet Book
     *
     * @param streams
     * @return
     * @throws ServiceException
     */
    public InputStream getXlsxStreamSheets(List<XslStreamSheet> streams) throws ServiceException {
        InputStream inputStream = null;

        try {
            List<JasperPrint> prints = new ArrayList<>();
            ServletContext servletContext = SessionServiceImpl.getServletContext();
            InputStream logo = servletContext.getResourceAsStream(Constant.REPOSITORY_LOG_DEFAULT_PATH);

            for (XslStreamSheet sheet : streams) {
                sheet.getParametersMap().put("logo", logo);

                InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + sheet.getReportXmlSource());
                JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);
                JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint print = JasperFillManager.fillReport(reportPath, sheet.getParametersMap(), sheet.getDataSource());

                print.setName(sheet.getName());
                prints.add(print);
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            JRXlsxExporter exporter = new JRXlsxExporter();
            ExporterInput exporterInput = SimpleExporterInput.getInstance(prints);

            exporter.setExporterInput(exporterInput);
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            exporter.exportReport();

            inputStream = new ByteArrayInputStream(os.toByteArray());

        } catch (JRException error) {
            LOG.error("--> Error: InputStream getXlsxStreamSheets(List<XslStreamSheet> streams)", error);
        }

        return inputStream;
    }

    /**
     * Escribe el reporte en el contexto de la aplicación
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @param outputFileName
     * @param path
     * @throws ServiceException
     */
    public void writeAsyncReportXlsToFileSystem(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource, String outputFileName, String path) throws ServiceException {

        try {
            InputStream logo = new FileInputStream(path + Constant.REPOSITORY_LOG_DEFAULT_PATH);//el archivo de diseno
            parametersMap.put("logo", logo);
            InputStream xmlResource = new FileInputStream(path + Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseño
            FileOutputStream os = new FileOutputStream(new File(path + Constant.REPOSITORY_GEN_FILES + outputFileName));
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSource);
            JRXlsxExporter exp = new JRXlsxExporter();

            exp.setExporterInput(new SimpleExporterInput(print));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            exp.exportReport();

        } catch (JRException | FileNotFoundException ex) {
            LOG.error("--> Error: void writeAsyncReportXlsToFileSystem(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource, String outputFileName, String path)", ex);
        }
    }

    /**
     * Exporto archivo a Xlsx
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @return
     * @throws ServiceException
     */
    public InputStream eportXlsx(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource) throws ServiceException {
        InputStream inputStream = null;

        try {
            ServletContext servletContext = SessionServiceImpl.getServletContext();
            InputStream logo = servletContext.getResourceAsStream(Constant.REPOSITORY_LOG_DEFAULT_PATH);//el archivo de diseno
            parametersMap.put("logo", logo);
            InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseño

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSource);
            JRXlsxExporter exp = new JRXlsxExporter();

            exp.setExporterInput(new SimpleExporterInput(print));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            exp.exportReport();

            inputStream = new ByteArrayInputStream(os.toByteArray());

        } catch (JRException ex) {
            LOG.error("--> Error: InputStream eportXlsx(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource)", ex);
        }

        return inputStream;
    }

    /**
     *
     * @param streams
     * @param outputFileName
     * @param path
     * @throws ServiceException
     */
    public void writeAsyncReportXlsToFileSystem(List<XslStreamSheet> streams, String outputFileName, String path) throws ServiceException {
        FileOutputStream os = null;
        InputStream logo = null;

        try {
            List<JasperPrint> prints = new ArrayList<>();
            logo = new FileInputStream(path + Constant.REPOSITORY_LOG_DEFAULT_PATH);//el archivo de diseno

            for (XslStreamSheet sheet : streams) {
                sheet.getParametersMap().put("logo", logo);
                InputStream xmlResource = new FileInputStream(path + Constant.REPOSITORY_REPORT_PATH + sheet.getReportXmlSource());//el archivo de diseno
                JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
                JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);//compilacion del xml de diseño
                JasperPrint print = JasperFillManager.fillReport(reportPath, sheet.getParametersMap(), sheet.getDataSource());

                print.setName(sheet.getName());
                prints.add(print);
            }

            os = new FileOutputStream(new File(path + Constant.REPOSITORY_GEN_FILES + outputFileName));
            JRXlsExporter exp = new JRXlsExporter();
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            exp.setConfiguration(configuration);
            exp.exportReport();
            os.close();
            logo.close();

        } catch (JRException | FileNotFoundException ex) {
            LOG.error("Ha ocurrido un error en AbstractReportServiceImpl.writeAsyncReportXlsToFileSystem:", ex);
        } catch (IOException ex) {
            LOG.error("Ha ocurrido un error al cerrar el recurso", ex);
        } finally {
            try {
                os.close();
                logo.close();
            } catch (IOException ex) {
                LOG.error("Ha ocurrido un error al cerrar el recurso->", ex);
            }
        }
    }

    /**
     * Escribe el reporte en el contexto de la aplicación
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @param outputFileName
     * @param path
     * @throws ServiceException
     */
    public void writeReportXlsToFileSystem(String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource, String outputFileName, String path) throws ServiceException {

        try {
            ServletContext servletContext = SessionServiceImpl.getServletContext();
            InputStream logo = servletContext.getResourceAsStream(Constant.REPOSITORY_LOG_DEFAULT_PATH);//el archivo de diseno
            parametersMap.put("logo", logo);
            InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseño
            FileOutputStream os = new FileOutputStream(new File(servletContext.getRealPath(path) + Constant.REPOSITORY_GEN_FILES + outputFileName));
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSource);
            JRXlsExporter exp = new JRXlsExporter();
            exp.setExporterInput(new SimpleExporterInput(print));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            exp.setConfiguration(configuration);
            exp.exportReport();

        } catch (JRException | FileNotFoundException ex) {
            LOG.error("Ha ocurrido un error en void AbstractReportServiceImpl.writeReportXlsToFileSystem", ex);
        }
    }

    /**
     * Descargar reporte con JRBeanCollectionDataSource
     *
     * @param request
     * @param servletResponse
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @param outputFileName
     * @throws ServiceException
     */
    public void getXlsFromByteWithCollection(HttpServletRequest request, HttpServletResponse servletResponse, String reportXmlSource, Map<String, Object> parametersMap, JRBeanCollectionDataSource dataSource, String outputFileName) throws ServiceException {

        try {
            ServletContext servletContext = request.getSession().getServletContext();
            InputStream logo = servletContext.getResourceAsStream(Constant.REPOSITORY_LOG_DEFAULT_PATH);//el archivo de diseno
            parametersMap.put("logo", logo);
            InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseño
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSource);
            JRXlsExporter exp = new JRXlsExporter();
            exp.setExporterInput(new SimpleExporterInput(print));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            exp.setConfiguration(configuration);
            exp.exportReport();
            byte[] bytes = os.toByteArray();

            commonReportService.downloadReportOnXlsFormat(bytes, exp, servletResponse, outputFileName);

        } catch (JRException ex) {
            LOG.error("Ha ocurrido un error en AbstractReportServiceImpl.writeReportXlsToFileSystem", ex);
        }
    }

    /**
     * El pdf como resultado de un servlet
     *
     * @param request
     * @param servletResponse
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @param outputFileName
     * @throws ServiceException
     */
    public void getPdfFromByte(HttpServletRequest request, HttpServletResponse servletResponse, String reportXmlSource, Map<String, Object> parametersMap, JRDataSource dataSource, String outputFileName) throws ServiceException {

        try {
            ServletContext servletContext = request.getSession().getServletContext();
            InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSource);//la impresion del reporte
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes

            commonReportService.descargaPdf(reportePdf, servletResponse, Constant.MEDIATYPE_APPLICATION_PDF, outputFileName);

        } catch (JRException ex) {
            LOG.error("Ha ocurrido un error en AbstractReportServiceImpl.getPdfFromByte", ex);
        }
    }

    /**
     * El reporte en pdf
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param outputFileName
     * @throws ServiceException
     */
    public void getPdfFromByte(String reportXmlSource, Map<String, Object> parametersMap, String outputFileName) throws ServiceException {

        try {
            HttpServletResponse servletResponse = SessionServiceImpl.getServletResponse();
            ServletContext servletContext = SessionServiceImpl.getServletContext();
            InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            List dataSource = new ArrayList();
            dataSource.add("");
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes

            commonReportService.descargaPdf(reportePdf, servletResponse, Constant.MEDIATYPE_APPLICATION_PDF, outputFileName);

        } catch (JRException ex) {
            LOG.error("Ha ocurrido un error en AbstractReportServiceImpl.getPdfFromByte: ", ex);
        }
    }

    /**
     * Reporte en pdf con default path
     *
     * @param reportXmlSource
     * @param parametersMap
     * @param dataSource
     * @param outputFileName
     * @throws ServiceException
     */
    public void getPdfFromByte(String reportXmlSource, Map<String, Object> parametersMap, List<E> dataSource, String outputFileName) throws ServiceException {

        try {
            HttpServletResponse servletResponse = SessionServiceImpl.getServletResponse();
            ServletContext servletContext = SessionServiceImpl.getServletContext();
            InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno
            JRBeanCollectionDataSource dataSourceCollection = new JRBeanCollectionDataSource(dataSource);//se crea la coleccion
            JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
            JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);// compilacion del xml de diseno
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametersMap, dataSourceCollection);//la impresion del reporte
            byte[] reportePdf = JasperExportManager.exportReportToPdf(print);//se crea el flujo de bytes

            commonReportService.descargaPdf(reportePdf, servletResponse, Constant.MEDIATYPE_APPLICATION_PDF, outputFileName);

        } catch (JRException ex) {
            LOG.error("Ha ocurrido un error en AbstractReportServiceImpl.getPdfFromByte ->", ex);
        }
    }

    /**
     * Construye el subreporte
     *
     * @param reportXmlSource
     * @return
     * @throws Exception
     */
    public JasperReport getSubreport(String reportXmlSource) throws Exception {
        ServletContext servletContext = SessionServiceImpl.getServletContext();
        InputStream xmlResource = servletContext.getResourceAsStream(Constant.REPOSITORY_REPORT_PATH + reportXmlSource);//el archivo de diseno        
        JasperDesign jasperDesign = JRXmlLoader.load(xmlResource);//se crea el rescurso
        JasperReport reportPath = JasperCompileManager.compileReport(jasperDesign);//compilacion del xml de diseno

        return reportPath;
    }
}
