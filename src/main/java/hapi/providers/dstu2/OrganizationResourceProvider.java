package hapi.providers.dstu2;

import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.rest.annotation.IncludeParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.method.RequestDetails;
import ca.uhn.fhir.rest.param.*;
import hapi.providers.AbstractResourceProvider;
import hapi.util.SearchParameterMap;
import org.hl7.fhir.instance.model.Organization;
import org.hl7.fhir.instance.model.api.IBaseResource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class OrganizationResourceProvider extends AbstractResourceProvider<Organization> {

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Organization.class;
    }

    @Search
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

            @Description(shortDefinition = "A portion of the organization's name") @OptionalParam(name = "name") StringAndListParam name,

            @Description(shortDefinition = "A portion of the organization's name using some kind of phonetic matching algorithm") @OptionalParam(name = "phonetic") StringAndListParam phonetic,

            @Description(shortDefinition = "A code for the type of organization") @OptionalParam(name = "type") TokenAndListParam type,

            @Description(shortDefinition = "Any identifier for the organization (not the accreditation issuer's identifier)") @OptionalParam(name = "identifier") TokenAndListParam identifier,

            @Description(shortDefinition = "Search all organizations that are part of the given organization") @OptionalParam(name = "partof", targetTypes = {}) ReferenceAndListParam partof,

            @Description(shortDefinition = "Whether the organization's record is active") @OptionalParam(name = "active") TokenAndListParam active,

            @Description(shortDefinition = "A (part of the) address of the Organization") @OptionalParam(name = "address") StringAndListParam address,

            @Description(shortDefinition = "A city specified in an address") @OptionalParam(name = "address-city") StringAndListParam address_city,

            @Description(shortDefinition = "A state specified in an address") @OptionalParam(name = "address-state") StringAndListParam address_state,

            @Description(shortDefinition = "A postal code specified in an address") @OptionalParam(name = "address-postalcode") StringAndListParam address_postalcode,

            @Description(shortDefinition = "A country specified in an address") @OptionalParam(name = "address-country") StringAndListParam address_country,

            @Description(shortDefinition = "A use code specified in an address") @OptionalParam(name = "address-use") TokenAndListParam address_use,

            @IncludeParam(reverse = true) Set<Include> revIncludes,
            @Description(shortDefinition = "Only return resources which were last updated as specified by the given range") @OptionalParam(name = "_lastUpdated") DateRangeParam lastUpdated,

            @IncludeParam(allow = {"Organization:partof", "*"}) Set<Include> includes,

            @Sort SortSpec sort,

            @ca.uhn.fhir.rest.annotation.Count Integer count) {

        SearchParameterMap paramMap = new SearchParameterMap(this.getResourceType().getSimpleName());

        paramMap.setIncludes(includes);
        paramMap.setRevIncludes(revIncludes);

        return super.search(paramMap);
    }
}

