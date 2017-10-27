package com.bidxi.gpsbrand.service;

import java.util.Iterator;
import java.util.List;
import com.bidxi.gpsbrand.exception.ServiceException;
import org.apache.poi.ss.usermodel.Row;
import com.bidxi.gpsbrand.model.Error;

/**
 *
 * @author roque
 */
public interface CommonRegexService
{

    public String getKeyFromFile(Row row) throws ServiceException;

    public String getKeyFromFile(Row row, Integer index) throws ServiceException;

    public String getKeyFromFile(Row row, Integer index, String formatDate) throws ServiceException;

    public boolean validatePriceByKey(Integer row, String column, String valueCel, List<Error> messages) throws ServiceException;

    public boolean validateInteger(Integer row, Integer column, String value, List<Error> messages) throws ServiceException;
    
    public boolean validateIntegerEmpty(Integer row, Integer column, String value, List<Error> messages) throws ServiceException;

    public boolean validateDecimal(Integer row, Integer column, String value, List<Error> messages) throws ServiceException;
    
    public boolean validateNumeric(Integer row, Integer column, String value, List<Error> messages) throws ServiceException;

    public boolean validateDate(Integer row, Integer column, String value, List<Error> messages) throws ServiceException;

    public boolean validateStringLength(Integer row, Integer column, String value, int length, List<com.bidxi.gpsbrand.model.Error> messages) throws ServiceException;

    public boolean validateStringLengthOrEmpty(Integer row, Integer column, String value, int length, List<com.bidxi.gpsbrand.model.Error> messages) throws ServiceException;
    
    public boolean validateNotNullString(Integer row, Integer column, String value, List<Error> messages) throws ServiceException;
    
    public boolean validateAlphaNumeric(Integer row, Integer column, String value, List<Error> messages) throws ServiceException;
    
    public boolean validateAlphaNumericNull(Integer row, Integer column, String value, List<Error> messages) throws ServiceException;
    
    public void setIteratorInRowIndex(Iterator<Row> rowIterator, Integer topIndex);

    public boolean isNumeric(String str);

    public boolean isInteger(String str);

    public boolean isDate(String str);
    
    public String getColumnExcel(int column);

    public boolean validateDateFromParse(Integer row, Integer column, String value, List<Error> messages) throws ServiceException;
    
    public boolean isDateFromPaser(String str) throws ServiceException;
    
    public boolean isDateFromParserFormat(String str, String format) throws ServiceException;
    
    public boolean isAlphaNumeric(String str);
    
    public boolean isLatitudeLongitude(String str, boolean latitude);
}
