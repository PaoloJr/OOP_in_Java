
import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage) {
        int maxPixelValue = 255;
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int redValue = inPixel.getRed();
            int greenValue = inPixel.getGreen();
            int blueValue = inPixel.getBlue();
            pixel.setRed(maxPixelValue - redValue);
            pixel.setGreen(maxPixelValue - greenValue);
            pixel.setBlue(maxPixelValue - blueValue);
        }
        return outImage;
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource inverted = makeInversion(inImage);
            // inverted.draw();
            String fileName = inImage.getFileName();
            String newFileName = "inverted-" + fileName;
            inverted.setFileName(newFileName);
            inverted.save();
        }
    }
}
