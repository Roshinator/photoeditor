package PhotoEditor;

public class Effects {
	
	/**
	 * Some utility functions to get you started. The functions redValue, greenValue, and blueValue
	 * each extract the amount of red, green, or blue in the pixel. The rgbValue function puts together
	 * a red, green, and blue value to produce a new RGB pixel.
	 */
	public static int redValue(int rgb) {
		return (rgb>>16) & 0xff;
	}

	public static int greenValue(int rgb) {
		return (rgb>>8) & 0xff;
	}

	public static int blueValue(int rgb) {
		return rgb & 0xff;
	}

	public static int rgbValue(int r, int g, int b) {
		return (r<<16 | g<<8 | b);
	}
	
	public static int getWidth(int[][] image) {
		return image.length;
	}
	
	public static int getHeight(int[][] image) {
		return image[0].length;
	}
	
	/**
	 * Resize the array image to the new width and height
	 * You are going to need to figure out how to map between a pixel
	 * in the destination image to a pixel in the source image.
	 */
	public static int[][] resize(int[][] source, int newWidth, int newHeight) {
		int width = getWidth(source);
		int height = getHeight(source);
		int[][] output = new int[newWidth][newHeight];
		
		for (int x = 0 ; x < newWidth ; x++) {
			for (int y = 0 ; y < newHeight ; y++) {
				output[x][y] = source[x * width / newWidth][y * height / newHeight];
			}
		}
		return output;
	}

	public static int[][] half(int[][] source) {
		return resize(source, getWidth(source) / 2, getHeight(source) / 2);
	}

	public static int[][] resize(int[][] source, int[][] reference) {
		return resize(source, getWidth(reference), getHeight(reference));
	}

	public static int[][] copy(int[][] source) {
		return resize(source, getWidth(source), getHeight(source));
	}
	
	/**
	 *	Flips the image over the x-axis.
	 */
	public static int[][] flip(int[][] source) {
		return source;
	}

	/**
	 *	Mirrors the image over the y-axis.
	 */
	public static int[][] mirror(int[][] source) {
		return source;
	}

	/**
	 *	Rotates the image left.
	 */
	public static int[][] rotateLeft(int[][] source) {
		return source;
	}

	/**
	 *	Rotates the image right.
	 */
	public static int[][] rotate(int[][] source) {
		return source;
	}

	/** 
	 * Merge the red,blue,green components from two images.
	 */
	public static int[][] merge(int[][] foreImage, int[][] backImage) {
		return foreImage;
	}

	/**
	 * Replace the green areas of the foreground image with parts of the back
	 * image.
	 */
	public static int[][] chromaKey(int[][] foreImage, int[][] backImage) {
		return foreImage;
	}

	/** 
	 *	Remove redeye. Note that sourceB is not used.
	 */
	public static int[][] redeye(int[][] source, int[][] sourceB) {
		return source;
	}

	/**
	 *	Add some funk to the image.
	 */
	public static int[][] funky(int[][] source, int[][] sourceB) {
		return source;
	}
	
    /**
     *  A very useful utility function
     */
    public static int changePixel(int pixel, int amount) {
        return pixel;
	}
    
	public static int[][] grayscale(int[][] source) {
		return source;
	}
	
	public static int[][] brighten(int[][] source) {
		return source;
	}
	
	public static int[][] fade(int[][] source) {
		return source;
	}
	
	public static int[][] vignette(int[][] source) {
		return source;
	}
	
	
	
	public static int[][] darken(int[][] source) {
		return source;
	}
	
	public static int[][] cool(int[][] source) {
		return source;
	}
	
	public static int[][] blur(int[][] source) {
		return source;
	}
	
	/**
	 * Hides one image inside another using bit operations.
	 */
	public static int[][] hide(int[][] mainImage, int[][] secretImage) {
		int width = mainImage.length, height = mainImage[0].length;
		int[][] resizedSecret = Effects.resize(secretImage, mainImage);
		int[][] result = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int secretRGB = resizedSecret[i][j]; 
				int sRed = redValue(secretRGB);
				int sGreen = greenValue(secretRGB);
				int sBlue = blueValue(secretRGB);
				
				// main decoy image should be something unremarkable
				int rgb = mainImage[i][j]; 
				int red = redValue(rgb);
				int green = greenValue(rgb);
				int blue = blueValue(rgb);
				
				red = (red & 0xf0) | (sRed >>4);
				blue = (blue & 0xf0) | (sBlue >>4);
				green =(green & 0xf0) | (sGreen >>4);
				result[i][j] = rgbValue(red, green, blue);
			}
		}
		return result;
	}
	
	public static int[][] hide2(int[][] mainImage, int[][] secretImage, String key) {
		int width = mainImage.length, height = mainImage[0].length;
		int[][] secret = Effects.resize(secretImage, mainImage);
		int[][] result = new int[width][height];
		key=key.substring(0,3);
		int rShift=0, gShift=0, bShift=0;
		for (int x=0 ; x<3; x++) {
			if (x%3==0) { rShift+=(int)key.charAt(x); }
			if (x%3==1) { gShift+=(int)key.charAt(x); }
			if (x%3==2) { bShift+=(int)key.charAt(x); }
		}
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int secretRGB = secret[i][j]; 
				int sRed = redValue(secretRGB);
				int sGreen = greenValue(secretRGB);
				int sBlue = blueValue(secretRGB);
				
				// main decoy image should be something unremarkable
				int rgb = mainImage[i][j]; 
				int red = redValue(rgb);
				int green = greenValue(rgb);
				int blue = blueValue(rgb);
				
				red = (red & 0xf0) | (sRed >>4);
				blue = (blue & 0xf0) | (sBlue >>4);
				green =(green & 0xf0) | (sGreen >>4);
				result[i][j] = rgbValue(red, green, blue);
			}
		}
		return result;
	}

	/**
	 * Extract the image back from the stenographically hidden one.
	 */
	public static int[][] extract(int[][] source) {
		int width = source.length, height = source[0].length;
		int[][] result = new int[width][height];
		
		for (int x = 0 ; x < width ; x++) {
			for (int y = 0 ; y < height ; y++) {
				int rgb = source[x][y];
				int red = redValue(rgb);
				int green = greenValue(rgb);
				int blue = blueValue(rgb);
				red = red << 4 & 0xff;
				blue = blue << 4 & 0xff;
				green = green << 4 & 0xff;
				result[x][y] = rgbValue(red, green, blue);
			}
		}
		return result;
	}
	
	/**
	 * Want to make your own effect? Here's how you do it.
	 * 1. Make a function in this class. There's a template below if you need help getting started.
	 * 2. Add in a condition to the process function below, which returns the output of your function. If your function
	 * 	  only takes one input, only pass it the source variable, otherwise pass it source and background.
	 * 3. Add your function to a menu.
	 */
	public static int[][] template(int[][] sourceOne, int[][] sourceTwo) {
		int width = sourceOne.length;
		int height = sourceOne[0].length;
		int[][] output = new int[width][height];
		
		// Your code here for modifying the output.
		
		return output;
	}
	
	public static int[][] process(String cmd, int[][] source, int[][] background) {
		try {
			if (cmd.equals("Half")) {			return Effects.half(source); 				}
			if (cmd.equals("Rotate")) {			return Effects.rotateLeft(source);			}
			if (cmd.equals("Flip")) {			return Effects.flip(source);				}
			if (cmd.equals("Mirror")) {			return Effects.mirror(source);				}
			if (cmd.equals("Redeye")) {			return Effects.redeye(source, background);	}
			if (cmd.equals("Funky")) {			return Effects.funky(source,background);	}
			if (cmd.equals("Grayscale")) {		return Effects.grayscale(source);			}
			if (cmd.equals("Vignette")) {		return Effects.vignette(source);			}
			if (cmd.equals("Cool")) {			return Effects.cool(source);				}
			if (cmd.equals("Fade")) {			return Effects.fade(source);				}
			if (cmd.equals("Blur")) {			return Effects.blur(source);				}
			if (cmd.equals("Brighten")) {		return Effects.brighten(source);			}
			if (cmd.equals("Resize")) {			return Effects.resize(source, background);  }
			
			if (cmd.equals("Merge")) {			return Effects.merge(source, background);	 }
			if (cmd.equals("Chromakey")) {		return Effects.chromaKey(source, background);}
			if (cmd.equals("Hide")) {			return Effects.hide(source, background);	 }
			if (cmd.equals("Extract")) {		return Effects.extract(source);				 }
		}
		catch (ArrayIndexOutOfBoundsException e) {
			PhotoEditor.failure("Array index went out of bounds.");
			PhotoEditor.failure(e.getMessage());
			return null;
		}
		return source;
	}
	
	public static String[][] getMenu() {
		return new String[][] {
			{ "Size", 		/* Contents */ "Half", "Rotate", "Flip", "Mirror" },
			{ "Color", 		/* Contents */ "Redeye", "Grayscale", "Brighten", "Vignette" },
			{ "Combine", 	/* Contents */ "Size To", "Merge", "Chromakey" },
			{ "Stenography",/* Contents */ "Hide", "extract" }
		};
	}
}
