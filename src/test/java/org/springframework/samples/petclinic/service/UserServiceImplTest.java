package org.springframework.samples.petclinic.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.model.Role;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.samples.petclinic.model.User;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = Exception.class)
    public void should_throwExceptionIfUserRolesIsEmpty_saveUser() throws Exception {
    	User user = Mockito.mock(User.class);
    	userService.saveUser(user);
    }

	@Test
	public void should_userRepositorySaveUser_saveUser() throws Exception {
		User user = Mockito.mock(User.class);
		Role role = Mockito.mock(Role.class);

		HashSet<Role> roles = Mockito.mock(HashSet.class);
		doReturn(roles).when(user).getRoles();
		doReturn("RULE").when(role).getName();
		doReturn(null).when(role).getUser();
		doReturn(false).when(roles).isEmpty();
		doReturn(new Iterator<Role>() {
			private int counter = 0;
			@Override
			public boolean hasNext() {
				return counter == 0;
			}

			@Override
			public Role next() {
				if (counter == 0) {
					counter++;
					return role;
				}
				else {
					return null;
				}
			}
		}).when(roles).iterator();

		userService.saveUser(user);
		verify(userRepository).save(user);
		verify(role).setUser(user);
		verify(role, times(2)).setName("ROLE_" + role.getName());
	}
}
