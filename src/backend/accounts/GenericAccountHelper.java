package backend.accounts;

public class GenericAccountHelper<T> {
    private Class<T> clazz;

    public GenericAccountHelper(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T buildOne() throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}
