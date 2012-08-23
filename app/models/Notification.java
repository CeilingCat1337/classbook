package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Notification extends Model {
	
	public String type;
	public String content;
	public List<User> to = new ArrayList<User>(0);
	public User from;
	public Squad concernedSquad;
	
	public Notification(String type, List<User> to, User from, String content){
		this.type = type;
		this.content = content;
		this.to = to;
		this.from = from;
	}
	
}
