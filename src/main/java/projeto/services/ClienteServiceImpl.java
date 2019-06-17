package projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto.models.Cliente;
import projeto.repository.ClienteRepository;

@Service
@CacheConfig(cacheNames = "cliente")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
    private ClienteRepository clienteRepository;
	
	@Override
	//@Cacheable(unless="#result==null")
	public Iterable<Cliente> listAll() {
		return clienteRepository.findAll();
	}

	@Override
	@Cacheable(key= "#id", unless="#result==null")
	@Transactional(readOnly = true)
	public Cliente getById(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	@CachePut(key= "#entity.id", unless="#result==null")
	public Cliente save(Cliente entity) {
			return clienteRepository.save(entity);
	}

	@Override
	@CacheEvict(key= "#id")
	public void delete(Integer id) {
		clienteRepository.deleteById(id);
	}

	@Override
	@CachePut(key= "#entity.id", unless="#result==null")
	public Cliente update(Cliente entity) {
		Cliente cliente = getById(entity.getId());
		if(cliente != null ) {
			return clienteRepository.save(entity);
		}
		return null;
	}

}
