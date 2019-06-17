package projeto.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.business.HistoricoBO;
import projeto.models.Historico;

@RestController
@RequestMapping("/historico")
public class HistoricoController {
	
	@Autowired
	private HistoricoBO historicoBO;
	
	@GetMapping
	public List<Historico> listaHistoricos() {
		return historicoBO.listaHistoricos();
	}

	@GetMapping("/{id}")
	public Historico buscarHistoricoClienteId(@PathVariable Integer id) {
		return historicoBO.buscarById(id);
	}
}
