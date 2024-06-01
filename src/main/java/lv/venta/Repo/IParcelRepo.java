package lv.venta.Repo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import lv.venta.Model.City;

import lv.venta.Model.Parcel;

public interface IParcelRepo extends CrudRepository<Parcel, Long> {

	

	String parcelsToDeliverToday = null;

	ArrayList<Parcel> findByReceiverCompanyIdc(long id);

	ArrayList<Parcel> findByDriverIds(long id);

	ArrayList<Parcel> findByPriceLessThan(float priceThreshold);

	ArrayList<Parcel> findByReceiverPersonAddressCity(City city);

	

	

	void save(ArrayList<Parcel> anotherParcel);

	Optional<Parcel> findParcelByIdpa(long parcelId);


}
