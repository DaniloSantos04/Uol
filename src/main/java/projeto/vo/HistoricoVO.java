package projeto.vo;

import java.io.Serializable;

public class HistoricoVO implements Serializable{
	private static final long serialVersionUID = 7461807845795941080L;

	private Integer id;
	
	private String min_temp;
	
	private String max_temp;
	
	private GeolocalizacaoVO localidade;
	
	public HistoricoVO( ) {
	}
	
	public HistoricoVO(Integer id, String min_temp, String max_temp, GeolocalizacaoVO localidade) {
		this.id = id;
		this.min_temp = min_temp;
		this.max_temp = max_temp;
		this.localidade = localidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMin_temp() {
		return min_temp;
	}

	public void setMin_temp(String min_temp) {
		this.min_temp = min_temp;
	}

	public String getMax_temp() {
		return max_temp;
	}

	public void setMax_temp(String max_temp) {
		this.max_temp = max_temp;
	}

	public GeolocalizacaoVO getLocalidade() {
		return localidade;
	}

	public void setLocalidade(GeolocalizacaoVO localidade) {
		this.localidade = localidade;
	}

}
