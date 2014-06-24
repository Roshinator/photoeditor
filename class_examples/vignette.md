The pixel's distance to the center point determines *how much it gets darkened*.

```java
// get the width and height of the image
		int width = getWidth(source);
		int height = getHeight(source);
		// make a new output image
		int[][] output = new int[width][height];

		// Figure out the center point before going to every pixel
		int centerx = width / 2;
		int centery = height / 2;
		
		for (int x = 0 ; x < width ; x = x + 1) {
			for (int y = 0 ; y < height ; y = y + 1) {
				int pixel = source[x][y];
				int red = redValue(pixel);
				int green = greenValue(pixel);
				int blue = blueValue(pixel);
				
				// Calculate the distance and the amount to subtract
				// from this pixel.
				double distance = Math.sqrt( Math.pow(x - centerx, 2) + Math.pow(y - centery, 2) );
				int amount = (int) (distance / 5);
				
				// Subtract the amount from r, g, and b
				red = red - amount;
				green = green - amount;
				blue = blue - amount;
				
				// Check that the r, g, b values haven't gotten too small
				if (red < 0) {
					red = 0;
				}
				if (green < 0) {
					green = 0;
				}
				if (blue < 0) {
					blue = 0;
				}
				// Set the output pixel to the r, g, b value
				output[x][y] = rgbValue(red, green, blue);
			}
		}
		
		return output;
```
