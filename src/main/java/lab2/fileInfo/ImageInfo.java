package lab2.fileInfo;


import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ImageInfo extends FileInfo {
    public ImageInfo(File file) throws IOException {
        super(file);
    }

    @Override
    public void printExtra() {
        Dimension dim = null;
        try(ImageInputStream in = ImageIO.createImageInputStream(this.file)){
            final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                try {
                    reader.setInput(in);
                    dim = new Dimension(reader.getWidth(0), reader.getHeight(0));
                } finally {
                    reader.dispose();
                }
            }
        } catch (Exception e) {
            System.out.println("Couldn't get image data");
            return;
        }
        if (dim == null) {
            System.out.println("Couldn't get image data");
            return;
        }
        System.out.printf("Height: %.0fpx\n", dim.getHeight());
        System.out.printf("Width:  %.0fpx\n", dim.getWidth());
    }
}
