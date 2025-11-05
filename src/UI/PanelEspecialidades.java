package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import servicios.ServicioEspecialidad;
import servicios.UtilidadesForm;

public class PanelEspecialidades extends JPanel {
    
    private AdminView adminView;
    private JTable tablaEspecialidades;
    private DefaultTableModel modeloTabla;
    private ServicioEspecialidad servicioEspecialidad;
    private FormEspecialidades formEspecialidades;
    private Boton botonCrear, botonCancelar;

    public PanelEspecialidades(AdminView adminView) throws Exception, IOException {
        this.adminView = adminView;
        this.servicioEspecialidad = new ServicioEspecialidad();

        setPreferredSize(new Dimension(700,400));
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        var formulario = crearFormulario();
        var botones = crearBotones();
        var tabla = crearTabla();

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(formulario, gbc);

        gbc.gridy = 1;
        formPanel.add(botones, gbc);

        add(formPanel, BorderLayout.WEST);

        add(tabla, BorderLayout.CENTER);

        actualizarTabla();
        modoCrear();
    }

    public JPanel crearFormulario() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcForm = new GridBagConstraints();
        gbcForm.gridwidth = 1;
        gbcForm.gridheight = 1;
        gbcForm.weightx = 1;
        gbcForm.weighty = 1;
        formPanel.setBackground(Color.GRAY);
        
        formEspecialidades = new FormEspecialidades("Gestión de Especialidades");
        formPanel.add(formEspecialidades);
        
        return formPanel;
    };

    public JPanel crearBotones() {
        JPanel botonesPanel = new JPanel();

        botonCrear = new Boton("Crear Especialidad");
        botonCancelar = new Boton("Cancelar");

        botonesPanel.add(botonCrear);
        botonesPanel.add(botonCancelar);

        // --- EVENTOS BOTONES ---
        botonCrear.addActionListener (e->{
            try {
                formEspecialidades.guardarEspecialidad();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            actualizarTabla();
        });

        botonCancelar.addActionListener(e->{
            UtilidadesForm.limpiarCampos(formEspecialidades);
            modoCrear();
        });     
          
        return botonesPanel;
    }

    public JPanel crearTabla() {
        JPanel tablaPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcTabla = new GridBagConstraints();
        gbcTabla.gridx = 0;
        gbcTabla.gridy = 0;
        gbcTabla.gridwidth = 1;
        gbcTabla.gridheight = 1;
        gbcTabla.weightx = 1;
        gbcTabla.weighty = 1;
        gbcTabla.fill = GridBagConstraints.BOTH;

        tablaPanel.setBackground(Color.GRAY);
        modeloTabla = new DefaultTableModel(new Object[]{"Especialidades", "ID", "Estado", "Descripción"}, 0);
        
        tablaEspecialidades = new JTable(modeloTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollTabla = new JScrollPane(tablaEspecialidades);

        JPopupMenu menu = new JPopupMenu();
        JMenuItem itemActualizar = new JMenuItem("Actualizar");
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        menu.add(itemActualizar);
        menu.add(itemEliminar);


        tablaEspecialidades.setComponentPopupMenu(menu);
        
        tablaPanel.add(scrollTabla, gbcTabla);

        // --- EVENTOS TABLA ---
        tablaEspecialidades.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int fila = tablaEspecialidades.getSelectedRow();
                    if (fila != -1) {
                        modoEdicion();
                        var nombre = tablaEspecialidades.getValueAt(fila, 0).toString();
                        try{
                        var datos = servicioEspecialidad.cargarEspecialidad(nombre);            
                        formEspecialidades.cargarEspecialidad(datos);
                        }catch(Exception ex){
                            ex.printStackTrace();
                            }
                        }
                    }
                }
            
        });

        itemActualizar.addActionListener(e->{
            var datos = formEspecialidades.cargarDatos();
            try {
                servicioEspecialidad.actualizarEspecialidad(datos);
                actualizarTabla();
                modoCrear();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
                UtilidadesForm.limpiarCampos(formEspecialidades);
        });
        

        itemEliminar.addActionListener(a->{
            var datos = formEspecialidades.cargarDatos();
            try {
                servicioEspecialidad.eliminarEspecialidad(datos);
                actualizarTabla();
                modoCrear();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
                UtilidadesForm.limpiarCampos(formEspecialidades);

        });

        return tablaPanel;

    };

    public void actualizarTabla() {
       try{ 
        var especialidades = servicioEspecialidad.getEspecialidades();
        if (especialidades == null) {
            return;
            }
        modeloTabla.setRowCount(0);
        modeloTabla = (DefaultTableModel) tablaEspecialidades.getModel();
        for (var especialidad : especialidades) {
            modeloTabla.addRow(new Object[]{especialidad.getNombreEspecialidad(), especialidad.getIdentificadorFormated(), especialidad.getEstadoFormated(), especialidad.getDescripcion()});
            }
        }catch(Exception e){
            e.printStackTrace();
            }
    };

    public void modoCrear() {
        botonCrear.setVisible(true);
        botonCancelar.setVisible(false);
    }
    
    public void modoEdicion() {
        botonCrear.setVisible(false);
        botonCancelar.setVisible(true);
    }    

}
