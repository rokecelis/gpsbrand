package com.bidxi.gpsbrand.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import com.bidxi.gpsbrand.model.Error;
import com.bidxi.gpsbrand.enums.TypeLogError;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.util.Util;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 *
 * @author roque
 */
@Service
public class CommonRegexServiceImpl implements CommonRegexService
{
    /**
     * Obtiene la clave del libro de la fila asignada
     *
     * @param row
     * @return
     * @throws ServiceException
     */
    @Override
    public String getKeyFromFile(Row row) throws ServiceException
    {
        String key = "";
        if (row.getCell(4) != null)
        {
            switch (row.getCell(4).getCellType())
            {
                case Cell.CELL_TYPE_STRING:
                    key = row.getCell(4).getStringCellValue();
                    break;

                case Cell.CELL_TYPE_NUMERIC:
                    key = Integer.toString((int)row.getCell(4).getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    key = row.getCell(4).getStringCellValue();
                    break;
                default:
                    key = row.getCell(4).getStringCellValue();
                    break;
            }
        }
        return key;
    }

    /**
     * Obtiene el valor de la celda en la fila indicada
     *
     * @param row
     * @param index
     * @return
     * @throws ServiceException
     */
    @Override
    public String getKeyFromFile(Row row, Integer index) throws ServiceException
    {
        String key = "";
        if (row.getCell(index) != null)
        {
            switch (row.getCell(index).getCellType())
            {
                case Cell.CELL_TYPE_STRING:
                    key = row.getCell(index).getStringCellValue();
                    break;

                case Cell.CELL_TYPE_NUMERIC:
                    key = Double.toString(row.getCell(index).getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    key = row.getCell(index).getStringCellValue();
                    break;
                default:
                    key = row.getCell(index).getStringCellValue();
                    break;
            }
        }
        return key;
    }

    @Override
    public String getKeyFromFile(Row row, Integer index, String formatDate) throws ServiceException
    {
        String key = "";
        if (row.getCell(index) != null)
        {
            switch (row.getCell(index).getCellType())
            {
                case Cell.CELL_TYPE_STRING:
                    key = row.getCell(index).getStringCellValue();
                    break;

                case Cell.CELL_TYPE_NUMERIC:
                    key = DateUtil.isCellDateFormatted(row.getCell(index)) && DateUtil.isValidExcelDate(row.getCell(index).getNumericCellValue()) ? (new SimpleDateFormat(formatDate)).format(row.getCell(index).getDateCellValue()) : Double.toString(row.getCell(index).getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    key = row.getCell(index).getStringCellValue();
                    break;
                default:
                    key = row.getCell(index).getStringCellValue();
                    break;
            }
        }
        return key;
    }

    /**
     * Valida el precio y la clave del pedido nacional
     *
     * @param row
     * @param column
     * @param valueCel
     * @param messages
     * @return
     * @throws com.bidxi.gpsbrand.exception.ServiceException
     */
    @Override
    public boolean validatePriceByKey(Integer row, String column, String valueCel, List<Error> messages) throws ServiceException
    {

        if (valueCel.isEmpty())
        {
            messages.add(new Error(row, column, TypeLogError.VACIO.getDescripcion()));
            return false;
        }
        if (!this.isNumeric(valueCel))
        {
            messages.add(new Error(row, column, TypeLogError.NAN.getDescripcion()));
            return false;
        }
        if (Double.parseDouble(valueCel) < 0)
        {
            messages.add(new Error(row, column, TypeLogError.NEGATIVE.getDescripcion()));
            return false;
        }
        if (new Double(valueCel).equals(new Double(0)))
        {
            messages.add(new Error(row, column, TypeLogError.CERO.getDescripcion()));
            return false;
        }
        return true;
    }

    /**
     * El iterador en la posición de los datos
     *
     * @param rowIterator
     * @param topIndex
     */
    @Override
    public void setIteratorInRowIndex(Iterator<Row> rowIterator, Integer topIndex)
    {
        int i = 0;
        while (rowIterator.hasNext() && i < topIndex)
        {
            rowIterator.next();
            i++;
        }
    }

    /**
     * Verifica que la cadena proporcionada no sea vacía y que sea un número
     * entero positivo.
     *
     * @param row
     * @param column
     * @param value
     * @param messages
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean validateInteger(Integer row, Integer column, String value, List<com.bidxi.gpsbrand.model.Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;

        if (value.isEmpty())
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_INFO.getDescripcion(), TypeLogError.VACIO.getDescripcion()));
            return false;
        }
        
        if(!value.matches("^\\d+((\\.+[0]+)*)$"))
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_ERROR.getDescripcion(), TypeLogError.NAN.getDescripcion()));
            return false;
        }
        
        Double valueCell = Double.parseDouble(value);
        if (valueCell > 0)
        {
            Double result = valueCell / valueCell.longValue();
            if (result > 1)
            {
                messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_ERROR.getDescripcion(), TypeLogError.NAN.getDescripcion()));
                return false;
            }
        }

        if (valueCell < 0)
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.NEGATIVE.getDescripcion()));
            return false;
        }
        return true;
    }
    
    /**
     * Verifica que la cadena proporcionada no sea vacía y que sea un número
     * entero positivo.
     *
     * @param row
     * @param column
     * @param value
     * @param messages
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean validateIntegerEmpty(Integer row, Integer column, String value, List<com.bidxi.gpsbrand.model.Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;

        if (!value.isEmpty())
        {
            if(!value.matches("^\\d+((\\.+[0]+)*)$"))
            {
                messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_ERROR.getDescripcion(), TypeLogError.NAN.getDescripcion()));
                return false;
            }
            
            Double valueCell = Double.parseDouble(value);
            if (valueCell > 0 && (valueCell / valueCell.longValue()) > 1)
            {
                messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_ERROR.getDescripcion(), TypeLogError.NAN.getDescripcion()));
                return false;
            }

            if (valueCell < 0)
            {
                messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.NEGATIVE.getDescripcion()));
                return false;
            }
        }
        return true;
    }
    
    /**
     * Verifica que la cadena proporcionada sea un número decimal.
     *
     * @param row
     * @param column
     * @param value
     * @param messages
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean validateDecimal(Integer row, Integer column, String value, List<Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;
        boolean valid = validateNumeric(row, column, value, messages);
        if (!valid)
            return valid;
        
        if (Double.parseDouble(value) < 0)
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.NEGATIVE.getDescripcion()));
            return false;
        }
        return true;
    }
    
    /**
     * Verifica que el valor proporcionado sea un decimal positivo o negativo.
     * @param row
     * @param column
     * @param value
     * @param messages
     * @return
     * @throws ServiceException 
     */
    @Override
    public boolean validateNumeric(Integer row, Integer column, String value, List<Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;
        if (value.isEmpty())
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_INFO.getDescripcion(), TypeLogError.VACIO.getDescripcion()));
            return false;
        }
        if (!this.isNumeric(value))
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_ERROR.getDescripcion(), TypeLogError.NAN.getDescripcion()));
            return false;
        }
        return true;
    }

    /**
     * Verifica que la cadena proporcionada tenga el fromato de fecha
     * correspondiente.
     *
     * @param row
     * @param column
     * @param value
     * @param messages
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean validateDate(Integer row, Integer column, String value, List<Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;
        if (!this.isDate(value))
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.INVALID_DATE.getDescripcion()));
            return false;
        }
        return true;
    }
    
    /**
     * Verifica que la fecha del parametro sea igual o posterior a la fecha actual del sistema
     * @param row
     * @param column
     * @param value
     * @param messages
     * @return 
     */
    public boolean validateTodayorLaterDate(Integer row, Integer column, String value, List<Error> messages){
        int physicalRow = row + 1;
        int physicalColumn = column + 1;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date eventDate;
            eventDate = sdf.parse(value);
            
            Date today =new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            today = cal.getTime();

            if (eventDate.before(today)){
               messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.EARLIER_DATE.getDescripcion())); 
               return false;
            }
        } catch (ParseException ex) {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.INVALID_DATE.getDescripcion()));
            return false;
        }
        return true;
    }

    /**
     * Verifica que la cadena proporcionada tenga el fromato de fecha
     * correspondiente.
     *
     * @param row
     * @param column
     * @param value
     * @param messages
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean validateDateFromParse(Integer row, Integer column, String value, List<Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;
        if (value.isEmpty())
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_INFO.getDescripcion(), TypeLogError.VACIO.getDescripcion()));
            return false;
        }
        if (!this.isDateFromPaser(value))
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.INVALID_DATE.getDescripcion()));
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param row
     * @param column
     * @param value
     * @param length
     * @param messages
     * @return
     * @throws ServiceException 
     */
    @Override
    public boolean validateStringLength(Integer row, Integer column, String value, int length, List<Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;
        
        if (value.isEmpty())
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_INFO.getDescripcion(), TypeLogError.VACIO.getDescripcion()));
            return false;
        }
        
        if(value.length() > length)
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_INFO.getDescripcion(), TypeLogError.STRING_LENGTH.getDescripcion() + ": " + length));
            return false;
        }
        
        return true;
    }
    
    /**
     * 
     * @param row
     * @param column
     * @param value
     * @param length
     * @param messages
     * @return
     * @throws ServiceException 
     */
    @Override
    public boolean validateStringLengthOrEmpty(Integer row, Integer column, String value, int length, List<com.bidxi.gpsbrand.model.Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;
        
        if(!value.isEmpty() && value.length() > length)
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_INFO.getDescripcion(), TypeLogError.STRING_LENGTH.getDescripcion() + ": " + length));
            return false;
        }
        
        return true;
    }
    
    /**
     *
     * @param row
     * @param column
     * @param value
     * @param messages
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean validateNotNullString(Integer row, Integer column, String value, List<Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;
        if (value == null || value.isEmpty())
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.VACIO.getDescripcion()));
            return false;
        }
        return true;
    }

    /**
     * 
     * @param row
     * @param column
     * @param value
     * @param messages
     * @return
     * @throws ServiceException 
     */
    @Override
    public boolean validateAlphaNumeric(Integer row, Integer column, String value, List<Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;
        if (value == null || value.isEmpty())
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.VACIO.getDescripcion()));
            return false;
        }
        
        if(!isAlphaNumeric(value))
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.ALPHA_NUMERIC.getDescripcion()));
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param row
     * @param column
     * @param value
     * @param messages
     * @return
     * @throws ServiceException 
     */
    @Override
    public boolean validateAlphaNumericNull(Integer row, Integer column, String value, List<Error> messages) throws ServiceException
    {
        int physicalRow = row + 1;
        int physicalColumn = column + 1;
        
        if(!(value == null || value.isEmpty()) && !isAlphaNumeric(value))
        {
            messages.add(new Error(physicalRow, getColumnExcel(physicalColumn), TypeLogError.SEVERITY_WARN.getDescripcion(), TypeLogError.ALPHA_NUMERIC.getDescripcion()));
            return false;
        }
        return true;
    }
    
    /**
     * Si la cadena es un número
     *
     * @param str
     * @return
     */
    @Override
    public boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Verifica si la cadena es un número entero.
     *
     * @param str
     * @return
     */
    @Override
    public boolean isInteger(String str)
    {
        return str.matches("-?\\d+");
    }

    /**
     * Verifica si la cadena es una fecha con el formato determinado
     * "dd/MM/yyyy"
     *
     * @param str
     * @return
     */
    @Override
    public boolean isDate(String str)
    {
        return str.matches("(^((((0?|[1,2,3])[0-9]{1})(\\/)((0[1,3-9]{1})|(1[0,1,2]{1})))|(((0?|[1,2])[0-9]{1})(\\/)(02)))(\\/)(([19][0-9]{2})|([2][0-9]{3}))$)");
    }

    @Override
    public boolean isDateFromPaser(String dateStr)
    {
        Date date = Util.parsearFecha(dateStr);
        return date != null;
    }
    
    @Override
    public boolean isDateFromParserFormat(String str, String format)
    {
        return Util.parsearFecha(str, format) != null;
    }
    
    /**
     * Obtiene la columna en texto "ALK" dado el número de columna, esto para los archivos de carga Excel.
     * @param column número de columna a obtener
     * @return String que contiene el texto con el nombre de la columna.
     */
    @Override
    public String getColumnExcel(int column)
    {
        return column <= 26 ? String.valueOf((char) (column + 64)) : this.getColumnExcel(column/26) + this.getColumnExcel(column%26);
    }
    
    /**
     * 
     * @param str
     * @return 
     */
    @Override
    public boolean isAlphaNumeric(String str)
    {
        return str.matches("(^[A-záéíóúñÁÉÍÓÚÑ0-9]*$)");
    }
    
    /**
     * 
     * @param str
     * @param latitude
     * @return 
     */
    @Override
    public boolean isLatitudeLongitude(String str, boolean latitude)
    {
        return latitude ? str.matches("(^([0-9]{1,3})(\\.)([0-9]+)$)")
                : str.matches("(^(\\-[0-9]{1,3})(\\.)([0-9]+)$)");
    }
}
