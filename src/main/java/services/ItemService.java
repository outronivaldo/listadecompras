package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Item;
import repositories.ItemRepository;
import services.exceptions.ObjectNotFoundException;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Item buscarPorCodigo(Integer codigo) {
		Optional<Item> obj = itemRepository.findById(codigo);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Item " + codigo + "não encontrado"));

	}
	
	public Item salvar(Item item) {
		item.setCodigo(null);
		return itemRepository.save(item);
	}
	
	public Item atualizar(Item item) {
		return itemRepository.saveAndFlush(item);
	}
	
	public void deletar(Integer codigo) {
		itemRepository.deleteById(codigo);
	}
	
	public List<Item> listar(){
		return itemRepository.findAll();
	}
}
