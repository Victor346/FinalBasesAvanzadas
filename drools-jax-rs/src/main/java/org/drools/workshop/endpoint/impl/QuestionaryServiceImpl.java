package org.drools.workshop.endpoint.impl;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.drools.workshop.model.*;

import org.drools.workshop.endpoint.api.*;
import org.kie.api.cdi.KReleaseId;
import org.kie.api.cdi.KSession;
import org.kie.api.KieServices;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieContainer;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class QuestionaryServiceImpl implements QuestionaryService {

    @Inject
    @KReleaseId(groupId = "org.drools.workshop", artifactId = "drools-user-kjar", version = "1.0-SNAPSHOT")
    @KSession
    private KieSession kSession;

    public QuestionaryServiceImpl() {
    }

    @Override
    public Pregunta getNextPregunta(Alumno alumno){
        boolean existe = checkAlumno(alumno);
        if(existe){
            //Si
                //Sacar la siguiente pregunta
            return getAlumno(alumno).getPregunta();
        } else{
            //No
                //Meter a la base de datos el alumno
                //Disparar las reglas                                                                                                                                               
                //Sacar la siguiente pregunta
            kSession.insert(new Alumno(alumno.getName(), alumno.getSemester(), alumno.getCourse()));
            int fired = kSession.fireAllRules();                           
            System.out.println(">> Fired: " + fired);
            System.out.println("Loop");

            return getAlumno(alumno).getPregunta();
        } 
    }

    @Override
    public void submitRespuesta(Respuesta respuesta){
        //prerespuesta a la base de concoocimientsos
        kSession.insert(respuesta);
        //Disparar reglas
        int fired = kSession.fireAllRules();
        System.out.println(">> Fired: " + fired);
        return;
    }

    @Override
    public Alumno insertAlumno(Alumno alumno) {
        System.out.println(">> kSession: " + kSession);
        printKieSessionAllFacts(kSession);
        System.out.println(">> User: " + alumno);
        kSession.insert(alumno);
        int fired = kSession.fireAllRules();
        System.out.println(">> Fired: " + fired);
        return alumno;
    }

    @Override
    public List<Alumno> getAlumnos(){
		List<Alumno> alumnos = new ArrayList<>();
		for(Object o : kSession.getObjects()){
			if(o instanceof Alumno){
				alumnos.add((Alumno) o);
			}
		}
		return alumnos;
	}

    @Override
    public List<Pregunta> getPreguntas(){
        List<Pregunta> preguntas = new ArrayList<>();
        for(Object o : kSession.getObjects()){
            if(o instanceof Pregunta){
                preguntas.add((Pregunta) o);
            }
        }
        return preguntas;
    }

    public List<Respuesta> getResputas(){
        List<Respuesta> respuestas = new ArrayList<>();
        for(Object o : kSession.getObjects()){
            if(o instanceof Respuesta){
                respuestas.add((Respuesta) o);
            }
        }
        return respuestas;
    }

    private Alumno getAlumno(Alumno alumno){
        for(Alumno o : getAlumnos()){
            if(o.getName().equals(alumno.getName()) && o.getSemester() == alumno.getSemester() 
                && o.getCourse().equals(alumno.getCourse())){
                //Es el bueno
                return o;
            }
        }

        return alumno;
    }
    
    private boolean checkAlumno(Alumno alumno){
        for(Alumno o : getAlumnos()){
            if(o.getName().equals(alumno.getName()) && o.getSemester() == alumno.getSemester() 
                && o.getCourse().equals(alumno.getCourse())){
                //Es el bueno
                return true;
            }
        }

        return false;
    }

    private void printKieSessionAllFacts(KieSession kSession) {
        System.out.println(" >> Start - Printing All Facts in the Kie Session");
        for (Object o : kSession.getObjects()) {
            System.out.println(">> Fact: " + o);
        }
        System.out.println(" >> End - Printing All Facts in the Kie Session");
    }

}
