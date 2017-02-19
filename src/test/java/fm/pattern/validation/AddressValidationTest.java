package fm.pattern.validation;

import static fm.pattern.validation.dsl.AddressDSL.address;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

public class AddressValidationTest extends ValidationTest {

    @Test
    public void shouldBeAbleToCreateAnAddress() {
        assertCreate(address().withUnit(randomAlphabetic(5)).build()).accepted().withType(ResultType.CREATED);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheUnitNumberIsGreaterThan30Characters() {
        assertCreate(address().withUnit(randomAlphabetic(31)).build()).rejected().withDescription("The unit number cannot be greater than 30 characters.");
        assertCreate(address().withUnit(randomAlphabetic(31)).build()).rejected().withCode("address.unit.size");
        assertCreate(address().withUnit(randomAlphabetic(31)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStreetNumberIsGreaterThan10Characters() {
        assertCreate(address().withNumber(randomAlphabetic(11)).build()).rejected().withDescription("The street number cannot be greater than 10 characters.");
        assertCreate(address().withNumber(randomAlphabetic(11)).build()).rejected().withCode("address.number.size");
        assertCreate(address().withNumber(randomAlphabetic(11)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStreetNameIsGreaterThan50Characters() {
        assertCreate(address().withStreet(randomAlphabetic(51)).build()).rejected().withDescription("The street name cannot be greater than 50 characters.");
        assertCreate(address().withStreet(randomAlphabetic(51)).build()).rejected().withCode("address.street.size");
        assertCreate(address().withStreet(randomAlphabetic(51)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheSuburbNameIsGreaterThan50Characters() {
        assertCreate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withDescription("The suburb name cannot be greater than 50 characters.");
        assertCreate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withCode("address.suburb.size");
        assertCreate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCityNameIsGreaterThan50Characters() {
        assertCreate(address().withCity(randomAlphabetic(51)).build()).rejected().withDescription("The city name cannot be greater than 50 characters.");
        assertCreate(address().withCity(randomAlphabetic(51)).build()).rejected().withCode("address.city.size");
        assertCreate(address().withCity(randomAlphabetic(51)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheStateNameIsGreaterThan50Characters() {
        assertCreate(address().withState(randomAlphabetic(51)).build()).rejected().withDescription("The state name cannot be greater than 50 characters.");
        assertCreate(address().withState(randomAlphabetic(51)).build()).rejected().withCode("address.state.size");
        assertCreate(address().withState(randomAlphabetic(51)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCountryNameIsGreaterThan50Characters() {
        assertCreate(address().withCountry(randomAlphabetic(51)).build()).rejected().withDescription("The country name cannot be greater than 50 characters.");
        assertCreate(address().withCountry(randomAlphabetic(51)).build()).rejected().withCode("address.country.size");
        assertCreate(address().withCountry(randomAlphabetic(51)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheCountryIsNotProvided() {
        assertCreate(address().withCountry(null).build()).rejected().withDescription("The country is required.");
        assertCreate(address().withCountry(null).build()).rejected().withCode("address.country.required");
        assertCreate(address().withCountry(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertCreate(address().withCountry("").build()).rejected().withDescription("The country is required.");
        assertCreate(address().withCountry("").build()).rejected().withCode("address.country.required");
        assertCreate(address().withCountry("").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertCreate(address().withCountry("   ").build()).rejected().withDescription("The country is required.");
        assertCreate(address().withCountry("   ").build()).rejected().withCode("address.country.required");
        assertCreate(address().withCountry("   ").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfThePostCodeIsGreaterThan10Characters() {
        assertCreate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withDescription("The post code cannot be greater than 10 characters.");
        assertCreate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withCode("address.post_code.size");
        assertCreate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfThePostCodeIsNotProvided() {
        assertCreate(address().withPostCode(null).build()).rejected().withDescription("The post code is required.");
        assertCreate(address().withPostCode(null).build()).rejected().withCode("address.post_code.required");
        assertCreate(address().withPostCode(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertCreate(address().withPostCode("").build()).rejected().withDescription("The post code is required.");
        assertCreate(address().withPostCode("").build()).rejected().withCode("address.post_code.required");
        assertCreate(address().withPostCode("").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertCreate(address().withPostCode("   ").build()).rejected().withDescription("The post code is required.");
        assertCreate(address().withPostCode("   ").build()).rejected().withCode("address.post_code.required");
        assertCreate(address().withPostCode("   ").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheFormattedAddressIsGreaterThan500Characters() {
        assertCreate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withDescription("The formatted address cannot be greater than 500 characters.");
        assertCreate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withCode("address.formatted_address.size");
        assertCreate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheLatitudeIsNotProvided() {
        assertCreate(address().withLatitude(null).build()).rejected().withDescription("The latitude is required.");
        assertCreate(address().withLatitude(null).build()).rejected().withCode("location.latitude.required");
        assertCreate(address().withLatitude(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAnAddressIfTheLongitudeIsNotProvided() {
        assertCreate(address().withLongitude(null).build()).rejected().withDescription("The longitude is required.");
        assertCreate(address().withLongitude(null).build()).rejected().withCode("location.longitude.required");
        assertCreate(address().withLongitude(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldBeAbleToUpdateAnAddress() {
        assertUpdate(address().withUnit(randomAlphabetic(5)).build()).accepted().withType(ResultType.UPDATED);
    }
    
    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheUnitNumberIsGreaterThan30Characters() {
        assertUpdate(address().withUnit(randomAlphabetic(31)).build()).rejected().withDescription("The unit number cannot be greater than 30 characters.");
        assertUpdate(address().withUnit(randomAlphabetic(31)).build()).rejected().withCode("address.unit.size");
        assertUpdate(address().withUnit(randomAlphabetic(31)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStreetNumberIsGreaterThan10Characters() {
        assertUpdate(address().withNumber(randomAlphabetic(11)).build()).rejected().withDescription("The street number cannot be greater than 10 characters.");
        assertUpdate(address().withNumber(randomAlphabetic(11)).build()).rejected().withCode("address.number.size");
        assertUpdate(address().withNumber(randomAlphabetic(11)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStreetNameIsGreaterThan50Characters() {
        assertUpdate(address().withStreet(randomAlphabetic(51)).build()).rejected().withDescription("The street name cannot be greater than 50 characters.");
        assertUpdate(address().withStreet(randomAlphabetic(51)).build()).rejected().withCode("address.street.size");
        assertUpdate(address().withStreet(randomAlphabetic(51)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheSuburbNameIsGreaterThan50Characters() {
        assertUpdate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withDescription("The suburb name cannot be greater than 50 characters.");
        assertUpdate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withCode("address.suburb.size");
        assertUpdate(address().withSuburb(randomAlphabetic(51)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCityNameIsGreaterThan50Characters() {
        assertUpdate(address().withCity(randomAlphabetic(51)).build()).rejected().withDescription("The city name cannot be greater than 50 characters.");
        assertUpdate(address().withCity(randomAlphabetic(51)).build()).rejected().withCode("address.city.size");
        assertUpdate(address().withCity(randomAlphabetic(51)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheStateNameIsGreaterThan50Characters() {
        assertUpdate(address().withState(randomAlphabetic(51)).build()).rejected().withDescription("The state name cannot be greater than 50 characters.");
        assertUpdate(address().withState(randomAlphabetic(51)).build()).rejected().withCode("address.state.size");
        assertUpdate(address().withState(randomAlphabetic(51)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCountryNameIsGreaterThan50Characters() {
        assertUpdate(address().withCountry(randomAlphabetic(51)).build()).rejected().withDescription("The country name cannot be greater than 50 characters.");
        assertUpdate(address().withCountry(randomAlphabetic(51)).build()).rejected().withCode("address.country.size");
        assertUpdate(address().withCountry(randomAlphabetic(51)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheCountryIsNotProvided() {
        assertUpdate(address().withCountry(null).build()).rejected().withDescription("The country is required.");
        assertUpdate(address().withCountry(null).build()).rejected().withCode("address.country.required");
        assertUpdate(address().withCountry(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertUpdate(address().withCountry("").build()).rejected().withDescription("The country is required.");
        assertUpdate(address().withCountry("").build()).rejected().withCode("address.country.required");
        assertUpdate(address().withCountry("").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertUpdate(address().withCountry("   ").build()).rejected().withDescription("The country is required.");
        assertUpdate(address().withCountry("   ").build()).rejected().withCode("address.country.required");
        assertUpdate(address().withCountry("   ").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfThePostCodeIsGreaterThan10Characters() {
        assertUpdate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withDescription("The post code cannot be greater than 10 characters.");
        assertUpdate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withCode("address.post_code.size");
        assertUpdate(address().withPostCode(randomAlphabetic(11)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfThePostCodeIsNotProvided() {
        assertUpdate(address().withPostCode(null).build()).rejected().withDescription("The post code is required.");
        assertUpdate(address().withPostCode(null).build()).rejected().withCode("address.post_code.required");
        assertUpdate(address().withPostCode(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertUpdate(address().withPostCode("").build()).rejected().withDescription("The post code is required.");
        assertUpdate(address().withPostCode("").build()).rejected().withCode("address.post_code.required");
        assertUpdate(address().withPostCode("").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertUpdate(address().withPostCode("   ").build()).rejected().withDescription("The post code is required.");
        assertUpdate(address().withPostCode("   ").build()).rejected().withCode("address.post_code.required");
        assertUpdate(address().withPostCode("   ").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheFormattedAddressIsGreaterThan500Characters() {
        assertUpdate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withDescription("The formatted address cannot be greater than 500 characters.");
        assertUpdate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withCode("address.formatted_address.size");
        assertUpdate(address().withFormattedAddress(randomAlphabetic(501)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheLatitudeIsNotProvided() {
        assertUpdate(address().withLatitude(null).build()).rejected().withDescription("The latitude is required.");
        assertUpdate(address().withLatitude(null).build()).rejected().withCode("location.latitude.required");
        assertUpdate(address().withLatitude(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAnAddressIfTheLongitudeIsNotProvided() {
        assertUpdate(address().withLongitude(null).build()).rejected().withDescription("The longitude is required.");
        assertUpdate(address().withLongitude(null).build()).rejected().withCode("location.longitude.required");
        assertUpdate(address().withLongitude(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

}
