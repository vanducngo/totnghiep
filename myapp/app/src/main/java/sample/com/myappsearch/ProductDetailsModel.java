package sample.com.myappsearch;

/**
 * Created by root on 22/07/2015.
 */
public class ProductDetailsModel {
    private long Id;
    private String name;
    private String imageUrl;



    public long getId() {
        return Id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
