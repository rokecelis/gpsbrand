package com.bidxi.gpsbrand.persistence;

import com.bidxi.gpsbrand.model.UserProfile;

public interface UserProfileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_profile
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int insert(UserProfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_profile
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    int insertSelective(UserProfile record);
}