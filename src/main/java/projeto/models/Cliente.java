package projeto.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable{
	private static final long serialVersionUID = -4413591751342846265L;

	//@JsonIgnore
	private Integer id;
	
	private String nome;
	
	private int idade;
	
	@JsonIgnore
	private Historico historico;
	
	public Cliente() {

	}

	public Cliente(Integer id, String nome, int idade) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
	}
	
	public Cliente(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 38, scale = 0)
	@SequenceGenerator( name = "ID_CLIENTE_SEQ", sequenceName = "CLIENTE_SEQ", allocationSize = 1 ) 
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ID_CLIENTE_SEQ" )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "ID_HISTORICO", referencedColumnName = "ID", nullable = false) })
	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

}
