package alex.jelia.empmanager.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationsSection extends Section {
    private final List<Organizations> organizations;

    public OrganizationsSection(List<Organizations> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizations = organizations;
    }

    public List<Organizations> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        return organizations.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationsSection that = (OrganizationsSection) o;

        return organizations.equals(that.organizations);

    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }
}
