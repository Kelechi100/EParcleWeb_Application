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
@Table(name = "CustomerAsPersonTable")
public class CustomerAsPerson {
	
	 	@Setter(AccessLevel.NONE)
	    @Id
	    @Column(name = "IDP")
	 	@GeneratedValue(strategy = GenerationType.AUTO)
	    private long idp;


	    @NotNull
	    @OneToOne
	    @JoinColumn(name = "IDA") //need to specify another class id column name
	    private Address address;

	    @OneToMany(mappedBy = "receiverPerson")
	    @ToString.Exclude
	    private Collection<Parcel> parcels;
	  
	    @NotEmpty
	    @Column(name = "PersonCode")
	    private String PersonCode;

	 
	    private String personId;


	    @NotNull
	    @Column(name = "PhoneNo")
	    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
	    private String phoneNO;

	    public CustomerAsPerson( String personId, String phoneNo, Address address) {
	    	
	    	setPersonId(personId);
	    	setPhoneNO(phoneNo);
	    	setAddress(address);
	    	
	    }
	    @PrePersist
	    private void  generatesPersonCode(){
	        this.PersonCode = this.idp+ "_"+ "Person" + this.personId;

	    }
	
		

	       
	  
}
