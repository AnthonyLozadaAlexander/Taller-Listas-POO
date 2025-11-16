
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Usuario
 */


public class frmPrincipal extends javax.swing.JFrame {

    ArrayList<Persona> lista = new ArrayList<>(); // arreglo de listas para la creacion de objetos dinamicos
    String regex = "\\d+";
    private DefaultTableModel modeloE;
    private DefaultTableModel modeloD;
    
    /**
     * Creates new form frmPrincipal
     */
    public frmPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false); 
        
        String[] estudiante = {"Indice","Nombre, ","Edad","Nota 1", "Nota 2", "Promedio"};
        modeloE = new DefaultTableModel(estudiante, 0);
        TablaEstudiantes.setModel(modeloE);
        
        String[] docente = {"Indice","Nombre", "Edad", "Sueldo", "Sueldo", "HorasExtras", "ValorHorasExtras", "TotalSueldo"};
        modeloD = new DefaultTableModel(docente, 0);
        TablaDocentes.setModel(modeloD);
        
    }
    
    public boolean isEmpty(String txt){
        if(!txt.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Error: No Pueden Haber Campos Vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
    
    public boolean isNumber(String txt){
        if(!txt.trim().matches(regex)){
            JOptionPane.showMessageDialog(this, "Error: Debe Ingresar Un Numero Entero", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
    
    private void ActualizarTablas(){
        modeloE.setRowCount(0);
        modeloD.setRowCount(0);
    }
    
    private void ActualizarEtiqueta(){
        if(cboTipo.getSelectedItem().equals("Estudiante")){
            jCampo1.setText("Nota [1]:");
            jCampo2.setText("Nota [2]:");
            jCampo3.setText("Promedio: ");
            txtCampo3.setEditable(false);
        }
        
        if(cboTipo.getSelectedItem().equals("Docente")){
            jCampo1.setText("Sueldo: ");
            jCampo2.setText("Horas Extras: ");
            jCampo3.setText("Valor Horas Extras: ");
            txtCampo3.setEditable(true);
        }
    }
    
    private void agregarElemento(){
        if(isNumber(txtEdad.getText())){
            return;
        }
        
        String nombre = txtNombre.getText();
        int edad = Integer.parseInt(txtEdad.getText());
            
        if(cboTipo.getSelectedItem().equals("Estudiante")){
            jTipo.setText("Estudiante");
            double n1 = Double.parseDouble(txtCampo1.getText());
            double n2 = Double.parseDouble(txtCampo2.getText());
            txtCampo3.setText((n1 + n2/2) + "");
            lista.add(new Estudiante(n1, n2, nombre, edad));
        }
        else{
            jTipo.setText("Docente");
            double sueldo = Double.parseDouble(txtCampo1.getText());
            int horas = Integer.parseInt(txtCampo2.getText());
            double valor = Double.parseDouble(txtCampo3.getText());
            lista.add(new Docente(sueldo, horas, valor, nombre, edad));
        }
        
        txtHistorial.setText("");
        for(Persona p: lista){
            txtHistorial.append(p.mostrarInfo());
        }
        
        txtNombre.setText("");
        txtEdad.setText("");
        txtCampo1.setText("");
        txtCampo2.setText("");
        txtCampo3.setText("");
        
        txtNombre.requestFocus();
    }
    
    private void modificarElemento(){      
        int index = 0;
        String nombreBuscado = txtNombre.getText();
        String nuevoNombre = JOptionPane.showInputDialog("Ingrese Nombre A Modificar: ");
        
        if(nombreBuscado.trim().isEmpty() || nombreBuscado == null){
            JOptionPane.showMessageDialog(this, "Error: Debe Un Nombre a Buscar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(nuevoNombre.isEmpty() || nuevoNombre == null){
            JOptionPane.showMessageDialog(this, "Error: Debe Ingresar Un Nuevo Nombre",  "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
         
        boolean modificado = false;
        
        // Modificar en la lista
        for (int i = 0; i < lista.size(); i++) {
            index = i;
            Persona p = lista.get(i);
            if(p.nombre.equalsIgnoreCase(nombreBuscado)){
                p.nombre = nuevoNombre;
                modificado = true;
            }    
        }
        
        if(modificado){
            txtHistorial.append("Nombre[" + index+"]\n");
            txtHistorial.append("Nombre Viejo: " + nombreBuscado + "\n");
            txtHistorial.append("Nombre Modificado: " + nuevoNombre + "\n");
        }
        else{
            JOptionPane.showMessageDialog(this, "Error: No Se Encontro Registro Del Nombre", "Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void BuscarElemento(){
        String nombreBuscado = txtNombre.getText();
        boolean encontrado = false;
        
        for (int i = 0; i < lista.size(); i++) {
            Persona p =lista.get(i); // el objeto p creado toma la informacion del indice para los atributos del objeto en la lista
            if(p.nombre.equalsIgnoreCase(nombreBuscado)){
                encontrado = true;
                txtHistorial.setText("Registro["+i+"] Encontrado: \n");
                txtHistorial.append(p.mostrarInfo() + "\n");
                txtEdad.setText(String.valueOf(p.edad));   
            }
            
        }
    }
    
    private void EliminarElemento(){
        String nombreBuscado = txtNombre.getText();
        boolean encontrado = false;
        
        for (int i = 0; i < lista.size(); i++) {
            Persona p = lista.get(i);
            if(p.nombre.equalsIgnoreCase(nombreBuscado)){
                encontrado = true;
                lista.remove(p);
                txtHistorial.append("Registro["+i+"] Eliminado: \n");
                txtHistorial.append(p.mostrarInfo() + "\n");
            }
            
        }
        
        if(!encontrado){
            JOptionPane.showMessageDialog(this, "Error: No Se Encontro En El Registro: " + nombreBuscado, "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTipo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHistorial = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        jCampo1 = new javax.swing.JLabel();
        txtCampo1 = new javax.swing.JTextField();
        jCampo2 = new javax.swing.JLabel();
        txtCampo2 = new javax.swing.JTextField();
        jCampo3 = new javax.swing.JLabel();
        txtCampo3 = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jTipo1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDocentes = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaEstudiantes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTipo.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jTipo.setText("Informacion");
        jPanel1.add(jTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        txtHistorial.setColumns(20);
        txtHistorial.setRows(5);
        jScrollPane1.setViewportView(txtHistorial);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 260, 450));

        jPanel2.setBackground(new java.awt.Color(248, 255, 225));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("CaskaydiaMono NF SemiBold", 0, 14)); // NOI18N
        jLabel2.setText("Edad:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 90, -1));

        jLabel1.setFont(new java.awt.Font("CaskaydiaMono NF SemiBold", 0, 14)); // NOI18N
        jLabel1.setText("Nombre:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
        jPanel2.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 90, -1));

        jLabel4.setFont(new java.awt.Font("CaskaydiaMono NF SemiBold", 0, 14)); // NOI18N
        jLabel4.setText("Tipo:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estudiante", "Docente" }));
        cboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoActionPerformed(evt);
            }
        });
        jPanel2.add(cboTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, 30));

        jCampo1.setFont(new java.awt.Font("CaskaydiaMono NF SemiBold", 0, 14)); // NOI18N
        jCampo1.setText("Campo 1:");
        jPanel2.add(jCampo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));
        jPanel2.add(txtCampo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 120, -1));

        jCampo2.setFont(new java.awt.Font("CaskaydiaMono NF SemiBold", 0, 14)); // NOI18N
        jCampo2.setText("Campo 2:");
        jPanel2.add(jCampo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, -1));
        jPanel2.add(txtCampo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 120, -1));

        jCampo3.setFont(new java.awt.Font("CaskaydiaMono NF SemiBold", 0, 14)); // NOI18N
        jCampo3.setText("Campo 3:");
        jPanel2.add(jCampo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, -1, -1));
        jPanel2.add(txtCampo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 110, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 90, -1));

        jTipo1.setFont(new java.awt.Font("CaskaydiaMono NF SemiBold", 0, 14)); // NOI18N
        jTipo1.setText("Informacion");
        jPanel2.add(jTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 229, 204));
        jPanel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel3.setText("Formulario Listas");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 80));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 90, -1));

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        jPanel2.add(btnRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 90, -1));

        btnBuscar.setText("Buscar");
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 90, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 530));

        TablaDocentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TablaDocentes);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, -1, 230));

        TablaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(TablaEstudiantes);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, 230));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoActionPerformed
        ActualizarEtiqueta();
    }//GEN-LAST:event_cboTipoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarElemento();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        modificarElemento();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        EliminarElemento();
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaDocentes;
    private javax.swing.JTable TablaEstudiantes;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JLabel jCampo1;
    private javax.swing.JLabel jCampo2;
    private javax.swing.JLabel jCampo3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jTipo;
    private javax.swing.JLabel jTipo1;
    private javax.swing.JTextField txtCampo1;
    private javax.swing.JTextField txtCampo2;
    private javax.swing.JTextField txtCampo3;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextArea txtHistorial;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
