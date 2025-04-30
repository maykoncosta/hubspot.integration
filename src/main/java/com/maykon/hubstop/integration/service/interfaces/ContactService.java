package com.maykon.hubstop.integration.service.interfaces;

import com.maykon.hubstop.integration.model.dto.ContactRequest;

public interface ContactService {
    /**
     * Creates a contact in HubSpot using the provided request data.
     *
     * @param request The contact request containing the necessary information to create a contact.
     */
    void createContact(ContactRequest request);
}
