package fm.pattern.microstructure;

import static fm.pattern.microstructure.dsl.ContactDSL.contact;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;

import fm.pattern.microstructure.ResultType;

public class ContactValidationTest extends ValidationTest {

    @Test
    public void shouldBeAbleToCreateAContactWhenTheMobileNumberIsNullOrEmpty() {
        assertCreate(contact().withMobileNumber("").build()).accepted().withType(ResultType.CREATED);
        assertCreate(contact().withMobileNumber("   ").build()).accepted().withType(ResultType.CREATED);
        assertCreate(contact().withMobileNumber(null).build()).accepted().withType(ResultType.CREATED);
    }

    @Test
    public void shouldBeAbleToCreateAContactWhenThePhoneNumberIsNullOrEmpty() {
        assertCreate(contact().withPhoneNumber("").build()).accepted().withType(ResultType.CREATED);
        assertCreate(contact().withPhoneNumber("   ").build()).accepted().withType(ResultType.CREATED);
        assertCreate(contact().withPhoneNumber(null).build()).accepted().withType(ResultType.CREATED);
    }

    @Test
    public void shouldBeAbleToCreateAContactIfTheEmailAddressIsNotPresent() {
        assertCreate(contact().withEmailAddress("").build()).accepted().withType(ResultType.CREATED);
        assertCreate(contact().withEmailAddress(null).build()).accepted().withType(ResultType.CREATED);
    }

    @Test
    public void shouldNotBeAbleToCreateAContactWhenTheMobileNumberIsInvalid() {
        assertCreate(contact().withMobileNumber("0426313313").build()).accepted().withType(ResultType.CREATED);

        assertCreate(contact().withMobileNumber("426313313").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("426313313").build()).rejected().withCode("contact.mobile_number.format");
        assertCreate(contact().withMobileNumber("426313313").build()).rejected().withCode("contact.mobile_number.format").withType(ResultType.UNPROCESSABLE_ENTITY);

        assertCreate(contact().withMobileNumber("0326313313").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("042678768").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("04267876877").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0426 313 313").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0426.313.313").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("0426-313-313").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertCreate(contact().withMobileNumber("042631331a").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactWhenThePhoneNumberIsInvalid() {
        assertCreate(contact().withPhoneNumber("0299345565").build()).accepted().withType(ResultType.CREATED);

        assertCreate(contact().withPhoneNumber("029934556").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("029934556").build()).rejected().withCode("contact.phone_number.format");
        assertCreate(contact().withPhoneNumber("029934556").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertCreate(contact().withPhoneNumber("02993455655").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0199345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0499345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0699345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("0999345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("02-9934-5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("02 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertCreate(contact().withPhoneNumber("(02) 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheEmailAddressIsGreaterThanEightyCharacters() {
        assertCreate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withDescription("An email address cannot be greater than 80 characters.");
        assertCreate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withCode("contact.email_address.size");
        assertCreate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheEmailAddressIsInvalid() {
        assertCreate(contact().withEmailAddress("test@email.com").build()).accepted().withType(ResultType.CREATED);

        assertCreate(contact().withEmailAddress("notemail").build()).rejected().withDescription("The email address supplied does not appear to be a valid email address.");
        assertCreate(contact().withEmailAddress("notemail").build()).rejected().withCode("contact.email_address.format");
        assertCreate(contact().withEmailAddress("notemail").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertCreate(contact().withEmailAddress("notemail@").build()).rejected().withDescription("The email address supplied does not appear to be a valid email address.");
        assertCreate(contact().withEmailAddress("@email.com").build()).rejected().withDescription("The email address supplied does not appear to be a valid email address.");
    }

    @Test
    public void shouldNotBeAbleToCreateAContactIfTheNameIsNotProvided() {
        assertCreate(contact().withName(null).build()).rejected().withDescription("A contact name is required.");
        assertCreate(contact().withName(null).build()).rejected().withCode("contact.name.required");
        assertCreate(contact().withName(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertCreate(contact().withName("").build()).rejected().withDescription("A contact name is required.");
        assertCreate(contact().withName("").build()).rejected().withCode("contact.name.required");
        assertCreate(contact().withName("").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertCreate(contact().withName("  ").build()).rejected().withDescription("A contact name is required.");
        assertCreate(contact().withName("  ").build()).rejected().withCode("contact.name.required");
        assertCreate(contact().withName("  ").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldBeAbleToUpdateAContactWhenTheMobileNumberIsNullOrEmpty() {
        assertUpdate(contact().withMobileNumber("").build()).accepted().withType(ResultType.UPDATED);
        assertUpdate(contact().withMobileNumber("   ").build()).accepted().withType(ResultType.UPDATED);
        assertUpdate(contact().withMobileNumber(null).build()).accepted().withType(ResultType.UPDATED);
    }

    @Test
    public void shouldBeAbleToUpdateAContactWhenThePhoneNumberIsNullOrEmpty() {
        assertUpdate(contact().withPhoneNumber("").build()).accepted().withType(ResultType.UPDATED);
        assertUpdate(contact().withPhoneNumber("   ").build()).accepted().withType(ResultType.UPDATED);
        assertUpdate(contact().withPhoneNumber(null).build()).accepted().withType(ResultType.UPDATED);
    }

    @Test
    public void shouldBeAbleToUpdateAContactIfTheEmailAddressIsNotPresent() {
        assertUpdate(contact().withEmailAddress("").build()).accepted().withType(ResultType.UPDATED);
        assertUpdate(contact().withEmailAddress(null).build()).accepted().withType(ResultType.UPDATED);
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactWhenTheMobileNumberIsInvalid() {
        assertUpdate(contact().withMobileNumber("0426313313").build()).accepted().withType(ResultType.UPDATED);

        assertUpdate(contact().withMobileNumber("426313313").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("426313313").build()).rejected().withCode("contact.mobile_number.format");
        assertUpdate(contact().withMobileNumber("426313313").build()).rejected().withCode("contact.mobile_number.format").withType(ResultType.UNPROCESSABLE_ENTITY);

        assertUpdate(contact().withMobileNumber("0326313313").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("042678768").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("04267876877").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0426 313 313").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0426.313.313").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("0426-313-313").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
        assertUpdate(contact().withMobileNumber("042631331a").build()).rejected().withDescription("The mobile number supplied does not appear to be a valid mobile number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactWhenThePhoneNumberIsInvalid() {
        assertUpdate(contact().withPhoneNumber("0299345565").build()).accepted().withType(ResultType.UPDATED);

        assertUpdate(contact().withPhoneNumber("029934556").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("029934556").build()).rejected().withCode("contact.phone_number.format");
        assertUpdate(contact().withPhoneNumber("029934556").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertUpdate(contact().withPhoneNumber("02993455655").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0199345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0499345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0699345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("0999345565").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("02-9934-5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("02 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
        assertUpdate(contact().withPhoneNumber("(02) 9934 5566").build()).rejected().withDescription("The phone number supplied does not appear to be a valid phone number.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheEmailAddressIsGreaterThanEightyCharacters() {
        assertUpdate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withDescription("An email address cannot be greater than 80 characters.");
        assertUpdate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withCode("contact.email_address.size");
        assertUpdate(contact().withEmailAddress(randomAlphabetic(81)).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheEmailAddressIsInvalid() {
        assertUpdate(contact().withEmailAddress("test@email.com").build()).accepted().withType(ResultType.UPDATED);

        assertUpdate(contact().withEmailAddress("notemail").build()).rejected().withDescription("The email address supplied does not appear to be a valid email address.");
        assertUpdate(contact().withEmailAddress("notemail").build()).rejected().withCode("contact.email_address.format");
        assertUpdate(contact().withEmailAddress("notemail").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertUpdate(contact().withEmailAddress("notemail@").build()).rejected().withDescription("The email address supplied does not appear to be a valid email address.");
        assertUpdate(contact().withEmailAddress("@email.com").build()).rejected().withDescription("The email address supplied does not appear to be a valid email address.");
    }

    @Test
    public void shouldNotBeAbleToUpdateAContactIfTheNameIsNotProvided() {
        assertUpdate(contact().withName(null).build()).rejected().withDescription("A contact name is required.");
        assertUpdate(contact().withName(null).build()).rejected().withCode("contact.name.required");
        assertUpdate(contact().withName(null).build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertUpdate(contact().withName("").build()).rejected().withDescription("A contact name is required.");
        assertUpdate(contact().withName("").build()).rejected().withCode("contact.name.required");
        assertUpdate(contact().withName("").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);

        assertUpdate(contact().withName("  ").build()).rejected().withDescription("A contact name is required.");
        assertUpdate(contact().withName("  ").build()).rejected().withCode("contact.name.required");
        assertUpdate(contact().withName("  ").build()).rejected().withType(ResultType.UNPROCESSABLE_ENTITY);
    }

}