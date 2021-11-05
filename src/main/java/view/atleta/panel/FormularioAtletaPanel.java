package view.atleta.panel;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.ModelFactory;
import model.atleta.AtletaDto;
import model.competicion.CompeticionDto;
import model.inscripcion.InscripcionDto;
import util.Validate;
import util.exceptions.AtletaNoValidoException;
import util.exceptions.ModelException;
import view.atleta.dialog.JustificanteDialog;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

public class FormularioAtletaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel panelFormulario;
	private JButton btnValidarEInscribirse;
	private JTextField textNombre;
	private JLabel lblNombre;
	private JPanel panelValidarBtn;
	
	private AtletaDto atleta;
	private CompeticionDto competicion;
	private JLabel lblFechaNacimiento;
	private JTextField textFechaNacimiento;
	private JLabel lblSexo;
	private ButtonGroup sexo = new ButtonGroup();
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;

	private boolean isSelectedTarjeta;
	
	/**
	 * Create the panel.
	 */
	public FormularioAtletaPanel(InscribirsePanel incripcionesPanel, CompeticionDto competicion, 
			boolean isSelectedTarjeta) {
		this.competicion = competicion;
		
		this.isSelectedTarjeta = isSelectedTarjeta;
		
		setLayout(new BorderLayout(0, 0));
		setSize(new Dimension(465, 350));
		add(getPanelFormulario(), BorderLayout.CENTER);
		add(getPanelValidarBtn(), BorderLayout.SOUTH);
	}
	
	public void setCompeticionDto(CompeticionDto competicion) {
		this.competicion = competicion;
	}
	
	private void showError(String arg) {
		JOptionPane.showMessageDialog(this,
				arg,
			    "ERROR - " + arg,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private void showJustificante(InscripcionDto inscripcion) {
		JustificanteDialog justificanteDialog = new JustificanteDialog(inscripcion, isSelectedTarjeta);
		justificanteDialog.setLocationRelativeTo(null);
		justificanteDialog.setModal(true);
		justificanteDialog.setVisible(true);
	}
	
	private JPanel getPanelFormulario() {
		if (panelFormulario == null) {
			panelFormulario = new JPanel();
			panelFormulario.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Formulario de Inscripción a la Carrera", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			panelFormulario.setLayout(new MigLayout("", "[grow,center][grow,center]", "[grow][][][][][][][grow]"));
			panelFormulario.add(getLblNombre(), "cell 0 1 2 1,alignx left,growy");
			panelFormulario.add(getTextNombre(), "cell 0 2 2 1,grow");
			panelFormulario.add(getLblFechaNacimiento(), "cell 0 3 2 1,alignx left,aligny center");
			panelFormulario.add(getTextFechaNacimiento(), "cell 0 4 2 1,grow");
			panelFormulario.add(getLblSexo(), "cell 0 5 2 1,alignx center,aligny top");
			panelFormulario.add(getRdbtnHombre(), "cell 0 6,alignx center,aligny bottom");
			panelFormulario.add(getRdbtnMujer(), "cell 1 6,alignx center,aligny bottom");
		}
		return panelFormulario;
	}
	
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNombre.setDisplayedMnemonic('N');
			lblNombre.setToolTipText("Inserte su nombre (no apellidos)");
			lblNombre.setLabelFor(getTextNombre());
		}
		return lblNombre;
	}
	
	private JTextField getTextNombre() {
		if (textNombre == null) {
			textNombre = new JTextField();
			textNombre.setColumns(10);
		}
		return textNombre;
	}
	
	private JLabel getLblFechaNacimiento() {
		if (lblFechaNacimiento == null) {
			lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
			lblFechaNacimiento.setToolTipText("Introduzca su fecha de nacimiento en el formato: DD/MM/AAAA");
			lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblFechaNacimiento.setDisplayedMnemonic('N');
		}
		return lblFechaNacimiento;
	}
	
	private JTextField getTextFechaNacimiento() {
		if (textFechaNacimiento == null) {
			textFechaNacimiento = new JTextField();
			textFechaNacimiento.setColumns(10);
		}
		return textFechaNacimiento;
	}
	
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
			lblSexo.setToolTipText("");
			lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSexo.setDisplayedMnemonic('N');
		}
		return lblSexo;
	}
	
	private JRadioButton getRdbtnHombre() {
		if (rdbtnHombre == null) {
			rdbtnHombre = new JRadioButton("Hombre");
			sexo.add(rdbtnHombre);
		}
		return rdbtnHombre;
	}
	
	private JRadioButton getRdbtnMujer() {
		if (rdbtnMujer == null) {
			rdbtnMujer = new JRadioButton("Mujer");
			sexo.add(rdbtnMujer);
		}
		return rdbtnMujer;
	}
	
	private JPanel getPanelValidarBtn() {
		if (panelValidarBtn == null) {
			panelValidarBtn = new JPanel();
			panelValidarBtn.add(getBtnValidarEInscribirse());
		}
		return panelValidarBtn;
	}
	
	private JButton getBtnValidarEInscribirse() {
		if (btnValidarEInscribirse == null) {
			btnValidarEInscribirse = new JButton("Validar e Inscribirse");
			getRootPane().setDefaultButton(btnValidarEInscribirse);
			
			btnValidarEInscribirse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					atleta = new AtletaDto();
					
					// Validamos el nombre
					String nombre = getTextNombre().getText();
					if(!nombre.trim().isEmpty())
						atleta.nombre = nombre;
					else {
						showError("Nombre no válido");
						return;
					} // Show warning
					
					// Validamos la fechaDeNacimiento
					String fechaNacimiento = getTextFechaNacimiento().getText();
					atleta.fechaNacimiento = Validate.validateFecha(fechaNacimiento);
					if(atleta.fechaNacimiento == null) {
						showError("Fecha no válida");
						return;
					} // Show warning
					
					// Validamos el sexo
					if (getRdbtnHombre().isSelected())
						atleta.sexo = "H";
					else if (getRdbtnMujer().isSelected())
						atleta.sexo = "M";
					else {
						showError("Sexo no seleccionado");
						return;
					}
					
					InscripcionDto inscripcion = null;
					try {
						inscripcion = ModelFactory.forAtletaCrudService().registerAtletaToCompeticion(atleta, competicion);
					} catch (AtletaNoValidoException anve) { // manejamos correctamente las excepciones
						showError(anve.getMessage());
						// TODO close
						return;
					} catch (ModelException me) {
						me.printStackTrace();
						showError("Lo siento, algo ha salido mal...");
						// TODO close
						return;
					}
					
					// TODO close
					showJustificante(inscripcion);
				}
			});
		}
		return btnValidarEInscribirse;
	}

}