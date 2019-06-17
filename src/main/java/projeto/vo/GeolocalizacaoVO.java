package projeto.vo;

import java.io.Serializable;

import projeto.models.Localidade;

public class GeolocalizacaoVO implements Serializable{
	private static final long serialVersionUID = 8307074430601846184L;
	
	private Integer id;
	
	private String ipv4;
	
	private String continete;
	
	private String pais;
	
	private String cidade;
	
	private String latitude;
	
	private String longitude;
	
	
	public GeolocalizacaoVO() {
		
	}

	public GeolocalizacaoVO(Integer id, String ipv4, String continete, String pais, String cidade, String latitude,
			String longitude) {
		this.id = id;
		this.ipv4 = ipv4;
		this.continete = continete;
		this.pais = pais;
		this.cidade = cidade;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public GeolocalizacaoVO(Localidade local) {
		this.id = local.getId();
		this.ipv4 = local.getData().getIpv4();
		this.continete = local.getData().getContinent_name();
		this.pais = local.getData().getCity_name();
		this.cidade = local.getData().getCity_name();
		this.latitude = local.getData().getLatitude();
		this.longitude = local.getData().getLongitude();
	}

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
