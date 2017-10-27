package com.bidxi.gpsbrand.user;



import java.util.List;
import com.bidxi.gpsbrand.exception.PersistenceException;
import com.bidxi.gpsbrand.model.User;
import com.bidxi.gpsbrand.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.apache.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author gerardo.roque
 */
@ContextConfiguration(
        locations =
        {
            "classpath:com/bidxi/gpsbrandspring/applicationContext-main.xml",
            "classpath:com/bidxi/gpsbrandspring/applicationContext-persistence.xml"
        })

public class UserTest extends AbstractTestNGSpringContextTests
{

    @Autowired
    private UserMapper userMapper;
    private final Logger log = Logger.getLogger(UserTest.class);

    public UserTest()
    {
    }

    @Test(enabled = true)
    public void showDataUser() throws PersistenceException
    {
        List<User> users = this.userMapper.getListUsers();
        Assert.notNull(users,"->>" +  users.toString());
    }

    @BeforeClass
    public static void setUpClass() throws PersistenceException
    {
    }

    @AfterClass
    public static void tearDownClass() throws PersistenceException
    {
    }

    @BeforeMethod
    public void setUpMethod() throws PersistenceException
    {
    }

    @AfterMethod
    public void tearDownMethod() throws PersistenceException
    {
    }
}
