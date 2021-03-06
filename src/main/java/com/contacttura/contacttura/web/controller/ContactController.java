package com.contacttura.contacttura.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.contacttura.contacttura.model.Contact;
import com.contacttura.contacttura.repository.ContactRepository;
import com.contacttura.contacttura.service.ContactService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactService contactService;

	@ApiOperation("Deve retornar uma lista de contatos.")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Contact> list() {
		return contactRepository.findAll();
	}

	@ApiOperation("Deve permitir salvar um contato.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contact save(@RequestBody Contact contact) {
		return contactService.save(contact);
	}

	@ApiOperation("Deve retornar um contato a partir de um id passado pelo consumidor da API.")
	@GetMapping("/{id}")
	public ResponseEntity<Contact> find(@PathVariable Long id) {

		Optional<Contact> contact = contactRepository.findById(id);

		if (!contact.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(contact.get());
	}

	@ApiOperation("Deve atualizar um contato a partir de um id passado pelo consumidor da API.")
	@PutMapping("/{id}")
	public ResponseEntity<Contact> update(@PathVariable Long id, @RequestBody Contact contact) {
		Optional<Contact> contactFound = contactRepository.findById(id);

		if (!contactFound.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(contact, contactFound.get(), "id");
		Contact savedContact = contactService.save(contactFound.get());

		return ResponseEntity.ok(savedContact);
	}

	@ApiOperation("Deve excluir um contato a partir de um id passado pelo consumidor da API.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Contact> delete(@PathVariable Long id) {
		Optional<Contact> contact = contactRepository.findById(id);

		if (!contact.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		contactService.remove(id);
		return ResponseEntity.noContent().build();
	}
}
