package org.neeraj.restwebservice.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.neeraj.restwebservice.messenger.model.ErrorMessage;



@Provider							//JAX RS knows that this class is for exception mapping
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	public Response toResponse(DataNotFoundException ex)
	{
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404," ");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		
	}
}
