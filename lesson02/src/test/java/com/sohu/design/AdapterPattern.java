package com.sohu.design;

import java.util.Objects;

interface MediaPlayer {
    public void play(String audioType, String fileName);
}

interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}

class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("playVlc: "+fileName);
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("no to do");
    }
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("no to do");
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("playMp4: "+fileName);
    }
}

/**
 * 适配器模式:让MediaPlayer接口的实现类AudioPlayer本来只能播放mp3的通过适配器要能去播放vlc和mp4
 */
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if(Objects.equals(audioType, "vlc")) {
            this.advancedMediaPlayer = new VlcPlayer();
        }
        if(Objects.equals(audioType, "mp4")) {
            this.advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(Objects.equals(audioType, "vlc")) {
            this.advancedMediaPlayer.playVlc(fileName);
        }
        if(Objects.equals(audioType, "mp4")) {
            this.advancedMediaPlayer.playMp4(fileName);
        }
    }
}

class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if(Objects.equals(audioType, "mp3")) {
            System.out.println("playmp3: "+fileName);
        }

        if(Objects.equals(audioType, "vlc")) {
            new MediaAdapter(audioType).play(audioType, fileName);
        }
        if(Objects.equals(audioType, "mp4")) {
            new MediaAdapter(audioType).play(audioType, fileName);
        }
    }
}

/**
 * 适配器模式
 */
public class AdapterPattern {
    public static void main(String[] args) {
        new AudioPlayer().play("mp3", "start mp3 end");
        new AudioPlayer().play("vlc", "start vlc end");
        new AudioPlayer().play("mp4", "start mp4 end");
    }
}
