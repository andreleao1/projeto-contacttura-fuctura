package com.contacttura.contacttura.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.contacttura.contacttura.model.Contact;
import com.contacttura.contacttura.repository.ContactRepository;
import com.contacttura.contacttura.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactService contactService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Contact> list() {
		return contactRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contact save(@RequestBody Contact contact) {
		return contactService.save(contact);
	}
}
