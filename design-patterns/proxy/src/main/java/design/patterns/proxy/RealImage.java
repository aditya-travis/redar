package design.patterns.proxy;

/**
 * Created by Feng on 1/9/14.
 */
public class RealImage implements Image {

    private String url;

    public RealImage(String url) {
        this.url = url;

        loadImage(url);
    }

    private void loadImage(String url) {
        System.out.println("loading image...from " + url);
    }

    @Override
    public void displayImage() {
        System.out.println("displaying image from the Real Image");
    }
}
