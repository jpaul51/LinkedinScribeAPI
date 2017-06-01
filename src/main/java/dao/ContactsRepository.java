package dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import model.Contacts;

@Component
public interface ContactsRepository  extends CrudRepository<Contacts, Long>{

}
