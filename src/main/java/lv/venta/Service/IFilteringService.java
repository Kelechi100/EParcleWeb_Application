package lv.venta.Service;


import java.util.ArrayList;

import lv.venta.Model.Driver;


public interface IFilteringService { 
	 public abstract ArrayList<Driver>  selectAllDriver() throws  Exception;
	 public  Driver selectDriverByIds(long id) throws Exception;
	 public void deleteDriverByIds( long id) throws Exception;
	 public void insertNewDriver(String name, String surname, String personCode, String licenceNo, float experienceInYears) throws Exception;
	 public void  updateDriverByIds( long id ,String name, String surname, String personCode, String licenceNo, float experienceInYears) throws Exception;
	 
	 
	 
	 
	 
	 
	 
}


//