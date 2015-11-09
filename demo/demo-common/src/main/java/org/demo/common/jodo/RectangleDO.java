package org.demo.common.jodo;


/**
 * Rectangle of mondrian background
 * @author willy
 */
public class RectangleDO {
	/**
	 * x coordinate
	 */
	private int x;
	
	/**
	 * y coordinate
	 */
	private int y;
	
	/**
	 * the width of rectangle
	 */
	private int width;
	
	/**
	 * the height of the rectangle
	 */
	private int height;
	
	/**
	 * the color of the rectangle, it can be of red, green or blue.
	 * eg. 'rgb(0,255,0)','rgb(0,0,200)', 'rgb(100,0,0)'
	 */
	private String color;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Color of Rectangle
	 * @author willy
	 *
	 */
	public static class ColorDO {
		/**
		 * red color
		 */
		public static final int COLOR_TYPE_RED = 0;
		
		/**
		 * green color
		 */
		public static final int COLOR_TYPE_YELLOW = 1;
		
		/**
		 * blue color
		 */
		public static final int COLOR_TYPE_BLUE = 2;
		
		/**
		 * white color
		 */
		public static final int COLOR_TYPE_WHITE = 3;
		
		/**
		 * white color in RGB color system.
		 */
		public static final String COLOR_WHITE = "rgb(255,255,255)";
		
		/**
		 * red color in RGB color system.
		 */
		public static final String COLOR_RED = "rgb(230,0,0)";
		
		/**
		 * yellow color in RGB color system.
		 */
		public static final String COLOR_YELLOW = "rgb(255,185,0)";
		
		/**
		 * blue color in RGB color system.
		 */
		public static final String COLOR_BLUE = "rgb(25,25,112)";
		
		/**
		 * Four groups of colors: red, green, blue, white
		 */
		public static final int COLOR_TYPE_COUNT = 4;
		
		/**
		 * Using RGB color system. Each color(red, yellow, blue) can be represented by 0 ~ 255.
		 */
		public static final int COLOR_VALUE_RANGE = 256;
		
	}
}
