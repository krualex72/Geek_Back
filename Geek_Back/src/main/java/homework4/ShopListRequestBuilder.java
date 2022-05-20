package homework4;

public class ShopListRequestBuilder {

    private String item;
    private String aisle;
    private Boolean parse;

    public ShopListRequestBuilder setItem(String item) {
        this.item = item;
        return this;
    }

    public ShopListRequestBuilder setAisle(String aisle) {
        this.aisle = aisle;
        return this;
    }

    public ShopListRequestBuilder setParse(Boolean parse) {
        this.parse = parse;
        return this;
    }

//    public ShopListRequest createShopListRequest() {
//        return new ShopListRequest(item, aisle, parse);
//    }
}