/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.graphics;

import static java.lang.Math.abs;

import java.awt.Font;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.joshuahuelsman.molasses.display.Display;
import com.joshuahuelsman.molasses.display.RenderingContext;

/**
 * This is a custom software renderer to replace AWT's
 * Graphics renderer. It's not nearly as useful, however.
 * @author joshuahuelsman
 *
 */
public class Graphics {

	private int clearColor;

	private int currentColor;
	
	private int filter = Color.magenta.getRGB();

	private int[] pixels;

	private Font font;

	private Transform t = new Transform();

	public Graphics() {
		pixels = RenderingContext.getInstance().getPixels();
		font = new Font("Arial", Font.PLAIN, 16);
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = clearColor;
		}
	}

	public void setColor(Color current) {
		currentColor = current.getRGB();
	}

	public void setColor(int color) {
		currentColor = color;
	}

	public void setClearColor(Color clear) {
		clearColor = clear.getRGB();
	}

	private void fillRect(int x, int y, int width, int height) {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				putPixel(i + x, j + y);
			}
		}
	}

	public void fillRect(float x, float y, float width, float height) {
		fillRect((int) (x * t.scale), (int) (y * t.scale),
				(int) (width * t.scale), (int) (height * t.scale));
	}

	/*
	 * http://www.gamedev.net/page/resources/_/technical/game-programming/line-
	 * drawing-algorithm-explained-r1275
	 */
	private void drawLine(int x1, int y1, int x2, int y2) {
		int deltax = abs(x2 - x1);
		int deltay = abs(y2 - y1);

		int x = x1;
		int y = y1;

		int xinc1, xinc2, yinc1, yinc2, den, num, numadd, numpixels;

		if (x2 >= x1) // The x-values are increasing
		{
			xinc1 = 1;
			xinc2 = 1;
		} else // The x-values are decreasing
		{
			xinc1 = -1;
			xinc2 = -1;
		}

		if (y2 >= y1) // The y-values are increasing
		{
			yinc1 = 1;
			yinc2 = 1;
		} else // The y-values are decreasing
		{
			yinc1 = -1;
			yinc2 = -1;
		}

		if (deltax >= deltay) // There is at least one x-value for every y-value
		{
			xinc1 = 0; // Don't change the x when numerator >= denominator
			yinc2 = 0; // Don't change the y for every iteration
			den = deltax;
			num = deltax / 2;
			numadd = deltay;
			numpixels = deltax; // There are more x-values than y-values
		} else // There is at least one y-value for every x-value
		{
			xinc2 = 0; // Don't change the x for every iteration
			yinc1 = 0; // Don't change the y when numerator >= denominator
			den = deltay;
			num = deltay / 2;
			numadd = deltax;
			numpixels = deltay; // There are more y-values than x-values
		}

		for (int curpixel = 0; curpixel <= numpixels; curpixel++) {
			putPixel(x, y); // Draw the current pixel
			num += numadd; // Increase the numerator by the top of the fraction
			if (num >= den) // Check if numerator >= denominator
			{
				num -= den; // Calculate the new numerator value
				x += xinc1; // Change the x as appropriate
				y += yinc1; // Change the y as appropriate
			}
			x += xinc2; // Change the x as appropriate
			y += yinc2; // Change the y as appropriate
		}
	}

	public void drawLine(float x1, float y1, float x2, float y2) {
		drawLine((int) (x1 * t.scale), (int) (y1 * t.scale), (int)(x2 * t.scale),
				(int) (y2 * t.scale));
	}

	private void putPixel(int x, int y) {
		int location = x + y * Display.getDisplayMode().getWidth();
		if (location >= pixels.length
				|| x >= Display.getDisplayMode().getWidth() || x < 0 || y < 0
				|| y > Display.getDisplayMode().getHeight()) {
			return;
		}

		if(currentColor != filter) {
			pixels[location] = currentColor;
		}
	}

	private void putPixel(int x, int y, int color) {
		int last = currentColor;
		setColor(color);
		putPixel(x, y);
		setColor(last);
	}

	public void putPoint(int x, int y) {
		putPixel(x, y);
	}

	private void drawRect(int x, int y, int width, int height) {
		drawLine(x, y, x + width, y);
		drawLine(x, y, x, y + height);
		drawLine(x + width, y, x + width, y + height);
		drawLine(x, y + height, x + width, y + height);
	}
	
	public void drawRect(float x, float y, float width, float height) {
		drawRect((int) (x * t.scale), (int) (y * t.scale),
				(int) (width * t.scale), (int) (height * t.scale));
	}


	private void drawImage(Image image, int x, int y) {
		BufferedImage bi = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		bi.getGraphics().drawImage(image, 0, 0, null);
		int[] datao = ((DataBufferInt) bi.getData().getDataBuffer()).getData();
		int w = bi.getWidth(null);
		int h = bi.getHeight(null);
		int[] data = resizePixels(datao, w, h, (int)(w * t.scale), (int)(h * t.scale));
		for (int j = 0; j < (int)(h * t.scale); j++) {
			for (int i = 0; i < (int)(w * t.scale); i++) {
				putPixel(x + i, y + j, data[i + j * (int)(w * t.scale)]);
			}
		}
	}

	public int[] resizePixels(int[] pixels, int w1, int h1, int w2, int h2) {
		int[] temp = new int[w2 * h2];
		// EDIT: added +1 to account for an early rounding problem
		int x_ratio = (int) ((w1 << 16) / w2) + 1;
		int y_ratio = (int) ((h1 << 16) / h2) + 1;
		// int x_ratio = (int)((w1<<16)/w2) ;
		// int y_ratio = (int)((h1<<16)/h2) ;
		int x2, y2;
		for (int i = 0; i < h2; i++) {
			for (int j = 0; j < w2; j++) {
				x2 = ((j * x_ratio) >> 16);
				y2 = ((i * y_ratio) >> 16);
				temp[(i * w2) + j] = pixels[(y2 * w1) + x2];
			}
		}
		return temp;
	}

	private void drawString(String str, int x, int y) {
		FontRenderContext frc = new FontRenderContext(null,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF,
				RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		GlyphVector glyph = font.createGlyphVector(frc, str);
		PathIterator i = glyph.getOutline(x, y).getPathIterator(null);
		float[] last = new float[2];
		float[] first = null;
		while (!i.isDone()) {
			float[] p = new float[6];
			int type = i.currentSegment(p);

			if (first == null) {
				first = new float[2];
				first[0] = p[0];
				first[1] = p[1];
			}

			if (type == PathIterator.SEG_LINETO) {
				drawLine(last[0], last[1], p[0], p[1]);
				last[0] = p[0];
				last[1] = p[1];
			} else if (type == PathIterator.SEG_CUBICTO) {
				for (float t = 0; t < 1f; t += 0.05f) {
					float[] p1 = bezier(t, last, p);
					putPoint((int) p1[0], (int) p1[1]);
					last[0] = p1[0];
					last[1] = p1[1];
				}
			} else if (type == PathIterator.SEG_QUADTO) {
				for (float t = 0; t < 1f; t += 0.05f) {
					float[] p1 = quad(t, last, p);
					putPoint((int) p1[0], (int) p1[1]);
					last[0] = p[0];
					last[1] = p[1];
				}
			} else if (type == PathIterator.SEG_MOVETO) {
				last[0] = p[0];
				last[1] = p[1];
			} else if (type == PathIterator.SEG_CLOSE) {
				drawLine(last[0], last[1], first[0], first[1]);
				first = null;
			}

			i.next();
		}
	}

	public void drawString(String str, float x, float y) {
		Font orig = font;
		font = font.deriveFont(font.getSize2D() * t.scale);
		drawString(str, (int)(x * t.scale), (int)(y * t.scale));
		font = orig;
	}
	
	private float[] quad(float t, float[] cp, float[] p) {
		// P(t) = B(2,0)*CP + B(2,1)*P1 + B(2,2)*P2
		// 0 <= t <= 1
		float x = bpolynomial(2, 0, t) * cp[0] + bpolynomial(2, 1, t) * p[0]
				+ bpolynomial(2, 2, t) * p[2];
		float y = bpolynomial(2, 0, t) * cp[1] + bpolynomial(2, 1, t) * p[1]
				+ bpolynomial(2, 2, t) * p[3];
		return new float[] { x, y };
	}

	private float[] bezier(float t, float[] cp, float[] p) {
		// P(t) = B(3,0)*CP + B(3,1)*P1 + B(3,2)*P2 + B(3,3)*P3
		// 0 <= t <= 1
		float x = bpolynomial(3, 0, t) * cp[0] + bpolynomial(3, 1, t) * p[0]
				+ bpolynomial(3, 2, t) * p[2] + bpolynomial(3, 3, t) * p[4];
		float y = bpolynomial(3, 0, t) * cp[1] + bpolynomial(3, 1, t) * p[1]
				+ bpolynomial(3, 2, t) * p[3] + bpolynomial(3, 3, t) * p[5];
		return new float[] { x, y };
	}

	private float bpolynomial(float n, float m, float t) {
		return (float) (combination(n, m) * Math.pow(t, m) * Math.pow(1 - t, n
				- m));
	}

	private float combination(float n, float m) {
		return factorial(n) / (factorial(m) * factorial(n - m));
	}

	private float factorial(float n) {
		int ret = 1;
		for (int i = 1; i <= n; i++) {
			ret *= i;
		}
		return ret;
	}

	public void drawImage(Image image, float x, float y) {
		drawImage(image, (int) (x * t.scale), (int) (y * t.scale));
	}
	
	public void scale(float s) {
		t.scale = s;
	}

}
