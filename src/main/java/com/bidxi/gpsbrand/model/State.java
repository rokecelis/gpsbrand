package com.bidxi.gpsbrand.model;

public class State {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column state.id
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column state.name
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column state.country_id
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    private Integer countryId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column state.id
     *
     * @return the value of state.id
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column state.id
     *
     * @param id the value for state.id
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column state.name
     *
     * @return the value of state.name
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column state.name
     *
     * @param name the value for state.name
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column state.country_id
     *
     * @return the value of state.country_id
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column state.country_id
     *
     * @param countryId the value for state.country_id
     *
     * @mbggenerated Wed Mar 02 18:27:17 CST 2016
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
}