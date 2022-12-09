package net.aaronlangley.tetrisClone;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TetrisClone {
	
	static boolean canContinue     = true;
	public static int width        = 250;
	public static int height       = 550;
	public static int spriteX      = 100;
	public static int spriteY      = 0;
	public static int maxSpriteY   = 475;
	public static ArrayList<Rectangle> sprites= new ArrayList<>();
	
	static void drawSprite(PaintEvent e) {
		Rectangle test = new Rectangle(spriteX+25, spriteY+25, 25, 25);
//		if(spriteY == maxSpriteY ){
//			maxSpriteY -= 25;
//			sprites.add(new Rectangle(spriteX, spriteY, 25, 25));
//			spriteX = 100;
//			spriteY = 0;
//		}
		
		for(int i=0; i < sprites.size(); i++) {
			if(sprites.get(i) == test) {
				sprites.add(new Rectangle(spriteX, spriteY, 25, 25));
				spriteX = 100;
				spriteY = 0;
			}
			e.gc.drawRectangle(sprites.get(i));
		}
		e.gc.drawRectangle(new Rectangle(spriteX, spriteY, 25, 25)); 
	}
	
	public static void main(String[] args) {

		String title        = "Tetris Clone";
		
		Display display = new Display();
		Shell shell     = new Shell(display);
		shell.setText(title);
		shell.setSize(width,height-50);
		shell.setLayout(new FillLayout());
		
		Canvas canvas = new Canvas(shell, SWT.BORDER | SWT.NO_BACKGROUND);
		canvas.addKeyListener(new KeyListener() {
			  @Override
			  public void keyPressed( KeyEvent e ) {
				  if (e.keyCode == SWT.ARROW_RIGHT && spriteX !=225) {
					  spriteX+=25;
				  }
			   
				  if (e.keyCode == SWT.ARROW_LEFT && spriteX !=0) {
					  spriteX-=25;
				  }
			  }

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		canvas.addPaintListener(new PaintListener(){	
			public void paintControl(PaintEvent e) {
				drawSprite(e);
			}    
		});
		
			display.timerExec(42, new Runnable() {
				 @Override
				 public void run() {
					canvas.redraw();
					spriteY++;
					display.timerExec(42, this);
				 }	            
			});
			shell.open();
			
			while(!shell.isDisposed())
				if (!display.readAndDispatch())
					display.sleep();
			display.dispose();
	}
}
