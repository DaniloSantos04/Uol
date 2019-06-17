package projeto.web.controller;


import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.business.ClienteBO;
import projeto.models.Cliente;
import projeto.vo.RetornoVO;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteBO clienteBO;
	
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Cliente cliente) throws URISyntaxException {	
		RetornoVO clienteSave = clienteBO.salvar(cliente);
		return ResponseEntity
				.created(new URI("localhost:8080/clientes/"+clienteSave.getCliente().getId()))
				.body(clienteSave);
	}
	
	
	@PutMapping()
	public ResponseEntity<?> atualizar(@RequestBody RetornoVO cliente) {
		return ResponseEntity.ok().body(clienteBO.atualizar(cliente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarClienteId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(clienteBO.buscarClienteId(id));
	}
	
	
	@GetMapping
	public ResponseEntity<?> listaClientes() {
		return ResponseEntity.ok().body(clienteBO.listaClientes());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Integer id) {
		clienteBO.deletar(id);
		return ResponseEntity.ok().build();
	}
	
}
