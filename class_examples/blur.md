[ Description is on the way ]

```java
// get the width and height of the image
		int width = getWidth(source);
		int height = getHeight(source);
		// make a new output image
		int[][] output = new int[width][height];

		int range = 5;
		
		for (int x = 0 ; x < width ; x = x + 1) {
			for (int y = 0 ; y < height ; y = y + 1) {
				
				int sumRed = 0;
				int sumGreen = 0;
				int sumBlue = 0;
				int count = 0;
				
				// with this x and y, check out the neighborhood of pixels
				// around the pixel at x, y
				for (int a = x - range ; a <= x + range ; a = a + 1) {
					for (int b = y - range ; b <= y + range ; b = b + 1) {
						// If the a, b coordinate is valid
						if (a >= 0 && a < width && b >= 0 && b < height) {
							// Get the pixel, and sum up the red, green, blue parts
							int pixel = source[a][b];
							sumRed = sumRed + redValue(pixel);
							sumGreen = sumGreen + greenValue(pixel);
							sumBlue = sumBlue + blueValue(pixel);
							count = count + 1;
						}
					}
				}
				
				sumRed = sumRed / count;
				sumGreen = sumGreen / count;
				sumBlue = sumBlue / count;
				
				output[x][y] = rgbValue(sumRed, sumGreen, sumBlue);
				
				// go to every x from x -2 to x + 2
				// and every y from y - 2 to y + 2
				
			}
		}
		
		return output;
```
