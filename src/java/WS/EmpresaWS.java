/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import DAO.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author x601533
 */
@Path("rest")
public class EmpresaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmpresaWS
     */
    public EmpresaWS() {
    }
    
//        @GET
//    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
//    @Path("funcionarios") //Indentifica o caminho na URL
//    public String getFuncionarios() {
//        FuncionarioDao dao = new FuncionarioDao();
//        ArrayList<Funcionario> listaFuncionarios = dao.getLista();
//        String listaJSON = "";
//
//        for(Funcionario funcionario : listaFuncionarios)
//            listaJSON += "Numat:" + funcionario.getNummat() +
//                         " Nome:" + funcionario.getNome() +
//                         " Salario:" + funcionario.getSalario() +
//                         " Sexo:" + funcionario.getSexo() + 
//                         " Ndepto:" + funcionario.getNdepto() +
//                         " Nsuper:" + funcionario.getNsuper() + "\n";  
//
//        return listaJSON;
//    }
    
        @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("funcionarios") //Indentifica o caminho na URL
    public String getFuncionarios() {
        FuncionarioDao dao = new FuncionarioDao();
        ArrayList <Funcionario> listaFuncionarios = dao.getLista();
        Gson gson = new Gson();
        return gson.toJson(listaFuncionarios);
    }
    
    
    
            @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("funcionarios/{Numat}") 
    public String getFuncionarios(@PathParam("Numat") int numat) {
        FuncionarioDao dao = new FuncionarioDao();
        Funcionario funcionario = dao.consulta(numat);
        if(funcionario != null){
        Gson gson = new Gson();
        return gson.toJson(funcionario);
        }
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    /**
     * Retrieves representation of an instance of WS.EmpresaWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of EmpresaWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
