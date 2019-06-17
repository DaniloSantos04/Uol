package projeto.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.business.HistoricoBO;

@RestController
@RequestMapping("/historico")
public class HistoricoController {
	
	@Autowired
	private HistoricoBO historicoBO;
	
	@GetMapping
	public ResponseEntity<?> listaHistoricos() {
		return ResponseEntity.ok().body(historicoBO.listaHistoricos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarHistoricoClienteId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(historicoBO.buscarById(id));
	}
}
