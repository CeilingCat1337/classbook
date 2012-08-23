package models;

import play.*;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import play.mvc.*;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import controllers.NoLoginRequired;

import models.*;

@Entity
public class Squad extends Model {
	
	@Unique("school")
	@Required
	public String name;
	
	
	@Required
	@ManyToOne
	public School school;
	

	public List<User> usersApplying = new ArrayList<User>(0);
	
	public Long reservedTill = (System.currentTimeMillis()+7200000);
	public User reservedFor = NoLoginRequired.userLogged;
	
	public Squad(String name, School school){
		this.name = name;
		this.school = school;
	}
	
	public void deleteReservation(){
		this.reservedFor=null;
		this.reservedTill=null;
	}
	
	public void addAdmin(User admin){
		this.admins.add(admin);
		this.users.add(admin);
	}
	
	public void addUser(User user){
		this.users.add(user);
	}
	
	public Boolean dropAdmin(User admin){
		this.users.remove(admin);
		return this.admins.remove(admin);
	}
	
	public Boolean dropUser(User user){
		return this.admins.remove(user);
	}
	
	@ManyToMany
    @JoinTable(name="SQUAD_USER")
	public List<User> users;
	
	@ManyToMany
	@JoinTable(name="SQUAD_USERADMIN")
	public List<User> admins;
	

}
