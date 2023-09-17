package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

  Clip clip;
  URL[] soundURL = new URL[30];

  public Sound() {
    soundURL[0] = getClass().getResource("/sound/game_music.wav");
    soundURL[1] = getClass().getResource("/sound/key_sound.wav");
    soundURL[2] = getClass().getResource("/boots_sound.wav");
    soundURL[3] = getClass().getResource("/sound/door_sound.wav");
    soundURL[4] = getClass().getResource("/sound/chest_sound.wav");

  }

  public void setFile(int i) {

    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public void play() {
    clip.start();
  }

  public void loop() {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public void stop() {
    clip.stop();
  }
}
