package org.springframework.samples.petclinic.owner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.validation.Valid;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = VisitController.class)
@RunWith(SpringRunner.class)
public class VisitControllerTest {

	private MockMvc mockMvc;

	private Pet petMock;

	@MockBean
	private VisitRepository visits;

	@MockBean
	PetRepository pets;

	@Autowired
	private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		petMock = Mockito.mock(Pet.class);
		doReturn(petMock).when(pets).findById(anyInt());
    }

    @After
    public void tearDown() throws Exception {
    }

	@Test
	public void should_returnProperHTMLPage_initNewVisitForm() throws Exception {
    	this.mockMvc.perform(get("/owners/aryan/pets/3/visits/new"))
					.andExpect(status().isOk())
					.andExpect(view().name("pets/createOrUpdateVisitForm"));
	}

	@Test
	public void should_loadPetWithVisitMethodGetsCalledBeforeHandlingRequests_loadPetWithVisit() throws Exception {
    	// initNewVisitForm
		this.mockMvc.perform(get("/owners/aryan/pets/3/visits/new"));
		verify(pets).findById(anyInt());
		verify(petMock).setVisitsInternal(anyCollection());
		verify(petMock).addVisit(any(Visit.class));

		// processNewVisitForm
		this.mockMvc.perform(post("/owners/aryan/pets/3/visits/new"));
		verify(pets, times(2)).findById(anyInt());
		verify(petMock, times(2)).setVisitsInternal(anyCollection());
		verify(petMock, times(2)).addVisit(any(Visit.class));
	}

	@Test
	public void should_returnHTMLPageWhenRequestHasBindingErrors_processNewVisitForm() throws Exception {
		this.mockMvc.perform(post("/owners/aryan/pets/1/visits/new"))
					.andExpect(status().isOk())
					.andExpect(view().name("pets/createOrUpdateVisitForm"))
					.andExpect(model().hasErrors());
	}

	@Test
	public void should_redirectWhenRequestHasDescriptionParam_processNewVisitForm() throws Exception {
		this.mockMvc.perform(post("/owners/aryan/pets/1/visits/new").param("description", "description"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"))
			.andExpect(model().hasNoErrors());
	}
}
