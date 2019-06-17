package projeto.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projeto.exception.NotFoundException;
import projeto.models.Historico;
import projeto.services.HistoricoService;
import projeto.vo.GeolocalizacaoVO;
import projeto.vo.HistoricoVO;

@Component
public class HistoricoBO {
	
	@Autowired
	private HistoricoService historicoService;
	
	public HistoricoVO buscarById(Integer id) {
		Historico historico = historicoService.getById(id);
		if (Optional.ofNullable(historico).isPresent()) {
			return buildHistoricoVO(historico);
		}else {
			throw new NotFoundException("Not Found");
		}
	}
	
	public List<HistoricoVO> listaHistoricos() {
		Iterable<Historico> historicos = historicoService.listAll();
		
		if (Optional.ofNullable(historicos).isEmpty()) {
			List<HistoricoVO> lista = new ArrayList<HistoricoVO>();
			historicos.forEach(cliente -> lista.add(buildHistoricoVO(cliente)));
			return lista;
		}else {
			throw new NotFoundException("Not Found");
		}
	}
	
	
	private HistoricoVO buildHistoricoVO(Historico historico) {
		if (!Optional.ofNullable(historico).isPresent()) {
			return null;
		}
		HistoricoVO retorno = new HistoricoVO(historico.getId(), historico.getMin_temp(), historico.getMax_temp(), new GeolocalizacaoVO(historico.getLocalidade()));
		return retorno;
	}

}
