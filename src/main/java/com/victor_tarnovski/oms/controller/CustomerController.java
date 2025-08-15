package com.victor_tarnovski.oms.controller;

import com.victor_tarnovski.oms.dto.CreateCustomerDTO;
import com.victor_tarnovski.oms.service.CustomerService;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {
    private final CustomerService service;

    @Inject
    public CustomerController(final CustomerService service) {
        this.service = service;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreateCustomerDTO dto) {
        var response = service.create(dto);
        return Response
            .status(Response.Status.CREATED)
            .entity(response)
            .build();
    }

    @GET
    public Response find(
        @QueryParam("page") 
        @DefaultValue("1")
        @Min(1)
        int page,
        @DefaultValue("30")
        @QueryParam("pageSize")
        int pageSize
    ) {
        var response = service.find(page - 1, pageSize);
        return Response.ok(response).build();
    }

    @GET
    @Path("{id}")
    public Response findOne(@PathParam("id") Long id) {
        var dto = service.findOne(id);
        if(dto.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(dto).build();
    }
}
