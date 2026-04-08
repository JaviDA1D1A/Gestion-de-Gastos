package GestionDeGastos;

import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JMenuItem;

public class InterfazGrafica {

	private JFrame frame;
	private JTextField textField_Id;
	private JTextField textFieldMotivo;
	private JTextField textFieldCantidad;
	private JTextField textFieldFecha;
	private JTextField textFieldTipo;
	private JTextField textFieldPaginas;
	private JLabel lblTotalPaginas = new JLabel("texto inicial");

	private final String URL = "jdbc:mariadb://localhost:3306/gestionGastos";
	private final String USER = "programacion";
	private final String PASS = "programacion";

	private Connection connNav;
	private Statement stmtNav;
	private ResultSet rsNav;
	private int totalFilas = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazGrafica window = new InterfazGrafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazGrafica() {
		initialize();
		cargarGastos();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(frame.getContentPane(), popupMenu);
		
		JMenuItem MenuItemInsertar = new JMenuItem("Insertar");
		MenuItemInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarGasto();
			}
		});
		popupMenu.add(MenuItemInsertar);
		
		JMenuItem MenuItemUpdate = new JMenuItem("Actualizar");
		MenuItemUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarGasto();
			}
		});
		popupMenu.add(MenuItemUpdate);
		
		JMenuItem MenuItemDelete = new JMenuItem("Borrar");
		MenuItemDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarGasto();
			}
		});
		popupMenu.add(MenuItemDelete);

		textField_Id = new JTextField();
		textField_Id.setBounds(86, 27, 96, 18);
		frame.getContentPane().add(textField_Id);
		textField_Id.setColumns(10);

		JLabel lbl_Id = new JLabel("Id: ");
		lbl_Id.setBounds(25, 30, 64, 12);
		frame.getContentPane().add(lbl_Id);

		JLabel lblMotivo = new JLabel("Motivo: ");
		lblMotivo.setBounds(25, 70, 64, 12);
		frame.getContentPane().add(lblMotivo);

		textFieldMotivo = new JTextField();
		textFieldMotivo.setBounds(86, 67, 96, 18);
		frame.getContentPane().add(textFieldMotivo);
		textFieldMotivo.setColumns(10);

		JLabel lblCantidad = new JLabel("Cantidad: ");
		lblCantidad.setBounds(25, 106, 80, 12);
		frame.getContentPane().add(lblCantidad);

		textFieldCantidad = new JTextField();
		textFieldCantidad.setBounds(86, 103, 96, 18);
		frame.getContentPane().add(textFieldCantidad);
		textFieldCantidad.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setBounds(25, 145, 44, 12);
		frame.getContentPane().add(lblFecha);

		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(86, 142, 96, 18);
		frame.getContentPane().add(textFieldFecha);
		textFieldFecha.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo: ");
		lblTipo.setBounds(25, 183, 44, 12);
		frame.getContentPane().add(lblTipo);

		textFieldTipo = new JTextField();
		textFieldTipo.setBounds(86, 180, 96, 18);
		frame.getContentPane().add(textFieldTipo);
		textFieldTipo.setColumns(10);

		JButton btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				primero();
			}
		});
		btnInicio.setBounds(37, 222, 72, 20);
		frame.getContentPane().add(btnInicio);

		JButton btnSiguiente = new JButton(">");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguiente();
			}
		});
		btnSiguiente.setBounds(234, 222, 44, 20);
		frame.getContentPane().add(btnSiguiente);

		textFieldPaginas = new JTextField();
		textFieldPaginas.setBounds(171, 223, 33, 18);
		frame.getContentPane().add(textFieldPaginas);
		textFieldPaginas.setColumns(10);

		lblTotalPaginas = new JLabel("---");
		lblTotalPaginas.setBounds(214, 226, 27, 12);
		frame.getContentPane().add(lblTotalPaginas);

		JButton btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anterior();
			}
		});
		btnAnterior.setBounds(117, 222, 44, 20);
		frame.getContentPane().add(btnAnterior);

		JButton btnFin = new JButton("Fin");
		btnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ultimo();
			}
		});
		btnFin.setBounds(288, 222, 72, 20);
		frame.getContentPane().add(btnFin);

		JButton btnLimpiarCampos = new JButton("Limpiar");
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnLimpiarCampos.setBounds(288, 26, 84, 20);
		frame.getContentPane().add(btnLimpiarCampos);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPorId();
			}
		});

		btnBuscar.setBounds(192, 26, 92, 20);
		frame.getContentPane().add(btnBuscar);
		
		JButton btnGestionCRUD = new JButton("Gastos");
		btnGestionCRUD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(btnGestionCRUD, 0, btnGestionCRUD.getHeight());
			}
		});
		btnGestionCRUD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnGestionCRUD.setBounds(192, 66, 92, 20);
		frame.getContentPane().add(btnGestionCRUD);
		
		JButton btnIngresos = new JButton("Ingresos");
		btnIngresos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIngresos.setBounds(288, 66, 84, 20);
		frame.getContentPane().add(btnIngresos);
	}

	// Metodo auxilar singletone
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}

	public void cargarGastos() {
		try {
			if (connNav == null || connNav.isClosed()) {
				connNav = getConnection();
			}

			stmtNav = connNav.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rsNav = stmtNav.executeQuery("SELECT id, motivo, cantidad, fecha, tipo FROM gastos");

			if (rsNav.last()) {
				totalFilas = rsNav.getRow();
				lblTotalPaginas.setText("/ " + totalFilas);
				rsNav.first();
				mostrarGastoActual();
			} else {
				totalFilas = 0;
				lblTotalPaginas.setText("/ 0");
				textFieldPaginas.setText("0");
				limpiarCampos();
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "Error al cargar contactos: " + e.getMessage());
		}
	}

	public void buscarPorId() {
		if (textField_Id.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Por favor, introduce un ID para buscar.");
			return;
		}

		String sql = "SELECT motivo, cantidad, fecha, tipo FROM gastos WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, textField_Id.getText());

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					textFieldMotivo.setText(rs.getString("motivo"));
					textFieldCantidad.setText(rs.getString("cantidad"));
					textFieldFecha.setText(rs.getString("fecha"));
					textFieldTipo.setText(rs.getString("tipo"));

				} else {
					JOptionPane.showMessageDialog(frame,
							"No se encontró ningún gasto con el ID: " + textField_Id.getText());

					textFieldMotivo.setText("");
					textFieldCantidad.setText("");
					textFieldFecha.setText("");
					textFieldTipo.setText("");
				}
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "Error al buscar: " + ex.getMessage());
		}
	}

	private void limpiarCampos() {
		textField_Id.setText("");
		textFieldMotivo.setText("");
		textFieldCantidad.setText("");
		textFieldFecha.setText("");
		textFieldTipo.setText("");
	}

	private void mostrarGastoActual() {
		try {
			textField_Id.setText(rsNav.getString("id"));
			textFieldMotivo.setText(rsNav.getString("motivo"));
			textFieldCantidad.setText(rsNav.getString("cantidad"));
			textFieldFecha.setText(rsNav.getString("fecha"));
			textFieldTipo.setText(rsNav.getString("tipo"));

			textFieldPaginas.setText(String.valueOf(rsNav.getRow()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertarGasto() {
		String sql = "INSERT INTO gastos (motivo, cantidad, fecha, tipo) VALUES (?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, textFieldMotivo.getText());
			pstmt.setString(2, textFieldCantidad.getText());
			pstmt.setString(3, textFieldFecha.getText());
			pstmt.setString(4, textFieldTipo.getText());
			

			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(frame, "Registro insertado correctamente.");
			cargarGastos();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "Error al insertar: " + ex.getMessage());
		}
	}
	
	public void insertarIngreso() {
		String sql = "INSERT INTO ingresos (tipo, cantidad, fecha) VALUES (?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, textFieldMotivo.getText());
			pstmt.setString(2, textFieldCantidad.getText());
			pstmt.setString(3, textFieldFecha.getText());
			pstmt.setString(4, textFieldTipo.getText());
			

			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(frame, "Registro insertado correctamente.");
			cargarGastos();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "Error al insertar: " + ex.getMessage());
		}
	}

	public void eliminarGasto() {
		String sql = "DELETE FROM gastos WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, textField_Id.getText());
			int filasAfectadas = pstmt.executeUpdate();
			if (filasAfectadas > 0) {
				JOptionPane.showMessageDialog(frame, "Registro eliminado correctamente.");
				cargarGastos();
			} else {
				JOptionPane.showMessageDialog(frame, "No se encontró el id especificado.");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "Error al eliminar: " + ex.getMessage());
		}
	}

	public void actualizarGasto() {
		String sql = "UPDATE gastos SET motivo = ?, cantidad = ?, fecha = ?, tipo = ? WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, textFieldMotivo.getText());
			pstmt.setString(2, textFieldCantidad.getText());
			pstmt.setString(3, textFieldFecha.getText());
			pstmt.setString(4, textFieldTipo.getText());
			pstmt.setString(5, textField_Id.getText());

			int filasAfectadas = pstmt.executeUpdate();
			if (filasAfectadas > 0) {
				JOptionPane.showMessageDialog(frame, "Registro actualizado correctamente.");
				cargarGastos();
			} else {
				JOptionPane.showMessageDialog(frame, "No se encontró el id especificado.");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "Error al actualizar: " + ex.getMessage());
		}
	}

	public void primero() {
		try {
			if (rsNav != null && rsNav.first())
				mostrarGastoActual();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ultimo() {
		try {
			if (rsNav != null && rsNav.last())
				mostrarGastoActual();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void siguiente() {
		try {
			if (rsNav != null && !rsNav.isLast() && rsNav.next()) {
				mostrarGastoActual();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void anterior() {
		try {
			if (rsNav != null && !rsNav.isFirst() && rsNav.previous()) {
				mostrarGastoActual();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
