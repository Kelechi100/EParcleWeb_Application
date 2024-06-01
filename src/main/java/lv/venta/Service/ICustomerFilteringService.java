package lv.venta.Service;

import java.util.ArrayList;

import lv.venta.Model.Address;
import lv.venta.Model.CustomerAsCompany;
import lv.venta.Model.CustomerAsPerson;

public interface ICustomerFilteringService {
	
	public ArrayList<CustomerAsCompany> selectAllCustomerAsCompany() throws  Exception;
	public void insertNewCustomerAsCompany(String title,  String companyRegNo, String phoneNo, Address address)throws Exception;
	public void  insertNewcustomerAsPerson(String personId, String phoneNo, Address address)throws Exception;
	public  void  addAddressToCustomerByCustomerId(long customerId, Address address) throws Exception;
	public ArrayList<CustomerAsPerson> selectAllCustomerAsPerson() throws Exception;
}





