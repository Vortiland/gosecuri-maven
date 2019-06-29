package com.boc.gosecuri.view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPicture extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// l'image redimentionne
	private Image picture;

	/**
	 * 
	 * @param path
	 * @throws IOException
	 */
	public PanelPicture(String path) throws IOException {
		this(path, 200, 200);
	}

	/**
	 * 
	 * @param path
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public PanelPicture(String path, int width, int height) throws IOException {
		this(pictureFromPath(path), width, height);
	}

	/**
	 * 
	 * @param picture
	 */
	public PanelPicture(Image picture) {
		this(picture, 100, 100);
	}

	/**
	 * 
	 * @param img
	 * @param width
	 * @param height
	 */
	public PanelPicture(Image picture, int width, int height) {
		this.picture = getScaledImage(picture, width, height);
		this.add(new JLabel(new ImageIcon(this.picture)));
	}

	/*
	 * load the picture
	 */
	private static Image pictureFromPath(String path) throws IOException {
		return ImageIO.read(new File(PanelPicture.class.getClassLoader().getResource(path).getFile()));
	}

	/*
	 * resize the picture
	 */
	private Image getScaledImage(Image srcImg, int width, int height) {
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, width, height, null);
		g2.dispose();

		return resizedImg;
	}

}
