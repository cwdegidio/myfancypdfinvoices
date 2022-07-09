package com.cwdegidio.myfancypdfinvoices.web;

import com.cwdegidio.myfancypdfinvoices.dto.InvoiceDTO;
import com.cwdegidio.myfancypdfinvoices.model.Invoice;
import com.cwdegidio.myfancypdfinvoices.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyFancyPdfInvoicesController {

    private final InvoiceService invoiceService;

    public MyFancyPdfInvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    // @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<Invoice> index() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.create(invoiceDTO.getUserId(), invoiceDTO.getAmount());
    }
}
