package lv.venta.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "ParcelTable")
public class Parcel {

    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "IDPA")
    private long idpa;

    @Column(name = "is_fragile")
    private boolean isFragile;

    @Column(name = "Order_created")
    private LocalDateTime orderCreated;

    @Column(name = "Planned_Delivery")
    private LocalDateTime plannedDelivery;

    @Column(name = "Price")
    @Min(value = 0, message = "Price should not be less than 0")
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "Size")
    private Size parcelSize;

    

    @ManyToOne
    @JoinColumn(name = "IdC")
    private CustomerAsCompany receiverCompany;


    @ManyToOne
    @JoinColumn(name = "Ids")
    private Driver  driver; 
    
    
    @ManyToOne
    @JoinColumn(name = "IDP")
    private CustomerAsPerson receiverPerson;

    

    public Parcel( CustomerAsPerson receiverPerson, boolean isFragile, Driver driver, LocalDateTime plannedDelivery, double price, Size parcelSize, LocalDateTime orderCreated) {
       setReceiverPerson(receiverPerson);
       setFragile(isFragile);
       setDriver(driver);
       setOrderCreated(orderCreated);
       setPlannedDelivery(plannedDelivery);
       setPrice(price);
       setParcelSize(parcelSize);
   
        
        
    }
    
    public Parcel(CustomerAsCompany receiverCompany,   boolean isFragile,Driver driver, LocalDateTime plannedDelivery, double price, Size parcelSize, LocalDateTime orderCreated) {
    	setReceiverCompany(receiverCompany);
        setFragile(isFragile);
        setDriver(driver);
        setOrderCreated(orderCreated);
        setPlannedDelivery(plannedDelivery);
        setPrice(price);
        setParcelSize(parcelSize);
    }
       

	public Parcel(CustomerAsPerson customer, Driver driver) {
		// TODO Auto-generated constructor stub
	}
}
