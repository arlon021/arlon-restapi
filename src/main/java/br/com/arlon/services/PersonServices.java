package br.com.arlon.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arlon.exceptions.ResourceNotFoundException;
import br.com.arlon.model.Person;
import br.com.arlon.respositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository personRepository;
	
	public List<Person> findAll() {
		
		logger.info("Fiding all peaple!");
		return personRepository.findAll();
	}
	
	public Person findById(Long id) {
		
		logger.info("Fiding one person!");
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	public Person create(Person person) {
		
		logger.info("Creating one person");
		return personRepository.save(person);
	}
	
	public Person update(Person person) {
		
		logger.info("Update a person");
		
		Person entity = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFistName(person.getFistName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return personRepository.save(entity);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person");
		
		Person entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		personRepository.delete(entity);
	}
	

}
