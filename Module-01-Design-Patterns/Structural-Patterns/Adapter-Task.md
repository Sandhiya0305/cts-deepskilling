# Task Description

Implement the Adapter pattern. Create a MediaPlayer interface with play(String filename) method. Create an AdvancedMediaPlayer interface with playVlc(String filename) and playMp4(String filename). Create concrete implementations: VlcPlayer and Mp4Player. Create a MediaAdapter that implements MediaPlayer and internally uses AdvancedMediaPlayer to play the file. Create an AudioPlayer that uses MediaAdapter when it encounters advanced formats. Show how the adapter bridges two incompatible interfaces without modifying either.
