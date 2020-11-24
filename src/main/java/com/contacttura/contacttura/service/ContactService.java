package com.contacttura.contacttura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contacttura.contacttura.model.Contact;
import com.contacttura.contacttura.repository.ContactRepository;

@Service
public class ContactService implements IContactService{

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public void remove(Long id) {
		contactRepository.deleteById(id);
	}

}
