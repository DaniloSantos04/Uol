package projeto.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintDefinitionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import projeto.exception.NotFoundException;
import projeto.integration.IpVigilanteClient;
import projeto.integration.MetaweatherClient;
import projeto.models.Cliente;
import projeto.models.Clima;
import projeto.models.Data;
import projeto.models.Geolocalizacao;
import projeto.models.Historico;
import projeto.models.Localidade;
import projeto.services.ClienteService;
import projeto.vo.ClienteVO;
import projeto.vo.GeolocalizacaoVO;
import projeto.vo.HistoricoVO;
import projeto.vo.RetornoVO;

@Component
public class ClienteBO {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private IpVigilanteClient ipVigilante;
	
	@Autowired
	private MetaweatherClient metaweather;
	
	@Transactional
	public RetornoVO salvar(Cliente cliente) {
		
		if (!Optional.ofNullable(cliente.getId()).isPresent()) {
			cliente = new Cliente(cliente.getNome(), cliente.getIdade());
			
			Historico hist = new Historico();
			
			Localidade localidade = ipVigilante.getLocalidade();
			
			List<Geolocalizacao> listaGeolocalizacao = metaweather.obterLocalizacao(String.format("%s,%s", localidade.getData().getLatitude(), localidade.getData().getLongitude()));
			
			String pattern = "yyyy/MM/dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String data = simpleDateFormat.format(new Date());
			
			List<Clima> listaClimas = new ArrayList<Clima>();
			
			int i = 0;
			while (listaClimas.size() == 0) {
				listaClimas.addAll(metaweather.obterClima(listaGeolocalizacao.get(i).getWoeid(), data));
				i++;
			}
					
			listaClimas.forEach(clima ->{
				if(hist.getMin_temp() == null || Double.parseDouble(clima.getMin_temp()) < Double.parseDouble(hist.getMin_temp()))
					hist.setMin_temp(clima.getMin_temp());
				
				if(hist.getMax_temp() == null || Double.parseDouble(clima.getMax_temp()) > Double.parseDouble(hist.getMax_temp()))
					hist.setMax_temp(clima.getMax_temp());		
			});
			
			hist.getClientes().add(cliente);
			hist.setLocalidade(localidade);
			localidade.getHistoricos().add(hist);
			cliente.setHistorico(hist);
			
			Cliente clienteSave = clienteService.save(cliente);
			
			return buildRetornoVO(clienteSave);

			
		}else {
			throw new ConstraintDefinitionException();
		}
	}

	public List<RetornoVO> listaClientes() {
		Iterable<Cliente> clientes = clienteService.listAll();
		
		if (Optional.ofNullable(clientes).isEmpty()) {
			List<RetornoVO> lista = new ArrayList<RetornoVO>();
			clientes.forEach(cliente -> lista.add(buildRetornoVO(cliente)));
			return lista;
		}else {
			throw new NotFoundException("Não encontrado");
		}
	}
	
	public RetornoVO buscarClienteId(Integer id) {
		Cliente cliente = clienteService.getById(id);
		if (Optional.ofNullable(cliente).isPresent()) {
			return buildRetornoVO(cliente);
		}else {
			throw new NotFoundException("Não encontrado");
		}
	}

	public void deletar(Integer id) {
		try {
			clienteService.delete(id);
		}catch (Exception e) {
			throw new NotFoundException("Not Found");
		}
	}

	public RetornoVO  atualizar(RetornoVO clienteVO) {
		Cliente cliente = clienteService.getById(clienteVO.getCliente().getId());
		if (Optional.ofNullable(cliente).isPresent()) {
			
			Cliente clienteAtualizado = new Cliente(clienteVO.getCliente().getId(), clienteVO.getCliente().getNome(), clienteVO.getCliente().getIdade());
			Historico historico = new Historico(clienteVO.getHistorico().getId(), clienteVO.getHistorico().getMin_temp(), clienteVO.getHistorico().getMax_temp());
			
			Localidade localidade = new Localidade(cliente.getHistorico().getLocalidade().getId(), cliente.getHistorico().getLocalidade().getData());
			localidade.setData(new Data(clienteVO.getHistorico().getLocalidade().getIpv4(), clienteVO.getHistorico().getLocalidade().getContinete(), clienteVO.getHistorico().getLocalidade().getPais(), clienteVO.getHistorico().getLocalidade().getCidade(), clienteVO.getHistorico().getLocalidade().getLatitude(), clienteVO.getHistorico().getLocalidade().getLongitude()));
			
			historico.getClientes().add(clienteAtualizado);
			historico.setLocalidade(localidade);
			localidade.getHistoricos().add(historico);
			clienteAtualizado.setHistorico(historico);
			
			cliente = clienteService.save(clienteAtualizado);
			
			return buildRetornoVO(clienteAtualizado);
			
		
		}else {
			throw new NotFoundException("Not Found");
		}
	}
	
	private RetornoVO buildRetornoVO(Cliente cliente) {
		if (!Optional.ofNullable(cliente).isPresent()) {
			return null;
		}

		RetornoVO retorno = new RetornoVO(new ClienteVO(), new HistoricoVO());

		// Montando VO Cliente
		retorno.getCliente().setId(cliente.getId());
		retorno.getCliente().setNome(cliente.getNome());
		retorno.getCliente().setIdade(cliente.getIdade());

		// Montar VO Historico
		retorno.getHistorico().setId(cliente.getHistorico().getId());
		retorno.getHistorico().setMin_temp(cliente.getHistorico().getMin_temp());
		retorno.getHistorico().setMax_temp(cliente.getHistorico().getMax_temp());
		
		//Corrigir VO e Mappear Data em Localidade
		GeolocalizacaoVO local = new GeolocalizacaoVO();
		
		local.setId(cliente.getHistorico().getLocalidade().getId());
		local.setCidade(cliente.getHistorico().getLocalidade().getData().getCity_name());
		local.setContinete(cliente.getHistorico().getLocalidade().getData().getContinent_name());
		local.setIpv4(cliente.getHistorico().getLocalidade().getData().getIpv4());
		local.setPais(cliente.getHistorico().getLocalidade().getData().getCountry_name());
		local.setLatitude(cliente.getHistorico().getLocalidade().getData().getLatitude());
		local.setLongitude(cliente.getHistorico().getLocalidade().getData().getLongitude());
		
		
		retorno.getHistorico().setLocalidade(local);
		

		return retorno;
	}
}
