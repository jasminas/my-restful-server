package hapi.util;

import ca.uhn.fhir.context.FhirContext;

public class Fhir {	private static FhirContext context;

    private Fhir() {

    }

    public static FhirContext getInstance() {
        if (null == context) {
            context = FhirContext.forDstu2Hl7Org();
        }
        return context;
    }

}
