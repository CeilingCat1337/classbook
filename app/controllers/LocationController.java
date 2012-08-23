
package controllers;

import play.*;
import play.data.validation.Check;
import play.data.validation.CheckWith;
import play.data.validation.Email;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.*;

import java.util.*;

import models.*;

public class LocationController extends LoginRequired {

    public static void selectNewLocation() {
    	List<School> schools = School.findAll();
    	List<Squad> squads;
    	if(flash.contains("schoolId")){
    		Long schoolId = Long.valueOf(flash.get("schoolId"));
    		squads = School.<School>findById(schoolId).squads;
    		render(schools, squads, schoolId);
    	}
    	else if(schools.size() > 0){
    		squads = School.all().<School>first().squads;
    		Long schoolId = School.all().<School>first().id;
    		render(schools, squads, schoolId);
    	}
    	else{
    		squads = new ArrayList<Squad>(0);
    		render(schools, squads);
    	}
    	
    }
    
    public static void handleSelectNewLocation(@Required Long schoolId, @Required Long squadId){
    	Squad squad = Squad.findById(squadId);
    	if(squad!=null){
    		if(squad.reservedTill<System.currentTimeMillis()){
				squad.reservedTill=null;
				squad.reservedFor=null;
			}
    		validation.isTrue("squadId", squad.reservedFor==null || squad.reservedFor==userLogged).message("This Squad is reserved");
    		validation.isTrue("squadId", squad.users.contains(userLogged)==false).message("You already are in this class");
    		validation.isTrue("squadId", squad.usersApplying.contains(userLogged)==false).message("You have already sent a request");
    	}
    	if(validation.hasErrors()){
    		params.flash();
    		validation.keep();
    		selectNewLocation();
    	}
    	else{
    		if(squad!=null){	
    			if(squad.reservedFor==userLogged){
    				//Inscribe user as admin
    				squad.deleteReservation();
    				squad.addAdmin(userLogged);
    			}
    			else if(squad.reservedTill==null && squad.admins.size()==0){
    				squad.addAdmin(userLogged);
    			}
    			else{
    				userLogged.applyForSquad(squad);
    			}
    		}
    	}
    }
    
    public static void ajaxFindSchools(String term){
    	if(term!=null){
    		List<School> schools = School.find("byNameLike", term+"%").fetch();
    		render(schools);
    	}
    	else{
    		List<School> schools = School.findAll();
    		render(schools);
    	}
    }
    
    public static void ajaxFindSquads(String term, @Required Long schoolId){
    	if(validation.hasErrors()){
    		error(validation.errors().get(0).message("validation.selectSchool"));
    		System.out.println("blubbi");
    	}
    	else if(term!=null){
    		List<Squad> squads = Squad.find("byNameLikeAndSchool", term+"%", School.findById(schoolId)).fetch();
    		render(squads);
    	}
    	else{
    		List<Squad> squads = Squad.find("bySchool", School.findById(schoolId)).fetch();
    		render(squads);
    	}
    }

    
    public static void ajaxAddSquad(@Valid Squad squad){
    	if(validation.hasErrors()){
    		error(validation.errors().get(0).message());
    	}
    	else{
    		squad.save();
    		List<Squad> squads = squad.school.squads;
    		Squad selectedSquad = squad;
    		render(squads, selectedSquad);
    	}
    }
    
    public static void ajaxAddSchool(@Valid School school){
    	System.out.println(school.name);
    	if(validation.hasErrors()){
    		error(validation.errors().get(0).message());
    	}
    	else{
    		school.save();
    		List<School> schools = School.findAll();
    		School selectedSchool = school;
    		render(schools, selectedSchool);
    	}
    }
    
    
    
}
