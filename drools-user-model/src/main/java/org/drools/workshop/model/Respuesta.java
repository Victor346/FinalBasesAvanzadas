package org.drools.workshop.model;

import javax.xml.bind.annotation.XmlRootElement;

public class Respuesta {
    private String name;
    private int semester;
    private String course;
	private boolean answer;

    public Respuesta() {}

    public Respuesta(String name, int semester, String course, boolean answer) {
        this.name = name;
        this.semester = semester;
        this.course = course;
        this.answer = answer;
	}

    public String getName(){
		return name;
	}

	public void setName(String name){
		this.name =  name;
	}

	public int getSemester(){
		return semester;
	}

	public void setSemester(int semester){
		this.semester = semester;
	}

	public String getCourse(){
		return course;
	}

	public void setCourse(String course){
		this.course = course;
	}

	public boolean getAnswer(){
		return answer;
	}

	public void setAnswer(boolean answer){
		this.answer = answer;
	}

    @Override
    public String toString() {
        return "Respuesta{" + "name=" + name + ", semester=" + semester + ", course=" + course + ", answer=" + answer + " }";
    }
}
