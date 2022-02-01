package com.luistrujillo.repo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.luistrujillo.document.Account;

public interface IAccountRepo   extends ReactiveMongoRepository<Account,String> {

}
