package alex.jelia.empmanager.webapp.model;

public class SimpleTextSection extends Section{
    private static final long serialVersionUID = 1L;
    private String content;

    public SimpleTextSection() {
    }

    public SimpleTextSection(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return  content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleTextSection that = (SimpleTextSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
