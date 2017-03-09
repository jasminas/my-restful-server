package hapi.providers;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.valueset.BundleEntrySearchModeEnum;
import ca.uhn.fhir.rest.server.IResourceProvider;
import hapi.util.Fhir;
import hapi.util.SearchParameterMap;
import org.apache.commons.io.IOUtils;
import org.hl7.fhir.instance.model.api.IAnyResource;
import org.hl7.fhir.instance.model.api.IBaseResource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResourceProvider<T extends IAnyResource> implements IResourceProvider {

    private FhirContext context;

    public AbstractResourceProvider() {
        context = Fhir.getInstance();
    }

    public FhirContext getContext() {
        return context;
    }

    public void setContext(FhirContext context) {
        this.context = context;
    }

    public List<IBaseResource> search(SearchParameterMap paramMap) {
        List<IBaseResource> resources = new ArrayList<>();

        URL patientURL = AbstractResourceProvider.class.getClassLoader().getResource("patient.json");
        URL organizationURL = AbstractResourceProvider.class.getClassLoader().getResource("organization.json");
        if (patientURL != null) {
            File patientFile = new File(patientURL.getFile());
            try {
                String patientString = IOUtils.toString(new FileReader(patientFile));
                IBaseResource patient = Fhir.getInstance().newJsonParser().parseResource(patientString);
                ResourceMetadataKeyEnum.ENTRY_SEARCH_MODE.put((IAnyResource) patient, BundleEntrySearchModeEnum.MATCH.getCode());
                resources.add(patient);

                if (paramMap.hasIncludes()) {
                    File organziationFile = new File(organizationURL.getFile());
                    String organizationString = IOUtils.toString(new FileReader(organziationFile));
                    IBaseResource organziation = Fhir.getInstance().newJsonParser().parseResource(organizationString);
                    ResourceMetadataKeyEnum.ENTRY_SEARCH_MODE.put((IAnyResource) organziation, BundleEntrySearchModeEnum.INCLUDE.getCode());
                    resources.add(organziation);
                }
            } catch (IOException e) {
                return null;
            }
        }

        return resources;
    }

}
