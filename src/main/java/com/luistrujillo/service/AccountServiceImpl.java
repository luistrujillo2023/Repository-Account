package com.luistrujillo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.luistrujillo.document.Account;
import com.luistrujillo.repo.IAccountRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountServiceImpl  implements IAccountService {
	
	@Autowired
	private IAccountRepo repo;
	
	

	@Override
	public Mono<Account> registrar(Account account) {
		return repo.save(account);
	}

	@Override
	public Mono<Account> modificar(Account account) {
		return repo.save(account);
	}

	@Override
	public Flux<Account> listar() {
		return repo.findAll();
	}

	@Override
	public Mono<Account> listarPorId(String id) {
		return repo.findById(id);
	}

	@Override
	public Mono<Void> eliminar(String id) {
		return repo.deleteById(id);
	}

}
