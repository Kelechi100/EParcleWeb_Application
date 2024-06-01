package lv.venta.Model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "DriverTable")
public class Driver {

    @Setter(value = AccessLevel.NONE)
    @Column(name = "Ids")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ids;

    @NotNull
    @Pattern(regexp = "[A-Z]{1}[a-z]+")
    @Size(min = 2, max = 10)
    @Column(name = "Name")
    private String name;

    @NotNull
    @Pattern(regexp = "[A-Z]{1}[a-z]+")
    @Size(min = 2, max = 20)
    @Column(name = "Surname")
    private String surname;

    @NotNull
    @Pattern(regexp = "[0-9]{6}-[0-9]{5}+")
    @Column(name = "PERSON_CODE")
    private String personCode;

    @Column(name = "ExperienceinYears")
    @Min(1)
    @Max(10)
    private float experienceInYears;
    
    
    @OneToMany(mappedBy = "driver")
    @ToString.Exclude
    private Collection<Parcel> parcels;
    
    @NotNull
    @Column(name = "LicenseNO")
    @Pattern(regexp = "[A-Z]{2}[0-9]{4}-[0-9]{5}+")
    private String licenceNo;

    public Driver(String name, String surname, String personCode, String licenceNo, float experienceInYears) {
        setName(name); // using setter
        setSurname(surname); // using setter
        setPersonCode(personCode); // using setter
        setLicenceNo(licenceNo); // using setter
        setExperienceInYears(experienceInYears); // using setter
    }


}
