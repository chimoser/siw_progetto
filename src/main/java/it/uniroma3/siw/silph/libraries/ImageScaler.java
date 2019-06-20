package it.uniroma3.siw.silph.libraries;

/**
 * Created by marco on 19/06/2017.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by marco on 19/06/2017.
 */
public class ImageScaler {

    private int heightAfterResize;
    private int widthAfterResize;

    public ImageScaler() {

    }

    public BufferedImage scaleAndAddBackground(BufferedImage image) {
        return (addSfondo(scaleImageInBoundry(image)));
    }


    private BufferedImage scaleImageInBoundry(BufferedImage img) {

        Dimension boundry = new Dimension(400, 300);
        Dimension originalSize = new Dimension(img.getWidth(), img.getHeight());

        Dimension outDimension = getScaledDimension(originalSize,boundry);

        heightAfterResize = (int) outDimension.getHeight();
        widthAfterResize = (int) outDimension.getWidth();

        double fheight =  outDimension.getHeight() / originalSize.getHeight();
        double fwidth = outDimension.getWidth() / originalSize.getWidth();


        System.out.println("Original size: " + img.getWidth() + " x " + img.getHeight());
        System.out.println("OutputDimension: "+ outDimension.getWidth() + " x " + outDimension.getHeight());
        System.out.println("FW: " + fwidth + " FH: " + fheight);


        BufferedImage scaledImage = scale(img, BufferedImage.TYPE_INT_RGB, (int) outDimension.getWidth(), (int) outDimension.getHeight(), fwidth, fheight);

        return scaledImage;

    }


    private BufferedImage addSfondo(BufferedImage img) {
        BufferedImage out = new BufferedImage(400,300,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = out.createGraphics();
        g.fillRect(0,0,400,300);

        int x; int y;

        if (heightAfterResize == 300) {
            y = 0;
        }
        else {
            y = ((300 - heightAfterResize)/2);
        }

        if (widthAfterResize == 400) {
            x = 0;
        }
        else {
            x = ((400 - widthAfterResize)/2);
        }

        g.drawImage(img,null,x,y);

        return out;
    }

    private BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }


    private Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

}
