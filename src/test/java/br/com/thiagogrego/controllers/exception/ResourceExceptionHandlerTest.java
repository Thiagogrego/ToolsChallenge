package br.com.thiagogrego.controllers.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import br.com.thiagogrego.controllers.exceptions.ResourceExceptionHandler;
import br.com.thiagogrego.controllers.exceptions.StandardError;
import br.com.thiagogrego.services.exceptions.DataIntegrityException;
import br.com.thiagogrego.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class ResourceExceptionHandlerTest {
	
	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;
	
	public static final String MSG_OBJECT_NOT_FOUND = "Não encontrado";
	public static final String MSG_DATA_INTEGRIT = "Integridade de dados";
	public static final int STATUS_ERROR_NOT_FOUND = 404;
	public static final int STATUS_ERROR_DATA_INTEGRATY = 400;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void quandoNaoEncontrarOObjetoRetornarUmResponseEntity() {
		ResponseEntity<StandardError> response = exceptionHandler
				.objectNotFound(
						new ObjectNotFoundException(MSG_OBJECT_NOT_FOUND), 
						new MockHttpServletRequest());
		
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(StandardError.class, response.getBody().getClass());
		Assertions.assertEquals(MSG_OBJECT_NOT_FOUND, response.getBody().getError());
		Assertions.assertEquals(STATUS_ERROR_NOT_FOUND, response.getBody().getStatus());
	}
	
	@Test
	void quandoHouverIntegridadeDeDadosRetornarUmResposeEntity() {
		ResponseEntity<StandardError> response = exceptionHandler
				.dataIntegrity(
						new DataIntegrityException(MSG_DATA_INTEGRIT), 
						new MockHttpServletRequest());
		
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(StandardError.class, response.getBody().getClass());
		Assertions.assertEquals(MSG_DATA_INTEGRIT, response.getBody().getError());
		Assertions.assertEquals(STATUS_ERROR_DATA_INTEGRATY, response.getBody().getStatus());
	}
	
}
