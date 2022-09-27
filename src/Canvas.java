import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rasterdata.Presentable;
import rasterdata.RasterImage;
import rasterdata.RasterImageBI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu
 * 
 * @author PGRF FIM UHK
 * @version 2020
 */

public class Canvas {

	private JFrame frame;
	private JPanel panel;
	private final @NotNull RasterImage<Integer> img;
	private final @NotNull Presentable<Graphics> presenter;

	public Canvas(int width, int height) {
		frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	//	img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final @NotNull RasterImageBI auxRasterImage = new RasterImageBI(width, height);
		img = auxRasterImage;
		presenter = auxRasterImage;
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				present(g);
			}
		};

		panel.setPreferredSize(new Dimension(width, height));

		panel.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				img.setPixel(e.getX(), e.getY(), 0xff0000);
				present();
			}
		});

		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_C) {
					clear();
					present();
				}
			}
		});

		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		panel.grabFocus();
	}

	public void clear() {
		img.clear(0x2f2f2f2);

	}

	public void present(Graphics graphics) {

		presenter.present(graphics);
	}

	public void present (){
		final @Nullable Graphics g = panel.getGraphics();
		if (g != null){

		presenter.present(g);}
	}

	public void draw() {
		clear();
		img.setPixel(10, 10, 0xffff00);
	}

	public void start() {
		img.setPixel(img.getHeight()/2, img.getWidth()/2,Color.red.getRGB());
		present();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Canvas(800, 800).start());
	}

}