package md.pharm.restservice.service;

import md.TopPharmResTfulServiceApplication;
import md.pharm.hibernate.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TopPharmResTfulServiceApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    String userID1 = UUID.randomUUID().toString();
    String userID2 = UUID.randomUUID().toString();
    User user1 = new User("A","First","Last",null,userID1,"password","email@toppharm.com");
    User user2 = new User("A","First","Last",null,userID2,"password","email@toppharm.com");

	@Test
	public void createFirstUser() {

	}

	@Test
	public void createSecondUser() {

	}

	@Test
	public void getAllUsers() {

	}

	@Test
	public void getFirstUser() {

	}

	@Test
	public void deleteSecondUser() {

	}

}
