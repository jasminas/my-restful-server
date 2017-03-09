package hapi.server;

import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.narrative.INarrativeGenerator;
import ca.uhn.fhir.rest.server.BundleInclusionRule;
import ca.uhn.fhir.rest.server.EncodingEnum;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import hapi.providers.dstu2.OrganizationResourceProvider;
import hapi.providers.dstu2.PatientResourceProvider;
import hapi.util.Fhir;
import org.hl7.fhir.instance.model.api.IAnyResource;

import java.util.ArrayList;
import java.util.List;

public class FhirServlet<T extends IAnyResource> extends RestfulServer {

    public FhirServlet() {
        super(Fhir.getInstance());
    }

    @Override
    public void initialize() {
		/*
		 * Two resource providers are defined. Each one handles a specific type
		 * of resource.
		 */
        List<IResourceProvider> providers = new ArrayList<IResourceProvider>();
        providers.add(new PatientResourceProvider());
        providers.add(new OrganizationResourceProvider());
        setResourceProviders(providers);


        setBundleInclusionRule(BundleInclusionRule.BASED_ON_INCLUDES);


		/*
		 * This server interceptor causes the server to return nicely formatter
		 * and coloured responses instead of plain JSON/XML if the request is
		 * coming from a browser window. It is optional, but can be nice for
		 * testing.
		 */
        registerInterceptor(new ResponseHighlighterInterceptor());


		/*
		 * Use a narrative generator. This is a completely optional step, but
		 * can be useful as it causes HAPI to generate narratives for resources
		 * which don't otherwise have one.
		 */
        INarrativeGenerator narrativeGen = new DefaultThymeleafNarrativeGenerator();
        getFhirContext().setNarrativeGenerator(narrativeGen);

		/*
		 * Tells the server to return pretty-printed responses by default
		 */
        setDefaultPrettyPrint(true);
        setDefaultResponseEncoding(EncodingEnum.JSON);
    }
}
