/*
 ********************************************************************************
 Copyright (C) 2014 gerardo.roque.

 The SOFTWARE PRODUCT is protected by copyright laws and international 
 copyright treaties, as well as other intellectual property laws and treaties. 
 The SOFTWARE PRODUCT is license, you may not copy, modify, sublicense, link 
 with, or distribute the Library except as expressly provided under this 
 License.
 verifica. bidxi Corp
 México D.F.
 *******************************************************************************
 */
package com.bidxi.gpsbrand.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * *****************************************************************************
 * @author gerardo.roque 8/10/2014 01:03:44 PM cima Util.java
 * Description:
 * ******************************************************************************
 */
public class Util
{

    public static final String FORMATODDMMYYYY = "dd/MM/yyyy";
    public static final String FORMATODDMMYYYYE = "ddMMyyyy";
    public static final String FORMATOHHMM = "HH:mm";
    public static final String FORMATODDMMYYYYHHMMSS = "dd/MM/yyyy HH:mm";
    public static final String FORMATOYYYYMMDD_HHMMSS = "yyyyMMdd_HHmmss";
    public static final String FORMATO_EVENTO = "dd/MM/yyyy HH:mm";
    public static final String FORMATODAY = "dd";
    public static final String FORMATOMOTH = "MMMM";
    public static final String FORMATOYEAR = "YYYY";

    public static String encriptarMd5(String valor)
    {
        try
        {
            Md5PasswordEncoder md5 = new Md5PasswordEncoder();
            valor = md5.encodePassword(valor, null);
        } catch (Exception e)
        {
            valor = "";
        }
        return valor;
    }

    public static String encriptarSha(String valor)
    {
        try
        {
            LdapShaPasswordEncoder sha = new LdapShaPasswordEncoder();
            valor = sha.encodePassword(valor, null);
        } catch (Exception e)
        {
            valor = "";
        }
        return valor;
    }

    public static Date parsearFecha(String valor)
    {
        Date fecha;
        String formato;
        try
        {
            formato = valor.charAt(2) == '/' ? "dd/MM/yyyy"
                    : valor.charAt(2) == '-' ? "dd-MM-yyyy"
                    : valor.charAt(4) == '/' ? "yyyy/MM/dd" : valor.charAt(4) == '-' ? "yyyy-MM-dd" : "";
            fecha = parsearFecha(valor, formato);

        } catch (Exception e)
        {
            fecha = null;
        }

        return fecha;
    }

    public static Date parsearHora(String valor)
    {
        Date fecha;
        String formato;
        try
        {
            formato = valor.charAt(2) == ':' ? "HH:mm" : "";
            fecha = parsearFecha(valor, formato);

        } catch (Exception e)
        {
            fecha = null;
        }

        return fecha;
    }

    public static Date parsearFecha(String valor, String formato)
    {

        Date fecha;
        SimpleDateFormat formateador = new SimpleDateFormat(formato);
        try
        {
            fecha = formateador.parse(valor);
        } catch (ParseException e)
        {
            fecha = null;
        }
        return fecha;
    }

    public static String formatearFecha(Date date, String formato)
    {

        String fecha;
        SimpleDateFormat formateador = new SimpleDateFormat(formato);
        if (date == null)
        {
            fecha = "";
        } else
        {
            fecha = formateador.format(date);
        }
        return fecha;
    }

    public static String formatearFecha(Date date)
    {

        String fecha;
        SimpleDateFormat formateador = new SimpleDateFormat(FORMATODDMMYYYY);
        fecha = formateador.format(date);
        return fecha;
    }

    public static String formatearFechaHora(Date date)
    {

        String fecha;
        SimpleDateFormat formateador = new SimpleDateFormat(FORMATODDMMYYYYHHMMSS);
        fecha = formateador.format(date);
        return fecha;
    }

    /**
     * Obtiene la fecha con el formato dd/MM/yyyy HH24:mm
     *
     * @param date
     * @return
     */
    public static String formatearFechaEvento(Date date)
    {

        String fecha;
        SimpleDateFormat formateador = new SimpleDateFormat(FORMATO_EVENTO);
        fecha = formateador.format(date);
        return fecha;
    }

    /**
     * Asigna un timestamp a una fecha con el formato YYYY-MM-DD HH24:MI
     *
     * @param valor
     * @return
     */
    public static Date formatearFechaToEvento(String valor)
    {
        Date fecha;
        SimpleDateFormat formateador = new SimpleDateFormat(FORMATO_EVENTO);
        try
        {
            fecha = formateador.parse(valor);
        } catch (ParseException e)
        {
            fecha = null;
        }
        return fecha;
    }

    public static Map ordenarHashMap(Map hmap)
    {
        HashMap map = new LinkedHashMap();
        List mapKeys = new ArrayList(hmap.keySet());
        Collections.sort(mapKeys);
        for (int i = 0; i < mapKeys.size(); i++)
        {
            map.put(mapKeys.get(i), hmap.get(mapKeys.get(i)));
        }
        return map;
    }

    public static Map ordenarHashValuesMap(Map map)
    {

        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;

    }

    public static Date restarDias(Date date, int dias)
    {
        Calendar fecha = Calendar.getInstance(); // obtiene la fecha actual
        fecha.add(Calendar.DATE, -dias); // incrementa en 30 dï¿œas la fecha
        // actual.
        return fecha.getTime();
    }
    
    public static String sumDaysToDate(Date date, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        
        return formatearFecha(calendar.getTime());                
    }
    
    public static Integer numberDaysBetween2Dates(Date from, Date to, int regularity)
    {        
        Long difference = to.getTime() - from.getTime();
        Long days = difference / (24 * 60 * 60 * 1000);
        Double ival = days / new Double(regularity);
        Double inteval = Math.ceil(ival);
        
        return (Integer) inteval.intValue();
    }    
    
    public static List<String> intervalsBetween2Dates(Date from, Date to, int regularity, int interval){                
        List<String> intervals = new ArrayList<>();        
        intervals.add(formatearFecha(from));
        
        if(regularity != 1)
            for(int i = 1; i < interval; i++){
                intervals.add(sumDaysToDate(from, regularity * i - 1));
            }
        else{
                intervals.add(formatearFecha(from));            
        }
        
        intervals.add(formatearFecha(to));
                
        return intervals;
    }
    
    public static Map<Integer, String> getListTransform(Map<Integer, String> map)
    {
        Map<Integer, String> mapTransform = new HashMap<Integer, String>();
        if (map != null)
        {
            java.util.Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry<Integer, String> pairs = (Map.Entry<Integer, String>) it.next();
                mapTransform.put(pairs.getKey(), getStringTransform(pairs.getValue()));
            }
        }
        return mapTransform;
    }

    public static String getStringTransform(String value)
    {
        String substr = "", substrLasted = "", string = value;
        if (value != null && value.length() > 0)
        {
            string = string.toLowerCase();
            substr = ("" + string.charAt(0)).toUpperCase();
            substrLasted = string.substring(1, string.length());
        }
        return "" + substr + substrLasted;
    }

    public String getDateTime(String format)
    {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Obtiene el no. de minutos entre una fecha y otra
     *
     * @param oldTime
     * @param currentTime
     * @return
     */
    public Long compareTimesByMinutes(Timestamp oldTime, Timestamp currentTime)
    {
        Long difmiliSeconds = currentTime.getTime() - oldTime.getTime(); // obtiene el no. de milisegundos
        difmiliSeconds = (difmiliSeconds / (1000 * 60));// la diferencia en minutos
        return difmiliSeconds;
    }

    public static Double averagePrices(List<Double> values){
        Double sum = 0.0;
        
        for (Double val : values) {
            sum = sum + val;
        }
        
        if(sum <= 0)
            return sum;
        
        return (Double) sum / values.size();
    }

}
