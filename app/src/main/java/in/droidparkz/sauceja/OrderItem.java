package in.droidparkz.sauceja;

public class OrderItem {

    private String name;
    private String quantity;
    private String price;

    public OrderItem(String name, String quantity, String price) {

        this.name = name;
        this.quantity = quantity;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

}