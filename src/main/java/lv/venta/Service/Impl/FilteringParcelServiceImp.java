package lv.venta.Service.Impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lv.venta.Model.City;
import lv.venta.Model.CustomerAsPerson;
import lv.venta.Model.Driver;
import lv.venta.Model.Parcel;
import lv.venta.Repo.ICustomerAsPersonRepo;
import lv.venta.Repo.IDriverRepo;
import lv.venta.Repo.IParcelRepo;
import lv.venta.Service.IParcelFilteringService;
@Service
public class FilteringParcelServiceImp implements IParcelFilteringService {
	
	
	@Autowired
	private IParcelRepo parcelRepo;
	@Autowired
	private ICustomerAsPersonRepo customerAsPersonRepo;
	@Autowired
	private IDriverRepo driverRepo;
	
	
	
	@Override
	public  ArrayList<Parcel> selectAll() throws Exception{
		if(parcelRepo.count() == 0) throw new Exception("Parcels table is empty");
		ArrayList<Parcel> result = (ArrayList<Parcel>) parcelRepo.findAll();
		return result;
	}
	@Override
	public ArrayList<Parcel> selsectAllParcelsByCustomerId(long id) throws Exception {
		if(id < 1) throw new Exception("Id should be positive");
		if(!customerAsPersonRepo.existsById(id))
			throw new Exception("Customer with id (" + id + ")  does not exist");
		ArrayList<Parcel> result = parcelRepo.findByReceiverCompanyIdc(id);
		if(result.isEmpty())
			throw new Exception("Customer with id (" + id + ")  does not exist");
		
		return result;
	}

	@Override
	public ArrayList<Parcel> selsectAllParcelsDeliveredByDriverIds(long id) throws Exception {
		if(id < 1) throw new Exception("Id should be positive");
		if(!driverRepo.existsById(id))
			throw new Exception("Driver with id (" + id + ") is not in the system");
		ArrayList<Parcel> result = parcelRepo.findByDriverIds(id);
		if(result.isEmpty()) throw new Exception("Driver with id (" + id + ") is not in the system");
		
		return result;
	}

	@Override
	public ArrayList<Parcel> selsectAllParcelsDeliveredToCity(City city) throws Exception {
		 if(city == null )throw new Exception("City should not be empty");
		 ArrayList<Parcel> result = parcelRepo.findByReceiverPersonAddressCity(city);
		 return result;
		 
		
	}

	@Override
	public ArrayList<Parcel> selsectAllParcelsPriceLessThan(float priceThreshold) throws Exception {
		 if(priceThreshold < 0) throw new Exception("Price threshold should be positive");
		    return parcelRepo.findByPriceLessThan(priceThreshold);
	}

	@Override
	public Parcel insertNewParcelByCustomerCodeAndDriverIds(long customerId, long driverId) throws Exception {
		 if(customerId < 1 || driverId < 1) throw new Exception("Customer and driver ids should be positive");
		    
		    // Fetch customer and driver details from repositories based on their ids
		    CustomerAsPerson customer = customerAsPersonRepo.findById(customerId).orElse(null);
		    Driver driver = driverRepo.findById(driverId).orElse(null);
		    
		    if(customer == null || driver == null) throw new Exception("Customer or driver not found");
		    
		    // Create a new parcel and assign it to the provided customer and driver
		    Parcel newParcel = new Parcel(customer, driver);
		    parcelRepo.save(newParcel);
		    
		    return newParcel;
	}

	@Override
	public Parcel changeParcelDriverByParcelIdAndDriverId(long parcelId, long newDriverId) throws Exception {
		if(parcelId < 1 || newDriverId < 1) throw new Exception("Parcel id and new driver id should be positive");
	    
	    // Fetch the parcel and new driver details from repositories based on their ids
	    Optional<Parcel> optionalParcel= parcelRepo.findParcelByIdpa(parcelId);
	    Optional<Driver>optionalDriver = driverRepo.findByIds(newDriverId);
	    
	    if(!optionalParcel.isPresent() || !optionalDriver.isPresent()) 
	    	throw new Exception("Parcel or new driver not found");
	    
	    Parcel parcel = optionalParcel.get();
        Driver newDriver = optionalDriver.get();
	    // Change the driver of the parcel
	    parcel.setDriver(newDriver);
	    parcelRepo.save(parcel);
	    
	    return parcel;


	}

	@Override
	public  float calculateIncomeOfParcelsByCustomerIdp(long customerId) throws Exception {
		if(customerId < 1) throw new Exception("Customer id should be positive");
	    
	    // Fetch all parcels associated with the provided customer id
	    ArrayList<Parcel> parcels = parcelRepo.findByReceiverCompanyIdc(customerId);
	    float totalIncome = 0;
	    for(Parcel tempP: parcels)
	    	totalIncome += tempP.getPrice();
	    	
	    
	    return totalIncome;
	}

	@Override
	public long calculateHowManyParcelsNeedToDeliverToday() throws Exception {
		// Assuming you have a method to fetch parcels to be delivered today, implement it accordingly
	    ArrayList<Parcel> parcelsToDeliverToday = fetchParcelsToDeliverToday();
	    
	    // Count the number of parcels to be delivered today
	    return parcelsToDeliverToday.size();
	}

	private ArrayList<Parcel> fetchParcelsToDeliverToday() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	

}
