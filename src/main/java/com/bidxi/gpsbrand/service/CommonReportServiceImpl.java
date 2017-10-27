package com.bidxi.gpsbrand.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import com.bidxi.gpsbrand.exception.ServiceException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleRtfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

@Service
public class CommonReportServiceImpl implements CommonReportService
{

    private final Logger log = Logger.getLogger(CommonReportServiceImpl.class);

    /**
     * El contentType para los reportes en pdf
     *
     * @param arrDatos
     * @param res
     * @param content
     * @param nomArchivo
     * @throws com.bidxi.gpsbrand.exception.ServiceException
     */
    @Override
    public void descargaPdf(byte[] arrDatos, HttpServletResponse res, String content, String nomArchivo) throws ServiceException
    {

        try (OutputStream outputStream = res.getOutputStream())
        {
            res.setContentType(content);
            res.setContentLength(arrDatos.length);
            //res.setHeader("Content-Disposition", "attachment; filename=" + nomArchivo);
            outputStream.write(arrDatos, 0, arrDatos.length);
            outputStream.flush();
        } catch (IOException ex)
        {
            this.log.error("Ocurrió un error en descargaPdf", ex);
        }
    }

    /**
     * Content type para los reportes en excel
     *
     * @param jasperReport
     * @param parms
     * @param dataSource
     * @param res
     * @param nomArchivo
     * @throws ServiceException
     */
    @Override
    public void descargaXls(JasperReport jasperReport, HashMap parms, JRBeanCollectionDataSource dataSource, HttpServletResponse res, String nomArchivo) throws ServiceException
    {
        try (ServletOutputStream out = res.getOutputStream())
        {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parms, dataSource);
            JRXlsExporter exp = new JRXlsExporter();
            exp.setExporterInput(new SimpleExporterInput(print));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            exp.setConfiguration(configuration);
            exp.exportReport();
            res.setContentType("application/vnd.ms-excel");
            res.setHeader("Content-Disposition", "attachment; filename=" + nomArchivo);
            byte[] bytes = os.toByteArray();
            out.write(bytes, 0, bytes.length);
            out.flush();
        } catch (IOException | JRException ex)
        {
            this.log.error("Ocurrió un error en descargaXls:", ex);
        }
    }

    /**
     * Exportar la plantilla a pdf
     *
     * @param flusByte
     * @param jRXlsExporter
     * @param res
     * @param nomArchivo
     * @throws ServiceException
     */
    @Override
    public void downloadReportOnXlsFormat(byte[] flusByte, JRXlsExporter jRXlsExporter, HttpServletResponse res, String nomArchivo) throws ServiceException
    {
        try (ServletOutputStream out = res.getOutputStream())
        {
            res.setContentType("application/vnd.ms-excel");
            res.setHeader("Content-Disposition", "attachment; filename=" + nomArchivo);
            out.write(flusByte, 0, flusByte.length);
            out.flush();
        } catch (IOException ex)
        {
            this.log.error("Ocurrió un error en downloadReportOnXlsFormat:", ex);
        }
    }

    @Override
    public void downloadXlsWithJsfServlet(byte[] flusByte, JRXlsExporter jRXlsExporter, HttpServletResponse res, String nomArchivo) throws ServiceException
    {
        try (ServletOutputStream out = res.getOutputStream())
        {
            res.setContentType("application/vnd.ms-excel");
            res.setHeader("Content-Disposition", "attachment; filename=" + nomArchivo);
            out.write(flusByte, 0, flusByte.length);
            out.flush();
        } catch (IOException ex)
        {
            this.log.error("Ocurrió un error en downloadXlsWithJsfServlet:", ex);
        }
    }

    /**
     * Content type para los reportes en rtf
     *
     * @param jasperReport
     * @param parms
     * @param dataSource
     * @return
     * @throws ServiceException
     */
    @Override
    public byte[] runReportToRtf(JasperReport jasperReport, HashMap parms, JRBeanCollectionDataSource dataSource) throws ServiceException
    {
        ByteArrayOutputStream os = null;
        try
        {
            os = new ByteArrayOutputStream();
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parms, dataSource);
            JRRtfExporter exp = new JRRtfExporter();
            exp.setExporterInput(new SimpleExporterInput(print));
            exp.setExporterOutput(new SimpleXmlExporterOutput(os));
            SimpleRtfReportConfiguration configuration = new SimpleRtfReportConfiguration();
            exp.setConfiguration(configuration);
            exp.exportReport();
        } catch (JRException ex)
        {
            this.log.error("Ocurrió un error en runReportToRtf:", ex);
        }
        finally
        {
            try
            {
                os.close();
            } catch (IOException ex)
            {
                this.log.error("Ocurrió un error al cerrar la conexión", ex);
            }
        }
        return os.toByteArray();

    }

    @Override
    public void reportToXls(List<Map<String, Object>> out, String pathFile, String nameFile) throws ServiceException
    {
        int rownum = 0;
        int cellnum = 0;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(nameFile);
        //List<Map<String,Object>> out =	reportesDao.listaSugerenciaLibrosTexto(1);
        Row row = sheet.createRow(rownum++);
        if (!out.isEmpty())
        {
            for (String key : out.get(0).keySet())
            { //Headers
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(key);
            }
            for (Map<String, Object> map : out)
            { //Values
                cellnum = 0;
                row = sheet.createRow(rownum++);
                for (String key : out.get(0).keySet())
                {
                    Cell cell = row.createCell(cellnum++);
                    Object obj = null != map.get(key) ? map.get(key) : "";
                    cell.setCellValue(obj.toString());
                }
            }
            try
            {
                File file = new File(pathFile + "/" + nameFile + ".xls");
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileOutputStream excel = new FileOutputStream(file);
                workbook.write(excel);
                excel.close();
                System.out.println("Excel written successfully..");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        } else
        {
            System.out.println("Excel no written successfully..");
        }
    }
}
