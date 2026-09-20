package product.star.account.manager.contact;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping
public class ContactController {
    private final Map<Long,Contact> contacts = new LinkedHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping("/contacts")
    public List<Contact> getContacts(){
        return new ArrayList<>(contacts.values());
    }

    @GetMapping("/contact/{contactId}")
    public Contact getContact(@PathVariable long contactId){
        Contact contact = contacts.get(contactId);

        if(contact == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Contact not found"
            );
        }

        return contact;
    }

    @PostMapping("/contacts")
    public Contact createContact(@RequestBody Contact contact){
        Long id = idGenerator.incrementAndGet();

        contact.setId(id);
        contacts.put(id, contact);

        return contact;
    }

    @PutMapping("/contacts/{contactId}")
    public Contact updateContact(
            @PathVariable long contactId,
            @RequestBody Contact contact
    ){
        if (!contacts.containsKey(contactId)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Contact not found"
            );
        }

        contact.setId(contactId);
        contacts.put(contactId, contact);

        return contact;
    }
}
