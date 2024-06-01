package lv.venta.Service;

import java.util.ArrayList;

import lv.venta.Model.City;
import lv.venta.Model.Parcel;

public interface IParcelFilteringService {
	 public ArrayList<Parcel> selectAll() throws Exception;
	 public abstract ArrayList<Parcel>  selsectAllParcelsByCustomerId(long id) throws Exception;
	 public abstract ArrayList<Parcel>  selsectAllParcelsDeliveredByDriverIds(long id) throws Exception;
	 public abstract ArrayList<Parcel>  selsectAllParcelsDeliveredToCity(City City) throws Exception;
	 public abstract ArrayList<Parcel>  selsectAllParcelsPriceLessThan(float priceThreshold) throws Exception;
	 public abstract Parcel  insertNewParcelByCustomerCodeAndDriverIds(long customerId, long driverId) throws Exception;
	 public abstract Parcel  changeParcelDriverByParcelIdAndDriverId(long parcelId, long newDriverId) throws Exception;
	 public abstract float calculateIncomeOfParcelsByCustomerIdp(long customerId) throws Exception;
	 public abstract long  calculateHowManyParcelsNeedToDeliverToday() throws Exception;


}
