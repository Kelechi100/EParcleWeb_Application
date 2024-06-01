package lv.venta.Controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.ui.Model;
import jakarta.validation.Valid;
import lv.venta.Model.Driver;
import lv.venta.Service.IFilteringService;

@Controller
@RequestMapping("driver/crud")
public class DriverCrudController {

    @Autowired
    private IFilteringService filteringService;

    @GetMapping("/all") // localhost:8080/driver/crud/all
    public String getDriverCrudAll(Model model) {
        try {
            ArrayList<Driver> dataFromService = filteringService.selectAllDriver();
            model.addAttribute("mypackage", dataFromService);
            return "show-all-driver-page"; // will show this html page in the web browser with mypackage data
        } catch (Exception e) {
            model.addAttribute("mypackage", e.getMessage());
            return "error-page"; // will show error-page.html page with exception message
        }
    }

    @GetMapping("/all/{id}") // localhost:8080/driver/crud/all/1
    public String getDriverCrudById(@PathVariable("id") long id, Model model) {
        try {
            Driver result = filteringService.selectDriverByIds(id);
            model.addAttribute("mypackage", result);
            return "show-one-driver-page";
        } catch (Exception e) {
            model.addAttribute("mypackage", e.getMessage());
            return "error-page"; // will show error-page.html page with exception message
        }
    }

    @GetMapping("/create") // localhost:8080/driver/crud/create
    public String getDriverCRUDCreate(Model model) {
        model.addAttribute("driver", new Driver());
        return "create-driver-page"; // will show create-driver-page.html page with default driver
    }

    @PostMapping("/create")
    public String postDriverCRUDCreate(@Valid Driver driver, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-driver-page"; // this will show the same html page
        } else {
            try {
                filteringService.insertNewDriver(driver.getName(), driver.getSurname(), driver.getPersonCode(),
                        driver.getLicenceNo(), driver.getExperienceInYears());
                return "redirect:/driver/crud/all"; // the endpoint localhost:8080/driver/crud/all will be called
            } catch (Exception e) {
                model.addAttribute("mypackage", e.getMessage());
                return "error-page"; // will show error-page.html page with exception message
            }
        }
    }

    @GetMapping("/update/{id}") // localhost:8080/driver/crud/update/1
    public String getDriverCRUDUpdateById(@PathVariable("id") long id, Model model) {
        try {
            Driver driverForUpdating = filteringService.selectDriverByIds(id);
            
                model.addAttribute("driver", driverForUpdating);
                model.addAttribute("id", id);
                return "update-driver-page"; // will show update-driver-page.html page with driverForUpdating
            
                
                
            
        } catch (Exception e) {
            model.addAttribute("mypackage", e.getMessage());
            return "error-page"; // will show error-page.html page with exception message
        }
    }

    @PostMapping("/update/{id}")
    public String postDriverCRUDUpdateById(@PathVariable("id") long id, @Valid Driver driver, BindingResult result,
                                           Model model) {
        if (result.hasErrors()) {
            return "update-driver-page";
        } else {
            try {
                filteringService.updateDriverByIds(id, driver.getName(), driver.getSurname(), driver.getPersonCode(),
                        driver.getLicenceNo(), driver.getExperienceInYears());
                return "redirect:/driver/crud/all/" + id;
            } catch (Exception e) {
                model.addAttribute("mypackage", e.getMessage());
                return "error-page"; // will show error-page.html page with exception message
            }
        }
    }

    @GetMapping("/delete/{id}") // localhost:8080/driver/crud/delete/2
    public String getDriverCRUDDeleteById(@PathVariable("id") long id, Model model) {
        try {
            filteringService.deleteDriverByIds(id);
            model.addAttribute("mypackage", filteringService.selectAllDriver());
            return "show-all-driver-page"; // will show this html page in the web browser with mypackage data
        } catch (Exception e) {
            model.addAttribute("mypackage", e.getMessage());
            return "error-page"; // will show error-page.html page with exception message
        }
    }
}
