
package com.bidxi.gpsbrand.model;

import java.util.HashMap;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author bidxi
 */
public class XslStreamSheet {
    
    private String name;
    private String reportXmlSource;
    private HashMap<String, Object> parametersMap;
    private JRDataSource dataSource;

    public XslStreamSheet() {
    }

    /**
     * @return the reportXmlSource
     */
    public String getReportXmlSource() {
        return reportXmlSource;
    }

    /**
     * @param reportXmlSource the reportXmlSource to set
     */
    public void setReportXmlSource(String reportXmlSource) {
        this.reportXmlSource = reportXmlSource;
    }

    /**
     * @return the parametersMap
     */
    public HashMap<String, Object> getParametersMap() {
        return parametersMap;
    }

    /**
     * @param parametersMap the parametersMap to set
     */
    public void setParametersMap(HashMap<String, Object> parametersMap) {
        this.parametersMap = parametersMap;
    }

    /**
     * @return the dataSource
     */
    public JRDataSource getDataSource() {
        return dataSource;
    }

    /**
     * @param dataSource the dataSource to set
     */
    public void setDataSource(JRDataSource dataSource) {
        this.dataSource = dataSource;
    }        

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
