package Recognizer;

import Capture.Capture;
import Util.ConectaBanco;
import com.mysql.cj.x.protobuf.MysqlxExpr.Array;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2BGR555;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Rect;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.awt.Point;
import org.bytedeco.opencv.opencv_core.AbstractScalar;

/**
 *
 * @author Kenny
 */
public class Recognizer extends javax.swing.JFrame {
    
    int contador = 0;

    private Recognizer.DaemonThread myThread = null;

    //JavaCV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C:\\Users\\eddu_\\OneDrive\\Imagens\\recognition\\haarcascade_frontalface_alt.xml");
    FaceRecognizer recognizer = LBPHFaceRecognizer.create();
    //FaceRecognizer recognizer = opencv_face.LBPHFaceRecognizer.create();

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();

    //Vars
    String root, firstNamePerson, lastNamePerson, officerPerson, dobPerson;
    int idPerson;

    //Utils
    ConectaBanco conecta = new ConectaBanco();

    public Recognizer() {
        initComponents();
        
        recognizer.read("C:\\Users\\eddu_\\OneDrive\\Imagens\\recognition\\classifierLBPH.yml"); //classifierLBPH é um algoritmo de reconhecimento facial usado para reconhecer o rosto de uma pessoa. (realiza o mapeamento da matriz da imagem por meio da frequência - luz)
                                                                                   //O próximo passo é criar um histograma, que é um conceito de estatística que contará quantas vezes cada cor aparece em cada quadrado. Esta é a representação do histograma.
                                                                                   //Por exemplo, se o valor 110 aparecer 50 vezes uma barra como essa será criada com esse tamanho igual a 50, se 201 aparecer 110 vezes e a outra barra será criada nesse histograma
                                                                                   //com esse tamanho igual a 100. Com base na comparação dos histogramas, o algoritmo será capaz de identificar as bordas e também os cantos das imagens. Por exemplo, neste primeiro
                                                                                   //quadrado aqui, não temos informações sobre o rosto da pessoa. Então o histograma será diferente desse outro quadrado que tem a borda da face. Em suma, o algoritmo sabe quais histogramas
                                                                                   //representam bordas e quais histogramas representam as principais características da pessoa, como a cor dos olhos, o formato da boca e assim por diante.
        recognizer.setThreshold(80);
        
        startCamera();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label_photo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        label_name = new javax.swing.JLabel();
        labelOffice = new javax.swing.JLabel();
        objecName = new javax.swing.JLabel();
        objectTest = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Security System - Recognizer Person");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_photo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(200, 200, 200))); // NOI18N
        jPanel1.add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 330, 440));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_name.setBackground(new java.awt.Color(72, 120, 200));
        label_name.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        label_name.setForeground(new java.awt.Color(255, 255, 255));
        label_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_name.setText("First - Lastname");
        label_name.setOpaque(true);
        jPanel2.add(label_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 40));

        labelOffice.setBackground(new java.awt.Color(72, 120, 200));
        labelOffice.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelOffice.setForeground(new java.awt.Color(255, 255, 255));
        labelOffice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelOffice.setText("Info");
        labelOffice.setAutoscrolls(true);
        labelOffice.setOpaque(true);
        jPanel2.add(labelOffice, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 630, 110));

        objecName.setText("Object");
        jPanel2.add(objecName, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, -1, -1));
        objecName.getAccessibleContext().setAccessibleParent(jPanel2);

        objectTest.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                objectTestComponentShown(evt);
            }
        });
        jPanel2.add(objectTest, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 650, 170));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 660, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 670));

        setSize(new java.awt.Dimension(675, 672));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        stopCamera();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void objectTestComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_objectTestComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_objectTestComponentShown

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recognizer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelOffice;
    private javax.swing.JLabel label_name;
    private javax.swing.JLabel label_photo;
    private javax.swing.JLabel objecName;
    private javax.swing.JLabel objectTest;
    // End of variables declaration//GEN-END:variables

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = label_photo.getGraphics();

                            Mat imageGray = new Mat();
                            cvtColor(cameraImage, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFace = new RectVector();
                            cascade.detectMultiScale(imageGray, detectedFace, 1.1, 2, 0, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFace.size(); i++) {
                                Rect dadosFace = detectedFace.get(i);
                                //rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                Mat faceCapturada = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));

                                IntPointer rotulo = new IntPointer(1);
                                DoublePointer confidence = new DoublePointer(1);
                                recognizer.predict(faceCapturada, rotulo, confidence);
                                int prediction = rotulo.get(0);
                                String name = null;

                                if (prediction == -1) {
                                    // Pessoa desconhecida
                                    label_name.setText("Desconhecido");
                                    labelOffice.setText("");
                                    idPerson = 0;
                                } else {
                                    // Pessoa reconhecida
                                    System.out.println(confidence.get(0));
                                    idPerson = prediction;
                                    rec();

                                    // Desenhar forma com base na pessoa reconhecida
                                    String personName = label_name.getText();
                                    String objectNameT = objectTest.getText();
                                    if (objectNameT.equals("Retangle")) {
                                        // Criar retângulo verde
                                        rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                        
                                    } else if (objectNameT.equals("Circle")) {
                                        // Criar círculo azul
                                        int centerX = dadosFace.x() + dadosFace.width() / 2;
                                        int centerY = dadosFace.y() + dadosFace.height() / 2;
                                        int radius = dadosFace.width() / 2;
                                        org.bytedeco.opencv.global.opencv_imgproc.circle(
                                                cameraImage,
                                                new org.bytedeco.opencv.opencv_core.Point(centerX, centerY),
                                                radius,
                                                new Scalar(255, 0, 0, 3),
                                                3,
                                                0,
                                                0
                                        );
                                        
                                    }
                                     else if (objectNameT.equals("Line")) {
                                        // Criar linha vermelha
                                        org.bytedeco.opencv.global.opencv_imgproc.line(
                                                cameraImage,
                                                new org.bytedeco.opencv.opencv_core.Point(dadosFace.x(), dadosFace.y()),
                                                new org.bytedeco.opencv.opencv_core.Point(dadosFace.x() + dadosFace.width(), dadosFace.y() + dadosFace.height()),
                                                new Scalar(0, 0, 255, 3),
                                                3,
                                                0,
                                                0
                                        );
                                        
                                    }
                                }
                                                                }
                            

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;

                            //try {
                                if (g.drawImage(buff, 0, 0, 360, 390, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (runnable == false) {
                                        this.wait();
                                    }
                                }
                            //} catch (Exception e) {
                            }
                        //}
                    } //catch (Exception ex) {
                        catch (IOException | InterruptedException ex) {
                    }
                }
            }
        }
    }

    private void rec() {
        //Recognizer face with database
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                conecta.conexao();
                
                try {
                    String SQL = "SELECT * FROM person WHERE id = " + String.valueOf(idPerson);
                    conecta.executeSQL(SQL);
                    
                    while (conecta.rs.next()){  
                        contador++;
                        label_name.setText(conecta.rs.getString("first_name") + " " + conecta.rs.getString("last_name"));
                        labelOffice.setText(conecta.rs.getString("office"));
                        objectTest.setText(conecta.rs.getString("object"));
                        //Welcome Voice
                        String personName = conecta.rs.getString("first_name") + " " + conecta.rs.getString("last_name");
                        System.out.println("Contador:" + contador);
                        if(contador <= 10){
                            String welcomeMessage = "Welcome, " + personName + "!";
                        
                            // Configurando a voz
                            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
                            VoiceManager voiceManager = VoiceManager.getInstance();
                            Voice voice = voiceManager.getVoice("kevin16");
                            voice.allocate();

                            // Reproduzindo a mensagem de boas-vindas
                            voice.speak(welcomeMessage);
                            
                        }
                        
                        
                        System.out.println("Person: " + conecta.rs.getString("id"));
                        
                        java.sql.Array ident = conecta.rs.getArray("first_name");
                        String[] person = (String[]) ident.getArray();
                        
                        for(int i = 0; i < person.length; i++){
                            System.out.println(person[i]);
                        }
                        
                        }
                } catch (Exception e) {
                    
                }
                conecta.desconectar();
                return null;
            }
        };
        worker.execute();
    }
    
    public void stopCamera() {
        myThread.runnable = false;
        webSource.release();
        dispose();
    }

    public void startCamera() {
        webSource = new VideoCapture(0);
        myThread = new Recognizer.DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }
}
