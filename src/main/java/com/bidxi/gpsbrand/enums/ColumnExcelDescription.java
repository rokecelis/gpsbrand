package com.bidxi.gpsbrand.enums;

import java.io.Serializable;

/**
 *
 * @author roque
 */
public enum ColumnExcelDescription implements Serializable
{

    A("A", "Columna A"),
    B("B", "Columna B"),
    C("C", "Columna C"),
    D("D", "Columna D"),
    E("E", "Columna E");
    private String descripcion;
    private String column;

    private ColumnExcelDescription(String column, String descripcion)
    {
        this.descripcion = descripcion;
        this.column = column;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
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
}
