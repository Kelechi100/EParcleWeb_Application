package lv.venta.Model;

import java.util.Collection;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "CustomerAsCompanyTable")
public class CustomerAsCompany {
	
	  	@Setter(AccessLevel.NONE)
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "IdC")
	    private long idc;

	    @NotNull
	    @Column(name = "Name" )
	    @Pattern(regexp = "[A-Z]{1}[a-z]+")
	    
	    private String title;

	    @NotNull
	    @OneToOne
	    @JoinColumn(name = "IDA")
	    private Address address;
	    
	    @OneToMany(mappedBy = "receiverCompany")
	    @ToString.Exclude
	    private Collection<Parcel> parcels;
	    
	    
	    @NotEmpty
	    @Column(name = "CompanyCode")
	    private String companyCode;
	    
	    
	    @Pattern(regexp = "[A-Z]{2}[0-9]{6}+")
	    @Column(name = "CompanyRegNO")
	    private String companyRegNo;


	    @NotNull
	    @Column(name = "PhoneNo")
	    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
	    private String phoneNo;

	    public CustomerAsCompany(String title,  String companyRegNo, String phoneNo, Address address ){
	      setTitle(title);
	      setCompanyRegNo(companyRegNo);
	      setPhoneNo(phoneNo);
	      setAddress(address);
	      
	        
	       
	    }
	    @PrePersist
	    private void  generatescompanyCode(){
	        this.companyCode = this.idc + "_"+ "company" + this.companyRegNo;

	    }


}
