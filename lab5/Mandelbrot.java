package com.example.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Mandelbrot {
    private final int threads;
    private final int tasks;
    private final BufferedImage bufferedImage;

    public Mandelbrot(int threads, int tasks) {
        this.threads = threads;
        this.tasks = tasks;
        this.bufferedImage = new BufferedImage(Config.getWidth(), Config.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void run(){
        ExecutorService threadPool = Executors.newFixedThreadPool(threads);
        List<Future<Integer>> computations = new ArrayList<>();

        //Task per pixel
        if (tasks == Config.getHeight() * Config.getWidth()) {
            for (int x = 0; x < Config.getWidth(); x++) {
                for (int y = 0; y < Config.getHeight(); y++) {
                    ColorSeter colorSeter= new ColorSeter(x, x, y, y, bufferedImage);
                    Future<Integer> computation = threadPool.submit(colorSeter);
                    computations.add(computation);
                }
            }
        }
        else {
            int step = Config.getWidth() / tasks;
            int x1 = 0,x2;

            //task per strip
            for (int i = 0; i < tasks - 1; i++) {
                x2 = x1 + step - 1;
                ColorSeter colorSeter = new ColorSeter(x1, x2, 0, Config.getHeight() - 1,bufferedImage);
                Future<Integer> computation = threadPool.submit(colorSeter);
                computations.add(computation);
                x1 += step;
            }
            ColorSeter colorSeter = new ColorSeter(x1, Config.getWidth() - 1, 0, Config.getHeight() - 1, bufferedImage);
            Future<Integer> computation = threadPool.submit(colorSeter);
            computations.add(computation);
        }
        for (Future<Integer> computation : computations) {
            try {
                computation.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        threadPool.shutdown();
    }

    public void showImage()
    {
        JFrame editorFrame = new JFrame();
        editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon(this.bufferedImage);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

        editorFrame.pack();
        editorFrame.setLocationRelativeTo(null);
        editorFrame.setVisible(true);
    }
}