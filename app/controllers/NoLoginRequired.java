package controllers;

import models.User;
import play.mvc.Before;
import play.mvc.Controller;

public class NoLoginRequired extends Controller {
	public static User userLogged;
	@Before
	static void setConnectedUser() throws Throwable{
		userLogged = User.find("byUsername", Security.connected()).first();
		if(userLogged==null && Security.connected()!=null){
			Secure.logout();
		}
		else{
			renderArgs.put("userLogged", userLogged);
			System.out.println(userLogged);
		}
	}	
}
