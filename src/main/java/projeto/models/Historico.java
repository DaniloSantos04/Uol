package projeto.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "HISTORICO")
public class Historico {
	
	private Integer id;
	
	private Set<Cliente> clientes = new HashSet<Cliente>(0);
	
	private Localidade localidade;
	
	private String min_temp;
	
	private String max_temp;
	
	
	public Historico() {
		this.min_temp = null;
		this.max_temp = null;
	}
	
	public Historico(Integer id, Localidade localidade, String min_temp, String max_temp) {
		this.id = id;
		this.localidade = localidade;
		this.min_temp = min_temp;
		this.max_temp = max_temp;
	}
	
	public Historico(Integer id, String min_temp, String max_temp) {
		this.id = id;
		this.min_temp = min_temp;
		this.max_temp = max_temp;
	}
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 38, scale = 0)
	@SequenceGenerator( name = "ID_HISTORICO_SEQ", sequenceName = "HISTORICO_SEQ", allocationSize = 1 ) 
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ID_HISTORICO_SEQ" )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "ID_LOCALIDADE", referencedColumnName = "ID", nullable = false) })
	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "historico")
	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}
}
