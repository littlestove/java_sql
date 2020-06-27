package helloSQL;

public class Hero {
	public int id;
	public int damage;
	public float hp;
	public String name;
	public Hero(int id, String name, float hp, int damage) {
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.damage = damage;
	}
	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getDamage() {
		return damage;
	}




	public void setDamage(int damage) {
		this.damage = damage;
	}




	public float getHp() {
		return hp;
	}




	public void setHp(float hp) {
		this.hp = hp;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String toString() {
		
		return id+" "+name+" "+hp+" "+damage;
	}
	
}
