package com.mathetrainer.audio;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

/**
 * Text-to-Speech engine wrapper with performance optimizations.
 * Reuses synthesizer instance to avoid repeated initialization.
 */
public class SpeechEngine {
    
    private static Synthesizer synthesizer;
    private static boolean initialized = false;
    private static final Object LOCK = new Object();
    
    static {
        initializeSynthesizer();
    }
    
    private static void initializeSynthesizer() {
        try {
            // Try to initialize Java Speech API
            SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "General", null, null, null);
            synthesizer = Central.createSynthesizer(desc);
            if (synthesizer != null) {
                synthesizer.allocate();
                synthesizer.resume();
                initialized = true;
            }
        } catch (Exception e) {
            System.err.println("Speech synthesis not available: " + e.getMessage());
            initialized = false;
        }
    }
    
    /**
     * Speak text asynchronously
     * @param text Text to speak
     */
    public static void speak(String text) {
        if (!initialized || synthesizer == null) {
            return;
        }
        
        try {
            synchronized (LOCK) {
                synthesizer.speakPlainText(text, null);
                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            }
        } catch (Exception e) {
            System.err.println("Speech error: " + e.getMessage());
        }
    }
    
    /**
     * Shutdown the synthesizer
     */
    public static void shutdown() {
        if (synthesizer != null) {
            try {
                synthesizer.deallocate();
            } catch (Exception e) {
                System.err.println("Error shutting down synthesizer: " + e.getMessage());
            }
        }
    }
}
