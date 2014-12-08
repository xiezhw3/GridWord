import java.io.*;

import imagereader.*;

import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;

public class ImageIOER implements IImageIO
{
    private Image image = null;
    private static final int BITMAPHEADERLEN = 14;
    private static final int BITMAPINFOLEN = 40;
    private static final int LEFTMOVE24 = 24;
    private static final int LEFTMOVE16 = 16;
    private static final int LEFTMOVE8 = 8;
    private static final int MAPWIDTHSTART = 7;
    private static final int MAPINFOSTART = 11;    
    private static final int READSTART = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int MINEIGHTBIT = 0xff;
    private static final int MAXRGB = 255;
    /**
     * This method read an image with the path
     * 
     * @param filepath
     *             The path of the image
     * 
     * @return    return the image
     * */
    public Image myRead(String filepath) throws IOException
    {
        int bitmapWidth;
        int bitmapHeight;
        FileInputStream imagefile = null;
        File file = new File(filepath);
        if (file.exists())
        {
            try
            {
                imagefile = new FileInputStream(file);
                byte[] headerinfo = new byte[BITMAPHEADERLEN];
                byte[] bitmapinfo = new byte[BITMAPINFOLEN];
                // Get the header information of the bitmap, from 0-13
                imagefile.read(headerinfo, READSTART, BITMAPHEADERLEN);
                // Get the bitmap information, from 14-53
                imagefile.read(bitmapinfo, READSTART, BITMAPINFOLEN);
                
                bitmapWidth = changeToInt(bitmapinfo, MAPWIDTHSTART);
                bitmapHeight = changeToInt(bitmapinfo, MAPINFOSTART);
                
                int[] rgbData = getMap(imagefile, bitmapHeight, bitmapWidth);
                image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(
                        bitmapWidth, bitmapHeight, rgbData, READSTART, bitmapWidth));
            } catch (IOException e) {
                e.printStackTrace();
            }
            imagefile.close();
        }
        return image;
    }
    
    /**
     * This method change the byte number to int
     * 
     * @param byteList
     *             The list store the byte number
     * 
     * @param startIndex
     *             The start byte number index
     * 
     * @return    return the number corresponding
     *             to the byte list.
     * */
    private int changeToInt(byte[] byteList, int startIndex)
    {
        return  (((int)byteList[startIndex] & MINEIGHTBIT) << LEFTMOVE24)
                | (((int)byteList[startIndex - ONE] & MINEIGHTBIT) << LEFTMOVE16)
                | (((int)byteList[startIndex - TWO] & MINEIGHTBIT) << LEFTMOVE8)
                | (((int)byteList[startIndex - THREE] & MINEIGHTBIT));
    }
    
    /**
     * This method get the rgb of the image 
     * and set this to an array
     * */
    private int[] getMap(FileInputStream imagefile, int bitmapHeight, int bitmapWidth) throws IOException
    {        
        int[] rgbData = new int[bitmapWidth * bitmapHeight * THREE];
        
        int widthSizeSup = bitmapWidth * THREE % FOUR;
        int skipNum = 0;
        if (widthSizeSup != READSTART)
        {
            skipNum = FOUR - widthSizeSup;
        }
        
        for (int i = bitmapHeight - 1; i >= READSTART; i--)
        {
            for (int j = 0; j != bitmapWidth; j++)
            {
                rgbData[bitmapWidth * i + j] = (((int)imagefile.read() & MINEIGHTBIT)
                                                | (((int)imagefile.read() & MINEIGHTBIT) << LEFTMOVE8)
                                                | ((int)imagefile.read() & MINEIGHTBIT) << LEFTMOVE16)
                                                | (MAXRGB & MINEIGHTBIT) << LEFTMOVE24;
            }
            imagefile.skip(skipNum);
        }
        return rgbData;
    }
    
    /**
     * This method write an image to the path
     * 
     * @param image
     *             The image to write
     * 
     * @param filepath
     *             The path to write the image
     * 
     * @return    return the image
     * */
    public Image myWrite(Image image, String filepath)
    {
        File imagefile = new File(filepath + ".bmp");
        BufferedImage buffimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = buffimage.createGraphics();
        try
            {
            graph.drawImage(image, READSTART, READSTART, null);
            graph.dispose();
            ImageIO.write(buffimage, "bmp", imagefile);    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
