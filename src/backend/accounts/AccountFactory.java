package backend.accounts;

/**
 * @param <T>
 */
public interface AccountFactory<T> {
    private Class<T> clazz;

    public AccountFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T buildOne() throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}
