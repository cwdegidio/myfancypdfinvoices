package com.cwdegidio.myfancypdfinvoices.service;

import com.cwdegidio.myfancypdfinvoices.model.Invoice;
import com.cwdegidio.myfancypdfinvoices.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private final UserService userService;
    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    public List<Invoice> findAll() {
        return invoices;
    }

    public Invoice create(String userId, Integer amount) {
        User user = userService.findById(userId);

        if (user == null) {
            throw new IllegalStateException();
        }

        // TODO create pdf creation and storing it on the network server
        Invoice invoice =  new Invoice(userId, amount, "http://www.aficanu.edu/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }

    @PostConstruct
    public void init() {
        System.out.println("Fetching PDF Template from S3...");
        // TODO download from S3 and save locally
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Deleting downloaded templates...");
        // TODO actual deletion of PDFs
    }
}
