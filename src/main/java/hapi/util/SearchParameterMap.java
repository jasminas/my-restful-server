package hapi.util;

import ca.uhn.fhir.model.api.Include;

import java.util.HashSet;
import java.util.Set;

// Sample transfer object for search parameters (following JPA server model)

public class SearchParameterMap {

    // only supports includes/revincludes for now

    private String resourceName;
    private Set<Include> includes;
    private Set<Include> revIncludes;

    public SearchParameterMap(String resourceName) {
        this.resourceName = resourceName;
    }

    public Set<Include> getIncludes() {
        if (includes == null) {
            includes = new HashSet<>();
        }
        return includes;
    }

    public Set<Include> getRevIncludes() {
		if (revIncludes == null) {
			revIncludes = new HashSet<>();
		}
        return revIncludes;
    }

    public boolean hasIncludes() {
        if (includes != null && !includes.isEmpty())
            return true;
        return false;
    }

    public boolean hasRevIncludes() {
        if (revIncludes != null && !revIncludes.isEmpty())
            return true;
        return false;
    }

    public void setIncludes(Set<Include> includes) {
        this.includes = includes;
    }

    public void setRevIncludes(Set<Include> revIncludes) {
        this.revIncludes = revIncludes;
    }
}
