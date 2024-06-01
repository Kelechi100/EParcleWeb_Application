package lv.venta.Service.Impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.Model.Address;
import lv.venta.Model.CustomerAsCompany;
import lv.venta.Model.CustomerAsPerson;

import lv.venta.Repo.ICustomerAsCompanyRepo;
import lv.venta.Repo.ICustomerAsPersonRepo;
import lv.venta.Service.ICustomerFilteringService;
@Service
public class FilteringCustomerServiceImp implements ICustomerFilteringService
{
	
	@Autowired
	private ICustomerAsCompanyRepo customerAsCompanyRepo;
	@Autowired
	private ICustomerAsPersonRepo customerAsPersonRepo;
	@Override
	public void insertNewCustomerAsCompany(String title, String companyRegNo, String phoneNo, Address address)
			throws Exception {
		
		if(title == null || companyRegNo == null || phoneNo == null || address == null)throw new Exception("input error");
		CustomerAsCompany newCompany = customerAsCompanyRepo.findByCompanyRegNoAndTitle(companyRegNo, title);
		if(newCompany != null) {
			newCompany.setTitle(newCompany.getTitle() + title);
			customerAsCompanyRepo.save(newCompany);
		}
		else {
			CustomerAsCompany  Cc2 = new CustomerAsCompany(title, companyRegNo, phoneNo, address);
			customerAsCompanyRepo.save(Cc2);
		}
	}

	@Override
	public void insertNewcustomerAsPerson(String personId, String phoneNo, Address address) throws Exception {
		if(personId == null || phoneNo == null || address == null)throw new Exception("Input error");
		CustomerAsPerson newPerson = customerAsPersonRepo.findByPersonIdAndAddress(personId,address);
		if(newPerson != null) {
			newPerson.setPhoneNO(newPerson.getPhoneNO() + phoneNo);
			customerAsPersonRepo.save(newPerson);
		}
		else
		{
			CustomerAsPerson  Cp1 = new CustomerAsPerson(personId, phoneNo, address);
			customerAsPersonRepo.save(Cp1);
		}
		
	}

	@Override
	public void addAddressToCustomerByCustomerId(long customerId, Address address) throws Exception {
		if(customerId  <= 0)throw new Exception("Id should be positive");
		CustomerAsPerson customer = customerAsPersonRepo.findByIdp(customerId);
		CustomerAsCompany newcustomer = customerAsCompanyRepo.findByIdc(customerId);
		if(customer == null) throw new Exception("customer not found");
		//Setting Address for the customer and saving it
		if(customer instanceof CustomerAsPerson) {
			customerAsPersonRepo.save((CustomerAsPerson) customer);
			
		}else if(newcustomer instanceof CustomerAsCompany) {
			customerAsCompanyRepo.save((CustomerAsCompany) newcustomer);
		}
		
	}
	@Override
	public ArrayList<CustomerAsPerson> selectAllCustomerAsPerson() throws  Exception{
		if(customerAsPersonRepo.count() == 0) throw new Exception("customerPerson Table is empty");
		ArrayList<CustomerAsPerson> result  = (ArrayList<CustomerAsPerson>) customerAsPersonRepo.findAll();
		return result;
			
	}
	@Override
	public ArrayList<CustomerAsCompany> selectAllCustomerAsCompany() throws  Exception{
		if(customerAsCompanyRepo.count() == 0) throw new Exception("customerPerson Table is empty");
		ArrayList<CustomerAsCompany> result  = (ArrayList<CustomerAsCompany>) customerAsCompanyRepo.findAll();
		return result;
			
	}

	
	

}
