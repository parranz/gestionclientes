package com.example.project.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.example.project.entities.Customer;

/**
 * 
 */
@Stateless
@Path("/customers")
public class CustomerEndpoint
{
   @PersistenceContext(unitName = "testDB")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(Customer entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(CustomerEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Customer entity = em.find(Customer.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<Customer> findByIdQuery = em.createQuery("SELECT c FROM Customer c LEFT JOIN FETCH c.address WHERE c.id = :entityId", Customer.class);
      findByIdQuery.setParameter("entityId", id);
      Customer entity = findByIdQuery.getSingleResult();
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Customer> listAll()
   {
      final List<Customer> results = em.createQuery("SELECT c FROM Customer c LEFT JOIN FETCH c.address", Customer.class).getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(@PathParam("id") Long id, Customer entity)
   {
      entity.setId(id);
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}