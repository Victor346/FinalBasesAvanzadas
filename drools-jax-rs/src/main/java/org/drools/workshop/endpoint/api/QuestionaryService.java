/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.workshop.endpoint.api;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.drools.workshop.model.*;

@Path("questionary")
public interface QuestionaryService {
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/getPregunta")
    public Pregunta getNextPregunta(@NotNull Alumno alumno);

    @POST
    @Consumes("application/json")
    @Path("/submitPregunta")
    public void submitRespuesta(@NotNull Respuesta respuesta);
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/insertAlumno")
    public Alumno insertAlumno(@NotNull Alumno alumno);

	@GET
	@Produces("application/json")
	@Path("alumnos")
	public List<Alumno> getAlumnos();

    @GET
	@Produces("application/json")
	@Path("preguntas")
	public List<Pregunta> getPreguntas();
}
