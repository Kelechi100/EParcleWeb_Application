package lv.venta.Controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.Model.City;


import lv.venta.Model.Parcel;

import lv.venta.Service.Impl.FilteringParcelServiceImp;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/parcel")
public class ParcelController {
	@Autowired
	private FilteringParcelServiceImp filteringParcelservice;
	
	 @GetMapping("/all/pages") //localhost:8080/parcel/all/pages
	    public String showAllParcelPages(Model model) {
	       
	        try {
	        	
	        	ArrayList<Parcel> parcels = filteringParcelservice.selectAll();
	        	model.addAttribute("mypackage", parcels);
	        	return "parcel-all-page";
	 	        
	 	    
	        } catch (Exception e) {
				 model.addAttribute("mypackage", e.getMessage());
					return "error-parcel-page";// will show error-page.html page with exception message
				
			}
	        	
	    }
	       

	
	// Get- /parcel/show/customer/{id}
	 @GetMapping("/show/customer/{id}") // localhost:8080/parcel/show/customer/1
	 public  String getParcelBycustomerId(@PathVariable ("id") long id, Model model){
		 try
		 {
			ArrayList<Parcel> parcels = filteringParcelservice.selsectAllParcelsByCustomerId(id);
			if(parcels.isEmpty()) {
				throw new Exception("Customer with id (" + id + ") does not exist");
			}
			model.addAttribute("mypackage", parcels);
			//model.addAttribute("customer", parcels);
			return "show-all-parcelby-customerid-page";
		 }
		 catch (Exception e) {
			 model.addAttribute("mypackage", e.getMessage());
				return "error-parcel-page";// will show error-page.html page with exception message
			
		}
	 }

	 
	 @GetMapping("/show/driver/{id}")// localhost:8080/parcel/show/driver/1
	 public String getParcelByDriverId(@PathVariable("id") long id, Model model) {
		 try
		 {
			 ArrayList<Parcel> result  = filteringParcelservice.selsectAllParcelsDeliveredByDriverIds(id);
			 model.addAttribute("mypackage", result);
			 return "show-all-parcelby-driverid-page";
		 }
		 catch (Exception e) {
			 model.addAttribute("mypackage", e.getMessage());
				return "error-parcel-page";// will show error-page.html page with exception message
			
		}
	 }
	 
	 //Get- /parcel/show/price/{threshold}
	 @GetMapping("/show/price/{threshold}")//localhost:8080/parcel/show/price/300
	 public String ShowParcelsByThreshold(@PathVariable("threshold") float threshold, Model model) {
		 try
		 {
			 ArrayList<Parcel> parcels = filteringParcelservice.selsectAllParcelsPriceLessThan(threshold);
			 model.addAttribute("mypackage", parcels);
			 return "show-parcelby-threshold-page";
			 
		 }
		 catch (Exception e) {
			 model.addAttribute("mypackage", e.getMessage());
				return "error-parcel-page";// will show error-page.html page with exception message
			
		}
	 }
	 
	 @GetMapping("/show/city/{cityparam}") //localhost:8080/parcel/show/city/Riga
	 public String getparcelBycity(@PathVariable("cityparam") City city, Model model) {
		 try
		 {
			 ArrayList<Parcel> parcels = filteringParcelservice.selsectAllParcelsDeliveredToCity(city);
			 model.addAttribute("mypackage", parcels);
			 return "show-parcelsby-city-page";
		 }
		 catch (Exception e) {
			 model.addAttribute("mypackage", e.getMessage());
				return "error-parcel-page";// will show error-page.html page with exception message
			
		}
	 }
	 
	
	 
	 


	 
	// g. Get- /parcel/calculate/income/{customerid}
	    @GetMapping("/calculate/income/{customerid}")// localhost:8080/parcel/calculate/income/1
	    public String calculateCustomerIncome(@PathVariable("customerid") long customerId, Model model) {
	        try {
	            double income =filteringParcelservice.calculateIncomeOfParcelsByCustomerIdp(customerId);
	            model.addAttribute("income", income);
	            return "show-customer-income";
	        } catch (Exception e) {
	            model.addAttribute("errorMessage", e.getMessage());
	            return "error-parcel-page";
	        }
	    }
	    
	 // h. Get- /parcel/calculate/count/today
	    @GetMapping("/calculate/count/today") // localhost:8080/parcel/calculate/count/today
	    public String calculateParcelCountToday(Model model) {
	        try {
	            long count = filteringParcelservice.calculateHowManyParcelsNeedToDeliverToday();
	            model.addAttribute("count", count);
	            return "show-parcel-count-today";
	        } catch (Exception e) {
	            model.addAttribute("errorMessage", e.getMessage());
	            return "error-parcel-page";
	        }
	    }
	    
	    
	   

	
	    // Show form to add a new parcel
	    @GetMapping("/add") //localhost:8080/parcel/add
	    public String showAddParcelForm(Model model) {
	        model.addAttribute("parcel", new Parcel());
	        return "parcel-add-page";
	    }

	    // Add a new parcel using insertNewParcelByCustomerCodeAndDriverIds
	    @PostMapping("/add")
	    public String addParcel(@RequestParam long customerId, @RequestParam long driverId, @ModelAttribute("parcel") Parcel parcel,BindingResult result, Model model) {
	    	if(result.hasErrors()) {
	    		return "parcel-add-page";
	    	}else {
	    		try {
		        	
		            filteringParcelservice.insertNewParcelByCustomerCodeAndDriverIds(customerId, driverId);
		            return "redirect:parcel/all/pages";
		        } catch (Exception e) {
		            model.addAttribute("errorMessage", e.getMessage());
		            return "error-parcel-page";
		        }
	    		
	    	}
	        
	        
	    }
}











