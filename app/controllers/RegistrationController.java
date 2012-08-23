package controllers;

import play.*;
import play.data.validation.Check;
import play.data.validation.CheckWith;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.mvc.*;

import java.util.*;

import org.apache.commons.mail.EmailException;

import models.*;

public class RegistrationController extends NoLoginRequired {
	
	public static void register() {
    	render();
    }
    
    public static void handleRegister(@Valid User user, @Required String passwordCheck) throws EmailException {
    	validation.equals(user.password, passwordCheck);
    	if(validation.hasErrors()){
    		params.flash();
    		validation.keep();
    		register();
    	}
    	else{
    		user.save();
    		Mails mailer = new Mails();
    		mailer.activationMail(user);
    		
    		UserController.index();
    	}
    	
    	
    }
    
    public static void confirmEmail(String userId, String token){
    	User user = User.findById(Long.parseLong(userId));
    	Boolean failed;
    	if(user!=null && user.confirmEmail(token)){
    		failed = false;
    	}
    	else{
    		failed = true;
    	}
    	render(failed);
    }
    
   

}
