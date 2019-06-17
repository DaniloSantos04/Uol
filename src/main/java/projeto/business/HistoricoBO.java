package projeto.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projeto.exception.NotFoundException;
import projeto.models.Historico;
import projeto.services.HistoricoService;

@Component
public class HistoricoBO {
	
	@Autowired
	private HistoricoService historicoService;
	
	public Historico buscarById(Integer id) {
		return historicoService.getById(id);
	}
	
	public List<Historico> listaHistoricos() {
		Iterable<Historico> historicos = historicoService.listAll();
		
		if (!Optional.ofNullable(historicos).isPresent()) {
			List<Historico> lista = new ArrayList<Historico>();
			historicos.forEach(hist -> lista.add(new Historico(hist.getId(), hist.getLocalidade(), hist.getMin_temp(), hist.getMax_temp())));
			
			return lista;
		}else {
			throw new NotFoundException("Not Found");
		}
	}

}
