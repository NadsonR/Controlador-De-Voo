package atc;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.Panel;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Checkbox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import entidades.*;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class ATCViewer extends JFrame {

	private JPanel painelPrincipal;
	
	/*##### Variaveis referente as tabelas ##########################*/
	private TableModelPousos tmp = new TableModelPousos();
	private TableModelDecolagem tmd = new TableModelDecolagem();
	
	public int index;
	private JTable tabelaPousos;
	private JTable tabelaDecolagens;
	private int numbPousosConsecutivos;
	private JLabel rtRelogio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATCViewer frame = new ATCViewer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void relogio(){
		Thread relogio = new Thread(){
			public void run(){
				try{
					while(true){
						Calendar cal = Calendar.getInstance();
				        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				        
				        rtRelogio.setText(sdf.format(cal.getTime()));
				        sleep(1000);
					}
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		};
		relogio.start();
	}
	
	public void pousos(){
		Thread Pousos = new Thread(){
			public void run(){
				try{
					while(true){
						tmp.fireTableRowsDeleted(0, 0);
				        sleep(10000);
					}
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		};
	}
	
	public MaskFormatter createFormatter(String s){
		MaskFormatter formatter = null;
		try{
			formatter = new MaskFormatter(s);
		}
		catch(ParseException e){
			System.err.println("Problema ao formatar a String para 4 numeros");
		}
		return formatter;
	}
	/**
	 * Create the frame.
	 */

	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nadson&Carol\\Documents\\GitHub\\FinalProjectOOP\\Controlador De Voo\\iconPlane.png"));
		setPreferredSize(new Dimension(1000, 1000));
		setTitle("Visualisador do Controlador de Tr\u00E1fegos A\u00E9reos - Air Traffic Controller Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 680);
		
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		JMenu arquivoMenu = new JMenu("Arquivo");
		barraMenu.add(arquivoMenu);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mntmNovo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0));
		arquivoMenu.add(mntmNovo);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Abrir..");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0));
		arquivoMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Salvar");
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0));
		arquivoMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sobre...");
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0));
		arquivoMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		arquivoMenu.add(mntmSair);
		
		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelPrincipal);
		painelPrincipal.setLayout(null);
		
		JPanel painelRelogio = new JPanel();

		painelRelogio.setBorder(new TitledBorder(null, "Relogio em Tempo Real", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(59, 59, 59)));
		painelRelogio.setBounds(871, 6, 178, 57);
		painelPrincipal.add(painelRelogio);
		painelRelogio.setLayout(null);
		
		rtRelogio = new JLabel("00:00:00");
		rtRelogio.setBounds(58, 24, 55, 16);
		painelRelogio.add(rtRelogio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(539, 99, 510, 350);
		painelPrincipal.add(scrollPane);
		
		tabelaDecolagens = new JTable(tmd);
		
		scrollPane.setViewportView(tabelaDecolagens);
		
		JLabel lblPousos = new JLabel("Pousos");
		lblPousos.setBounds(17, 65, 104, 20);
		painelPrincipal.add(lblPousos);
		lblPousos.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(17, 99, 510, 350);
		painelPrincipal.add(scrollPane_1);
		
		tabelaPousos = new JTable(tmp);
		scrollPane_1.setViewportView(tabelaPousos);
		
		JLabel lblDecolagens = new JLabel("Decolagens");
		lblDecolagens.setBounds(539, 65, 104, 20);
		painelPrincipal.add(lblDecolagens);
		lblDecolagens.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		
		JLabel nomeCCV = new JLabel("CCV - Central de Controle de V\u00F4os");
		nomeCCV.setFont(new Font("Lucida Sans", Font.PLAIN, 22));
		nomeCCV.setBounds(17, 17, 382, 36);
		painelPrincipal.add(nomeCCV);
		
		JLabel lblNewLabel = new JLabel("Numero do Voo");
		lblNewLabel.setBounds(55, 478, 90, 16);
		painelPrincipal.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Empresa");
		lblNewLabel_1.setBounds(55, 506, 90, 16);
		painelPrincipal.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Origem");
		lblNewLabel_2.setBounds(55, 534, 90, 16);
		painelPrincipal.add(lblNewLabel_2);
		
		JComboBox ccbEmpresaP = new JComboBox();
		ccbEmpresaP.setModel(new DefaultComboBoxModel(new String[] {"", "TAM", "GOL", "Avianca", "AZUL", "TAP", "Passaredo", "Delta", "Swiss AirLines", "Air France", "British Airways", "Korean Air", "Lan", "United Airlines"}));
		ccbEmpresaP.setBounds(165, 501, 98, 26);
		painelPrincipal.add(ccbEmpresaP);
		
		JComboBox ccbOrigemP = new JComboBox();
		ccbOrigemP.setModel(new DefaultComboBoxModel(new String[] {"", "Aracaju", "Bel\u00E9m", "Belo Horizonte", "Boa Vista", "Bras\u00EDlia", "Cuiab\u00E1", "Campo Grande", "Curitiba", "Florian\u00F3polis", "Goi\u00E2nia", "Jo\u00E3o Pessoa", "Macap\u00E1", "Macei\u00F3", "Manaus", "Natal", "Palmas", "Porto Alegre", "Porto Velho", "Recife", "Rio branco", "Rio de Janeiro", "Salvador", "S\u00E3o Lu\u00EDs", "S\u00E3o Paulo", "Teresina", "Vit\u00F3ria"}));
		ccbOrigemP.setBounds(165, 529, 98, 26);
		painelPrincipal.add(ccbOrigemP);
		
		JFormattedTextField fmtNumVooPouso = new JFormattedTextField( createFormatter("####"));
		fmtNumVooPouso.setFocusLostBehavior(JFormattedTextField.PERSIST);
		fmtNumVooPouso.setColumns(4);
		fmtNumVooPouso.setBounds(165, 475, 98, 22);
		painelPrincipal.add(fmtNumVooPouso);
		
		JLabel lblNewLabel_3 = new JLabel("Destino");
		lblNewLabel_3.setBounds(55, 562, 90, 16);
		painelPrincipal.add(lblNewLabel_3);
		
		JComboBox ccbDestinoP = new JComboBox();
		ccbDestinoP.setModel(new DefaultComboBoxModel(new String[] {"Fortaleza"}));
		ccbDestinoP.setBounds(165, 557, 98, 26);
		painelPrincipal.add(ccbDestinoP);
		
		JCheckBox poucoCombustivelP = new JCheckBox("Baixo N\u00EDvel de Combust\u00EDvel?");
		poucoCombustivelP.setBounds(292, 477, 178, 18);
		painelPrincipal.add(poucoCombustivelP);
		
		JCheckBox problemaMecanicoP = new JCheckBox("Problemas Mec\u00E2nicos?");
		problemaMecanicoP.setBounds(292, 505, 178, 18);
		painelPrincipal.add(problemaMecanicoP);
		
		JButton btnAddPouso = new JButton("Adicionar Pouso");
		btnAddPouso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String 	id,
				empresa,
				origem,
				destino,
				probGas,
				probMec;
				id = fmtNumVooPouso.getText();
				empresa = (String)ccbEmpresaP.getSelectedItem();
				origem = (String)ccbOrigemP.getSelectedItem();
				destino = (String)ccbDestinoP.getSelectedItem();
				if (!poucoCombustivelP.isSelected())
					probGas = "nao";	
				else
					probGas = "sim";
				
				if (!problemaMecanicoP.isSelected())
					probMec = "nao";	
				else
					probMec = "sim";
		

				Aviao aviao = new Aviao(id, empresa, origem, destino, probGas, probMec);
				//Object[] objs = { aviao.getId(), aviao.getEmpresa(), aviao.getOrigem(), aviao.getDestino(), aviao.getTempo(), aviao.isProblemaGasol(), aviao.isProblemaMec()};
				//ATCViewer.modeloDePousos.addRow(objs);
				tmp.addPouso(aviao);
				if (poucoCombustivelP.isSelected() || problemaMecanicoP.isSelected()){
				}
			}
		});
		btnAddPouso.setBounds(292, 534, 178, 45);
		painelPrincipal.add(btnAddPouso);
		
		JLabel label = new JLabel("Numero do Voo");
		label.setBounds(598, 481, 90, 16);
		painelPrincipal.add(label);
		
		JLabel label_1 = new JLabel("Empresa");
		label_1.setBounds(598, 509, 90, 16);
		painelPrincipal.add(label_1);
		
		JLabel label_2 = new JLabel("Origem");
		label_2.setBounds(598, 537, 90, 16);
		painelPrincipal.add(label_2);
		
		JLabel label_3 = new JLabel("Destino");
		label_3.setBounds(598, 565, 90, 16);
		painelPrincipal.add(label_3);
		
		JComboBox ccbEmpresaD = new JComboBox();
		ccbEmpresaD.setModel(new DefaultComboBoxModel(new String[] {"", "TAM", "GOL", "Avianca", "AZUL", "TAP", "Passaredo", "Delta", "Swiss AirLines", "Air France", "British Airways", "Korean Air", "Lan", "United Airlines"}));
		ccbEmpresaD.setBounds(708, 504, 98, 26);
		painelPrincipal.add(ccbEmpresaD);
		
		JComboBox ccbOrigemD = new JComboBox();
		ccbOrigemD.setModel(new DefaultComboBoxModel(new String[] {"Fortaleza"}));
		ccbOrigemD.setBounds(708, 532, 98, 26);
		painelPrincipal.add(ccbOrigemD);
		
		JFormattedTextField fmtNemVooD = new JFormattedTextField(createFormatter("####"));
		fmtNemVooD.setFocusLostBehavior(JFormattedTextField.PERSIST);
		fmtNemVooD.setColumns(4);
		fmtNemVooD.setBounds(708, 478, 98, 22);
		painelPrincipal.add(fmtNemVooD);
		
		JComboBox ccbDestinoD = new JComboBox();
		ccbDestinoD.setModel(new DefaultComboBoxModel(new String[] {"", "Aracaju", "Bel\u00E9m", "Belo Horizonte", "Boa Vista", "Bras\u00EDlia", "Cuiab\u00E1", "Campo Grande", "Curitiba", "Florian\u00F3polis", "Goi\u00E2nia", "Jo\u00E3o Pessoa", "Macap\u00E1", "Macei\u00F3", "Manaus", "Natal", "Palmas", "Porto Alegre", "Porto Velho", "Recife", "Rio branco", "Rio de Janeiro", "Salvador", "S\u00E3o Lu\u00EDs", "S\u00E3o Paulo", "Teresina", "Vit\u00F3ria"}));
		ccbDestinoD.setBounds(708, 560, 98, 26);
		painelPrincipal.add(ccbDestinoD);
		
		JCheckBox poucoCombustivelD = new JCheckBox("Baixo N\u00EDvel de Combust\u00EDvel?");
		poucoCombustivelD.setBounds(835, 480, 178, 18);
		painelPrincipal.add(poucoCombustivelD);
		
		JCheckBox problemaMecanicoD = new JCheckBox("Problemas Mec\u00E2nicos?");
		problemaMecanicoD.setBounds(835, 508, 178, 18);
		painelPrincipal.add(problemaMecanicoD);
		
		JButton btnAddDecolagem = new JButton("Adicionar Decolagem");
		btnAddDecolagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String 	id,
				empresa,
				origem,
				destino,
				problema;
				id = fmtNemVooD.getText();
				empresa = (String)ccbEmpresaD.getSelectedItem();
				origem = (String)ccbOrigemD.getSelectedItem();
				destino = (String)ccbDestinoD.getSelectedItem();
				problema = "nao";
		
				Aviao aviao = new Aviao(id, empresa, origem, destino, problema, problema);
		
				tmd.addDecolagem(aviao);
			}
		});
		btnAddDecolagem.setBounds(835, 537, 178, 45);
		painelPrincipal.add(btnAddDecolagem);
		
		JButton removePouso = new JButton("Autorizar Pouso");
		removePouso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tabelaPousos.getSelectedRow();
				if (tmp.getPousos(linhaSelecionada).isProblemaGasol() == "sim" || 
						tmp.getPousos(linhaSelecionada).isProblemaMec() == "sim" ||
						linhaSelecionada == 0){
					tmd.addDecolagem(tmp.getPousos(linhaSelecionada));
					tmp.removeAviaoPouso(linhaSelecionada);
					numbPousosConsecutivos++;
				}
			}
		});
		removePouso.setBounds(380, 65, 147, 28);
		painelPrincipal.add(removePouso);
		
		JButton removeDecolagem = new JButton("Autorizar Decolagem");
		removeDecolagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tabelaDecolagens.getSelectedRow();
				boolean temProblema = tmd.getDecolagem(linhaSelecionada).isProblemaGasol() == "sim" || 
						tmd.getDecolagem(linhaSelecionada).isProblemaMec() == "sim";
				JOptionPane.showMessageDialog(new JFrame(),
					    temProblema);
				
				if (!temProblema && numbPousosConsecutivos>=2){
					tmd.removeDecolagem(linhaSelecionada);
					numbPousosConsecutivos = 0;
				}
			}
		});
		removeDecolagem.setBounds(901, 65, 147, 28);
		painelPrincipal.add(removeDecolagem);
		/*tabelaPousos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});*/	
	}
	public ATCViewer() {
		initComponents();
		relogio();
//		pousos();
		
	}
}
