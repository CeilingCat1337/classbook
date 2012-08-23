package controllers;

import models.User;
 
public class Security extends Secure.Security {
	static boolean authenticate(String username, String password) {
		User user = User.find("byUsernameAndPassword", username, password).first();
		System.out.println(user.name+user.emailConfirmationToken);
		return (user != null && user.emailConfirmationToken==null);
    }
	
	static boolean authentify(String username, String password) {
        throw new UnsupportedOperationException();
    }

    static boolean check(String profile) {
    	
        return true;
    }


    static String connected() {
        return session.get("username");
    }

    
    static boolean isConnected() {
        return session.contains("username");
    }

    
    static void onAuthenticated() {
    }

    
    static void onDisconnect() {
    }


    static void onDisconnected() {
    }


    static void onCheckFailed(String profile) {
        forbidden();
    }
	
}