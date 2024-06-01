package lv.venta.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "AddressTable")
public class Address {

    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDA")
    private Long ida;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "City")
    private City city;

    @OneToOne(mappedBy = "address")
    @ToString.Exclude
    private CustomerAsPerson customerAsPerson;

    @OneToOne(mappedBy = "address")
    @ToString.Exclude
    private CustomerAsCompany customerAsCompany;

    @Column(name = "HouseNo")
    @Max(value = 9999, message = "House number should be less than or equal to 9999")
    @Min(value = 1, message = "House number should be greater than or equal to 1")
    private int houseNo;

    @NotNull
    @Column(name = "Street_name")
    @Pattern(regexp = "[A-Z][a-z]{1,19}", message = "Street name should start with a capital letter and be between 2 and 20 characters long")
    private String streetName;

    public Address(City city, String streetName, int houseNo) {
        setCity(city);
        setStreetName(streetName);
        setHouseNo(houseNo);
    }
}
