package design.patterns.proxy;

/**
 * Created by Feng on 1/9/14.
 */
public class ImageProxy implements Image {

    private String url;

    private Image image = new RealImage(url);

    @Override
    public void displayImage() {
        image.displayImage();
    }
}
