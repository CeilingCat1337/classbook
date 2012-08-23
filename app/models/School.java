package models;

import play.*;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import play.mvc.*;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import models.*;

@Entity
public class School extends Model{
	
	@Unique
	@Required
	public String name;
	
	@OneToMany(mappedBy="school", cascade=CascadeType.ALL)
	public List<Squad> squads;
	
	
	public School(String name){
		this.name = name;		
	}

}
