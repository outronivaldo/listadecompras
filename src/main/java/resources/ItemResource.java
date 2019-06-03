package resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import domain.Item;
import services.ItemService;

@RestController
@RequestMapping(value = "/itens")
public class ItemResource {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<?> buscaPorCodigo(@PathVariable Integer codigo) {
		Item item = itemService.buscarPorCodigo(codigo);
		return ResponseEntity.ok().body(item);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody Item item) {
		item = itemService.salvar(item);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(item.getCodigo())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@PathVariable Integer codigo, @RequestBody Item item) {
		item.setCodigo(codigo);
		item = itemService.atualizar(item);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable Integer codigo){
		itemService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listar(){
		List<Item> lista = itemService.listar();
		return ResponseEntity.ok().body(lista);
}
	

}
