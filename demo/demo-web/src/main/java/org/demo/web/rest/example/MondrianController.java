package org.demo.web.rest.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.demo.common.jodo.RectangleDO;
import org.demo.common.jodo.RectangleDO.ColorDO;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Mondrian Controller
 * 
 * Tunings:
 * 1. Using 'unit' instead of px to random rectangle elegantly.
 * 2. Actively avoid creating abnormal rectangles, only allow passive abnormal rectangle.
 * 3. Filling color after generating all rectangles with rates to make sure every color would appear,
 *    and control their rates. White is usually half of total; red, yellow and blue always have similar count.
 * 4. Using 'border div' for each rectangle to make them orderly, instead of directly setting rectangles' border
 * 
 * @author willy
 *
 */
public class MondrianController implements ModelDriven<List<RectangleDO>> {
	/**
	 * unit of width and height, which splits the window into a grid
	 */
	private final int unit = 50;
	
	/**
	 * real window width (px)
	 */
	private int width;
	
	/**
	 * real window height (px)
	 */
	private int height;
	
	/**
	 * horizontal size when split the window into a grid
	 */
	private int horizontalUnitNum;
	
	/**
	 * vertical size when split the window into a grid
	 */
	private int verticalUnitNum;
	
	/**
	 * request parameter
	 * format: width_height  eg.'1000_800'
	 */
	private String id;
	
	/**
	 * response data
	 */
	private List<RectangleDO> model = new ArrayList<RectangleDO>();
	
	private Random random = new Random(System.currentTimeMillis());
	
	
	public HttpHeaders show() {
		if (!this.parseWindowSize(id)) {
			return new DefaultHttpHeaders("error");
		}
		
		// Consider the window as a canvas, consisted of many squares. Each square is unit px * unit px.
		// Each element of 'int [][] grid' represents a square.
		int[][] grid = new int[horizontalUnitNum][verticalUnitNum];
		
		for (int y = 0; y < verticalUnitNum; y ++) {
			for (int x = 0; x < horizontalUnitNum; x ++) {
				if (grid[x][y] != 0) {
					continue;
				}
				// Have got the next rectangle's vertex
				RectangleDO rectangle = new RectangleDO();
				rectangle.setX(x);
				rectangle.setY(y);
				this.generateWidth(grid, rectangle);
				this.generateHeight(grid, rectangle);
				model.add(rectangle);
				
				// Update the grid
				updateGridwithNewRec(grid, rectangle);
			}
		}
		
		// fill colors
		this.fillColors(model);
		
        return new DefaultHttpHeaders("mondrian");
    }
	
	/**
	 * generate a reasonable width, according to the context
	 * @param grid
	 * @param rectangle
	 */
	private void generateWidth(int[][] grid, RectangleDO rectangle) {
		int horizontalRange = this.getHorizontalRange(grid, rectangle);
		// avoid to create less than 2 units actively, only create abnormal rectangles when the horizontalRange <= 2
		int minWidth = 2 > horizontalRange ? horizontalRange : 2;
		// avoid too big rectangle, width should be less than 3/4 of the total width of the grid
		int maxWidth = Math.max(minWidth, Math.min(this.horizontalUnitNum * 3 / 4, horizontalRange));
		// random between minWidth and maxWidth
		rectangle.setWidth(minWidth + (minWidth == maxWidth ? 0 : random.nextInt(maxWidth - minWidth) + 1));
	}

	/**
	 * generate a reasonable height, according to the context
	 * @param grid
	 * @param rectangle
	 */
	private void generateHeight(int[][] grid, RectangleDO rectangle) {
		int verticalRange = this.getVerticalRange(grid, rectangle);
		// set min and max height according to width, make rectangle more normal
		// avoid the rectangle too short, height is at least is 1/3 of width
		int minHeight = Math.min(rectangle.getWidth() / 3, verticalRange);
		// avoid the rectangle too tall, height is less than 3/4 of the grid's height and less than 3 times of its width.
		int maxHeight = Math.max(minHeight, Math.min(Math.min(rectangle.getWidth() * 3, this.verticalUnitNum * 3 / 4), verticalRange));
		if (minHeight == 0) {
			minHeight = 1;
		}
		// random between minHight and maxHeight
		rectangle.setHeight(minHeight + (minHeight == maxHeight ? 0 : this.random.nextInt(maxHeight - minHeight) + 1));
	}
	
	
	/**
	 * fill random colors into rectangles
	 * make sure all kinds of colors will appear
	 * @param rectangles
	 * @param color
	 */
	private void fillColors(List<RectangleDO> rectangles) {
		int whiteRate;
		int redRate;
		int yellowRate;
		int blueRate;
		
		if (rectangles.size() <= 4) {
			redRate = 1;
			yellowRate = 1;
			blueRate = 1;
			whiteRate = 1;
		} else {
			// hope white has the around half rate; red, yellow and blue have an equal rates
			// but at least 3 spaces should be left for red, yellow and blue in total
			whiteRate = rectangles.size() >= 6 ? rectangles.size() / 2 : rectangles.size() - 3;
			redRate = (rectangles.size() - whiteRate) / 3;
			yellowRate = (rectangles.size() - whiteRate) / 3;
			blueRate = rectangles.size() - redRate - yellowRate - whiteRate;
		}
		
		// random
		Collections.shuffle(rectangles);
		
		for (int i = 0; i < rectangles.size(); i ++) {
			if (i < redRate) {
				rectangles.get(i).setColor(ColorDO.COLOR_RED);;
			} else if (i >= redRate && i < redRate + yellowRate) {
				rectangles.get(i).setColor(ColorDO.COLOR_YELLOW);
			} else if (i >= redRate + yellowRate && i < redRate + yellowRate + blueRate) {
				rectangles.get(i).setColor(ColorDO.COLOR_BLUE);
			} else {
				rectangles.get(i).setColor(ColorDO.COLOR_WHITE);
			}
		}
	}
	
	/**
	 * Fill the grid with a new rectangle, change the squares of the rectangle from 0 to 1 in the grid.
	 * The squares of '1' cannot be used any more. The squares of '0' still can be used.
	 * @param grid
	 * @param rectangle
	 */
	private void updateGridwithNewRec(int[][] grid, RectangleDO rectangle) {
		for (int x = rectangle.getX(); x < rectangle.getX() + rectangle.getWidth(); x ++) {
			for (int y = rectangle.getY(); y < rectangle.getY() + rectangle.getHeight(); y ++) {
				grid[x][y] = 1;
			}
		}
	}
	
	/**
	 * Get the available horizontal range for the rectangle.
	 * @param grid
	 * @param rectangle
	 * @return
	 */
	private int getHorizontalRange(int[][] grid, RectangleDO rectangle) {
		int horizontalRange = 0;
		for (int i = rectangle.getX(); i < this.horizontalUnitNum; i ++) {
			if (grid[i][rectangle.getY()] == 0) {
				horizontalRange ++;
			} else {
				break;
			}
		}
		return horizontalRange;
	}
	
	/**
	 * Get the available vertical range for the rectangle.
	 * @param grid
	 * @param rectangle
	 * @return
	 */
	private int getVerticalRange(int[][] grid, RectangleDO rectangle) {
		int verticalRange = 0;
		for (int i = rectangle.getY(); i < this.verticalUnitNum; i ++) {
			if (grid[rectangle.getX()][i] == 0) {
				verticalRange ++;
			} else {
				break;
			}
		}
		return verticalRange;
	}
	
	/**
	 * get browser's window size
	 * @param size
	 * @return
	 */
	private boolean parseWindowSize(String size) {
		if (size == null) {
			return false;
		} 
		
		String[] widthAndHeight = size.split("_");
		
		if (widthAndHeight.length != 2) {
			return false;
		} else if (!StringUtils.isNumeric(widthAndHeight[0]) 
				|| !StringUtils.isNumeric(widthAndHeight[1])
				|| Integer.parseInt(widthAndHeight[0]) <= unit
				|| Integer.parseInt(widthAndHeight[1]) <= unit) {
			return false;
		} else {
			this.width = Integer.parseInt(widthAndHeight[0]);
			this.height = Integer.parseInt(widthAndHeight[1]);
			this.horizontalUnitNum = (this.width + this.unit - 1) / this.unit;
			this.verticalUnitNum = (this.height + this.unit - 1) / this.unit;
			return true;
		}
	}

	public List<RectangleDO> getModel() {
		return model;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setModel(List<RectangleDO> model) {
		this.model = model;
	}
	
}
