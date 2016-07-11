package atc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class Piloto extends JFrame {

	private JPanel painelPiloto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Piloto frame = new Piloto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Piloto() {
		setTitle("Janela do Piloto");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nadson&Carol\\Documents\\GitHub\\FinalProjectOOP\\Controlador De Voo\\iconPlane.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		painelPiloto = new JPanel();
		painelPiloto.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPiloto.setLayout(new BorderLayout(0, 0));
		setContentPane(painelPiloto);
	}

}
