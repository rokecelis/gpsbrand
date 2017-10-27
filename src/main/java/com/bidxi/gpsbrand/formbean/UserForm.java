/*
 *******************************************************************************
 * Copyright (C) 2014 gerardo.roque.
 *
 * The SOFTWARE PRODUCT is protected by copyright laws and international 
 * copyright treaties, as well as other intellectual property laws and treaties. 
 * The SOFTWARE PRODUCT is license, you may not copy, modify, sublicense, link 
 * with, or distribute the Library except as expressly provided under this 
 * License.
 * verifica. bidxi Corp
 * México D.F.
 *******************************************************************************
 */
package com.bidxi.gpsbrand.formbean;

import java.util.List;
import com.bidxi.gpsbrand.model.User;

/**
 * *****************************************************************************
 * @author gerardo.roque 16/10/2014 05:24:33 PM cima UserForm.java
 * Description: Form asociada con el xml -> jsf
 * *****************************************************************************
 */
public class UserForm
{

    private User createUser;
    private List<User> users;
    private User deletedUser;

    /**
     * @return the createUser
     */
    public User getCreateUser()
    {
        return createUser;
    }

    /**
     * @param createUser the createUser to set
     */
    public void setCreateUser(User createUser)
    {
        this.createUser = createUser;
    }

    /**
     * @return the users
     */
    public List<User> getUsers()
    {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    /**
     * @return the deletedUser
     */
    public User getDeletedUser()
    {
        return deletedUser;
    }

    /**
     * @param deletedUser the deletedUser to set
     */
    public void setDeletedUser(User deletedUser)
    {
        this.deletedUser = deletedUser;
    }
}
