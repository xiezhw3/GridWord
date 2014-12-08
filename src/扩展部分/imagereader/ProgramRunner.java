import java.io.IOException;

import imagereader.Runner;

public final class ProgramRunner {
	private ProgramRunner() {}
	public static void main(String[] args) throws IOException{
		ImageIOER imageioer = new ImageIOER();
		ImageProcessor processor = new ImageProcessor();
		String sourImagPath = "/Users/wc/Downloads/bmptest/my2.bmp";
		imageioer.myWrite(imageioer.myRead("/Users/wc/Downloads/bmptest/2.bmp"), "/Users/wc/Downloads/bmptest/my2");
		imageioer.myWrite(processor.showChanelR(imageioer.myRead(sourImagPath)), "/Users/wc/Downloads/bmptest/myGoal/my2_red");
		imageioer.myWrite(processor.showChanelG(imageioer.myRead(sourImagPath)), "/Users/wc/Downloads/bmptest/myGoal/my2_green");
		imageioer.myWrite(processor.showChanelB(imageioer.myRead(sourImagPath)), "/Users/wc/Downloads/bmptest/myGoal/my2_blue");
		imageioer.myWrite(processor.showGray(imageioer.myRead(sourImagPath)), "/Users/wc/Downloads/bmptest/myGoal/my2_gray");
		Runner.run(imageioer, processor);
	}
}