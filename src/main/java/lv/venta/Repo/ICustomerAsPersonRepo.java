package lv.venta.Repo;




import org.springframework.data.repository.CrudRepository;

import lv.venta.Model.Address;
import lv.venta.Model.CustomerAsPerson;



public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson , Long> {

	CustomerAsPerson findByIdp(long customerId);

	CustomerAsPerson findByPersonIdAndAddress(String personId, Address address);





}
