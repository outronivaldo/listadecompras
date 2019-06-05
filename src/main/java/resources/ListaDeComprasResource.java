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

import domain.ListaDeCompras;
import services.ListaService;

@RestController
@RequestMapping(value = "/listas")
public class ListaDeComprasResource {

	@Autowired
	private ListaService listaService;

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<?> buscaPorCodigo(@PathVariable Integer codigo) {
		ListaDeCompras lista = listaService.buscarPorCodigo(codigo);
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody ListaDeCompras lista) {
		lista = listaService.salvar(lista);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(lista.getCodigo())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@PathVariable Integer codigo, @RequestBody ListaDeCompras lista) {
		lista.setCodigo(codigo);
		lista = listaService.atualizar(lista);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable Integer codigo){
		listaService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listar(){
		List<ListaDeCompras> lista = listaService.listar();
		return ResponseEntity.ok().body(lista);
	}
}
