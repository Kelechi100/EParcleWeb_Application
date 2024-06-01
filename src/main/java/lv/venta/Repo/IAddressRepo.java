package lv.venta.Repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.Model.Address;

public interface IAddressRepo extends CrudRepository<Address, Long> {

}
