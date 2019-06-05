package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.ListaDeCompras;
import repositories.ListaDeComprasRepository;
import services.exceptions.ObjectNotFoundException;

@Service
public class ListaService {
	@Autowired
	private ListaDeComprasRepository listaRepository;

	public ListaDeCompras buscarPorCodigo(Integer codigo) {
		Optional<ListaDeCompras> obj = listaRepository.findById(codigo);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Lista " + codigo + "não encontrada"));

	}

	public ListaDeCompras salvar(ListaDeCompras lista) {
		lista.setCodigo(null);
		return listaRepository.save(lista);
	}

	public ListaDeCompras atualizar(ListaDeCompras lista) {
		return listaRepository.saveAndFlush(lista);
	}

	public void deletar(Integer codigo) {
		listaRepository.deleteById(codigo);
	}

	public List<ListaDeCompras> listar() {
		return listaRepository.findAll();
	}
}
