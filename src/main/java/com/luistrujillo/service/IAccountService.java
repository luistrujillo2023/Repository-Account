package com.luistrujillo.service;

import com.luistrujillo.document.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountService {
	
	Mono<Account>registrar(Account account);
	Mono<Account>modificar(Account account);
	Flux<Account> listar();
	Mono<Account> listarPorId(String id);
    Mono<Void> eliminar(String id);

}
