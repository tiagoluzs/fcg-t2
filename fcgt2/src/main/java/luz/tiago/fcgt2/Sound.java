/*
 * Copyright (C) 2021 tiagoluz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package luz.tiago.fcgt2;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

/**
 *
 * @author tiagoluz
 */
public class Sound {

    public void playFile(File file) {
        Thread t = new Thread(() -> {
            try {
                Sequencer sequencer = MidiSystem.getSequencer(); // Get the default Sequencer
                if (sequencer == null) {
                    System.err.println("Sequencer device not supported");
                    return;
                }
                sequencer.open(); // Open device
                Sequence sequence = MidiSystem.getSequence(file);
                sequencer.setSequence(sequence); // load it into sequencer
                sequencer.start();  // start the playback

            } catch (MidiUnavailableException | InvalidMidiDataException | IOException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        });
        t.start();
    }

    Sequencer rockSequencer;

    public void rock() {
        File file = new File("assets/entersandman.mid");
        Thread t = new Thread(() -> {
            try {
                rockSequencer = MidiSystem.getSequencer(); // Get the default Sequencer
                if (rockSequencer == null) {
                    System.err.println("Sequencer device not supported");
                    return;
                }
                rockSequencer.open(); // Open device
                Sequence sequence = MidiSystem.getSequence(file);
                rockSequencer.setSequence(sequence); // load it into sequencer
                rockSequencer.start();  // start the playback
                rockSequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            } catch (MidiUnavailableException | InvalidMidiDataException | IOException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        });
        t.start();
    }

    public void rockStop() {
        if (this.rockSequencer != null) {
            this.rockSequencer.stop();
        }
    }

}
