package projeto.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "GEOLOCALIZACAO")
public class Geolocalizacao implements Comparable<Geolocalizacao>{
	
	private Integer id;
	
	private String distance;
	
	private String title;
	
	private String location_type;
	
	private String woeid;
	
	private String latt_long;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 38, scale = 0)
	@SequenceGenerator( name = "ID_GEOLOCALIZACAO_SEQ", sequenceName = "GEOLOCALIZACAO_SEQ", allocationSize = 1 ) 
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ID_GEOLOCALIZACAO_SEQ" )
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation_type() {
		return location_type;
	}
	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}
	public String getWoeid() {
		return woeid;
	}
	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}
	public String getLatt_long() {
		return latt_long;
	}
	public void setLatt_long(String latt_long) {
		this.latt_long = latt_long;
	}
	
	@Override
	public int compareTo(Geolocalizacao arg0) {
		if(Integer.parseInt(this.distance) <= Integer.parseInt(arg0.distance)) {
			return -1;
		}else {
			return 1;
		}
	}
	
}
