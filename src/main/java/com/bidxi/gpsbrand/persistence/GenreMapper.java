package com.bidxi.gpsbrand.persistence;

import com.bidxi.gpsbrand.model.Genre;

public interface GenreMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table genre
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table genre
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int insert(Genre record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table genre
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int insertSelective(Genre record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table genre
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    Genre selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table genre
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int updateByPrimaryKeySelective(Genre record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table genre
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int updateByPrimaryKey(Genre record);
}