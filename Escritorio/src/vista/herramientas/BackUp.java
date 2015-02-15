/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.herramientas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import vista.interfazPrincipal.InterfazPrincipal;

public class BackUp extends javax.swing.JPanel {

    private static final JFileChooser fc = new JFileChooser();
    private static boolean isConnected = false;
    private static Connection conn = null;
    private static final String host = "localhost",
            port = "3306",
            user = "root",
            pass = "KmzvC6SjNmHNJPbUl2FP",
            dbName = "stf",
            dumpExePath = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump.exe";
    private static final int BUFFER = 10485760;

    public static boolean connect() {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BackUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String getData(String host, String port, String user, String password, String db) {
        StringBuffer content;
        Process run;
        try {
            run = Runtime.getRuntime().exec(
                    dumpExePath
                    + " --host=" + host
                    + " --port=" + port
                    + " --user=" + user
                    + " --password=" + password
                    + " --skip-comments "
                    + " --add-drop-database "
                    + " --databases "
                    + db
            );
            try (InputStream in = run.getInputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                content = new StringBuffer();
                int count;
                char[] cbuf = new char[BUFFER];
                while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
                    content.append(cbuf, 0, count);
                }
                return content.toString();
            }
        } catch (IOException ex) {
            Logger.getLogger(BackUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void writeStringToFile(File file, String data) {
        FileWriter fichero = null;
        PrintWriter pw;
        try {
            fichero = new FileWriter(file);
            pw = new PrintWriter(fichero);
            pw.println(data);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void backUp(javax.swing.JFrame parent) {
        if (connect()) {
            try {
                if (fc.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fc.getSelectedFile().getAbsolutePath()+"__"+new Date().getTime() + ".sql");
                    String data;
                    data = getData(host, port, user, pass, dbName);
                    writeStringToFile(file, data);
                }
                JOptionPane.showMessageDialog(parent, "Back Up exitoso", "Back Up", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Back Up fallido", "Back Up", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
