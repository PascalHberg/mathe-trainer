package com.mathetrainer.ui;

import com.mathetrainer.logic.MathLogic;
import com.mathetrainer.data.StatsManager;
import com.mathetrainer.audio.SpeechEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Main GUI for Mathe Trainer
 * Optimized with efficient layout and event handling
 */
public class MatheTrainerGUI extends JFrame implements KeyListener {
    
    private JLabel titleLabel;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton checkButton;
    private JLabel statsLabel;
    private JComboBox<Integer> maxValueCombo;
    private JComboBox<String> operatorCombo;
    
    private MathLogic.Problem currentProblem;
    private int correctCount = 0;
    private int wrongCount = 0;
    private int highscore;
    
    public MatheTrainerGUI() {
        setTitle("Mathe Trainer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        highscore = StatsManager.loadHighscore();
        
        initializeUI();
        generateNewProblem();
    }
    
    private void initializeUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Title
        titleLabel = new JLabel("Mathe Trainer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // Settings panel
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        settingsPanel.setBackground(new Color(240, 240, 240));
        
        settingsPanel.add(new JLabel("Max Value:"));
        maxValueCombo = new JComboBox<>(new Integer[]{5, 10, 20, 50, 100});
        maxValueCombo.setSelectedItem(10);
        settingsPanel.add(maxValueCombo);
        
        settingsPanel.add(new JLabel("Operator:"));
        operatorCombo = new JComboBox<>(new String[]{"+", "-", "*"});
        operatorCombo.setSelectedItem("+");
        settingsPanel.add(operatorCombo);
        
        mainPanel.add(settingsPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // Question
        questionLabel = new JLabel("0 + 0 = ?");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 48));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(questionLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        
        // Answer input
        answerField = new JTextField(10);
        answerField.setFont(new Font("Arial", Font.PLAIN, 24));
        answerField.setMaximumSize(new Dimension(200, 40));
        answerField.setAlignmentX(Component.CENTER_ALIGNMENT);
        answerField.addKeyListener(this);
        mainPanel.add(answerField);
        mainPanel.add(Box.createVerticalStrut(15));
        
        // Check button
        checkButton = new JButton("Check Answer");
        checkButton.setFont(new Font("Arial", Font.PLAIN, 18));
        checkButton.setMaximumSize(new Dimension(200, 50));
        checkButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkButton.addActionListener(e -> checkAnswer());
        mainPanel.add(checkButton);
        mainPanel.add(Box.createVerticalStrut(30));
        
        // Stats
        statsLabel = new JLabel();
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateStatsLabel();
        mainPanel.add(statsLabel);
        
        add(mainPanel);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                SpeechEngine.shutdown();
            }
        });
    }
    
    private void generateNewProblem() {
        int maxValue = (Integer) maxValueCombo.getSelectedItem();
        String operatorStr = (String) operatorCombo.getSelectedItem();
        char operator = operatorStr.charAt(0);
        
        currentProblem = MathLogic.generateProblem(maxValue, operator);
        updateQuestionDisplay();
        
        answerField.setText("");
        answerField.requestFocus();
        
        // Non-blocking speech
        new Thread(() -> SpeechEngine.speak(
            currentProblem.num1 + " " + currentProblem.operator + " " + currentProblem.num2
        )).start();
    }
    
    private void updateQuestionDisplay() {
        questionLabel.setText(
            currentProblem.num1 + " " + currentProblem.operator + " " + currentProblem.num2
        );
    }
    
    private void checkAnswer() {
        boolean isCorrect = MathLogic.checkAnswer(answerField.getText(), currentProblem.result);
        
        if (isCorrect) {
            correctCount++;
            if (correctCount > highscore) {
                highscore = correctCount;
                StatsManager.saveHighscore(highscore);
            }
            new Thread(() -> SpeechEngine.speak("Richtig")).start();
        } else {
            wrongCount++;
            new Thread(() -> SpeechEngine.speak("Falsch")).start();
        }
        
        updateStatsLabel();
        generateNewProblem();
    }
    
    private void updateStatsLabel() {
        statsLabel.setText(String.format(
            "Correct: %d | Wrong: %d | Highscore: %d",
            correctCount, wrongCount, highscore
        ));
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            checkAnswer();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
