import java.awt.*;
import java.awt.image.*;

import imagereader.IImageProcessor;

class RRgbFilter extends RGBImageFilter {
    private static final int FILTER = 0xff000000;
    private static final int LEFTMOVE = 16;
    private static final int FILTERMIN = 0;
    private static final int MINEIGHT = 0xff;
    public int filterRGB(int x, int y, int rgb) { 
        // alpha
        int alpha = rgb & FILTER;
        // red information
        int r = (rgb >> LEFTMOVE) & MINEIGHT;
        return alpha | (r << LEFTMOVE) | FILTERMIN | FILTERMIN;
    } 
}

class GRgbFilter extends RGBImageFilter
{
    private static final int FILTER = 0xff000000;
    private static final int LEFTMOVE = 8;
    private static final int FILTERMIN = 0;
    private static final int MINEIGHT = 0xff;
    public int filterRGB(int x, int y, int rgb)
    {
        // alpha
        int alpha = rgb & FILTER;
        // green information
        int g = (rgb >> LEFTMOVE) & MINEIGHT;
        return alpha | FILTERMIN | (g << LEFTMOVE) | FILTERMIN;
    }
}

class BRgbFilter extends RGBImageFilter
{
    private static final int FILTER = 0xff000000;
    private static final int FILTERMIN = 0;
    private static final int MINEIGHT = 0xff;
    public int filterRGB(int x, int y, int rgb)
    {
        // alpha
        int alpha = rgb & FILTER;
        // blue information
        int b = rgb & MINEIGHT;
        return alpha | FILTERMIN | FILTERMIN | b;
    }
}

class GrayRgbFilter extends RGBImageFilter
{
    private static final int FILTER = 0xff000000;
    private static final int MINEIGHT = 0xff;
    private static final int LEFTMOVE8 = 8;
    private static final int LEFTMOVE16 = 16;
    private static final double RP = 0.299;
    private static final double GP = 0.587;
    private static final double PP = 0.114;
    public int filterRGB(int x, int y, int rgb)
    {
        // alpha
        int alpha = rgb & FILTER;
        // red information
        int r = (rgb >> LEFTMOVE16) & MINEIGHT;
        // green information
        int g = (rgb >> LEFTMOVE8) & MINEIGHT;
        // blue information
        int b = rgb & MINEIGHT;
        int gray = (int) (RP * r + GP * g + PP * b);
        return alpha | (gray << LEFTMOVE16) | (gray << LEFTMOVE8) | gray;
    } 
}

public class ImageProcessor implements IImageProcessor {
    public Image showChanelR(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(sourceImage.getSource(), new RRgbFilter()));
    }
    public Image showChanelG(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(sourceImage.getSource(), new GRgbFilter()));
    }
    public Image showChanelB(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(sourceImage.getSource(), new BRgbFilter()));
    }
    public Image showGray(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(sourceImage.getSource(), new GrayRgbFilter()));
    }
}
