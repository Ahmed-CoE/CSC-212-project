package phase1;

public class Rider extends Person implements IRider {
	private String email;
	private String homeCity;

	public Rider(int id, String name, String PhoneNumber, String email, String homeCity) {
		super(id,name, PhoneNumber);
		this.email = email;
		this.homeCity = homeCity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public int compareTo(IRider other) {
		if (this.getId() < other.getId())
			return -1;
		else if (this.getId() == other.getId())
			return 0;
		else
			return 1;
	}

}
