package model;

public class Room {
	private int id;
	private String name;
	private int capacity;
	private boolean hasProjector;
	private boolean hasWhiteboard;


	public Room() {
	}

	public Room(int id, String name, int capacity, boolean hasProjector, boolean hasWhiteboard) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.hasProjector = hasProjector;
		this.hasWhiteboard = hasWhiteboard;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isHasProjector() {
		return hasProjector;
	}

	public void setHasProjector(boolean hasProjector) {
		this.hasProjector = hasProjector;
	}

	public boolean isHasWhiteboard() {
		return hasWhiteboard;
	}

	public void setHasWhiteboard(boolean hasWhiteboard) {
		this.hasWhiteboard = hasWhiteboard;
	}
}