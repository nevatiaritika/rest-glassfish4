/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ritika.nevatia
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    public static String MAP_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static int BASE_SYSTEM = MAP_STRING.length() - 1;

    public GenericResource() {
    }

    @GET
    @Path("getS")
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        return "<html><body><h1>Please work!</h1></body></html>";
    }

    @GET
    @Path("getJson")
    @Produces(MediaType.APPLICATION_JSON)
    public String getName() {
        return "Ritika";
    }

    @GET
    @Path("getStudent")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent() {
        return new Student("Ritika", "Nevatia", 23, 1);
    }

    @GET
    @Path("getStudentList")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudentList() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Ritika", "Nevatia", 23, 1));
        list.add(new Student("Ritika1", "Nevatia", 23, 2));
        list.add(new Student("Ritika2", "Nevatia", 23, 3));
        return list;
    }

    @POST
    @Path("showString")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String showString(Student name) {
        System.out.println("Name: " + name.getFirstName());
        System.out.println("Name Ob: " + name);
        return name.getFirstName();
    }

    @POST
    @Path("showStudent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student showStudent(Student name) {
        System.out.println("Name: " + name.getFirstName());
        System.out.println("Name Ob: " + name);
        name.setFirstName("New name");
        return name;
    }

    @GET
    @Path("getAllCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mavenSamplePU");
        EntityManager em = factory.createEntityManager();

        Query query = em.createNamedQuery("Customer.findAll");
        List<Customer> list = query.getResultList();
        return list;
    }

    @POST
    @Path("createCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createCustomer(Customer c) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mavenSamplePU");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();

        return "Done yes!";
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @POST
    @Path("createUniqueUrl")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createUniqueUrl(String originalUrl) {

        UrlStore urlStore = new UrlStore();
        urlStore.setUrlLong(originalUrl);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mavenSamplePU");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(urlStore);
        em.getTransaction().commit();
        
        Query query = em.createNamedQuery("UrlStore.findByUrlLong");
        query.setParameter("urlLong", originalUrl);
        List<UrlStore> list = query.getResultList();
        
        while(list!=null){
            int id = list.get(0).getUrlId();
            return shorten(id);
        }
        
        return "Error";
    }
    
    private String shorten(int id){
        StringBuilder shortenedUrl = new StringBuilder();
        while (id > 0) {
            shortenedUrl.append(MAP_STRING.charAt(id % BASE_SYSTEM));
            id = id / BASE_SYSTEM;
        }
        return shortenedUrl.reverse().toString();
    }
    
    private int backToOriginal(String shortUrl){
        
    }
    
    public String getLongUrl(String shortUrl){
        
    }

}
