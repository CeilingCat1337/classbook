package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Notification extends Model {
	
	public String type;
	public String content;
	@ManyToMany
    @JoinTable(name="NOT_USER")
	public List<User> to = new ArrayList<User>(0);
	@ManyToOne
	public User from;
	public Squad concernedSquad;
	public Boolean seen = false;
	
	public Notification(String type, List<User> to, User from, String content){
		this.type = type;
		this.content = content;
		this.to = to;
		this.from = from;
	}
	
}
