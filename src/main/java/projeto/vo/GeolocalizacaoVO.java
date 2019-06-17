package projeto.vo;

import java.io.Serializable;

public class GeolocalizacaoVO implements Serializable{
	private static final long serialVersionUID = 8307074430601846184L;
	
	private Integer id;
	
	private String ipv4;
	
	private String continete;
	
	private String pais;
	
	private String cidade;
	
	private String latitude;
	
	private String longitude;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpv4() {
		return ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	public String getContinete() {
		return continete;
	}

	public void setContinete(String continete) {
		this.continete = continete;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
