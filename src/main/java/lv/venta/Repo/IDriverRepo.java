package lv.venta.Repo;



import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import lv.venta.Model.Driver;

public interface IDriverRepo extends CrudRepository<Driver, Long> {

	ArrayList<Driver> findAllByIds(long ids);

	

	

	void deleteAllByIdsIn(ArrayList<Driver> driverForDeleting);



	Driver findByPersonCodeAndLicenceNo(String personCode, String licenceNo);





	Optional<Driver> findByIds(long newDriverId);





	

	
}
