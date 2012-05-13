/*
 * Copyright (c) 2012 Dominik Obermaier.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package org.tomighty.plugin.statistics.gui.dialogs;

import net.miginfocom.swing.MigLayout;
import org.tomighty.plugin.TomightyLoader;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.*;

/**
 * @author dobermai
 */
public class StatisticsDialog extends JDialog {

    private JPanel contentPane;
    private JLabel successfulPomodoros;
    private JLabel unSuccessfulPomodoros;
    private final TomightyLoader tomightyLoader;

    @Inject
    public StatisticsDialog(final TomightyLoader tomightyLoader) {
        this.tomightyLoader = tomightyLoader;

        createContentPane();
        configureDialog();
    }

    @PostConstruct
    public void initialize() {
        setTitle("Today");
        pack();
        setLocationRelativeTo(null);

//        Statistics statistics = tomightyLoader.getInstance(Statistics.class);
//        List<Pomodoro> allPomodoros = statistics.getAllPomodoros();

        int successfulCounter = 0;
        int unsuccessfulCounter = 0;

//        for (Pomodoro pomodoro : allPomodoros) {
//            if (pomodoro.getStatus() == Pomodoro.PomodoroStatus.FINISHED) {
//                successfulCounter++;
//            } else if (pomodoro.getStatus() == Pomodoro.PomodoroStatus.UNFINISHED) {
//                unsuccessfulCounter++;
//            }
//        }

        successfulPomodoros.setText("" + successfulCounter);
        unSuccessfulPomodoros.setText("" + unsuccessfulCounter);
    }

    private void configureDialog() {
        setContentPane(contentPane);
        setResizable(false);
    }

    private void createContentPane() {
        contentPane = new JPanel(new MigLayout());

        final JLabel successfulPomodorosLabel = new JLabel("Successful Pomodoros:");
        successfulPomodoros = new JLabel();

        final JLabel unSuccessfulPomodorosLabel = new JLabel("Unsuccessful Pomodoros:");
        unSuccessfulPomodoros = new JLabel();

        contentPane.add(successfulPomodorosLabel);
        contentPane.add(successfulPomodoros, "wrap");
        contentPane.add(unSuccessfulPomodorosLabel);
        contentPane.add(unSuccessfulPomodoros);
    }


    public void showDialog() {
        setVisible(true);
    }

}
