package lv.venta.Repo;

import org.springframework.data.repository.CrudRepository;


import lv.venta.Model.CustomerAsCompany;




public interface ICustomerAsCompanyRepo extends CrudRepository<CustomerAsCompany, Long> {

	CustomerAsCompany findByCompanyRegNoAndTitle(String companyRegNo, String title);

	CustomerAsCompany findByIdc(long customerId);

	
	
	

}
