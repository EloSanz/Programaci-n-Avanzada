package GrafosPaquete;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Simulador extends JPanel implements Runnable, MouseListener {
	private static final long serialVersionUID = 1L;
	private final int ancho = 780, alto = 680, FPS = 60, NODO_RADIO = 25;
	private int mouseX, mouseY, proximoPesoArista = 0;
	private boolean arrastrandoNodo = false, dibujandoArista = false;
	private Grafo grafo;
	private Nodo nodoSeleccionado;
	private BasicStroke stroke;
	private Font defaultFont, arialFont;
	private JTextArea pesoAristaArea;
	private Thread simulacionThread;
	private Random random;
	
	public Simulador() {
		this.setPreferredSize(new Dimension(this.ancho, this.alto));
		addMouseListener(this);
		this.setFocusable(true);
		this.setLayout(null);
		this.grafo = new Grafo();
		this.stroke = new BasicStroke(4); 
		this.defaultFont = new Font(Font.decode(null).getName(), Font.BOLD, 28);
		this.arialFont = new Font("Arial", Font.BOLD, 16);
		this.pesoAristaArea = new JTextArea();
		this.pesoAristaArea.setBounds(315, 84, 50, 20);
		this.pesoAristaArea.setFont(this.arialFont);
		this.pesoAristaArea.setEditable(true);
		this.pesoAristaArea.setFocusable(true);
		this.pesoAristaArea.setColumns(3);
		this.pesoAristaArea.setBackground(Color.DARK_GRAY);
		this.pesoAristaArea.setForeground(Color.WHITE);
		this.pesoAristaArea.setCaretColor(Color.WHITE);
		add(this.pesoAristaArea);
		this.random = new Random();
	}
	
	public void iniciarSimulacion() {
		simulacionThread = new Thread(this);
		simulacionThread.start();
	}
	
	private void agregarNodo(int x, int y) {
		this.grafo.agregarNodo(x, y);
	}
	
	private void update() {
		this.mouseX = MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x;
		this.mouseY = MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y;
		if(!this.arrastrandoNodo && !this.dibujandoArista) {
			this.nodoSeleccionado = null;
		} else if(this.arrastrandoNodo) {
			int x = (this.mouseX - this.NODO_RADIO);
			int y = (this.mouseY - this.NODO_RADIO);
			if(x < 0) x = 0;
				else if(x > getWidth() - this.NODO_RADIO * 2) x = getWidth() - this.NODO_RADIO * 2;
			if(y < 75) y = 75;
				else if(y > getHeight() - 125 - this.NODO_RADIO * 2) y = getHeight() - 125 - this.NODO_RADIO * 2;
			this.nodoSeleccionado.setX(x);
			this.nodoSeleccionado.setY(y);
		}
		if(!esEntero(this.pesoAristaArea.getText())) {
			this.pesoAristaArea.setText("");
			this.proximoPesoArista = 0;
		} else { 
			if(this.pesoAristaArea.getText().length() > 5) {
				this.pesoAristaArea.setText(this.pesoAristaArea.getText().substring(0, 5));
			}
			this.proximoPesoArista = Integer.parseInt(this.pesoAristaArea.getText());
		}
	}
	
	private boolean esEntero(String s) {
        if (s == null) return false;
        if (s.length() == 0) return false;
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	private void dibujarString(Graphics2D g2, Font f, String s, Color c, int x, int y, int tam, boolean sombra) {
		g2.setFont(f.deriveFont((float)tam));
		if(sombra) {
			g2.setColor(Color.black);
			g2.drawString(s, x + 3, y + 3);	
		}
		g2.setColor(c);
		g2.drawString(s, x, y);
	}
		
	private boolean mouseOver(int _mouseX, int _mouseY, int x, int y, int ancho, int alto) {
		return (_mouseX >= x && _mouseX <= x + ancho && _mouseY >= y && _mouseY <= y + alto);
	} 
	
	private boolean mouseDist(int _mouseX, int _mouseY, int x, int y, int radio) {
		return (Math.hypot(this.mouseX - x, this.mouseY - y) <= radio);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getWidth(), 75);
		g.fillRect(0, getHeight() - 125, getWidth(), 125);
		dibujarString(g2, arialFont, "Camino mas corto: ", Color.BLUE, 10, 30, 24, false);
		dibujarString(g2, arialFont, "Camino menos costoso: ", Color.RED, 10, 60, 24, false);
		dibujarString(g2, arialFont, "Click izquierdo para arrastrar nodo", Color.BLACK, 460, 100, 18, false);
		dibujarString(g2, arialFont, "Click derecho para dibujar aristas", Color.BLACK, 460, 125, 18, false);
		dibujarString(g2, arialFont, "Peso de la proxima arista: ", Color.BLACK, 10, 100, 24, false);

		g.setColor(Color.WHITE);
		g.fillRect(25, this.alto - 100, 150, 75);
		g.fillRect((this.ancho / 2) - 75, this.alto - 100, 150, 75);
		g.fillRect(this.ancho - 175, this.alto - 100, 150, 75);
		g.setColor(Color.BLACK);
		g2.setStroke(this.stroke);
		g.drawRect(25, this.alto - 100, 150, 75);
		g.drawRect((this.ancho / 2) - 75, this.alto - 100, 150, 75);
		g.drawRect(this.ancho - 175, this.alto - 100, 150, 75);
		if (mouseOver(this.mouseX, this.mouseY, 25, this.alto - 100, 150, 75)) {
			dibujarString(g2, arialFont, "DIJKSTRA", Color.ORANGE, 40, this.alto - 55, 24, false);
		} else
			dibujarString(g2, arialFont, "DIJKSTRA", Color.BLACK, 40, this.alto - 55, 24, false);
		if (mouseOver(this.mouseX, this.mouseY, (this.ancho / 2) - 75, this.alto - 100, 150, 75)) {
			dibujarString(g2, arialFont, "AGREGAR", Color.ORANGE, (this.ancho / 2) - 60, this.alto - 68, 24, false);
			dibujarString(g2, arialFont, "NODO", Color.ORANGE, (this.ancho / 2) - 36, this.alto - 40, 24, false);
		} else {
			dibujarString(g2, arialFont, "AGREGAR", Color.BLACK, (this.ancho / 2) - 60, this.alto - 68, 24, false);
			dibujarString(g2, arialFont, "NODO", Color.BLACK, (this.ancho / 2) - 36, this.alto - 40, 24, false);
		}
		if (mouseOver(this.mouseX, this.mouseY, this.ancho - 175, this.alto - 100, 150, 75)) {
			dibujarString(g2, arialFont, "BORRAR", Color.ORANGE, this.ancho - 150, this.alto - 68, 24, false);
			dibujarString(g2, arialFont, "NODOS", Color.ORANGE, this.ancho - 142, this.alto - 40, 24, false);
		} else {
			dibujarString(g2, arialFont, "BORRAR", Color.BLACK, this.ancho - 150, this.alto - 68, 24, false);
			dibujarString(g2, arialFont, "NODOS", Color.BLACK, this.ancho - 142, this.alto - 40, 24, false);
		}

		if (this.dibujandoArista) {
			g.setColor(Color.ORANGE);
			g2.drawLine(this.nodoSeleccionado.getX() + this.NODO_RADIO, this.nodoSeleccionado.getY() + this.NODO_RADIO, this.mouseX, this.mouseY);
			double angle = -Math.atan2(this.mouseX - (this.nodoSeleccionado.getX() + this.NODO_RADIO), this.mouseY - (this.nodoSeleccionado.getY() + this.NODO_RADIO)) - Math.PI / 2;
			int[] trianguloX = {this.mouseX, (int) (this.mouseX + 25 * Math.cos(angle + 0.4)), (int) (this.mouseX + 25 * Math.cos(angle - 0.4))};
			int[] trianguloY = {this.mouseY, (int) (this.mouseY + 25 * Math.sin(angle + 0.4)), (int) (this.mouseY + 25 * Math.sin(angle - 0.4))};
			g2.fillPolygon(trianguloX, trianguloY, 3);
		}

		g2.setStroke(this.stroke);
		g2.setFont(this.defaultFont);
		g2.setColor(Color.BLACK);
		for (Nodo nodo : this.grafo.getNodos()) {
			for (Map.Entry<Integer, Integer> arista : nodo.getConexiones().entrySet()) {
				int aristaX = this.grafo.getNodos().get(arista.getKey()).getX(), aristaY = this.grafo.getNodos().get(arista.getKey()).getY();
				g2.drawLine(nodo.getX() + this.NODO_RADIO, nodo.getY() + this.NODO_RADIO, aristaX + this.NODO_RADIO, aristaY + this.NODO_RADIO);
				double angle = -Math.atan2((aristaX + this.NODO_RADIO) - (nodo.getX() + this.NODO_RADIO), (aristaY + this.NODO_RADIO) - (nodo.getY() + this.NODO_RADIO)) - Math.PI / 2;
				int[] trianguloX = {(int) ((aristaX + this.NODO_RADIO) + this.NODO_RADIO * Math.cos(angle)), (int) ((aristaX + this.NODO_RADIO) + (this.NODO_RADIO + 25) * Math.cos(angle + 0.2)), (int) ((aristaX + this.NODO_RADIO) + (this.NODO_RADIO + 25) * Math.cos(angle - 0.2))};
				int[] trianguloY = {(int) ((aristaY + this.NODO_RADIO) + this.NODO_RADIO * Math.sin(angle)), (int) ((aristaY + this.NODO_RADIO) + (this.NODO_RADIO + 25) * Math.sin(angle + 0.2)), (int) ((aristaY + this.NODO_RADIO) + (this.NODO_RADIO + 25) * Math.sin(angle - 0.2))};
				g2.fillPolygon(trianguloX, trianguloY, 3);
				angle = -Math.atan2((nodo.getX() + this.NODO_RADIO) - (aristaX + this.NODO_RADIO), (nodo.getY() + this.NODO_RADIO) - (aristaY + this.NODO_RADIO)) - Math.PI / 2;
				AffineTransform orig = g2.getTransform();
				g2.translate((int) (nodo.getX() + this.NODO_RADIO + (aristaX + this.NODO_RADIO - nodo.getX() + this.NODO_RADIO) / 2 - g2.getFontMetrics().getStringBounds(Integer.toString(arista.getValue()), g2).getWidth()), nodo.getY() + this.NODO_RADIO + (aristaY + this.NODO_RADIO - nodo.getY() + this.NODO_RADIO) / 2);
				g2.rotate(angle);
				g.setColor(getBackground());
				g2.fillRect(0, -(int)(g2.getFontMetrics().getStringBounds(Integer.toString(arista.getValue()), g2).getHeight())/2, (int)(g2.getFontMetrics().getStringBounds(Integer.toString(arista.getValue()), g2).getWidth()*0.75), (int)(g2.getFontMetrics().getStringBounds(Integer.toString(arista.getValue()), g2).getHeight())/2);
				dibujarString(g2, arialFont, Integer.toString(arista.getValue()), Color.BLACK, 0, 0, 18, false);
				g2.setTransform(orig);				
			}
		}
		for (Nodo nodo : this.grafo.getNodos()) {
			g.setColor(Color.WHITE);
			g.fillOval(nodo.getX(), nodo.getY(), this.NODO_RADIO * 2, this.NODO_RADIO * 2);
			if(this.arrastrandoNodo && nodo.id == this.nodoSeleccionado.id) g.setColor(Color.ORANGE); 
				else g.setColor(Color.BLACK);
			g.drawOval(nodo.getX(), nodo.getY(), this.NODO_RADIO * 2, this.NODO_RADIO * 2);
			g.drawString(Integer.toString(nodo.id + 1), nodo.getX() + this.NODO_RADIO - (int) (g2.getFontMetrics().getStringBounds(Integer.toString(nodo.id + 1), g2).getWidth() / 2), nodo.getY() + this.NODO_RADIO + (int) (g2.getFontMetrics().getStringBounds(Integer.toString(nodo.id + 1), g2).getHeight() / 4));
		}
	}
	 
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(mouseOver(this.mouseX, this.mouseY, (this.ancho / 2) - 75, this.alto - 100, 150, 75)) {
				agregarNodo(this.random.nextInt(this.ancho - this.NODO_RADIO * 2), this.random.nextInt(75, this.alto - 125 - this.NODO_RADIO * 2));
			}
			if(mouseOver(this.mouseX, this.mouseY, this.ancho - 175, this.alto - 100, 150, 75)) {
				this.grafo.getNodos().clear();
				this.grafo.setCantNodos(0);
			}
			if(mouseOver(this.mouseX, this.mouseY, 0, 75, this.ancho, getHeight() - 200)) {
				for(Nodo nodo : this.grafo.getNodos()) {
					if(mouseDist(this.mouseX, this.mouseY, nodo.getX() + this.NODO_RADIO, nodo.getY() + this.NODO_RADIO, this.NODO_RADIO)) {
						this.arrastrandoNodo = true;
						this.nodoSeleccionado = nodo;
					}
				}
			}
		} else if(e.getButton() == MouseEvent.BUTTON3) {
			if(mouseOver(this.mouseX, this.mouseY, 0, 75, this.ancho, getHeight() - 200)) {
				for(Nodo nodo : this.grafo.getNodos()) {
					if(mouseDist(this.mouseX, this.mouseY, nodo.getX() + this.NODO_RADIO, nodo.getY() + this.NODO_RADIO, this.NODO_RADIO)) {
						this.dibujandoArista = true;
						this.nodoSeleccionado = nodo;
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(this.arrastrandoNodo) {
				this.arrastrandoNodo = false;
			}
		} else if(e.getButton() == MouseEvent.BUTTON3) {
			if(this.dibujandoArista) {
				this.dibujandoArista = false;
				for(Nodo nodo : this.grafo.getNodos()) {
					if(mouseDist(this.mouseX, this.mouseY, nodo.getX() + this.NODO_RADIO, nodo.getY() + this.NODO_RADIO, this.NODO_RADIO) && this.nodoSeleccionado.id != nodo.id) {
						this.grafo.agregarArista(this.nodoSeleccionado.id, nodo.id, this.proximoPesoArista);
					}
				}
				/*for (Nodo nodo : this.grafo.getNodos()) {
					for (Map.Entry<Integer, Integer> arista : nodo.getConexiones().entrySet()) {
						System.out.println("Nodo id: " + nodo.id + " | Arista id: " + this.grafo.getNodos().get(arista.getKey()).id + " | Peso: " + arista.getValue());
					}
				}
				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+");*/
			}	
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS, delta = 0;
		long lastTime = System.nanoTime(), currentTime;
		
		while(simulacionThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();	
				delta--;
			}
		}
	}
}
