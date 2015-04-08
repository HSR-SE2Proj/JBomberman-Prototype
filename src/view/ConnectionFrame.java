package view;

import domain.client.Keyboard;
import java.awt.BorderLayout;
import domain.client.GameClient;
import utils.Timer;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JButton;

import action.ActionType;
import utils.ActionSerializer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import network.NetworkFacade;
import network.client.ClientNetwork;

import action.Action;

public class ConnectionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldhostname;
	private NetworkFacade network = new ClientNetwork();
	private int id;

	public ConnectionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textFieldhostname = new JTextField();
		contentPane.add(textFieldhostname);
		textFieldhostname.setColumns(20);
		
		JButton btnConnect = new JButton("connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				network.connect(textFieldhostname.getText());
				if(network.isOpen()) {
					System.out.println("[*] Connected to RabbitMQ Broker");
					network.sendMessage(ActionSerializer.serializeAction(new Action(ActionType.HELLO, new Object[]{"Spieler1"})));
					/*Action action = ActionSerializer.deserializeAction(network.receiveMessage());
					if(action.getActionType() == ActionType.HELLO) {
						id = (Integer) action.getPropertie(0);
						System.out.println("[*] My ID is: " + id);
						foo(id);
					}*/
					foo(0);
				}
			}
		});
		contentPane.add(btnConnect);
		
		pack();
		setResizable(false);
	}
	
	private void foo(int id) {
		System.out.println("[*] Waiting for Game to start");
		Action action = ActionSerializer.deserializeAction(network.receiveMessage());
		if(action.getActionType() == ActionType.StartGame) {
			System.out.println("[*] Starting Game");
			GameClient game = new GameClient(network);
			Keyboard keyboard = new Keyboard(id, network);
			GameFrame gf = new GameFrame(game, keyboard);
			Timer timer = new Timer(1000/60, game);
			Thread timerThread = new Thread(timer);
			timerThread.start();
			gf.setVisible(true);
			this.dispose();
		}
	}

}
