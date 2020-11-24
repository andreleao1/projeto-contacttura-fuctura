package com.contacttura.contacttura.service;

import com.contacttura.contacttura.model.Contact;

public interface IContactService {

	public Contact save(Contact contact);

	public void remove(Long id);
}
