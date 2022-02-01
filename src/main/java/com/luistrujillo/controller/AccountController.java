package com.luistrujillo.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luistrujillo.document.Account;
import com.luistrujillo.service.IAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/account")
@RequestMapping(value = "/api/v1/account")
public class AccountController {
	
	@Autowired
	private IAccountService service; 
	
	// metodo listar 
		@GetMapping
		public Mono<ResponseEntity<Flux<Account>>> listar(){		
			Flux<Account> fxAccount = service.listar();
			
			return Mono.just(ResponseEntity
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(fxAccount)
					);
		}
	
		 //metodo listar por id
		@GetMapping("/{id}")
		public Mono<ResponseEntity<Account>> listarPorId(@PathVariable("id") String id){
			return service.listarPorId(id)
					.map(c -> ResponseEntity
							.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(c)
							)				
					.defaultIfEmpty(ResponseEntity.notFound().build());
		}
		
		//metodo registar 
		@PostMapping
		public Mono<ResponseEntity<Account>> registrar(@RequestBody Account a, final ServerHttpRequest req){
			return service.registrar(a)
					.map(cu -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(cu.getId())))
							.contentType(MediaType.APPLICATION_JSON)
							.body(cu)
						);
		}
		
		
		//metodo modificar 
		
		@PutMapping("/{id}")
		public Mono<ResponseEntity<Account>> modificar(@PathVariable("id") String id, @RequestBody Account c){
			
			Mono<Account> monoBody = Mono.just(c);
			Mono<Account> monoBD = service.listarPorId(id);
			
			return monoBD
					.zipWith(monoBody, (bd, cu) -> {
						bd.setId(id);
						bd.setUsername(cu.getAccountNumber());								
						return bd;
					})
					.flatMap(service::modificar)
					.map(cu -> ResponseEntity.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(cu))
					.defaultIfEmpty(new ResponseEntity<Account>(HttpStatus.NOT_FOUND));
		}
		
		
		
		//metodo eliminar 
		
		@DeleteMapping("/{id}")
		public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id){
			return service.listarPorId(id)
					.flatMap(p -> {
						return service.eliminar(p.getId())
								.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
					})				
					.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
		}
		
		
		
		
		
}
