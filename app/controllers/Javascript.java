package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Javascript extends Controller {
	
	public static void jsLinks(){
		render("Javascript/jsLinks.js");
	}
	
}
