package it.uniroma3.siw.silph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.silph.model.Cliente;
import it.uniroma3.siw.silph.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente inserisci(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	@Transactional
	public List<Cliente> tutte(){
		return (List<Cliente>) this.clienteRepository.findAll();
	}

	public Cliente clientePerId(Long id){
		return this.clienteRepository.findById(id).get();
	}
}
