package tppagrupo7.xpress.domain;

public class Statement<T> {
    private Class<T> expectedType;

    private String query;

    public Class<T> getExpectedType() {
        return expectedType;
    }

    public void setExpectedType(Class<T> expectedType) {
        this.expectedType = expectedType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
