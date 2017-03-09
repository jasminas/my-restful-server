package hapi.providers.dstu2;

import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.method.RequestDetails;
import ca.uhn.fhir.rest.param.*;
import hapi.providers.AbstractResourceProvider;
import hapi.util.SearchParameterMap;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.api.IBaseResource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class PatientResourceProvider extends AbstractResourceProvider<Patient> {

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Patient.class;
    }

    @Search()
    public List<IBaseResource> search(
            HttpServletRequest servletRequest,
            RequestDetails requestDetails,

            @Description(shortDefinition = "The resource identity") @OptionalParam(name = "_id") StringAndListParam id,

            @Description(shortDefinition = "The resource language") @OptionalParam(name = "_language") StringAndListParam resourceLanguage,

            @Description(shortDefinition = "Search the contents of the resource's data using a fulltext search") @OptionalParam(name = ca.uhn.fhir.rest.server.Constants.PARAM_CONTENT) StringAndListParam ftContent,

            @Description(shortDefinition = "Search the contents of the resource's narrative using a fulltext search") @OptionalParam(name = ca.uhn.fhir.rest.server.Constants.PARAM_TEXT) StringAndListParam ftText,

            @Description(shortDefinition = "Search for resources which have the given tag") @OptionalParam(name = ca.uhn.fhir.rest.server.Constants.PARAM_TAG) TokenAndListParam searchForTag,

            @Description(shortDefinition = "Search for resources which have the given security labels") @OptionalParam(name = ca.uhn.fhir.rest.server.Constants.PARAM_SECURITY) TokenAndListParam searchForSecurity,

            @Description(shortDefinition = "Search for resources which have the given profile") @OptionalParam(name = ca.uhn.fhir.rest.server.Constants.PARAM_PROFILE) UriAndListParam searchForProfile,

            @Description(shortDefinition = "Return resources linked to by the given target") @OptionalParam(name = "_has") HasAndListParam has,

            @Description(shortDefinition = "A patient identifier") @OptionalParam(name = "identifier") TokenAndListParam identifier,

            @Description(shortDefinition = "A portion of either family or given name of the patient") @OptionalParam(name = "name") StringAndListParam name,

            @Description(shortDefinition = "A portion of the family name of the patient") @OptionalParam(name = "family") StringAndListParam family,

            @Description(shortDefinition = "A portion of the given name of the patient") @OptionalParam(name = "given") StringAndListParam given,

            @Description(shortDefinition = "A portion of either family or given name using some kind of phonetic matching algorithm") @OptionalParam(name = "phonetic") StringAndListParam phonetic,

            @Description(shortDefinition = "The value in any kind of telecom details of the patient") @OptionalParam(name = "telecom") TokenAndListParam telecom,

            @Description(shortDefinition = "A value in a phone contact") @OptionalParam(name = "phone") TokenAndListParam phone,

            @Description(shortDefinition = "A value in an email contact") @OptionalParam(name = "email") TokenAndListParam email,

            @Description(shortDefinition = "An address in any kind of address/part of the patient") @OptionalParam(name = "address") StringAndListParam address,

            @Description(shortDefinition = "A city specified in an address") @OptionalParam(name = "address-city") StringAndListParam address_city,

            @Description(shortDefinition = "A state specified in an address") @OptionalParam(name = "address-state") StringAndListParam address_state,

            @Description(shortDefinition = "A postalCode specified in an address") @OptionalParam(name = "address-postalcode") StringAndListParam address_postalcode,

            @Description(shortDefinition = "A country specified in an address") @OptionalParam(name = "address-country") StringAndListParam address_country,

            @Description(shortDefinition = "A use code specified in an address") @OptionalParam(name = "address-use") TokenAndListParam address_use,

            @Description(shortDefinition = "Gender of the patient") @OptionalParam(name = "gender") TokenAndListParam gender,

            @Description(shortDefinition = "Language code (irrespective of use value)") @OptionalParam(name = "language") TokenAndListParam language,

            @Description(shortDefinition = "The patient's date of birth") @OptionalParam(name = "birthdate") DateRangeParam birthdate,

            @Description(shortDefinition = "The organization at which this person is a patient") @OptionalParam(name = "organization", targetTypes = {}) ReferenceAndListParam organization,

            @Description(shortDefinition = "Patient's nominated care provider, could be a care manager, not the organization that manages the record") @OptionalParam(name = "careprovider", targetTypes = {}) ReferenceAndListParam careprovider,

            @Description(shortDefinition = "Whether the patient record is active") @OptionalParam(name = "active") TokenAndListParam active,

            @Description(shortDefinition = "The species for animal patients") @OptionalParam(name = "animal-species") TokenAndListParam animal_species,

            @Description(shortDefinition = "The breed for animal patients") @OptionalParam(name = "animal-breed") TokenAndListParam animal_breed,

            @Description(shortDefinition = "All patients linked to the given patient") @OptionalParam(name = "link", targetTypes = {}) ReferenceAndListParam link,

            @Description(shortDefinition = "This patient has been marked as deceased, or as a death date entered") @OptionalParam(name = "deceased") TokenAndListParam deceased,

            @Description(shortDefinition = "The date of death has been provided and satisfies this search value") @OptionalParam(name = "deathdate") DateRangeParam deathdate,

            @IncludeParam(reverse = true) Set<Include> revIncludes,
            @Description(shortDefinition = "Only return resources which were last updated as specified by the given range") @OptionalParam(name = "_lastUpdated") DateRangeParam lastUpdated,

            @IncludeParam(allow = {"Patient:careprovider", "Patient:link", "Patient:organization",
                    "Patient:careprovider", "Patient:link", "Patient:organization", "Patient:careprovider",
                    "Patient:link", "Patient:organization", "*"}) Set<Include> includes,

            @Sort SortSpec sort,

            @Count Integer theCount) {
        SearchParameterMap paramMap = new SearchParameterMap(this.getResourceType().getSimpleName());

        paramMap.setIncludes(includes);
        paramMap.setRevIncludes(revIncludes);

        return super.search(paramMap);
    }

}