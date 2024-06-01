package lv.venta.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.Model.CustomerAsCompany;
import lv.venta.Model.CustomerAsPerson;

import lv.venta.Service.Impl.FilteringCustomerServiceImp;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private FilteringCustomerServiceImp filtercustomerService;
	
	
	 @GetMapping("/all/person") // localhost:8080/customer/all/person
	    public String getcustomerCrudAll(Model model) {
	        try {
	            ArrayList<CustomerAsPerson> customerPerson = filtercustomerService.selectAllCustomerAsPerson();
	            model.addAttribute("mypackage", customerPerson);
	            return "show-all-person-page"; // will show this html page in the web browser with mypackage data
	        } catch (Exception e) {
	            model.addAttribute("mypackage", e.getMessage());
	            return "error-page"; // will show error-page.html page with exception message
	        }
	    }

	
	@GetMapping("/all/company") // localhost:8080/customer/all/company
	   public String getcustomerCompanyAll(Model model) {
	       try {
	           ArrayList<CustomerAsCompany> customerCompany = filtercustomerService.selectAllCustomerAsCompany();
	           model.addAttribute("mypackage", customerCompany);
	           return "show-all-company-page"; // will show this html page in the web browser with mypackage data
	       } catch (Exception e) {
	           model.addAttribute("mypackage", e.getMessage());
	           return "error-page"; // will show error-page.html page with exception message
	       }
	   }
	@GetMapping("/person/create") // localhost:8080/customer/person/create
    public String getinsertNewCustomerAsPerson(Model model) {
        model.addAttribute("person", new CustomerAsPerson());
        return "newcustomer-asperson"; // will show create-customer-page.html page with default customer
    }

    @PostMapping("/person/create")
    public String postinsertNewCustomerAsPerson(@Valid CustomerAsPerson customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newcustomer-asperson"; // this will show the same html page
        } else {
            try {
                filtercustomerService.insertNewcustomerAsPerson(customer.getPersonId(), customer.getPhoneNO(), customer.getAddress());
                       
                return "redirect:/customer/all/person"; // the endpoint localhost:8080/customer/crud/all will be called
            } catch (Exception e) {
                model.addAttribute("mypackage", e.getMessage());
                return "error-customer-page"; // will show error-page.html page with exception message
            }
        }
    }
    
    @GetMapping("/create") // localhost:8080/customer/create
    public String getinsertNewCustomerAsCompany(Model model) {
        model.addAttribute("person", new CustomerAsPerson());
        return "newcustomer-ascompany"; // will show create-customer-page.html page with default customer
    }

    @PostMapping("/create")
    public String postinsertNewCustomerAsCompany(@Valid CustomerAsCompany customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newcustomer-ascompany"; // this will show the same html page
        } else {
            try {
                filtercustomerService.insertNewCustomerAsCompany(customer.getTitle(), customer.getCompanyRegNo(), customer.getPhoneNo(), customer.getAddress());
                       
                return "redirect:/customer/all/company"; // the endpoint localhost:8080/customer/crud/all will be called
            } catch (Exception e) {
                model.addAttribute("mypackage", e.getMessage());
                return "error-customer-page"; // will show error-page.html page with exception message
            }
        }
    }
	
}
