package lv.venta.Service.Impl;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.Model.Driver;
import lv.venta.Repo.IDriverRepo;
import lv.venta.Service.IFilteringService;

@Service
public class FilteringServiceImpl implements IFilteringService {
	@Autowired
	private IDriverRepo driverRepo;
	
	

	@Override
	public ArrayList<Driver> selectAllDriver() throws Exception {
		if(driverRepo.count() == 0) throw new Exception("Driver Table is empty");
		ArrayList<Driver> result  = (ArrayList<Driver>) driverRepo.findAll();
		return result;
		
	}

	@Override
	public Driver selectDriverByIds(long id) throws Exception {
		if(id <= 0)throw new Exception("Id should be positive");
		if(driverRepo.existsById(id))
			return driverRepo.findAllByIds(id).get(0);
			
		throw new Exception("Driver with Id (" + id + ") is not in the driver table");
	}

	@Override
	public void deleteDriverByIds(long id) throws Exception {
		Driver  driverForDeleting = selectDriverByIds(id);
		//I need to get all parcels for this driver, Create a for each loop and go into every parcel, and then set driver to null
		// and save every parcel in database.
		driverRepo.delete(driverForDeleting);
		
		
		
	}

	@Override
	public void  insertNewDriver(String name, String surname, String personCode, String licenceNo, float experienceInYears) throws Exception {
		if(name == null || surname == null|| personCode == null|| licenceNo ==null ||  experienceInYears < 0)
			throw new Exception("Input error");
		Driver driverFromDB = driverRepo.findByPersonCodeAndLicenceNo(personCode,licenceNo);
		// checking if driver exists in Database
		if(driverFromDB != null) {
			driverFromDB.setName(driverFromDB.getName() + name);
			driverRepo.save(driverFromDB);
		}
		else
		{
			Driver newDriver = new Driver(name, surname, personCode, licenceNo, experienceInYears);
			driverRepo.save(newDriver); // this will save in the database layer
		}
		
		
	}

	@Override
	public void updateDriverByIds( long id,String name, String surname, String personCode, String licenceNo, float experienceInYears) throws Exception {
		if(name == null || surname == null|| personCode == null|| licenceNo ==null ||  experienceInYears < 0)
			throw new Exception("Input error");
		Driver updatingDriver = selectDriverByIds(id);
		updatingDriver.setName(name);
		updatingDriver.setSurname(surname);
		updatingDriver.setPersonCode(personCode);
		updatingDriver.setLicenceNo(licenceNo);
		updatingDriver.setExperienceInYears(experienceInYears);
		
		
		driverRepo.save(updatingDriver);
		
		
		
		
	}

	



}
