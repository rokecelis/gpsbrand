package com.bidxi.gpsbrand.persistence;

import com.bidxi.gpsbrand.model.Address;

public interface AddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @param id
     * @return 
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @param record
     * @return 
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int insert(Address record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @param record
     * @return 
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int insertSelective(Address record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @param id
     * @return 
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    Address selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @param record
     * @return 
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int updateByPrimaryKeySelective(Address record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @param record
     * @return 
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int updateByPrimaryKey(Address record);
}