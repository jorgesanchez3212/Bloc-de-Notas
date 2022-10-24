import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class BlocNotas extends  JFrame{
BorderLayout layout = new BorderLayout();
JPanel panel = new JPanel();
JMenuBar menu = new JMenuBar();
JMenu archivo = new JMenu();
JMenu edicion=new JMenu();

JMenu ver = new JMenu();
JMenu ayuda = new JMenu();
JTextArea area = new JTextArea();
JScrollPane scroll = new JScrollPane();
JFileChooser chooser = new JFileChooser();
JMenuItem nuevo = new JMenuItem();
JMenuItem abrir = new JMenuItem();
JMenuItem guardar = new JMenuItem();
JMenuItem guardarComo = new JMenuItem();
JMenuItem imprimir = new JMenuItem();
JMenuItem deshacer = new JMenuItem();
JMenuItem copiar = new JMenuItem();
JMenuItem cortar = new JMenuItem();
JMenuItem pegar = new JMenuItem();
JMenuItem eliminar = new JMenuItem();

JMenuItem barraEstado = new JMenuItem();
JMenuItem acercaDe = new JMenuItem();
JMenuItem verAyuda = new JMenuItem();


public BlocNotas(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel.setLayout(layout);
    archivo.setText("archivo");
    edicion.setText("edicion");
    ver.setText("ver");
    ayuda.setText("ayuda");
    nuevo.setText("nuevo");
    abrir.setText("abrir");

    guardar.setText("guardar");

    guardarComo.setText("guardarComo");

    imprimir.setText("imprimir");

    deshacer.setText("deshacer");
    copiar.setText("copiar");

    cortar.setText("cortar");

    pegar.setText("pegar");

    eliminar.setText("eliminar");

    barraEstado.setText("barraEstado");
    acercaDe.setText("acercaDe");
    verAyuda.setText("verAyuda");


    barraEstado.setSelected(true);

    menu.add(archivo);
    menu.add(edicion);
    menu.add(ver);
    menu.add(ayuda);

    archivo.add(nuevo);
    archivo.add(abrir);
    archivo.add(guardar);
    archivo.add(guardarComo);
    archivo.add(imprimir);

    edicion.add(deshacer);
    edicion.add(copiar);
    edicion.add(cortar);
    edicion.add(pegar);
    edicion.add(eliminar);

    ver.add(barraEstado);

    ayuda.add(acercaDe);
    ayuda.add(verAyuda);


    this.setJMenuBar(menu);

    this.getContentPane().add(panel,BorderLayout.CENTER);
    this.getContentPane().setPreferredSize(new Dimension(500,400));
    this.getContentPane().add(scroll,BorderLayout.CENTER);
    scroll.getViewport().add(area,null);

    nuevo.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            area.setText("");
        }
    });

    abrir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==abrir){
                int archivoAbrir = chooser.showOpenDialog(BlocNotas.this);
                if (archivoAbrir==chooser.APPROVE_OPTION){
                    File archivo = chooser.getSelectedFile();
                    FileInputStream miArchivo;
                    int  sizeArchivo = Long.valueOf(archivo.length()).intValue();
                    byte archivoLeer[] = new byte[sizeArchivo];
                    try {
                        miArchivo = new FileInputStream(archivo.getPath());
                        miArchivo.read(archivoLeer);
                        area.append(new String(archivoLeer));
                    } catch (IOException  ex) {
                        System.out.println("Error");
                    }
                    area.setCaretPosition(area.getDocument().getLength());

                }
            }
        }
    });
    guardar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            File archivo = chooser.getSelectedFile();
            FileOutputStream miArchivo;
            String contenidoTexto = new String();

            try {
                miArchivo = new FileOutputStream(archivo.getPath());

                contenidoTexto = (area.getText());
                miArchivo.write(contenidoTexto.getBytes());
                miArchivo.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    });

    guardarComo
            .addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == guardarComo) {
                int archivoAbrir = chooser.showOpenDialog(BlocNotas.this);
                if (archivoAbrir == chooser.APPROVE_OPTION) {
                    File archivo = chooser.getSelectedFile();
                    FileOutputStream miArchivo;
                    String contenido = new String();
                    try {
                        miArchivo = new FileOutputStream(archivo.getPath());
                        contenido = new String(area.getText());
                        miArchivo.write(contenido.getBytes());
                        miArchivo.close();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    });

    copiar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==copiar){
                area.copy();
            }
        }
    } );

    pegar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==pegar){
                area.paste();
            }
        }
    } );

    eliminar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==eliminar){
                area.setText(area.getText().replace(area.getSelectedText(),""));;
            }
        }
    } );

    cortar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==cortar){
                area.cut();
            }
        }
    } );

    acercaDe.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String text = "Este proyecto esta hecho por Jorge";
          JOptionPane.showMessageDialog(new JFrame(),text,"Acerca de Jorge"+"Editor",JOptionPane.INFORMATION_MESSAGE);
        }
    });
    verAyuda.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        URI uri = new URI("https://www.google.com/search?q=ayuda+java+swing&rlz=1C1CHBF_esES1028ES1028&sxsrf=ALiCzsbmVBMUrndRPnrdmabo7qCkwzcgUQ%3A1666547461498&ei=BX9VY_-AHoGwabXumdAJ&ved=0ahUKEwi_l8aL9fb6AhUBWBoKHTV3BpoQ4dUDCA8&uact=5&oq=ayuda+java+swing&gs_lcp=Cgdnd3Mtd2l6EAMyBQghEKABMgUIIRCgAToKCAAQRxDWBBCwAzoECAAQQzoICAAQgAQQsQM6BQgAEIAEOgsIABCABBCxAxDJAzoKCAAQgAQQhwIQFDoGCAAQFhAeOggIABAWEB4QDzoKCAAQFhAeEA8QCkoECEEYAEoECEYYAFDiFljLLWDkMWgBcAF4AIABsQGIAaoKkgEEMC4xMZgBAKABAcgBCMABAQ&sclient=gws-wiz");
                        desktop.browse(uri);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
        }

    });
    barraEstado.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int linea = 1;
            int columna = 1;

            try {
                int caretpos = area.getCaretPosition();
                linea= area.getLineOfOffset(caretpos);
                columna = caretpos - area.getLineStartOffset(linea);

                // Ya que las líneas las cuenta desde la 0
                linea += 1;
            } catch(Exception ex) { }
            JOptionPane.showMessageDialog(new JFrame(),"Linea ="+linea +", Columna = "+columna ,"Barra de Estado",JOptionPane.INFORMATION_MESSAGE);
        }

    });



super.pack();
super.show();

}

}
