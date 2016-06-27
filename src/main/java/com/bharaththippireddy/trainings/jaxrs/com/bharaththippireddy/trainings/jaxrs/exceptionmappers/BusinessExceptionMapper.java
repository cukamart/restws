package com.bharaththippireddy.trainings.jaxrs.com.bharaththippireddy.trainings.jaxrs.exceptionmappers;

import com.bharaththippireddy.trainings.jaxrs.exceptions.SomeBusinessException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<SomeBusinessException> {

    @Override
    public Response toResponse(SomeBusinessException e) {
        String response = "<response>" + "<status>Error</status>" +
                "<message>" + e.getMessage() + "</message>" +
                "</response>";

        return Response.serverError().entity(response).type(MediaType.APPLICATION_XML).build();
    }
}
