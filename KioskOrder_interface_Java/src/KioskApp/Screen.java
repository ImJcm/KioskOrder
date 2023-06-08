package KioskApp;

public interface Screen {
    default void run() throws Exception {};
    default void run(Product product) throws Exception {};
    default void run(int index) throws Exception {};

    default void ScreenShow() {};
    default void ScreenShow(Product product) {};
    default void ScreenShow(int index) {};
}
