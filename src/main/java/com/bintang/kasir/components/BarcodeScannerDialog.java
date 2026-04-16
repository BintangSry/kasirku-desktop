package com.bintang.kasir.components;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class BarcodeScannerDialog extends JDialog implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private String scanResult = null;

    public interface ScanCallback {
        void onScanSuccess(String result);
    }

    private ScanCallback callback;

    public BarcodeScannerDialog(Frame parent, ScanCallback callback) {
        super(parent, "Scan Barcode", true);
        this.callback = callback;
        initWebcam();
        
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(parent);
        
        // Tutup webcam saat dialog ditutup
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                webcam.close();
            }
        });
        
        executor.execute(this);
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); // Ambil kamera pertama
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
    }

    @Override
    public void run() {
        do {
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            BufferedImage image = null;
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) continue;
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                Result result = new MultiFormatReader().decode(bitmap);
                if (result != null) {
                    scanResult = result.getText();
                    callback.onScanSuccess(scanResult);
                    webcam.close();
                    dispose();
                    break;
                }
            } catch (NotFoundException e) {
                // Barcode belum terlihat
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}
