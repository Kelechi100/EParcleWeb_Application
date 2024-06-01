package lv.venta;

import lv.venta.Model.Address;
import lv.venta.Model.City;
import lv.venta.Model.CustomerAsCompany;
import lv.venta.Model.CustomerAsPerson;
import lv.venta.Model.Size;

import lv.venta.Model.Driver;
import lv.venta.Model.Parcel;
import lv.venta.Repo.IAddressRepo;
import lv.venta.Repo.ICustomerAsCompanyRepo;
import lv.venta.Repo.ICustomerAsPersonRepo;
import lv.venta.Repo.IDriverRepo;
import lv.venta.Repo.IParcelRepo;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;





@SpringBootApplication
public class WebEParcelSystemApplication {
	

    public static void main(String[] args) {
        SpringApplication.run(WebEParcelSystemApplication.class, args);
    }

	protected CustomerAsCompany sender1;

    @Bean
   
    public CommandLineRunner testDB(IDriverRepo driverRepo, IAddressRepo addressRepo, IParcelRepo parcelRepo,ICustomerAsCompanyRepo customerAscompanyRepo, ICustomerAsPersonRepo customerAspersonRepo ) {
        return new CommandLineRunner() {
          

		

			@Override
            public void run(String... args) throws Exception {
                Driver dr1 = new Driver("Peter", "Solomon", "456798-98763", "PH4567-87965", 6);
                Driver dr2 = new Driver("John", "Doe", "234567-12345", "GH1234-12355", 7);
                driverRepo.save(dr1);
                driverRepo.save(dr2);

                Address ad1 = new Address(City.Riga, "Kuldigas", 100);
                Address ad2 = new Address(City.Riga, "Elvis", 120);
                addressRepo.save(ad1);
                addressRepo.save(ad2);
                
                CustomerAsPerson reciever2 = new CustomerAsPerson( "123456-67895", "+37127364536", ad2);
                CustomerAsPerson reciever3 = new CustomerAsPerson( "123456-67125", "+37127367536", ad1);
                customerAspersonRepo.save(reciever2);
                customerAspersonRepo.save(reciever3);
                
                CustomerAsCompany Cc1 = new CustomerAsCompany("Qurestnet",  "QT234567", "+371243354657", ad2);
                CustomerAsCompany Cc2 = new CustomerAsCompany("Accenture",  "AC234567", "+371783354657", ad1);
                customerAscompanyRepo.save(Cc1);
                customerAscompanyRepo.save(Cc2);
               Parcel p1 = new Parcel(reciever2, false, dr2,  LocalDateTime.of(2024, 03, 10, 23, 40), 1500.0, Size.Medium, LocalDateTime.now());
               Parcel p2 = new Parcel(Cc1, false, dr1, LocalDateTime.of(2024, 05, 30, 12, 30), 200.0,Size.Small , LocalDateTime.now());
               parcelRepo.save(p1);
               parcelRepo.save(p2);
               
               
              
            }
        };
    }
}
