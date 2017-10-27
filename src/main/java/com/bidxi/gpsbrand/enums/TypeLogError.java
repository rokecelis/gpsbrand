package com.bidxi.gpsbrand.enums;

import java.io.Serializable;

/**
 *
 * @author roque
 */
public enum TypeLogError implements Serializable
{

    VACIO(1, "La celda esta vacía"), NAN(2, "No es un número entero"),
    NEGATIVE(3, "El valor es menor a cero"), CERO(4, "El valor es cero"), OTHER(4, "El valor debe ser numérico, Favor de verificar"),
    FILA(5, "Fila:"), COLUMN(6, "Columna:"), VALUE(7, "Valor:"), ALMACEN_INVALIDO(8, "El almacén NO existe"),
    PROCESO_ACADEMICO_INVALIDO(9, "Valor para proceso academico NO válido"), PBIEB_INVALIDO(10, "Valor para proceso PNIEB NO válido"),
    DIRECTOR_INVALIDO(11, "El usuario NO existe"), TURNO_INVALIDO(12, "El turno NO existe"),
    MUNICIPIO_INVALIDO(13, "El municipio NO existe"), MEF_INVALIDO(14, "La Matricula Entidad Federativa NO existe"),
    SEVERITY_WARN(15, "SEVERITY_WARN"), SEVERITY_INFO(16, "SEVERITY_INFO"), SEVERITY_FATAL(17, "SEVERITY_FATAL"),
    SEVERITY_ERROR(18, "SEVERITY_ERROR"), INVALID_DATE(19, "Fecha no valida"), NAD(20, "No es un número decimal"),
    NO_EXISTS_ESTADO(21, "No existe un estado con el id proporcionado"),
    NO_EXISTS_CICLO(22, "No existe un ciclo con el id proporcionado"),
    NO_EXISTS_PRODUCTO(23, "No existe un producto con el id proporcionado"),
    NO_EXISTS_VARIEDAD(24, "No existe una variedad con el id proporcionado"),
    EXISTS_ACOPIO(25, "Ya existe un registro de Acopio para los datos proporcionados"),
    EXISTS_MOVILIZACION(26, "Ya existe un registro de Movilización para los datos proporcionados"),
    NO_EXISTS_CLASIFICACION(27, "No existe una clasificacion con el id proporcionado"),
    NO_EXISTS_DISTRITO_DESARROLLO_RURAL(28, "No existe un distrito desarrollo rural con el id proporcionado"),
    NO_EXISTS_EJIDO(29, "No existe un ejido con el id proporcionado"),
    NO_EXISTS_ESTATUS_CENTRO_ACOPIO(30, "No existe un estatus centro acopio con el id proporcionado"),
    NO_EXISTS_CADER(31, "No existe un cader con el id proporcionado"),
    EXISTS_CENTRO_ACOPIO(32, "Ya existe un registro de Centro Acopio para los datos proporcionados"),
    NO_EXISTS_CONCEPTO(33, "No existe un concepto con el id proporcionado"),
    NO_EXISTS_UNIDAD(34, "No existe una unidad con el id proporcionado"),
    EXISTS_PRECIO_CONCEPTO(35, "Ya existe un registro de Precio Concepto con la combinación id_Concepto y Fecha Publicacion"),
    NO_EXISTS_TIPO_CULTIVO(36, "No existe un tipo_cultivo con el id proporcionado"),
    NO_EXISTS_CULTIVO(37, "No existe un id Cultivo con el id proporcionado, o un id Cultivo para el id Tipo Cultivo proporcionado"),
    NO_EXISTS_TIPO_EVENTO(38, "No existe un tipo de evento con el id proporcionado"),
    EXISTS_PRECIO_FUTURO_PRODUCTO(39, "Ya existe un registro de Precio Futuro Producto para los datos proporcionados"),
    NO_EXISTS_CADER_DIST_DESARROLLO_RURAL(40, "No existe una combinacion del id_distrito_desarrollo_rural y el id_cader proporcionados"),
    EARLIER_DATE(41, "La Fecha ingresada es anterior a la fecha actual"),
    NO_EXISTS_ANIO(42, "No existe un año con el id proporcionado"),
    NO_EXISTS_MES(43, "No existe un mes con el id proporcionado"),
    STRING_LENGTH(44, "La longitud de la cadena es mayor a la definida"),
    ZIP_CODE(45, "El código postal no tiene el formato correcto"),
    NO_EXISTS_INDUSTRIA(46, "No existe una industria con el id proporcionado"),
    NO_EXISTS_MUNICIPIO(47, "No existe un municipio con el id proporcionado"),
    NO_EXISTS_LOCALIDAD(48, "No existe una localidad con el id proporcionado"),
    TEMPERATURA_INVALIDA(49, "La temperatura debe variar entre -50 y 60 grados"),
    NUMBER_MORE_THAN(50, "El número tiene que ser mayor a "),
    ALPHA_NUMERIC(51, "El texto no tiene un formato alfa numérico"),
    LATITUDE(52, "El texto no tiene un formato correcto para latitud"),
    LONGITUDE(53, "El texto no tiene un formato correcto para longitud"),
    PRECIPITACION_INVALIDA(54, "El valor de la precipitacion debe ser mayor a 0.0001"),
    NO_EXISTS_PRESA(55, "No existe una presa para el id_region y el id_cat_presas proporcionados"),
    VALID_CARACTER(56, "El valor solo puede ser igual a "),
    NO_EXISTS_FRACCION_ARANCELARIA(57, "No existe una Fraccion Arancelaria para el id proporcionado"),
    NO_EXISTS_PAIS(58, "No existe un Pais para el id proporcionado"),
    NO_EXISTS_ARCHIVO(59, "Favor de seleccionar un archivo"),
    PRECIPITACION_VACIA(60, "Ambas precipitaciones son vacias, favor de capturar al menos una"),
    EXISTS_EVENTO(61, "Ya existe un evento con los mismos lugar de Evento, nombre de Evento y enlace")
    ;

    private String descripcion;
    private Integer id;

    private TypeLogError(Integer id, String descripcion)
    {
        this.descripcion = descripcion;
        this.id = id;
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
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }
}
