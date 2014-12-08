import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.awt.image.BufferedImage;   

import javax.imageio.ImageIO;    


public class JunitImageTest {
	private String sourceFilePath = "/Users/wc/Downloads/bmptest/2.bmp";
	private String myFilePath = "/Users/wc/Downloads/bmptest/my2.bmp";
	private String sourceRFilePath = "/Users/wc/Downloads/bmptest/goal/2_red_goal.bmp";
	private String myRFilePath = "/Users/wc/Downloads/bmptest/myGoal/my2_red.bmp";
	private String sourceGFilePath = "/Users/wc/Downloads/bmptest/goal/2_green_goal.bmp";
	private String myGFilePath = "/Users/wc/Downloads/bmptest/myGoal/my2_green.bmp";
	private String sourceBFilePath = "/Users/wc/Downloads/bmptest/goal/2_blue_goal.bmp";
	private String myBFilePath = "/Users/wc/Downloads/bmptest/myGoal/my2_blue.bmp";
	private String sourceGrayFilePath = "/Users/wc/Downloads/bmptest/goal/2_gray_goal.bmp";
	private String myGrayFilePath = "/Users/wc/Downloads/bmptest/myGoal/my2_gray.bmp";

	@Test
	public void testMyRead() {
		RunTest runner = new RunTest(sourceFilePath, myFilePath);
		runner.init();
		runner.testHeight();
		runner.testWidth();
		runner.testRGB();
	}
	
	@Test
	public void testShowChanelR() {
		RunTest runner = new RunTest(sourceRFilePath, myRFilePath);
		runner.init();
		runner.testHeight();
		runner.testWidth();
		runner.testRGB();
	}
	
	@Test
	public void testShowChanelG() {
		RunTest runner = new RunTest(sourceGFilePath, myGFilePath);
		runner.init();
		runner.testHeight();
		runner.testWidth();
		runner.testRGB();
	}
	
	@Test
	public void testShowChanelB() {
		RunTest runner = new RunTest(sourceBFilePath, myBFilePath);
		runner.init();
		runner.testHeight();
		runner.testWidth();
		runner.testRGB();
	}
	
	@Test
	public void testShowGray() {
		RunTest runner = new RunTest(sourceGrayFilePath, myGrayFilePath);
		runner.init();
		runner.testHeight();
		runner.testWidth();
		runner.testRGB();
	}
}

class RunTest
{
	private String sourceFile = null;
	private String myFile = null;
	
	private int sourceImageWidth;
	private int sourceImageHeight;
	private int sourceMinx;
	private int sourceMiny;

	private int myImageWidth;
	private int myImageHeight;
	private int myMinx;
	private int myMiny;
	
	private int[][][] sourcergb;
	private int[][][] myrgb;
	
	public RunTest(String sf, String mf)
	{
		sourceFile = sf;
		myFile = mf;
	}
	
	public void init()
	{
		 BufferedImage bi = null;
		 BufferedImage mybi = null;
		 File sourcrImage = new File(sourceFile);
		 File myImage = new File(myFile);
		 try {
			 bi = ImageIO.read(sourcrImage);
			 mybi = ImageIO.read(myImage);
		 } catch(Exception e){   
			 e.printStackTrace();   
		 } 
		 sourceImageWidth = bi.getWidth();
		 sourceImageHeight = bi.getHeight();
		 sourceMinx = bi.getMinX();
		 sourceMiny = bi.getMinY();
		 
		 myImageWidth = mybi.getWidth();
		 myImageHeight = mybi.getHeight();
		 myMinx = mybi.getMinX();
		 myMiny = mybi.getMinY();
		 
		 sourcergb = new int[3][sourceImageHeight][sourceImageWidth];
		 myrgb = new int[3][myImageHeight][myImageWidth];
		 
		 for(int i = sourceMinx;i < sourceImageHeight;i++){   
			 for(int j = sourceMiny;j < sourceImageWidth;j++){      
				 int pixel = bi.getRGB(j, i);   
				 sourcergb[0][i][j] = (pixel & 0xff0000 ) >> 16 ;   
			 	 sourcergb[1][i][j] = (pixel & 0xff00 ) >> 8 ;   
			 	 sourcergb[2][i][j] = (pixel & 0xff );
			 }
		 }
		 
		 for(int i = myMinx;i < myImageHeight;i++){   
			 for(int j = myMiny;j < myImageWidth;j++){      
				 int pixel = mybi.getRGB(j, i);  
				 myrgb[0][i][j] = (pixel & 0xff0000 ) >> 16 ;   
			 	 myrgb[1][i][j] = (pixel & 0xff00 ) >> 8 ;   
			 	 myrgb[2][i][j] = (pixel & 0xff );
			 }
		 }
	}
	
	public void testHeight()
	{
		assertEquals(sourceImageWidth, myImageWidth);
	}
	
	public void testWidth()
	{
		assertEquals(sourceImageHeight, myImageHeight);
	}
	
	public void testRGB()
	{
		 if (sourceImageWidth == myImageWidth && sourceImageHeight == myImageHeight
				 && sourceMinx == myMinx && sourceMiny == myMiny)
		 {
			 for (int i = 0; i != 3; i++)
			 {
				 for (int j = 0; j != sourceImageHeight; j++){
					 for (int k = 0; k != sourceImageWidth; k++)
					 {
						 assertEquals(sourcergb[i][j][k], myrgb[i][j][k]);
					 }
				 }
			 }
		 }
	}
}


