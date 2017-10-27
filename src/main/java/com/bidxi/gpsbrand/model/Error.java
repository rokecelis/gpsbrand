package com.bidxi.gpsbrand.model;

/**
 *
 * @author gerardo.roque
 */
public class Error
{

    private Integer row;
    private String column;
    private String serverity;
    private String description;

    public Error()
    {
    }

    /**
     * Constructor de clase
     *
     * @param row
     * @param column
     * @param description
     */
    public Error(Integer row, String column, String description)
    {
        this.row = row;
        this.column = column;
        this.description = description;
    }

    /**
     * Constructor de clase
     *
     * @param row
     * @param column
     * @param severity
     * @param description
     */
    public Error(Integer row, String column, String severity, String description)
    {

        this.row = row;
        this.column = column;
        this.serverity = severity;
        this.description = description;
    }

    /**
     * @return the row
     */
    public Integer getRow()
    {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(Integer row)
    {
        this.row = row;
    }

    /**
     * @return the column
     */
    public String getColumn()
    {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(String column)
    {
        this.column = column;
    }

    /**
     * @return the serverity
     */
    public String getServerity()
    {
        return serverity;
    }

    /**
     * @param serverity the serverity to set
     */
    public void setServerity(String serverity)
    {
        this.serverity = serverity;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
}
